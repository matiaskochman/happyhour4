package com.happyhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.Authority;
import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;
import com.happyhour.entity.Usuario;

@Service
@Transactional
public class PromotionInstanceServiceImpl implements PromotionInstanceService {
	
	private static String ROLE_ADMIN = "ROLE_ADMIN";
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BusinessEstablishmentService businessEstablishmentService;
	
    public long countAllPromotionInstances() {
        return PromotionInstance.countPromotionInstances();
    }
    
    /**
     * delete a promotionInstance with its list of  promotinRequests
     */
    public void deletePromotionInstance(PromotionInstance promotionInstance) {
    	
    	List<PromotionRequest> promotionRequestList = promotionInstance.getPromoRequestList();
    	
    	for (PromotionRequest promotionRequest : promotionRequestList) {
			promotionRequest.remove();
		}
    	
        promotionInstance.remove();
    }
    
    public PromotionInstance findPromotionInstance(Long id) {
        return PromotionInstance.findPromotionInstance(id);
    }
    
    public List<PromotionInstance> findAllPromotionInstances() {
        return PromotionInstance.findAllPromotionInstances();
    }
    
    public List<PromotionInstance> findPromotionInstanceEntries(int firstResult, int maxResults) {
        return PromotionInstance.findPromotionInstanceEntries(firstResult, maxResults);
    }
    
    public void savePromotionInstance(PromotionInstance promotionInstance) {
        promotionInstance.persist();
    }
    
    public PromotionInstance updatePromotionInstance(PromotionInstance promotionInstance) {
        return promotionInstance.merge();
    }
    
    /**
     * find a list of promotionInstance by user
     */
    public List<PromotionInstance> findPromotionInstanceEntriesByUser(int firstResult, int maxResults) {
    	
    	String username = usuarioService.getLoggedUserName();
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		Authority authority = null;
		authority =  Authority.findAuthoritysByRoleNameEquals(ROLE_ADMIN).getSingleResult();

		List<PromotionInstance> list = null;
		
		if(usuarioService.getLoggedUserAuthorities().contains(authority)){
			
			list = PromotionInstance.findPromotionInstanceEntries(firstResult, maxResults);
		}else{
			
			list = PromotionInstance.findPromotionInstanceEntriesByUser(usuario, firstResult, maxResults);
		}
		
        return list;
    }

	@Override
	public void deletePromotionRequestFromPromotionInstance(PromotionRequest promotionRequest) {
		
		Long id = new Long(promotionRequest.getPromoId());
		PromotionInstance promotionInstance = PromotionInstance.findPromotionInstance(id);
		
		for (PromotionRequest pRequest : promotionInstance.getPromoRequestList()) {
			if(pRequest.equals(promotionRequest)){
				promotionInstance.getPromoRequestList().remove(pRequest);
				break;
			}
		}
		promotionInstance.merge();
		
	}

	@Override
	public List<PromotionInstance> findPromotionInstanceEntriesByBusinessEstablishment(String businessEstablishmentId) {
		Long id = null;
			
		id = new Long(businessEstablishmentId);
		
		BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(id);
		List<PromotionInstance> list= businessEstablishment.getPromotionInstanceList();
		return list;
	}

	@Override
	public void savePromotionRequestProcessedToPromotionInstance(PromotionRequestProcessed processed) {
		Long id = new Long(processed.getPromoId());
		PromotionInstance promotionInstance = PromotionInstance.findPromotionInstance(id);
		
		promotionInstance.getPromotionRequestProcessedList().add(processed);
		promotionInstance.merge();
	}

	
}
