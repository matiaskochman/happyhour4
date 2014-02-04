package com.happyhour.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionInstanceProcessed;
import com.happyhour.entity.PromotionRequest;

@Service
@Transactional
public class PromotionInstanceProcessedServiceImpl implements PromotionInstanceProcessedService {
	
	@Autowired
	PromotionRequestProcessedService promotionRequestProcessedService;
	
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

	@Override
	public void processPromotionInstance(Long id) {
		
		PromotionInstanceProcessed promotionInstanceProcessed = new PromotionInstanceProcessed();
		
		PromotionInstance promotionInstance = PromotionInstance.findPromotionInstance(id);
		
		Set<PromotionRequest> promotionRequestList = promotionInstance.getPromoRequestList();
		
		for (PromotionRequest promotionRequest : promotionRequestList) {
			promotionRequestProcessedService.processPromotionRequestNotDelivered(promotionRequest.getId());
		}
		/*
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
		*/
	}
	
}
