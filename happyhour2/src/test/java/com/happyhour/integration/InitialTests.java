package com.happyhour.integration;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.email.NotificationService;
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

//@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-tests.xml" }) //commented for avoid problems in Amazon EC2
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

	@Autowired
	private NotificationService notificationService;	
    
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
        
        //notificationService.sendMessage("matiaskochman@yopmail.com", "the test has started.");
        
        createBusinessEstablishment(a1, "a","a@email.com" ,"promo_a", "business_a");
        createBusinessEstablishment(a2, "b","b@email.com", "promo_b", "business_b");
        createBusinessEstablishment(a2, "c","c@email.com", "promo_c", "business_c");
        createBusinessEstablishment(a2, "d","d@email.com", "promo_d", "business_d");
        createBusinessEstablishment(a2, "f","f@email.com", "promo_f", "business_f");
     
        createPromotionRequest("1", "1", "0000000001", new Date());
        createPromotionRequest("1", "1", "0000000002", new Date());
        createPromotionRequest("1", "1", "0000000003", new Date());

        createPromotionRequest("2", "2", "0000000004", new Date());
        createPromotionRequest("2", "2", "0000000005", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());
        createPromotionRequest("2", "2", "0000000006", new Date());

        createPromotionRequest("3", "3", "0000000007", new Date());
        createPromotionRequest("3", "3", "0000000008", new Date());
        createPromotionRequest("3", "3", "0000000009", new Date());

        createPromotionRequest("4", "4", "0000000010", new Date());
        createPromotionRequest("4", "4", "0000000011", new Date());
        createPromotionRequest("4", "4", "0000000012", new Date());

        createPromotionRequest("5", "5", "0000000013", new Date());
        createPromotionRequest("5", "5", "0000000014", new Date());
        createPromotionRequest("5", "5", "0000000015", new Date());
        
        
        
    }
    
    
    private void createBusinessEstablishment(Authority authority,String username,String email,String promoDescriptionName,String businessEstablishmentName){
    	
    	
        Usuario u1 = new Usuario();
        u1.setUserName(username);
        u1.setPassword(username);
        u1.setEmail(email);
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
    
    private void createPromotionRequest(String promoId, String businessEstablishmentId,String clientTelephone, Date creationTimeStamp){
    	PromotionRequest promotionRequest = new PromotionRequest(promoId,businessEstablishmentId,clientTelephone,creationTimeStamp);
    	
    	promotionRequest.setToken(tokenCreationServiceImpl.generateNewOrderNumber(promotionRequest));
    	
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