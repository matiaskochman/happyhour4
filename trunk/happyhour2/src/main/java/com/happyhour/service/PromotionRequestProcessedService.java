package com.happyhour.service;
import java.util.Collection;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;

@RooService(domainTypes = { com.happyhour.entity.PromotionRequestProcessed.class })
public interface PromotionRequestProcessedService {
    public abstract long countAllPromotionRequestProcesseds();    
    public abstract void deletePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed);    
    public abstract PromotionRequestProcessed findPromotionRequestProcessed(Long id);    
    public abstract List<PromotionRequestProcessed> findAllPromotionRequestProcesseds();    
    public abstract List<PromotionRequestProcessed> findPromotionRequestProcessedEntries(int firstResult, int maxResults);    
    public abstract void savePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed);    
    public abstract PromotionRequestProcessed updatePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed);
	public abstract PromotionRequestProcessed processPromotionRequestDelivered(PromotionRequest promotionRequest);
	public abstract PromotionRequestProcessed processPromotionRequestNotDelivered(PromotionRequest promotionRequest);    
	public abstract void processPromotionRequestCollectionNotDelivered(List<PromotionRequest> list);
	
}
