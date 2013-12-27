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


	public Usuario findUsuariosByUserNameEquals(String username) {
	    Usuario usuario = Usuario.findUsuariosByUserNameEquals(username).getSingleResult();

		return usuario;
	}

    public long countAllUsuarios() {
        return Usuario.countUsuarios();
    }
    
    public void deleteUsuario(Usuario usuario) {
        usuario.remove();
    }
    
    public Usuario findUsuario(Long id) {
        return Usuario.findUsuario(id);
    }
    
    public List<Usuario> findAllUsuarios() {
        return Usuario.findAllUsuarios();
    }
    
    public List<Usuario> findUsuarioEntries(int firstResult, int maxResults) {
        return Usuario.findUsuarioEntries(firstResult, maxResults);
    }
    
    public void saveUsuario(Usuario usuario) {
        usuario.persist();
    }
    
    public Usuario updateUsuario(Usuario usuario) {
        return usuario.merge();
    }

}
