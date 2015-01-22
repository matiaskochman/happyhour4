package com.happyhour.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUsuariosByUserNameEquals" })
public class Usuario {

	public Usuario(){
		this.creationDate = new Date();
		this.enabled = true;
	}
	@NotNull
	@Size(min=4, max=10) 
    private String userName;

	@NotNull
	@Size(min=1, max=10) 
    private String password;

	@NotNull
	@Size(min=2, max=60) 
    private String email;

    @Basic
    @Column(name = "enabled", columnDefinition = "BIT", length = 1)
    private Boolean enabled;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")    
    private Date creationDate;
    
    /**
     * this is just to use as backing Object in spring mvc post
     */
    @Transient
    private String authorityFormValue;
    
    
    public String getAuthorityFormValue() {
		return authorityFormValue;
	}

	public void setAuthorityFormValue(String authorityFormValue) {
		this.authorityFormValue = authorityFormValue;
	}

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
