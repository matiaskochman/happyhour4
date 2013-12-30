package com.happyhour.service;

import java.util.List;

import com.happyhour.entity.PromotionDescription;

public class PromotionDescriptionServiceImpl implements PromotionDescriptionService {
	
    public long countAllPromotionDescriptions() {
        return PromotionDescription.countPromotionDescriptions();
    }
    
    public void deletePromotionDescription(PromotionDescription promotionDescription) {
        promotionDescription.remove();
    }
    
    public PromotionDescription findPromotionDescription(Long id) {
        return PromotionDescription.findPromotionDescription(id);
    }
    
    public List<PromotionDescription> findAllPromotionDescriptions() {
        return PromotionDescription.findAllPromotionDescriptions();
    }
    
    public List<PromotionDescription> findPromotionDescriptionEntries(int firstResult, int maxResults) {
        return PromotionDescription.findPromotionDescriptionEntries(firstResult, maxResults);
    }
    
    public void savePromotionDescription(PromotionDescription promotionDescription) {
        promotionDescription.persist();
    }
    
    public PromotionDescription updatePromotionDescription(PromotionDescription promotionDescription) {
        return promotionDescription.merge();
    }
	
}
