package com.happyhour.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.happyhour.entity.Authority;
import com.happyhour.entity.Usuario;

@Service(value="usuarioService")
public class UsuarioServiceImpl implements UsuarioService ,UserDetailsService{

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Usuario u = Usuario.findUsuariosByUserNameEquals(username).getSingleResult();
		u.getRolesList();
		
        List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
        for (Authority ur : u.getRolesList()){
        	authorities.add(ur);
        } 
		
        org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(u.getUserName(), u 
        		.getPassword(), u.getEnabled(), true, true, true, authorities); 
        
		  

        return authUser;
	}

}
