package com.happyhour.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;

@RooService(domainTypes = { com.happyhour.entity.PromotionInstance.class })
public interface PromotionInstanceService {
    public abstract long countAllPromotionInstances();    
    public abstract void deletePromotionInstance(PromotionInstance promotionInstance);    
    public abstract PromotionInstance findPromotionInstance(Long id);    
    public abstract List<PromotionInstance> findAllPromotionInstances();    
    public abstract List<PromotionInstance> findPromotionInstanceEntries(int firstResult, int maxResults);    
    public abstract void savePromotionInstance(PromotionInstance promotionInstance);    
    public abstract PromotionInstance updatePromotionInstance(PromotionInstance promotionInstance);    
    public abstract List<PromotionInstance> findPromotionInstanceEntriesByUser(int firstResult, int maxResults);
	public abstract void deletePromotionRequestFromPromotionInstance(PromotionRequest promotionRequest);
    public abstract List<PromotionInstance> findPromotionInstanceEntriesByBusinessEstablishment(String businessEstablishmentId);
	public abstract void savePromotionRequestProcessedToPromotionInstance(PromotionRequestProcessed processed);
}
