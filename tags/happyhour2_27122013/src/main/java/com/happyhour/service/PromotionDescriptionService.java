package com.happyhour.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.happyhour.entity.PromotionDescription;

@RooService(domainTypes = { com.happyhour.entity.PromotionDescription.class })
public interface PromotionDescriptionService {
    public abstract long countAllPromotionDescriptions();    
    public abstract void deletePromotionDescription(PromotionDescription promotionDescription);    
    public abstract PromotionDescription findPromotionDescription(Long id);    
    public abstract List<PromotionDescription> findAllPromotionDescriptions();    
    public abstract List<PromotionDescription> findPromotionDescriptionEntries(int firstResult, int maxResults);    
    public abstract void savePromotionDescription(PromotionDescription promotionDescription);    
    public abstract PromotionDescription updatePromotionDescription(PromotionDescription promotionDescription);    
	
}
