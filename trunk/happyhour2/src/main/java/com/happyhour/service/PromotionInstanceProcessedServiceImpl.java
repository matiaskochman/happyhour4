package com.happyhour.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionInstanceProcessed;
import com.happyhour.entity.PromotionRequest;

@Service
@Transactional
public class PromotionInstanceProcessedServiceImpl implements PromotionInstanceProcessedService {
	
	@Autowired
	private PromotionRequestProcessedService promotionRequestProcessedService;

	@Autowired
	private PromotionInstanceService promotionInstanceService;

	@Autowired
	private BusinessEstablishmentService businessEstablishmentService;
	
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
		
		PromotionInstance promotionInstance = promotionInstanceService.findPromotionInstance(id);
		
		PromotionInstanceProcessed promotionInstanceProcessed = new PromotionInstanceProcessed(promotionInstance.getPromotionDescription(),
				promotionInstance.getBusinessEstablishment(),promotionInstance.getPromotionValidDate(), promotionInstance.getMaxClientsAllowed());
		
		Set<PromotionRequest> promotionRequestList = promotionInstance.getPromoRequestList();
		
		promotionInstance.setPromoRequestList(null);
		
		promotionRequestProcessedService.processPromotionRequestCollectionNotDelivered(promotionRequestList);
		
		/*
		for (PromotionRequest promotionRequest : promotionRequestList) {
			promotionRequestProcessedService.processPromotionRequestNotDelivered(promotionRequest.getId());
		}
		*/
		//promotionInstanceService.deletePromotionRequestFromPromotionInstance(promotionRequest);

		promotionInstanceProcessed.getPromotionRequestProcessedList().addAll(promotionInstance.getPromotionRequestProcessedList());
		
		promotionInstanceProcessed.merge();
		
		BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(promotionInstance.getBusinessEstablishment().getId());
		
		promotionInstance.setBusinessEstablishment(null);
		promotionInstance.merge();
		businessEstablishment.getPromotionInstanceList().remove(promotionInstance.getId());
		
		businessEstablishment.merge();
		
		//promotionInstance.setPromoRequestList(null);
		//promotionInstance.setPromotionRequestProcessedList(null);
		
		promotionInstanceService.deletePromotionInstance(promotionInstance);
		
	}
	
}
