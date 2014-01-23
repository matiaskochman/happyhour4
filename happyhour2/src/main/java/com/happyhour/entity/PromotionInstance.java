package com.happyhour.entity;
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
import javax.persistence.TypedQuery;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
	
    @ManyToOne
    private PromotionDescription promotionDescription;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PromotionRequest> promoRequestList = new HashSet<PromotionRequest>();

    @ManyToOne
    private BusinessEstablishment businessEstablishment;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date promotionValidDate;

    private Integer maxClientsAllowed;

    
    public static List<PromotionInstance> findPromotionInstanceEntriesByUser(Usuario usuario, int firstResult, int maxResults) {
        if (usuario == null || usuario.getUserName() == null || usuario.getUserName().length() == 0) {
            throw new IllegalArgumentException("The userName argument is required");
        }
        
        EntityManager em = BusinessEstablishment.entityManager();
        TypedQuery<BusinessEstablishment> q = em.createQuery("SELECT b FROM BusinessEstablishment AS b,Usuario AS u" + " WHERE u.userName = :userName and u.businessEstablishment = b", BusinessEstablishment.class).setFirstResult(firstResult).setMaxResults(maxResults);
        q.setParameter("userName", usuario.getUserName());
        
        
        BusinessEstablishment businessEstablishment = q.getSingleResult();
        
        List<PromotionInstance> list =  businessEstablishment.getPromotionInstanceList();
        
        
        return list;
    }
    
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((businessEstablishment == null) ? 0 : businessEstablishment
						.hashCode());
		result = prime
				* result
				+ ((maxClientsAllowed == null) ? 0 : maxClientsAllowed
						.hashCode());
		result = prime
				* result
				+ ((promotionDescription == null) ? 0 : promotionDescription
						.hashCode());
		result = prime
				* result
				+ ((promotionValidDate == null) ? 0 : promotionValidDate
						.hashCode());
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
		PromotionInstance other = (PromotionInstance) obj;
		if (businessEstablishment == null) {
			if (other.businessEstablishment != null)
				return false;
		} else if (!businessEstablishment.equals(other.businessEstablishment))
			return false;
		if (maxClientsAllowed == null) {
			if (other.maxClientsAllowed != null)
				return false;
		} else if (!maxClientsAllowed.equals(other.maxClientsAllowed))
			return false;
		if (promotionDescription == null) {
			if (other.promotionDescription != null)
				return false;
		} else if (!promotionDescription.equals(other.promotionDescription))
			return false;
		if (promotionValidDate == null) {
			if (other.promotionValidDate != null)
				return false;
		} else if (!promotionValidDate.equals(other.promotionValidDate))
			return false;
		return true;
	}
    
    
}
