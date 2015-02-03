package com.happyhour.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class PromotionInstanceProcessed {

    public PromotionInstanceProcessed() {
		super();
	}

	public PromotionInstanceProcessed(PromotionDescription promotionDescription,BusinessEstablishment businessEstablishment,
    		Date promotionValidDate, Integer maxClientsAllowed) {
		super();
		this.promotionDescription = promotionDescription;
		this.businessEstablishment = businessEstablishment;
		this.promotionValidDate = promotionValidDate;
		this.maxClientsAllowed = maxClientsAllowed;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
	
	
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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date promotionValidDate;

    /**
     */
    private Integer maxClientsAllowed;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PromotionRequestProcessed> promotionRequestProcessedList = new HashSet<PromotionRequestProcessed>();

	public static List<PromotionInstanceProcessed> findPromotionInstanceProcessedEntriesByUser(Usuario usuario, int firstResult, int maxResults) {
        if (usuario == null || usuario.getUserName() == null || usuario.getUserName().length() == 0) {
            throw new IllegalArgumentException("The userName argument is required");
        }
        EntityManager em = PromotionInstanceProcessed.entityManager();
        TypedQuery<PromotionInstanceProcessed> q = em.createQuery("SELECT pip FROM PromotionInstanceProcessed AS pip" +
        		"  WHERE pip.businessEstablishment.id = :businessEstablishmentId", PromotionInstanceProcessed.class).setFirstResult(firstResult).setMaxResults(maxResults);
        q.setParameter("businessEstablishmentId", usuario.getBusinessEstablishment().getId());
        return q.getResultList();
        
	}

}
