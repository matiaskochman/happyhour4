// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.service;

import com.happyhour.entity.PromotionRequestProcessed;
import com.happyhour.service.PromotionRequestProcessedServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PromotionRequestProcessedServiceImpl_Roo_Service {
    
    declare @type: PromotionRequestProcessedServiceImpl: @Service;
    
    declare @type: PromotionRequestProcessedServiceImpl: @Transactional;
    
    public long PromotionRequestProcessedServiceImpl.countAllPromotionRequestProcesseds() {
        return PromotionRequestProcessed.countPromotionRequestProcesseds();
    }
    
    public void PromotionRequestProcessedServiceImpl.deletePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed) {
        promotionRequestProcessed.remove();
    }
    
    public PromotionRequestProcessed PromotionRequestProcessedServiceImpl.findPromotionRequestProcessed(Long id) {
        return PromotionRequestProcessed.findPromotionRequestProcessed(id);
    }
    
    public List<PromotionRequestProcessed> PromotionRequestProcessedServiceImpl.findAllPromotionRequestProcesseds() {
        return PromotionRequestProcessed.findAllPromotionRequestProcesseds();
    }
    
    public List<PromotionRequestProcessed> PromotionRequestProcessedServiceImpl.findPromotionRequestProcessedEntries(int firstResult, int maxResults) {
        return PromotionRequestProcessed.findPromotionRequestProcessedEntries(firstResult, maxResults);
    }
    
    public void PromotionRequestProcessedServiceImpl.savePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed) {
        promotionRequestProcessed.persist();
    }
    
    public PromotionRequestProcessed PromotionRequestProcessedServiceImpl.updatePromotionRequestProcessed(PromotionRequestProcessed promotionRequestProcessed) {
        return promotionRequestProcessed.merge();
    }
    
}