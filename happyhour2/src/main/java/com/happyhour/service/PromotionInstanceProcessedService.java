package com.happyhour.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.happyhour.entity.PromotionInstanceProcessed;

@RooService(domainTypes = { com.happyhour.entity.PromotionInstanceProcessed.class })
public interface PromotionInstanceProcessedService {
    public abstract long countAllPromotionInstanceProcesseds();    
    public abstract void deletePromotionInstanceProcessed(PromotionInstanceProcessed promotionInstanceProcessed);    
    public abstract PromotionInstanceProcessed findPromotionInstanceProcessed(Long id);    
    public abstract List<PromotionInstanceProcessed> findAllPromotionInstanceProcesseds();    
    public abstract List<PromotionInstanceProcessed> findPromotionInstanceProcessedEntries(int firstResult, int maxResults);    
    public abstract void savePromotionInstanceProcessed(PromotionInstanceProcessed promotionInstanceProcessed);    
    public abstract PromotionInstanceProcessed updatePromotionInstanceProcessed(PromotionInstanceProcessed promotionInstanceProcessed);
	public abstract void processPromotionInstance(Long id);
	public abstract List<PromotionInstanceProcessed> findPromotionInstanceProcessedEntriesByUser(int firstResult, int sizeNo);    
	
}
