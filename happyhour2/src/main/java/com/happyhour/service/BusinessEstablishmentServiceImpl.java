package com.happyhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.Usuario;

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
	public List<BusinessEstablishment> findUserBusinessEstablishments(String username) {
		
		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		
		return null;
	}

	@Override
	public List<BusinessEstablishment> findBusinessEstablishmentEntriesByUser(int firstResult, int maxResults) {
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); 

		Usuario usuario = usuarioService.findUsuariosByUserNameEquals(username);
		
		List<BusinessEstablishment> list = BusinessEstablishment.findBusinessEstablishmentEntriesByUser(usuario, firstResult, maxResults);
	    
		return list;
	}
    
	
}
