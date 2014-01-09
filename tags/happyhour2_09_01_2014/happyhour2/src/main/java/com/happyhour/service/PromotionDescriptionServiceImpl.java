package com.happyhour.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;

import com.happyhour.entity.Authority;
import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionDescription;
import com.happyhour.entity.Usuario;
import com.happyhour.exception.BusinessException;

public class PromotionDescriptionServiceImpl implements PromotionDescriptionService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BusinessEstablishmentService businessEstablishmentService;
	
	
    public long countAllPromotionDescriptions() {
        return PromotionDescription.countPromotionDescriptions();
    }
    
    public void deletePromotionDescription(PromotionDescription promotionDescription) {
        promotionDescription.remove();
    }
    
    public PromotionDescription findPromotionDescription(Long id) {
        return PromotionDescription.findPromotionDescription(id);
    }
    
    public List<PromotionDescription> findAllPromotionDescriptions() {
        return PromotionDescription.findAllPromotionDescriptions();
    }
    
    public List<PromotionDescription> findPromotionDescriptionEntries(int firstResult, int maxResults) {
        return PromotionDescription.findPromotionDescriptionEntries(firstResult, maxResults);
    }
    
    public List<PromotionDescription> findPromotionDescriptionEntriesByUser(int firstResult, int maxResults) {
    	
    	String username = usuarioService.getLoggedUserName();
    	List<Authority> authorityList =  usuarioService.getLoggedUserAuthorities();
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		Authority authority = null;
		authority =  Authority.findAuthoritysByRoleNameEquals("ROLE_ADMIN").getSingleResult();

		List<PromotionDescription> list = null;
		
		if(usuarioService.getLoggedUserAuthorities().contains(authority)){
			
			list = PromotionDescription.findPromotionDescriptionEntries(firstResult, maxResults);
		}else{
			
			list = PromotionDescription.findPromotionDescriptionEntriesByUser(usuario, firstResult, maxResults);
		}
		
        return list;
    }
    
    public void savePromotionDescription(PromotionDescription promotionDescription) {
        promotionDescription.persist();
        
        BusinessEstablishment businessEstablishment =  businessEstablishmentService.findUserBusinessEstablishments(usuarioService.getLoggedUserName());
        if(businessEstablishment==null){
        	throw new BusinessException("no businessEstablishments for actual logged user.");
        }
        
        businessEstablishment.getPromotionDescriptionList().add(promotionDescription);
        
        businessEstablishmentService.saveBusinessEstablishment(businessEstablishment);
    }
    
    public PromotionDescription updatePromotionDescription(PromotionDescription promotionDescription) {
        return promotionDescription.merge();
    }
	
}
