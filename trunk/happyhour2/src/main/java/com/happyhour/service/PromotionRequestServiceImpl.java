package com.happyhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.Authority;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.Usuario;
import com.happyhour.exception.BusinessException;

@Service
@Transactional
public class PromotionRequestServiceImpl implements PromotionRequestService {
	
	private static String ROLE_ADMIN = "ROLE_ADMIN";
	
	
	@Autowired
	private TokenCreationServiceImpl tokenCreationServiceImpl;
	
	@Autowired
	private BusinessEstablishmentService businessEstablishmentService;
	
	@Autowired
	private PromotionDescriptionService promotionDescriptionService;
	
	@Autowired
	private PromotionInstanceService promotionInstanceService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * create a code for a specific promo, businessEstablishment
	 * @return 
	 */
    public void createToken(PromotionRequest promoRequest){
    	String token = tokenCreationServiceImpl.generateNewOrderNumber(promoRequest); 
    	promoRequest.setToken(token);
    }
	
    public void savePromotionRequest(PromotionRequest promoRequest) {
    	
    	PromotionInstance promotionInstance = null;
        
    	if(promoRequest==null){
			throw new BusinessException("the request is null");
    	}else if(promoRequest.getClientTelephone()==null){
			throw new BusinessException("client Telephone is null or empty");
    	}else if(promoRequest.getPromoId()==null){
			throw new BusinessException("promo id is null or empty");
    	}
    	
    	
    	
        if((promoRequest!=null)&&(promoRequest.getBusinessEstablishmentId()!=null)){
        	try{
        		Long id = new Long(promoRequest.getPromoId());
        		
        		
        		promotionInstance =  promotionInstanceService.findPromotionInstance(id);
        		
        		if(promotionInstance==null){
        			throw new BusinessException("the promotion id is not correct or is empty");
        		}
        		
        		if(promotionInstance.getMaxClientsAllowed().intValue() > promotionInstance.getPromoRequestList().size()){
        			promotionInstance.getPromoRequestList().add(promoRequest);
        			promotionInstanceService.savePromotionInstance(promotionInstance);
        		}else{
        			throw new BusinessException("This promotion Instance is already complete. It's not possible to complete the request");
        		}
        		
        	}catch(NumberFormatException e){
        		e.printStackTrace();
        	}
        }
    	
    }
    
    
    public long countAllPromotionRequests() {
        return PromotionRequest.countPromotionRequests();
    }
    
    public void deletePromotionRequest(PromotionRequest promotionRequest) {
        promotionRequest.remove();
    }
    
    public PromotionRequest findPromotionRequest(Long id) {
        return PromotionRequest.findPromotionRequest(id);
    }
    
    public List<PromotionRequest> findAllPromotionRequests() {
        return PromotionRequest.findAllPromotionRequests();
    }
    
    public List<PromotionRequest> findPromotionRequestEntries(int firstResult, int maxResults) {
        return PromotionRequest.findPromotionRequestEntries(firstResult, maxResults);
    }
    
    
    public PromotionRequest updatePromotionRequest(PromotionRequest promotionRequest) {
        return promotionRequest.merge();
    }

	@Override
	public List<PromotionRequest> findPromotionRequestEntriesByUser(int firstResult, int maxResults) {
		
	    String username = usuarioService.getLoggedUserName(); 
	    List<PromotionRequest> list = null;
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		Authority adminAuthority = null;
		adminAuthority =  Authority.findAuthoritysByRoleNameEquals(ROLE_ADMIN).getSingleResult();
		
		if(usuarioService.getLoggedUserAuthorities().contains(adminAuthority)){
			
			list = PromotionRequest.findPromotionRequestEntries(firstResult, maxResults);
		}else{
			list = PromotionRequest.findPromotionRequestEntriesByUser(usuario, firstResult, maxResults);
		}
	    
		return list;
		
	}

	
}
