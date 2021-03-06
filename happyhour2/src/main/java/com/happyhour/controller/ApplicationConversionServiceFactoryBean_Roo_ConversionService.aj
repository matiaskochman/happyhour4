// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.controller;

import com.happyhour.controller.ApplicationConversionServiceFactoryBean;
import com.happyhour.entity.PromotionInstanceProcessed;
import com.happyhour.service.PromotionInstanceProcessedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    PromotionInstanceProcessedService ApplicationConversionServiceFactoryBean.promotionInstanceProcessedService;
    
    public Converter<PromotionInstanceProcessed, String> ApplicationConversionServiceFactoryBean.getPromotionInstanceProcessedToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.PromotionInstanceProcessed, java.lang.String>() {
            public String convert(PromotionInstanceProcessed promotionInstanceProcessed) {
                return new StringBuilder().append(promotionInstanceProcessed.getPromotionValidDate()).append(' ').append(promotionInstanceProcessed.getMaxClientsAllowed()).toString();
            }
        };
    }
    
    public Converter<Long, PromotionInstanceProcessed> ApplicationConversionServiceFactoryBean.getIdToPromotionInstanceProcessedConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.PromotionInstanceProcessed>() {
            public com.happyhour.entity.PromotionInstanceProcessed convert(java.lang.Long id) {
                return promotionInstanceProcessedService.findPromotionInstanceProcessed(id);
            }
        };
    }
    
    public Converter<String, PromotionInstanceProcessed> ApplicationConversionServiceFactoryBean.getStringToPromotionInstanceProcessedConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.PromotionInstanceProcessed>() {
            public com.happyhour.entity.PromotionInstanceProcessed convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PromotionInstanceProcessed.class);
            }
        };
    }
    
}
