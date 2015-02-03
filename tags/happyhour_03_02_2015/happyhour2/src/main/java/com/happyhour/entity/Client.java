package com.happyhour.entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Client {

    /**
     */
    private String email;

    /**
     */
    private String firstname;

    /**
     */
    private String lastname;

    /**
     */
    private String gender;

    /**
     */
    private String telephoneNumber;

    /**
     */
    private String telephoneId;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Client> friendList = new ArrayList<Client>();
}
