// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.BusinessEstablishment;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect BusinessEstablishment_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager BusinessEstablishment.entityManager;
    
    public static final EntityManager BusinessEstablishment.entityManager() {
        EntityManager em = new BusinessEstablishment().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long BusinessEstablishment.countBusinessEstablishments() {
        return entityManager().createQuery("SELECT COUNT(o) FROM BusinessEstablishment o", Long.class).getSingleResult();
    }
    
    public static List<BusinessEstablishment> BusinessEstablishment.findAllBusinessEstablishments() {
        return entityManager().createQuery("SELECT o FROM BusinessEstablishment o", BusinessEstablishment.class).getResultList();
    }
    
    public static BusinessEstablishment BusinessEstablishment.findBusinessEstablishment(Long id) {
        if (id == null) return null;
        return entityManager().find(BusinessEstablishment.class, id);
    }
    
    public static List<BusinessEstablishment> BusinessEstablishment.findBusinessEstablishmentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM BusinessEstablishment o", BusinessEstablishment.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void BusinessEstablishment.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void BusinessEstablishment.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            BusinessEstablishment attached = BusinessEstablishment.findBusinessEstablishment(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void BusinessEstablishment.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void BusinessEstablishment.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public BusinessEstablishment BusinessEstablishment.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        BusinessEstablishment merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
