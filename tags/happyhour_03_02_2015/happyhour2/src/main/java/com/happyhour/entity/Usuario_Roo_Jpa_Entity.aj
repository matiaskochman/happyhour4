// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.Usuario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

privileged aspect Usuario_Roo_Jpa_Entity {
    
    declare @type: Usuario: @Entity;
    
    @Version
    @Column(name = "version")
    private Integer Usuario.version;
    
    public Integer Usuario.getVersion() {
        return this.version;
    }
    
    public void Usuario.setVersion(Integer version) {
        this.version = version;
    }
    
}
