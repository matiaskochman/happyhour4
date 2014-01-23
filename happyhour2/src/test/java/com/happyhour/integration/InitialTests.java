package com.happyhour.integration;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.Authority;
import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionDescription;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.Usuario;
import com.happyhour.service.BusinessEstablishmentService;

@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class InitialTests extends AbstractJUnit4SpringContextTests{
        
    @Autowired
    BusinessEstablishmentService businessEstablishmentService;
        
    @Test
    @Transactional
    @Rollback(false)
    public void createTests(){
        Authority a1 = new Authority();
        a1.setRoleName("ROLE_ADMIN");
        a1.persist();
        
        Authority a2 = new Authority();
        a2.setRoleName("ROLE_USER");
        a2.persist();
        
        /*
        Usuario u1 = new Usuario();
        u1.setUserName("a");
        u1.setPassword("a");
        u1.setEmail("a");
        u1.setEnabled(true);
        u1.getRolesList().add(a1);
        u1.persist();

        Usuario u2 = new Usuario();
        u2.setUserName("b");
        u2.setPassword("b");
        u2.setEmail("b");
        u2.setEnabled(true);
        u2.getRolesList().add(a2);
        u2.persist();

        
        PromotionDescription promotionDescription_a = new PromotionDescription();
        promotionDescription_a.setDescription("promo1");
        promotionDescription_a.persist();
        
        BusinessEstablishment businessEstablishment_a = new BusinessEstablishment();
        businessEstablishment_a.setName("les vendanges");
        businessEstablishment_a.getPromotionDescriptionList().add(promotionDescription_a);
        businessEstablishment_a.persist();

        PromotionDescription promotionDescription_b = new PromotionDescription();
        promotionDescription_b.setDescription("promo2");
        promotionDescription_b.persist();
        
        BusinessEstablishment b2 = new BusinessEstablishment();
        b2.setName("cafe l'industrie");
        b2.getPromotionDescriptionList().add(promotionDescription_b);
        b2.persist();
        
        Usuario user_a = Usuario.findUsuariosByUserNameEquals("a").getSingleResult();
        user_a.setBusinessEstablishment(businessEstablishment_a);
        user_a.persist();

        Usuario user_b = Usuario.findUsuariosByUserNameEquals("b").getSingleResult();
        user_b.setBusinessEstablishment(b2);
        user_b.persist();
        
        
        PromotionInstance promotionInstance_a = new PromotionInstance();
        promotionInstance_a.setBusinessEstablishment(businessEstablishment_a);
        promotionInstance_a.setPromotionDescription(promotionDescription_a);
        promotionInstance_a.setPromotionValidDate(new Date());
        //pi1.setPromotionAlreadyUtilized(false);
        promotionInstance_a.setMaxClientsAllowed(25);
        promotionInstance_a.persist();
        */
        
        createBusinessEstablishment(a1, "a", "promo_a", "business_a");
        createBusinessEstablishment(a2, "b", "promo_b", "business_b");
        createBusinessEstablishment(a1, "c", "promo_c", "business_c");
        createBusinessEstablishment(a1, "d", "promo_d", "business_d");
        createBusinessEstablishment(a1, "f", "promo_f", "business_f");
        
    }
    
    
    private void createBusinessEstablishment(Authority authority,String username,String promoDescriptionName,String businessEstablishmentName){
        Usuario u1 = new Usuario();
        u1.setUserName(username);
        u1.setPassword(username);
        u1.setEmail(username);
        u1.setEnabled(true);
        u1.getRolesList().add(authority);
        u1.persist();

        PromotionDescription promotionDescription_a = new PromotionDescription();
        promotionDescription_a.setDescription(promoDescriptionName);
        promotionDescription_a.persist();
        
        BusinessEstablishment businessEstablishment_a = new BusinessEstablishment();
        businessEstablishment_a.setName(businessEstablishmentName);
        businessEstablishment_a.getPromotionDescriptionList().add(promotionDescription_a);
        businessEstablishment_a.persist();
        
        Usuario user_a = Usuario.findUsuariosByUserNameEquals(u1.getUserName()).getSingleResult();
        user_a.setBusinessEstablishment(businessEstablishment_a);
        user_a.persist();
        
        PromotionInstance promotionInstance_a = new PromotionInstance();
        promotionInstance_a.setBusinessEstablishment(businessEstablishment_a);
        promotionInstance_a.setPromotionDescription(promotionDescription_a);
        promotionInstance_a.setPromotionValidDate(new Date());
        promotionInstance_a.setMaxClientsAllowed(25);
        promotionInstance_a.persist();
        
        BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(businessEstablishment_a.getId());
        
        businessEstablishment.getPromotionInstanceList().add(promotionInstance_a);
        businessEstablishment.persist();
    }
}