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
        
        BusinessEstablishment b1 = new BusinessEstablishment();
        b1.setName("les vendanges");
        //b1.setUsuario(u1);
        b1.persist();

        BusinessEstablishment b2 = new BusinessEstablishment();
        b2.setName("cafe l'industrie");
        //b1.setUsuario(u1);
        b2.persist();
        
        Usuario a = Usuario.findUsuariosByUserNameEquals("a").getSingleResult();
        a.setBusinessEstablishment(b1);
        a.persist();

        Usuario b = Usuario.findUsuariosByUserNameEquals("b").getSingleResult();
        b.setBusinessEstablishment(b2);
        b.persist();
        
        PromotionDescription p1 = new PromotionDescription();
        p1.setDescription("promo1");
        //p1.setBusinessEstablishment(b1);
        p1.persist();
        
        PromotionInstance pi1 = new PromotionInstance();
        pi1.setBusinessEstablishment(b1);
        pi1.setPromotionDescription(p1);
        pi1.setPromotionValidDate(new Date());
        //pi1.setPromotionAlreadyUtilized(false);
        pi1.setMaxClientsAllowed(5);
        pi1.persist();
    }
}