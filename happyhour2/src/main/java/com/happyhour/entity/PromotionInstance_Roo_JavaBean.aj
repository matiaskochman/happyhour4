// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionDescription;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionRequest;
import java.util.Date;
import java.util.Set;

privileged aspect PromotionInstance_Roo_JavaBean {
    
    public Long PromotionInstance.getId() {
        return this.id;
    }
    
    public void PromotionInstance.setId(Long id) {
        this.id = id;
    }
    
    public PromotionDescription PromotionInstance.getPromotionDescription() {
        return this.promotionDescription;
    }
    
    public void PromotionInstance.setPromotionDescription(PromotionDescription promotionDescription) {
        this.promotionDescription = promotionDescription;
    }
    
    public Set<PromotionRequest> PromotionInstance.getPromoRequestList() {
        return this.promoRequestList;
    }
    
    public void PromotionInstance.setPromoRequestList(Set<PromotionRequest> promoRequestList) {
        this.promoRequestList = promoRequestList;
    }
    
    public BusinessEstablishment PromotionInstance.getBusinessEstablishment() {
        return this.businessEstablishment;
    }
    
    public void PromotionInstance.setBusinessEstablishment(BusinessEstablishment businessEstablishment) {
        this.businessEstablishment = businessEstablishment;
    }
    
    public Date PromotionInstance.getPromotionValidDate() {
        return this.promotionValidDate;
    }
    
    public void PromotionInstance.setPromotionValidDate(Date promotionValidDate) {
        this.promotionValidDate = promotionValidDate;
    }
    
    public Integer PromotionInstance.getMaxClientsAllowed() {
        return this.maxClientsAllowed;
    }
    
    public void PromotionInstance.setMaxClientsAllowed(Integer maxClientsAllowed) {
        this.maxClientsAllowed = maxClientsAllowed;
    }
    
}
