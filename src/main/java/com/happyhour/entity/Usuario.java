package com.happyhour.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUsuariosByUserNameEquals" })
public class Usuario {

    private String userName;

    private String password;

    private String email;

    private Boolean enabled;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    
    @OneToOne
    private BusinessEstablishment businessEstablishment;
    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Authority> rolesList = new HashSet<Authority>();
    
	public BusinessEstablishment getBusinessEstablishment() {
		return businessEstablishment;
	}
	public void setBusinessEstablishment(BusinessEstablishment businessEstablishment) {
		this.businessEstablishment = businessEstablishment;
	}
    
    
}
