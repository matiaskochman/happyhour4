package com.happyhour.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    @PersistenceContext
    transient EntityManager entityManager;
	
	
    /**
     */
    @NotNull
    @Column(unique = true)
    private String name;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<PromotionInstance> promotionInstanceList = new ArrayList<PromotionInstance>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<PromotionDescription> promotionDescriptionList = new ArrayList<PromotionDescription>();

    public static List<BusinessEstablishment> findBusinessEstablishmentEntriesByUser(Usuario usuario, int firstResult, int maxResults) {
        if (usuario == null || usuario.getUserName() == null || usuario.getUserName().length() == 0) {
            throw new IllegalArgumentException("The userName argument is required");
        }
        EntityManager em = BusinessEstablishment.entityManager();
        TypedQuery<BusinessEstablishment> q = em.createQuery("SELECT b FROM BusinessEstablishment AS b,Usuario AS u" + " WHERE u.userName = :userName and u.businessEstablishment = b", BusinessEstablishment.class).setFirstResult(firstResult).setMaxResults(maxResults);
        q.setParameter("userName", usuario.getUserName());
        return q.getResultList();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		BusinessEstablishment other = (BusinessEstablishment) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
    
}
