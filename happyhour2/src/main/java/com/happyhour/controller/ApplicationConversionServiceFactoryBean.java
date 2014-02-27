package com.happyhour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

import com.happyhour.entity.Authority;
import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.entity.Client;
import com.happyhour.entity.PromotionDescription;
import com.happyhour.entity.PromotionInstance;
import com.happyhour.entity.PromotionRequest;
import com.happyhour.entity.PromotionRequestProcessed;
import com.happyhour.entity.Usuario;
import com.happyhour.service.AuthorityService;
import com.happyhour.service.BusinessEstablishmentService;
import com.happyhour.service.ClientService;
import com.happyhour.service.PromotionDescriptionService;
import com.happyhour.service.PromotionInstanceService;
import com.happyhour.service.PromotionRequestService;
import com.happyhour.service.UsuarioService;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

    @Autowired
    AuthorityService authorityService;
    
    @Autowired
    BusinessEstablishmentService businessEstablishmentService;
    
    @Autowired
    PromotionDescriptionService promotionDescriptionService;
    
    @Autowired
    PromotionInstanceService promotionInstanceService;
    
    @Autowired
    PromotionRequestService promotionRequestService;
    
    @Autowired
    UsuarioService usuarioService;
	
    @Autowired
    ClientService clientService;
	
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}
	
    public Converter<PromotionRequestProcessed, String> getPromotionRequestProcessedToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.PromotionRequestProcessed, java.lang.String>() {
            public String convert(PromotionRequestProcessed promotionRequestProcessed) {
                return new StringBuilder().append(promotionRequestProcessed.getPromoId()).append(' ').append(promotionRequestProcessed.getBusinessEstablishmentId()).append(' ').append(promotionRequestProcessed.getClientTelephone()).toString();
            }
        };
    }
    
    public Converter<Long, PromotionRequestProcessed> getIdToPromotionRequestProcessedConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.PromotionRequestProcessed>() {
            public com.happyhour.entity.PromotionRequestProcessed convert(java.lang.Long id) {
                return PromotionRequestProcessed.findPromotionRequestProcessed(id);
            }
        };
    }
    
    public Converter<String, PromotionRequestProcessed> getStringToPromotionRequestProcessedConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.PromotionRequestProcessed>() {
            public com.happyhour.entity.PromotionRequestProcessed convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PromotionRequestProcessed.class);
            }
        };
    }

    public void installLabelConverters(FormatterRegistry registry) {
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
        
        registry.addConverter(getPromotionRequestProcessedToStringConverter());
        registry.addConverter(getIdToPromotionRequestProcessedConverter());
        registry.addConverter(getStringToPromotionRequestProcessedConverter());

        registry.addConverter(getUsuarioToStringConverter());
        registry.addConverter(getIdToUsuarioConverter());
        registry.addConverter(getStringToUsuarioConverter());
        
        registry.addConverter(getClientToStringConverter());
        registry.addConverter(getIdToClientConverter());
        registry.addConverter(getStringToClientConverter());
        
    }
    
    
    public Converter<Authority, String> getAuthorityToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.Authority, java.lang.String>() {
            public String convert(Authority authority) {
                return new StringBuilder().append(authority.getRoleName()).toString();
            }
        };
    }
    
    public Converter<Long, Authority> getIdToAuthorityConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.Authority>() {
            public com.happyhour.entity.Authority convert(java.lang.Long id) {
                return authorityService.findAuthority(id);
            }
        };
    }
    
    public Converter<String, Authority> getStringToAuthorityConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.Authority>() {
            public com.happyhour.entity.Authority convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Authority.class);
            }
        };
    }
    
    public Converter<BusinessEstablishment, String> getBusinessEstablishmentToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.BusinessEstablishment, java.lang.String>() {
            public String convert(BusinessEstablishment businessEstablishment) {
                return new StringBuilder().append(businessEstablishment.getName()).toString();
            }
        };
    }
    
    public Converter<Long, BusinessEstablishment> getIdToBusinessEstablishmentConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.BusinessEstablishment>() {
            public com.happyhour.entity.BusinessEstablishment convert(java.lang.Long id) {
                return businessEstablishmentService.findBusinessEstablishment(id);
            }
        };
    }
    
    public Converter<String, BusinessEstablishment> getStringToBusinessEstablishmentConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.BusinessEstablishment>() {
            public com.happyhour.entity.BusinessEstablishment convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), BusinessEstablishment.class);
            }
        };
    }
    
    public Converter<PromotionDescription, String> getPromotionDescriptionToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.PromotionDescription, java.lang.String>() {
            public String convert(PromotionDescription promotionDescription) {
                return new StringBuilder().append(promotionDescription.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, PromotionDescription> getIdToPromotionDescriptionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.PromotionDescription>() {
            public com.happyhour.entity.PromotionDescription convert(java.lang.Long id) {
                return promotionDescriptionService.findPromotionDescription(id);
            }
        };
    }
    
    public Converter<String, PromotionDescription> getStringToPromotionDescriptionConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.PromotionDescription>() {
            public com.happyhour.entity.PromotionDescription convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PromotionDescription.class);
            }
        };
    }
    
    public Converter<PromotionInstance, String> getPromotionInstanceToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.PromotionInstance, java.lang.String>() {
            public String convert(PromotionInstance promotionInstance) {
                return new StringBuilder().append(promotionInstance.getPromotionValidDate()).append(' ').append(promotionInstance.getMaxClientsAllowed()).toString();
            }
        };
    }
    
    public Converter<Long, PromotionInstance> getIdToPromotionInstanceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.PromotionInstance>() {
            public com.happyhour.entity.PromotionInstance convert(java.lang.Long id) {
                return promotionInstanceService.findPromotionInstance(id);
            }
        };
    }
    
    public Converter<String, PromotionInstance> getStringToPromotionInstanceConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.PromotionInstance>() {
            public com.happyhour.entity.PromotionInstance convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PromotionInstance.class);
            }
        };
    }
    
    public Converter<PromotionRequest, String> getPromotionRequestToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.PromotionRequest, java.lang.String>() {
            public String convert(PromotionRequest promotionRequest) {
                return new StringBuilder().append(promotionRequest.getPromoId()).append(' ').append(promotionRequest.getBusinessEstablishmentId()).append(' ').append(promotionRequest.getClientTelephone()).toString();
            }
        };
    }
    
    public Converter<Long, PromotionRequest> getIdToPromotionRequestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.PromotionRequest>() {
            public com.happyhour.entity.PromotionRequest convert(java.lang.Long id) {
                return promotionRequestService.findPromotionRequest(id);
            }
        };
    }
    
    public Converter<String, PromotionRequest> getStringToPromotionRequestConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.PromotionRequest>() {
            public com.happyhour.entity.PromotionRequest convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PromotionRequest.class);
            }
        };
    }
    
    public Converter<Usuario, String> getUsuarioToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.Usuario, java.lang.String>() {
            public String convert(Usuario usuario) {
                return new StringBuilder().append(usuario.getUserName()).append(' ').append(usuario.getPassword()).append(' ').append(usuario.getEmail()).toString();
            }
        };
    }
    
    public Converter<Long, Usuario> getIdToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.Usuario>() {
            public com.happyhour.entity.Usuario convert(java.lang.Long id) {
                return usuarioService.findUsuario(id);
            }
        };
    }
    
    public Converter<String, Usuario> getStringToUsuarioConverter() {
    	return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.Usuario>() {
    		public com.happyhour.entity.Usuario convert(String id) {
    			return getObject().convert(getObject().convert(id, Long.class), Usuario.class);
    		}
    	};
    }
    
    public Converter<String, Client> getStringToClientConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.happyhour.entity.Client>() {
            public com.happyhour.entity.Client convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Client.class);
            }
        };
    }

    public Converter<Client, String> getClientToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.happyhour.entity.Client, java.lang.String>() {
            public String convert(Client client) {
                return new StringBuilder().append(client.getFirstname()).append(' ').append(client.getFirstname()).toString();
            }
        };
    }
    
    public Converter<Long, Client> getIdToClientConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.happyhour.entity.Client>() {
            public com.happyhour.entity.Client convert(java.lang.Long id) {
                return clientService.findClient(id);
            }
        };
    }
    
    
    
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
