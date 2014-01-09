package com.happyhour.entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PromotionInstanceProcessed {

    /**
     */
    @ManyToOne
    private PromotionDescription promotionDescription;

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

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PromotionRequestProcessed> promotionRequestProcessedList = new HashSet<PromotionRequestProcessed>();
}
