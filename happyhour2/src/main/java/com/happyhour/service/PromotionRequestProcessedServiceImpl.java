package com.happyhour.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.Authority;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;
import com.happyhour.entity.Usuario;

@Service
@Transactional
public class PromotionRequestProcessedServiceImpl implements PromotionRequestProcessedService {
	
	private static String ROLE_ADMIN = "ROLE_ADMIN";
	
	
	@Autowired
	PromotionRequestService promotionRequestService;

	@Autowired
	BusinessEstablishmentService businessEstablishmentService;

	@Autowired
	PromotionInstanceService promotionInstanceService;

	@Autowired
	private UsuarioService usuarioService;
	
    public long countAllPromotionRequestProcesseds() {
        return PromotionRequestProcessed.countPromotionRequestProcesseds();
    }
    
    public void deletePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed) {
        promotionRequestProcessed.remove();
    }
    
    public PromotionRequestProcessed findPromotionRequestProcessed(Long id) {
        return PromotionRequestProcessed.findPromotionRequestProcessed(id);
    }
    
    public List<PromotionRequestProcessed> findAllPromotionRequestProcesseds() {
        return PromotionRequestProcessed.findAllPromotionRequestProcesseds();
    }
    
    public List<PromotionRequestProcessed> findPromotionRequestProcessedEntries(int firstResult, int maxResults) {
        return PromotionRequestProcessed.findPromotionRequestProcessedEntries(firstResult, maxResults);
    }
    
    public void savePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed) {
        promotionRequestProcessed.persist();
    }
    
    public PromotionRequestProcessed updatePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed) {
        return promotionRequestProcessed.merge();
    }

	@Override
	public PromotionRequestProcessed processPromotionRequestDelivered(PromotionRequest promotionRequest) {
		
		PromotionRequestProcessed processed = new PromotionRequestProcessed(promotionRequest.getPromoId(),promotionRequest.getBusinessEstablishmentId()
				, promotionRequest.getClientTelephone(), promotionRequest.getToken(),promotionRequest.getCreationTimeStamp());
		
		processed.setDelivered(true);
		
		this.savePromotionRequestProcessed(processed);
		
		promotionInstanceService.savePromotionRequestProcessedToPromotionInstance(processed);
		
		promotionInstanceService.deletePromotionRequestFromPromotionInstance(promotionRequest);
		promotionRequestService.deletePromotionRequest(promotionRequest);
		
		return processed;
	}

	/**
	 * when processing a PromotionInstance, each PromotionRequest that hasn't been claimed by a client
	 * is processed as not delivered, that is accomplished by creating a collection of PromotionRequestProcessed
	 * in a PromotionInstanceProcessed 
	 */
	@Override
	public PromotionRequestProcessed processPromotionRequestNotDelivered(PromotionRequest promotionRequest) {
		
		
		PromotionRequestProcessed processed = new PromotionRequestProcessed(promotionRequest.getPromoId(),promotionRequest.getBusinessEstablishmentId()
				, promotionRequest.getClientTelephone(), promotionRequest.getToken(),promotionRequest.getCreationTimeStamp());
		
		processed.setDelivered(false);
		
		return processed;
		
	}
	
	/**
	 * when a promotion instance is processed, the promotion requests wich haven't been processed as delivered
	 * should be processed as not delivered
	 */
	@Override
	public void processPromotionRequestCollectionNotDelivered(PromotionInstance instance) {
		
		List<PromotionRequestProcessed> list = new ArrayList<PromotionRequestProcessed>();
		
		List<PromotionRequest> c  = instance.getPromoRequestList();
		
		for (PromotionRequest promotionRequest : c) {
			PromotionRequestProcessed promotionRequestProcessed = processPromotionRequestNotDelivered(promotionRequest);
			
			list.add(promotionRequestProcessed);
		}
		

		/**
		 * removing from the PromotionInstance all the PromotionRequests 
		 * and adding the new PromotionRequestsProcessed
		 */
		instance.getPromoRequestList().removeAll(c);
		instance.getPromotionRequestProcessedList().addAll(list);
		instance.merge();
		
		/**
		 * remove all the promotionRequests from the database for the user 
		 * TODO it has to be filtered by promotionId
		 */
		List<PromotionRequest> promoRequestList = promotionRequestService.findPromotionRequestEntriesByUser(0, 10000);
		for (PromotionRequest promotionRequest : promoRequestList) {
			promotionRequest.remove();
		}
	}

	@Override
	public List<PromotionRequestProcessed> findPromotionRequestProcessedEntriesByUser(int firstResult, int maxResults) {
	    String username = usuarioService.getLoggedUserName(); 
	    List<PromotionRequestProcessed> list = null;
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		Authority adminAuthority = null;
		adminAuthority =  Authority.findAuthoritysByRoleNameEquals(ROLE_ADMIN).getSingleResult();
		
		if(usuarioService.getLoggedUserAuthorities().contains(adminAuthority)){
			
			list = PromotionRequestProcessed.findPromotionRequestProcessedEntries(firstResult, maxResults);
		}else{
			list = PromotionRequestProcessed.findPromotionRequestProcessedEntriesByUser(usuario, firstResult, maxResults);
		}
	    
		return list;

	}
	
	
}
