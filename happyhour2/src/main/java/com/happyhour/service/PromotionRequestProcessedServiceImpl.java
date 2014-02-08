package com.happyhour.service;

import java.util.ArrayList;
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
	public PromotionRequestProcessed processPromotionRequestDelivered(PromotionRequest promotionRequest) {
		
		//PromotionRequest promotionRequest = PromotionRequest.findPromotionRequest(promotionRequestId);
		PromotionRequestProcessed processed = new PromotionRequestProcessed(promotionRequest.getPromoId(),promotionRequest.getBusinessEstablishmentId()
				, promotionRequest.getClientTelephone(), promotionRequest.getToken(),promotionRequest.getCreationTimeStamp());
		
		processed.setDelivered(true);
		
		this.savePromotionRequestProcessed(processed);
		
		promotionInstanceService.savePromotionRequestProcessedToPromotionInstance(processed);
		
		promotionInstanceService.deletePromotionRequestFromPromotionInstance(promotionRequest);
		promotionRequestService.deletePromotionRequest(promotionRequest);
		
		return processed;
	}

	@Override
	public PromotionRequestProcessed processPromotionRequestNotDelivered(PromotionRequest promotionRequest) {
		
		//PromotionRequest promotionRequest = PromotionRequest.findPromotionRequest(promotionRequestId);
		
		PromotionRequestProcessed processed = new PromotionRequestProcessed(promotionRequest.getPromoId(),promotionRequest.getBusinessEstablishmentId()
				, promotionRequest.getClientTelephone(), promotionRequest.getToken(),promotionRequest.getCreationTimeStamp());
		
		processed.setDelivered(false);

		/*
		this.savePromotionRequestProcessed(processed);
		
		promotionInstanceService.savePromotionRequestProcessedToPromotionInstance(processed);
		*/
		
		return processed;
		
	}
	
	@Override
	public void processPromotionRequestCollectionNotDelivered(List<PromotionRequest> c) {
		
		List<PromotionRequestProcessed> list = new ArrayList<PromotionRequestProcessed>();
		
		for (PromotionRequest promotionRequest : c) {
			PromotionRequestProcessed promotionRequestProcessed = processPromotionRequestNotDelivered(promotionRequest);
			
			
			list.add(promotionRequestProcessed);
		}
		
		for (PromotionRequestProcessed promotionRequestProcessed : list) {
			this.savePromotionRequestProcessed(promotionRequestProcessed);
			
			promotionInstanceService.savePromotionRequestProcessedToPromotionInstance(promotionRequestProcessed);
			
		}
		
		for (PromotionRequest promotionRequest : c) {
			promotionRequest.remove();
		}
		
		
	}
	
	private PromotionRequestProcessed setAPromotionRequestProcessed(PromotionRequest promotionRequest, PromotionRequestProcessed processed) {
		
		
		processed.setBusinessEstablishmentId(promotionRequest.getBusinessEstablishmentId());
		processed.setClientTelephone(promotionRequest.getClientTelephone());
		processed.setCreationTimeStamp(promotionRequest.getCreationTimeStamp());
		processed.setPromoId(promotionRequest.getPromoId());
		processed.setToken(promotionRequest.getToken());
		
		return processed;
	}

	
}
