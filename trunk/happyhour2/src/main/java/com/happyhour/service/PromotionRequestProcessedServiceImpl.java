package com.happyhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;

@Service
@Transactional
public class PromotionRequestProcessedServiceImpl implements PromotionRequestProcessedService {
	
	@Autowired
	PromotionRequestService promotionRequestService;

	@Autowired
	BusinessEstablishmentService businessEstablishmentService;

	@Autowired
	PromotionInstanceService promotionInstanceService;
	
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
	public void processPromotionRequest(Long promotionRequestId) {
		PromotionRequestProcessed processed = new PromotionRequestProcessed();
		
		PromotionRequest promotionRequest = PromotionRequest.findPromotionRequest(promotionRequestId);
		
		processed.setBusinessEstablishmentId(promotionRequest.getBusinessEstablishmentId());
		processed.setClientTelephone(promotionRequest.getClientTelephone());
		processed.setCreationTimeStamp(promotionRequest.getCreationTimeStamp());
		processed.setPromoId(promotionRequest.getPromoId());
		processed.setToken(promotionRequest.getToken());
		
		savePromotionRequestProcessed(processed);
		
		promotionInstanceService.deletePromotionRequestFromPromotionInstance(promotionRequest);
		
		promotionRequestService.deletePromotionRequest(promotionRequest);
	}
	
}
