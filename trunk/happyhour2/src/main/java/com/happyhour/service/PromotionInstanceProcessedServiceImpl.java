package com.happyhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionDescription;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionInstanceProcessed;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;

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
		
		List<PromotionRequest> promotionRequestList = promotionInstance.getPromoRequestList();
		
		promotionRequestProcessedService.processPromotionRequestCollectionNotDelivered(promotionRequestList);
		
		List<PromotionRequestProcessed> list = promotionInstance.getPromotionRequestProcessedList();
		promotionInstanceProcessed.getPromotionRequestProcessedList().addAll(list);
		
		promotionInstanceProcessed.merge();
		
		BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(promotionInstance.getBusinessEstablishment().getId());
		promotionInstance.getPromoRequestList().removeAll(promotionRequestList);
		promotionInstance.getPromotionRequestProcessedList().removeAll(list);
		
		promotionInstance.setBusinessEstablishment(null);
		
		promotionInstance.merge();
		businessEstablishment.getPromotionInstanceList().remove(promotionInstance);
		
		businessEstablishment.merge();
		
		promotionInstanceService.deletePromotionInstance(promotionInstance);
		
	}
	
}
