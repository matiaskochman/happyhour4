package com.happyhour.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class BusinessEstablishment {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String name;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PromotionInstance> promotionInstanceList = new HashSet<PromotionInstance>();
}
