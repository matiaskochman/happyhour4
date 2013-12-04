package com.happyhour.entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Authority implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	/**
     */
    private String roleName;

	public String getAuthority() {
		return roleName;
	}
}
