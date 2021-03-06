// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.PromotionInstance;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PromotionInstance_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PromotionInstance.entityManager;
    
    public static final EntityManager PromotionInstance.entityManager() {
        EntityManager em = new PromotionInstance().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PromotionInstance.countPromotionInstances() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PromotionInstance o", Long.class).getSingleResult();
    }
    
    public static List<PromotionInstance> PromotionInstance.findAllPromotionInstances() {
        return entityManager().createQuery("SELECT o FROM PromotionInstance o", PromotionInstance.class).getResultList();
    }
    
    public static PromotionInstance PromotionInstance.findPromotionInstance(Long id) {
        if (id == null) return null;
        return entityManager().find(PromotionInstance.class, id);
    }
    
    public static List<PromotionInstance> PromotionInstance.findPromotionInstanceEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PromotionInstance o", PromotionInstance.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PromotionInstance.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PromotionInstance.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PromotionInstance attached = PromotionInstance.findPromotionInstance(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PromotionInstance.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PromotionInstance.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PromotionInstance PromotionInstance.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PromotionInstance merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
