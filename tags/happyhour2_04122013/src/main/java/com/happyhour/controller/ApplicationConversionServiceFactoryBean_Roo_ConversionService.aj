// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.controller;

import com.happyhour.controller.ApplicationConversionServiceFactoryBean;
import com.happyhour.entity.Authority;
import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.PromotionDescription;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.Usuario;
import com.happyhour.service.AuthorityService;
import com.happyhour.service.BusinessEstablishmentService;
import com.happyhour.service.PromotionDescriptionService;
import com.happyhour.service.PromotionInstanceService;
import com.happyhour.service.PromotionRequestService;
import com.happyhour.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    @Autowired
    AuthorityService ApplicationConversionServiceFactoryBean.authorityService;
    
    @Autowired
    BusinessEstablishmentService ApplicationConversionServiceFactoryBean.businessEstablishmentService;
    
    @Autowired
    PromotionDescriptionService ApplicationConversionServiceFactoryBean.promotionDescriptionService;
    
    @Autowired
    PromotionInstanceService ApplicationConversionServiceFactoryBean.promotionInstanceService;
    
    @Autowired
    PromotionRequestService ApplicationConversionServiceFactoryBean.promotionRequestService;
    
    @Autowired
    UsuarioService ApplicationConversionServiceFactoryBean.usuarioService;
    
    public Converter<Authority, String> ApplicationConversionServiceFactoryBean.getAuthorityToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.Authority, java.lang.String>() {
            public String convert(Authority authority) {
                return new StringBuilder().append(authority.getRoleName()).toString();
            }
        };
    }
    
    public Converter<Long, Authority> ApplicationConversionServiceFactoryBean.getIdToAuthorityConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.Authority>() {
            public com.happyhour.entity.Authority convert(java.lang.Long id) {
                return authorityService.findAuthority(id);
            }
        };
    }
    
    public Converter<String, Authority> ApplicationConversionServiceFactoryBean.getStringToAuthorityConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.Authority>() {
            public com.happyhour.entity.Authority convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Authority.class);
            }
        };
    }
    
    public Converter<BusinessEstablishment, String> ApplicationConversionServiceFactoryBean.getBusinessEstablishmentToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.BusinessEstablishment, java.lang.String>() {
            public String convert(BusinessEstablishment businessEstablishment) {
                return new StringBuilder().append(businessEstablishment.getName()).toString();
            }
        };
    }
    
    public Converter<Long, BusinessEstablishment> ApplicationConversionServiceFactoryBean.getIdToBusinessEstablishmentConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.BusinessEstablishment>() {
            public com.happyhour.entity.BusinessEstablishment convert(java.lang.Long id) {
                return businessEstablishmentService.findBusinessEstablishment(id);
            }
        };
    }
    
    public Converter<String, BusinessEstablishment> ApplicationConversionServiceFactoryBean.getStringToBusinessEstablishmentConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.BusinessEstablishment>() {
            public com.happyhour.entity.BusinessEstablishment convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), BusinessEstablishment.class);
            }
        };
    }
    
    public Converter<PromotionDescription, String> ApplicationConversionServiceFactoryBean.getPromotionDescriptionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.PromotionDescription, java.lang.String>() {
            public String convert(PromotionDescription promotionDescription) {
                return new StringBuilder().append(promotionDescription.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, PromotionDescription> ApplicationConversionServiceFactoryBean.getIdToPromotionDescriptionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.PromotionDescription>() {
            public com.happyhour.entity.PromotionDescription convert(java.lang.Long id) {
                return promotionDescriptionService.findPromotionDescription(id);
            }
        };
    }
    
    public Converter<String, PromotionDescription> ApplicationConversionServiceFactoryBean.getStringToPromotionDescriptionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.PromotionDescription>() {
            public com.happyhour.entity.PromotionDescription convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PromotionDescription.class);
            }
        };
    }
    
    public Converter<PromotionInstance, String> ApplicationConversionServiceFactoryBean.getPromotionInstanceToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.PromotionInstance, java.lang.String>() {
            public String convert(PromotionInstance promotionInstance) {
                return new StringBuilder().append(promotionInstance.getPromotionValidDate()).append(' ').append(promotionInstance.getMaxClientsAllowed()).toString();
            }
        };
    }
    
    public Converter<Long, PromotionInstance> ApplicationConversionServiceFactoryBean.getIdToPromotionInstanceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.PromotionInstance>() {
            public com.happyhour.entity.PromotionInstance convert(java.lang.Long id) {
                return promotionInstanceService.findPromotionInstance(id);
            }
        };
    }
    
    public Converter<String, PromotionInstance> ApplicationConversionServiceFactoryBean.getStringToPromotionInstanceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.PromotionInstance>() {
            public com.happyhour.entity.PromotionInstance convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PromotionInstance.class);
            }
        };
    }
    
    public Converter<PromotionRequest, String> ApplicationConversionServiceFactoryBean.getPromotionRequestToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.PromotionRequest, java.lang.String>() {
            public String convert(PromotionRequest promotionRequest) {
                return new StringBuilder().append(promotionRequest.getPromoId()).append(' ').append(promotionRequest.getBusinessEstablishmentId()).append(' ').append(promotionRequest.getClientTelephone()).toString();
            }
        };
    }
    
    public Converter<Long, PromotionRequest> ApplicationConversionServiceFactoryBean.getIdToPromotionRequestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.PromotionRequest>() {
            public com.happyhour.entity.PromotionRequest convert(java.lang.Long id) {
                return promotionRequestService.findPromotionRequest(id);
            }
        };
    }
    
    public Converter<String, PromotionRequest> ApplicationConversionServiceFactoryBean.getStringToPromotionRequestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.PromotionRequest>() {
            public com.happyhour.entity.PromotionRequest convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PromotionRequest.class);
            }
        };
    }
    
    public Converter<Usuario, String> ApplicationConversionServiceFactoryBean.getUsuarioToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.Usuario, java.lang.String>() {
            public String convert(Usuario usuario) {
                return new StringBuilder().append(usuario.getUserName()).append(' ').append(usuario.getPassword()).append(' ').append(usuario.getEmail()).toString();
            }
        };
    }
    
    public Converter<Long, Usuario> ApplicationConversionServiceFactoryBean.getIdToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.Usuario>() {
            public com.happyhour.entity.Usuario convert(java.lang.Long id) {
                return usuarioService.findUsuario(id);
            }
        };
    }
    
    public Converter<String, Usuario> ApplicationConversionServiceFactoryBean.getStringToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.Usuario>() {
            public com.happyhour.entity.Usuario convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Usuario.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getAuthorityToStringConverter());
        registry.addConverter(getIdToAuthorityConverter());
        registry.addConverter(getStringToAuthorityConverter());
        registry.addConverter(getBusinessEstablishmentToStringConverter());
        registry.addConverter(getIdToBusinessEstablishmentConverter());
        registry.addConverter(getStringToBusinessEstablishmentConverter());
        registry.addConverter(getPromotionDescriptionToStringConverter());
        registry.addConverter(getIdToPromotionDescriptionConverter());
        registry.addConverter(getStringToPromotionDescriptionConverter());
        registry.addConverter(getPromotionInstanceToStringConverter());
        registry.addConverter(getIdToPromotionInstanceConverter());
        registry.addConverter(getStringToPromotionInstanceConverter());
        registry.addConverter(getPromotionRequestToStringConverter());
        registry.addConverter(getIdToPromotionRequestConverter());
        registry.addConverter(getStringToPromotionRequestConverter());
        registry.addConverter(getUsuarioToStringConverter());
        registry.addConverter(getIdToUsuarioConverter());
        registry.addConverter(getStringToUsuarioConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}