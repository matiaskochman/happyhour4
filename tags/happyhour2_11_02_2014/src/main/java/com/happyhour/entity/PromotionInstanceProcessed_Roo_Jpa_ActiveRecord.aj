// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.PromotionInstanceProcessed;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PromotionInstanceProcessed_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PromotionInstanceProcessed.entityManager;
    
    public static final EntityManager PromotionInstanceProcessed.entityManager() {
        EntityManager em = new PromotionInstanceProcessed().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PromotionInstanceProcessed.countPromotionInstanceProcesseds() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PromotionInstanceProcessed o", Long.class).getSingleResult();
    }
    
    public static List<PromotionInstanceProcessed> PromotionInstanceProcessed.findAllPromotionInstanceProcesseds() {
        return entityManager().createQuery("SELECT o FROM PromotionInstanceProcessed o", PromotionInstanceProcessed.class).getResultList();
    }
    
    public static PromotionInstanceProcessed PromotionInstanceProcessed.findPromotionInstanceProcessed(Long id) {
        if (id == null) return null;
        return entityManager().find(PromotionInstanceProcessed.class, id);
    }
    
    public static List<PromotionInstanceProcessed> PromotionInstanceProcessed.findPromotionInstanceProcessedEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PromotionInstanceProcessed o", PromotionInstanceProcessed.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PromotionInstanceProcessed.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PromotionInstanceProcessed.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PromotionInstanceProcessed attached = PromotionInstanceProcessed.findPromotionInstanceProcessed(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PromotionInstanceProcessed.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PromotionInstanceProcessed.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PromotionInstanceProcessed PromotionInstanceProcessed.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PromotionInstanceProcessed merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
