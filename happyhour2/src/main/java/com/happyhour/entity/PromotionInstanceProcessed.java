package com.happyhour.entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

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
}
