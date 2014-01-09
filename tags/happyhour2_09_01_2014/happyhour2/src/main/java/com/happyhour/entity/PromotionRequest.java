package com.happyhour.entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class PromotionRequest {

    /**
     */
    private String promoId;

    /**
     */
    private String businessEstablishmentId;

    /**
     */
    private String clientTelephone;

    /**
     */
    private String token;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationTimeStamp;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((businessEstablishmentId == null) ? 0
						: businessEstablishmentId.hashCode());
		result = prime * result
				+ ((clientTelephone == null) ? 0 : clientTelephone.hashCode());
		result = prime * result + ((promoId == null) ? 0 : promoId.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromotionRequest other = (PromotionRequest) obj;
		if (businessEstablishmentId == null) {
			if (other.businessEstablishmentId != null)
				return false;
		} else if (!businessEstablishmentId
				.equals(other.businessEstablishmentId))
			return false;
		if (clientTelephone == null) {
			if (other.clientTelephone != null)
				return false;
		} else if (!clientTelephone.equals(other.clientTelephone))
			return false;
		if (promoId == null) {
			if (other.promoId != null)
				return false;
		} else if (!promoId.equals(other.promoId))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
    
    
}
