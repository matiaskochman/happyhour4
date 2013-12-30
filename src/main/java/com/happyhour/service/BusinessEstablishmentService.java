package com.happyhour.service;
import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import com.happyhour.entity.BusinessEstablishment;

@RooService(domainTypes = { com.happyhour.entity.BusinessEstablishment.class })
public interface BusinessEstablishmentService {
    public abstract long countAllBusinessEstablishments();    
    public abstract void deleteBusinessEstablishment(BusinessEstablishment businessEstablishment);    
    public abstract BusinessEstablishment findBusinessEstablishment(Long id);
    public abstract List<BusinessEstablishment> findUserBusinessEstablishments(String username);   
    public abstract List<BusinessEstablishment> findAllBusinessEstablishments();    
    public abstract List<BusinessEstablishment> findBusinessEstablishmentEntries(int firstResult, int maxResults);
    public abstract List<BusinessEstablishment> findBusinessEstablishmentEntriesByUser(int firstResult, int maxResults);
    public abstract void saveBusinessEstablishment(BusinessEstablishment businessEstablishment);    
    public abstract BusinessEstablishment updateBusinessEstablishment(BusinessEstablishment businessEstablishment);    
	
}
