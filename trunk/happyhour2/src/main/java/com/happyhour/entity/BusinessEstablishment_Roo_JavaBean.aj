// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionDescription;
import com.happyhour.entity.PromotionInstance;
import java.util.List;
import java.util.Set;

privileged aspect BusinessEstablishment_Roo_JavaBean {
    
    public String BusinessEstablishment.getName() {
        return this.name;
    }
    
    public void BusinessEstablishment.setName(String name) {
        this.name = name;
    }
    
    public Set<PromotionInstance> BusinessEstablishment.getPromotionInstanceList() {
        return this.promotionInstanceList;
    }
    
    public void BusinessEstablishment.setPromotionInstanceList(Set<PromotionInstance> promotionInstanceList) {
        this.promotionInstanceList = promotionInstanceList;
    }
    
    public List<PromotionDescription> BusinessEstablishment.getPromotionDescriptionList() {
        return this.promotionDescriptionList;
    }
    
    public void BusinessEstablishment.setPromotionDescriptionList(List<PromotionDescription> promotionDescriptionList) {
        this.promotionDescriptionList = promotionDescriptionList;
    }
    
}
