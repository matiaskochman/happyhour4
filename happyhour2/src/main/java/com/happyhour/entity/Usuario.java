package com.happyhour.entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUsuariosByUserNameEquals" })
public class Usuario {

    /**
     */
    private String userName;

    /**
     */
    private String password;

    /**
     */
    private String email;

    /**
     */
    private Boolean enabled;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Authority> rolesList = new HashSet<Authority>();
}
