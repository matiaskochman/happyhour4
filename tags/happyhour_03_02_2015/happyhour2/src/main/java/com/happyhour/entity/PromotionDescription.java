package com.happyhour.entity;
import java.util.List;

import javax.persistence.EntityManager;
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
public class PromotionDescription {

    /**
     */
    @NotNull
    private String description;
    
    public static List<PromotionDescription> findPromotionDescriptionEntriesByUser(Usuario usuario, int firstResult, int maxResults) {
        if (usuario == null || usuario.getUserName() == null || usuario.getUserName().length() == 0) {
            throw new IllegalArgumentException("The userName argument is required");
        }
        
        EntityManager em = BusinessEstablishment.entityManager();
        TypedQuery<BusinessEstablishment> q = em.createQuery("SELECT b FROM BusinessEstablishment AS b,Usuario AS u" + " WHERE u.userName = :userName and u.businessEstablishment = b", BusinessEstablishment.class).setFirstResult(firstResult).setMaxResults(maxResults);
        q.setParameter("userName", usuario.getUserName());
        
        
        BusinessEstablishment businessEstablishment = q.getSingleResult();
        
        List<PromotionDescription> list =  businessEstablishment.getPromotionDescriptionList();
        
        return list;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
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
		PromotionDescription other = (PromotionDescription) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
    
    
    
}
