package com.happyhour.entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
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

    /**
     */
    @NotNull
    @Column(unique = true)
    private String name;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PromotionInstance> promotionInstanceList = new HashSet<PromotionInstance>();

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
    
}
