package com.happyhour.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.happyhour.entity.PromotionRequest;

@RooService(domainTypes = { com.happyhour.entity.PromotionRequest.class })
public interface PromotionRequestService {
	
    public abstract void createToken(PromotionRequest promoRequest);    
    public abstract long countAllPromotionRequests();    
    public abstract void deletePromotionRequest(PromotionRequest promotionRequest);    
    public abstract PromotionRequest findPromotionRequest(Long id);    
    public abstract List<PromotionRequest> findAllPromotionRequests();    
    public abstract List<PromotionRequest> findPromotionRequestEntries(int firstResult, int maxResults);    
    public abstract void savePromotionRequest(PromotionRequest promotionRequest);    
    public abstract PromotionRequest updatePromotionRequest(PromotionRequest promotionRequest);
	public abstract List<PromotionRequest> findPromotionRequestEntriesByUser(int firstResult,int maxResults);    
	
}
