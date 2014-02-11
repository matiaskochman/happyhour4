package com.happyhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhour.entity.Authority;
import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.Usuario;

@Service
@Transactional
public class BusinessEstablishmentServiceImpl implements BusinessEstablishmentService {
	
	@Autowired
	private UsuarioService usuarioService;
	
    public long countAllBusinessEstablishments() {
        return BusinessEstablishment.countBusinessEstablishments();
    }
    
    public void deleteBusinessEstablishment(BusinessEstablishment businessEstablishment) {
        businessEstablishment.remove();
    }
    
    public BusinessEstablishment findBusinessEstablishment(Long id) {
        return BusinessEstablishment.findBusinessEstablishment(id);
    }
    
    public List<BusinessEstablishment> findAllBusinessEstablishments() {
        return BusinessEstablishment.findAllBusinessEstablishments();
    }
    
    public List<BusinessEstablishment> findBusinessEstablishmentEntries(int firstResult, int maxResults) {
        return BusinessEstablishment.findBusinessEstablishmentEntries(firstResult, maxResults);
    }
    
    public void saveBusinessEstablishment(BusinessEstablishment businessEstablishment) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); //get logged in username

		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		
        businessEstablishment.persist();
        
        usuario.setBusinessEstablishment(businessEstablishment);
        
        usuarioService.saveUsuario(usuario);
    }
    
    public BusinessEstablishment updateBusinessEstablishment(BusinessEstablishment businessEstablishment) {
        return businessEstablishment.merge();
    }

	@Override
	public BusinessEstablishment findUserBusinessEstablishments(String username) {
		
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		
		return usuario.getBusinessEstablishment();
	}

	@Override
	public List<BusinessEstablishment> findBusinessEstablishmentEntriesByUser(int firstResult, int maxResults) {
		
	    String username = usuarioService.getLoggedUserName(); 
	    List<BusinessEstablishment> list = null;
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		Authority authority = null;
		authority =  Authority.findAuthoritysByRoleNameEquals("ROLE_ADMIN").getSingleResult();
		if(usuarioService.getLoggedUserAuthorities().contains(authority)){
			
			list = BusinessEstablishment.findBusinessEstablishmentEntries(firstResult, maxResults);
		}else{
			
			list = BusinessEstablishment.findBusinessEstablishmentEntriesByUser(usuario, firstResult, maxResults);
		}
	    
		return list;
	}

}
