package com.happyhour.entity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class PromotionInstance {

    /**
     */
    @ManyToOne
    private PromotionDescription promotionDescription;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PromotionRequest> promoRequestList = new HashSet<PromotionRequest>();

    /**
     */
    @ManyToOne
    private BusinessEstablishment businessEstablishment;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date promotionValidDate;

    /**
     */
    private Integer maxClientsAllowed;
}
