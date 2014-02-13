package com.happyhour.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
public class PromotionInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private PromotionDescription promotionDescription;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<PromotionRequest> promoRequestList = new ArrayList<PromotionRequest>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<PromotionRequestProcessed> promotionRequestProcessedList = new ArrayList<PromotionRequestProcessed>();

    @ManyToOne
    private BusinessEstablishment businessEstablishment;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date promotionValidDate;

    @ElementCollection
    private Set<String> tokenSet = new HashSet<String>();
    
    private String tokenIndex = "A";

    private Integer maxClientsAllowed;

    public static List<PromotionInstance> findPromotionInstanceEntriesByUser(Usuario usuario, int firstResult, int maxResults) {
        if (usuario == null || usuario.getUserName() == null || usuario.getUserName().length() == 0) {
            throw new IllegalArgumentException("The userName argument is required");
        }
        EntityManager em = PromotionInstance.entityManager();
        TypedQuery<PromotionInstance> q = em.createQuery("SELECT pip FROM PromotionInstance AS pip" + "  WHERE pip.businessEstablishment.id = :businessEstablishmentId", PromotionInstance.class).setFirstResult(firstResult).setMaxResults(maxResults);
        q.setParameter("businessEstablishmentId", usuario.getBusinessEstablishment().getId());
        return q.getResultList();
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
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PromotionInstance other = (PromotionInstance) obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        return true;
    }

}
