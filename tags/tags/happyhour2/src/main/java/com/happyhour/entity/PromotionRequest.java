package com.happyhour.entity;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import flexjson.JSONSerializer;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class PromotionRequest {

    @PersistenceContext
    transient EntityManager entityManager;
	
	
    public PromotionRequest() {
		super();
	}

	public PromotionRequest(String promoId, String businessEstablishmentId,String clientTelephone, Date creationTimeStamp) {
		super();
		this.promoId = promoId;
		this.businessEstablishmentId = businessEstablishmentId;
		this.clientTelephone = clientTelephone;
		this.creationTimeStamp = creationTimeStamp;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
    private String promoId;
    private String businessEstablishmentId;
    private String clientTelephone;
    private String token;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date creationTimeStamp;

    
    public static List<PromotionRequest> findPromotionRequestEntriesByUser(Usuario usuario, int firstResult, int maxResults) {
        if (usuario == null || usuario.getUserName() == null || usuario.getUserName().length() == 0) {
            throw new IllegalArgumentException("The userName argument is required");
        }
        EntityManager em = PromotionRequest.entityManager();

        TypedQuery<PromotionRequest> q = em.createQuery("SELECT pr FROM PromotionRequest AS pr WHERE pr.businessEstablishmentId = :businessEstablishmentId", PromotionRequest.class).setFirstResult(firstResult).setMaxResults(maxResults);
        q.setParameter("businessEstablishmentId", usuario.getBusinessEstablishment().getId().toString());
        return q.getResultList();
    }
    
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public static final EntityManager entityManager() {
        EntityManager em = new PromotionRequest().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionRequest other = (PromotionRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    public static String toJsonArray(Collection<PromotionRequest> collection) {
        return new JSONSerializer().exclude("*.class","entityManager").serialize(collection);
    }	
    
}
