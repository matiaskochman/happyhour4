package com.happyhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.Authority;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.Usuario;

@Service
@Transactional
public class PromotionInstanceServiceImpl implements PromotionInstanceService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BusinessEstablishmentService businessEstablishmentService;
	
    public long countAllPromotionInstances() {
        return PromotionInstance.countPromotionInstances();
    }
    
    public void deletePromotionInstance(PromotionInstance promotionInstance) {
        promotionInstance.remove();
    }
    
    public PromotionInstance findPromotionInstance(Long id) {
        return PromotionInstance.findPromotionInstance(id);
    }
    
    public List<PromotionInstance> findAllPromotionInstances() {
        return PromotionInstance.findAllPromotionInstances();
    }
    
    public List<PromotionInstance> findPromotionInstanceEntries(int firstResult, int maxResults) {
        return PromotionInstance.findPromotionInstanceEntries(firstResult, maxResults);
    }
    
    public void savePromotionInstance(PromotionInstance promotionInstance) {
        promotionInstance.persist();
    }
    
    public PromotionInstance updatePromotionInstance(PromotionInstance promotionInstance) {
        return promotionInstance.merge();
    }
    
    public List<PromotionInstance> findPromotionInstanceEntriesByUser(int firstResult, int maxResults) {
    	
    	String username = usuarioService.getLoggedUserName();
    	List<Authority> authorityList =  usuarioService.getLoggedUserAuthorities();
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		Authority authority = null;
		authority =  Authority.findAuthoritysByRoleNameEquals("ROLE_ADMIN").getSingleResult();

		List<PromotionInstance> list = null;
		
		if(usuarioService.getLoggedUserAuthorities().contains(authority)){
			
			list = PromotionInstance.findPromotionInstanceEntries(firstResult, maxResults);
		}else{
			
			list = PromotionInstance.findPromotionInstanceEntriesByUser(usuario, firstResult, maxResults);
		}
		
        return list;
    }

	
}
