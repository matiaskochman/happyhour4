package com.happyhour.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.Usuario;

@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-tests.xml" })
public class TokenCreationServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private TokenCreationServiceImpl tokenCreationServiceImpl;
	
    @Autowired
    private BusinessEstablishmentService businessEstablishmentService;
	
	private PromotionRequest promotionRequest;

    @Transactional
    @Before
    public void setUp() {
        Authority authority_type = new Authority();
        authority_type.setRoleName("ROLE_ADMIN");
        authority_type.persist();
        
        createBusinessEstablishment(authority_type, "user_name_1", "promo_a", "business_a");
        System.out.println("@Before - setUp");
    }	
	
	@Test
    @Transactional
    @Rollback(false)
	public void testTokenCreation(){
		
		List<PromotionRequest> promotionRequestList = new ArrayList<PromotionRequest>();
		
		List<String> tokenList = new ArrayList<String>();
		
		Integer idPromoRequest = 1;
		for (;idPromoRequest <= 10; idPromoRequest++) {
			promotionRequestList.add(new PromotionRequest("1","1","06 95 00 62 69",new Date())); 
			
		}
		
		
		for (PromotionRequest promotionRequest : promotionRequestList) {
			tokenList.add(tokenCreationServiceImpl.generateNewOrderNumber(promotionRequest));
		}
		
		Assert.assertEquals(tokenList.get(0).substring(0, 2),"A1");
		Assert.assertEquals(tokenList.get(1).substring(0, 2),"B1");
		Assert.assertEquals(tokenList.get(2).substring(0, 2),"C1");
		Assert.assertEquals(tokenList.get(3).substring(0, 2),"D1");
		Assert.assertEquals(tokenList.get(4).substring(0, 2),"A2");
		Assert.assertEquals(tokenList.get(5).substring(0, 2),"B2");
		Assert.assertEquals(tokenList.get(0).length(), 10);
		Assert.assertEquals(tokenList.get(1).length(), 10);
		Assert.assertEquals(tokenList.get(2).length(), 10);
		Assert.assertEquals(tokenList.get(3).length(), 10);
		Assert.assertEquals(tokenList.get(4).length(), 10);
		Assert.assertEquals(tokenList.get(5).length(), 10);
		
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
        promotionInstance_a.setMaxClientsAllowed(50);
        promotionInstance_a.persist();
        
        BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(businessEstablishment_a.getId());
        
        businessEstablishment.getPromotionInstanceList().add(promotionInstance_a);
        businessEstablishment.persist();
        
    }
	
}
