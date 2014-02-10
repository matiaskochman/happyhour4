package com.happyhour.integration;

import java.util.Date;
import java.util.List;

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
import com.happyhour.entity.PromotionInstanceProcessed;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;
import com.happyhour.entity.Usuario;
import com.happyhour.service.BusinessEstablishmentService;
import com.happyhour.service.PromotionDescriptionService;
import com.happyhour.service.PromotionInstanceProcessedService;
import com.happyhour.service.PromotionRequestProcessedService;
import com.happyhour.service.PromotionRequestService;
import com.happyhour.service.TokenCreationServiceImpl;
import com.happyhour.service.UsuarioService;

@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class InitialTests extends AbstractJUnit4SpringContextTests{
        
    @Autowired
    BusinessEstablishmentService businessEstablishmentService;

    @Autowired
    PromotionInstanceProcessedService promotionInstanceProcessedService;

    @Autowired
    PromotionRequestService promotionRequestService;

    @Autowired
    PromotionRequestProcessedService promotionRequestProcessedService;
    
    @Autowired
    PromotionDescriptionService promotionDescriptionService;
    
    @Autowired
    UsuarioService usuarioService;
    
	@Autowired
	private TokenCreationServiceImpl tokenCreationServiceImpl;
    
    
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
        
        
        createBusinessEstablishment(a1, "a", "promo_a", "business_a");
        createBusinessEstablishment(a2, "b", "promo_b", "business_b");
        createBusinessEstablishment(a2, "c", "promo_c", "business_c");
        createBusinessEstablishment(a2, "d", "promo_d", "business_d");
        createBusinessEstablishment(a2, "f", "promo_f", "business_f");
     
        createPromotionRequest("1", "1", "0000000001", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("1", "1", "0000000002", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("1", "1", "0000000003", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());

        createPromotionRequest("2", "2", "0000000004", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("2", "2", "0000000005", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("2", "2", "0000000006", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());

        createPromotionRequest("3", "3", "0000000007", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("3", "3", "0000000008", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("3", "3", "0000000009", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());

        createPromotionRequest("4", "4", "0000000007", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("4", "4", "0000000008", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("4", "4", "0000000009", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());

        createPromotionRequest("5", "5", "0000000007", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("5", "5", "0000000008", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        createPromotionRequest("5", "3", "0000000009", tokenCreationServiceImpl.generateNewOrderNumber(), new Date());
        
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
    
    private void createPromotionRequest(String promoId, String businessEstablishmentId,String clientTelephone, String token, Date creationTimeStamp){
    	PromotionRequest promotionRequest = new PromotionRequest(promoId,businessEstablishmentId,clientTelephone,token,creationTimeStamp);
    	
    	promotionRequestService.savePromotionRequest(promotionRequest);
    	
    }
    
    public void deleteAll(){
    	List<PromotionDescription> list = promotionDescriptionService.findAllPromotionDescriptions();
    	List<PromotionRequestProcessed> list2 = promotionRequestProcessedService.findAllPromotionRequestProcesseds();
    	List<PromotionRequest> list1 = promotionRequestService.findAllPromotionRequests();
    	List<PromotionInstanceProcessed> list3 = promotionInstanceProcessedService.findAllPromotionInstanceProcesseds();
    	
    	for (PromotionRequestProcessed promotionRequestProcessed : list2) {
    		promotionRequestProcessed.remove();
    	}
    	
    	for (PromotionInstanceProcessed promotionInstanceProcessed : list3) {
    		promotionInstanceProcessed.remove();
    	}
    	
    	for (PromotionRequest promotionRequest : list1) {
			promotionRequest.remove();
		}
    	
    	for (PromotionDescription promotionDescription : list) {
    		promotionDescription.remove();
    	}
    }
}