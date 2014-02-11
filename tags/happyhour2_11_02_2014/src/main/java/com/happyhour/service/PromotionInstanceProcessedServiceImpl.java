package com.happyhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.Authority;
import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionDescription;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionInstanceProcessed;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;
import com.happyhour.entity.Usuario;

@Service
@Transactional
public class PromotionInstanceProcessedServiceImpl implements PromotionInstanceProcessedService {
	
	@Autowired
	private PromotionRequestProcessedService promotionRequestProcessedService;

	@Autowired
	private PromotionRequestService promotionRequestService;
	
	@Autowired
	private PromotionInstanceService promotionInstanceService;

	@Autowired
	private BusinessEstablishmentService businessEstablishmentService;

	@Autowired
	private UsuarioService usuarioService;
	
    public long countAllPromotionInstanceProcesseds() {
        return PromotionInstanceProcessed.countPromotionInstanceProcesseds();
    }
    
    public void deletePromotionInstanceProcessed(PromotionInstanceProcessed promotionInstanceProcessed) {
        promotionInstanceProcessed.remove();
    }
    
    public PromotionInstanceProcessed findPromotionInstanceProcessed(Long id) {
        return PromotionInstanceProcessed.findPromotionInstanceProcessed(id);
    }
    
    public List<PromotionInstanceProcessed> findAllPromotionInstanceProcesseds() {
        return PromotionInstanceProcessed.findAllPromotionInstanceProcesseds();
    }
    
    public List<PromotionInstanceProcessed> findPromotionInstanceProcessedEntries(int firstResult, int maxResults) {
        return PromotionInstanceProcessed.findPromotionInstanceProcessedEntries(firstResult, maxResults);
    }
    
    public void savePromotionInstanceProcessed(PromotionInstanceProcessed promotionInstanceProcessed) {
        promotionInstanceProcessed.persist();
    }
    
    public PromotionInstanceProcessed updatePromotionInstanceProcessed(PromotionInstanceProcessed promotionInstanceProcessed) {
        return promotionInstanceProcessed.merge();
    }

	public void processPromotionInstance(Long id) {
		
		/**
		 * loading the promotionInstance 
		 */
		PromotionInstance promotionInstance = promotionInstanceService.findPromotionInstance(id);
		
		/**
		 * creating a promotionInstanceProcessed
		 */
		PromotionInstanceProcessed promotionInstanceProcessed = new PromotionInstanceProcessed(promotionInstance.getPromotionDescription(),
				promotionInstance.getBusinessEstablishment(),promotionInstance.getPromotionValidDate(), promotionInstance.getMaxClientsAllowed());
		
		/**
		 * getting a list of promotionRequest of the promotionInstance to process , after they shoud be deleted
		 */
		//List<PromotionRequest> promotionRequestList = promotionInstance.getPromoRequestList();

		/**
		 * processing the list of promotionRequest as not delivered.
		 */
		promotionRequestProcessedService.processPromotionRequestCollectionNotDelivered(promotionInstance);
		
		/**
		 * getting the list of promotionRequestProcessed from the promotionInstance and setting it to the
		 * promotionInstanceProcessed
		 */
		List<PromotionRequestProcessed> promotionRequestProcessedList = promotionInstance.getPromotionRequestProcessedList();
		promotionInstanceProcessed.getPromotionRequestProcessedList().addAll(promotionRequestProcessedList);
		
		/**
		 * saving the promotinInstanceProcessed
		 */
		promotionInstanceProcessed.persist();
		
		BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(promotionInstance.getBusinessEstablishment().getId());
		//promotionInstance.getPromoRequestList().removeAll(promotionRequestList);
		promotionInstance.getPromotionRequestProcessedList().removeAll(promotionRequestProcessedList);
		
		businessEstablishment.getPromotionInstanceList().remove(promotionInstance);
		businessEstablishment.merge();
		
		promotionInstance.setBusinessEstablishment(null);
		promotionInstance.merge();
		
		promotionInstanceService.deletePromotionInstance(promotionInstance);
		
	}

	@Override
	public List<PromotionInstanceProcessed> findPromotionInstanceProcessedEntriesByUser(int firstResult, int maxResults) {
    	String username = usuarioService.getLoggedUserName();
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		Authority authority = null;
		authority =  Authority.findAuthoritysByRoleNameEquals("ROLE_ADMIN").getSingleResult();

		List<PromotionInstanceProcessed> list = null;
		
		if(usuarioService.getLoggedUserAuthorities().contains(authority)){
			
			list = PromotionInstanceProcessed.findPromotionInstanceProcessedEntries(firstResult, maxResults);
		}else{
			
			list = PromotionInstanceProcessed.findPromotionInstanceProcessedEntriesByUser(usuario, firstResult, maxResults);
		}
		
        return list;
	}
	
}
