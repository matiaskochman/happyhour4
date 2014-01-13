package com.happyhour.service;

import java.util.List;

import com.happyhour.entity.PromotionInstanceProcessed;

public class PromotionInstanceProcessedServiceImpl implements PromotionInstanceProcessedService {
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
	
}
