package com.happyhour.service;

import java.util.List;

import com.happyhour.entity.PromotionInstance;

public class PromotionInstanceServiceImpl implements PromotionInstanceService {
    public long countAllPromotionInstances() {
        return PromotionInstance.countPromotionInstances();
    }
    
    public void deletePromotionInstance(PromotionInstance promotionInstance) {
        promotionInstance.remove();
    }
    
    public PromotionInstance findPromotionInstance(Long id) {
        return PromotionInstance.findPromotionInstance(id);
    }
    
    public List<PromotionInstance> findAllPromotionInstances() {
        return PromotionInstance.findAllPromotionInstances();
    }
    
    public List<PromotionInstance> findPromotionInstanceEntries(int firstResult, int maxResults) {
        return PromotionInstance.findPromotionInstanceEntries(firstResult, maxResults);
    }
    
    public void savePromotionInstance(PromotionInstance promotionInstance) {
        promotionInstance.persist();
    }
    
    public PromotionInstance updatePromotionInstance(PromotionInstance promotionInstance) {
        return promotionInstance.merge();
    }
	
}
