package com.happyhour.entity;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class PromotionRequestProcessed {

	
	
    public PromotionRequestProcessed() {
		super();
	}

	public PromotionRequestProcessed(String promoId,String businessEstablishmentId, String clientTelephone, String token,Date creationTimeStamp) {
		super();
		this.promoId = promoId;
		this.businessEstablishmentId = businessEstablishmentId;
		this.clientTelephone = clientTelephone;
		this.token = token;
		this.creationTimeStamp = creationTimeStamp;
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
	
	
	private String promoId;
    private String businessEstablishmentId;
    private String clientTelephone;
    private String token;
    private Boolean delivered = false;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date creationTimeStamp;
    
    public static List<PromotionRequestProcessed> findPromotionRequestProcessedEntriesByUser(Usuario usuario, int firstResult, int maxResults) {
        if (usuario == null || usuario.getUserName() == null || usuario.getUserName().length() == 0) {
            throw new IllegalArgumentException("The userName argument is required");
        }
        EntityManager em = PromotionRequestProcessed.entityManager();

        TypedQuery<PromotionRequestProcessed> q = em.createQuery("SELECT prp FROM PromotionRequestProcessed AS prp WHERE prp.businessEstablishmentId = :businessEstablishmentId", PromotionRequestProcessed.class).setFirstResult(firstResult).setMaxResults(maxResults);
        q.setParameter("businessEstablishmentId", usuario.getBusinessEstablishment().getId().toString());
        return q.getResultList();
    }
    
}
