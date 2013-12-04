package com.happyhour.service;

import java.util.List;

import com.happyhour.entity.BusinessEstablishment;

public class BusinessEstablishmentServiceImpl implements BusinessEstablishmentService {
	
    public long countAllBusinessEstablishments() {
        return BusinessEstablishment.countBusinessEstablishments();
    }
    
    public void deleteBusinessEstablishment(BusinessEstablishment businessEstablishment) {
        businessEstablishment.remove();
    }
    
    public BusinessEstablishment findBusinessEstablishment(Long id) {
        return BusinessEstablishment.findBusinessEstablishment(id);
    }
    
    public List<BusinessEstablishment> findAllBusinessEstablishments() {
        return BusinessEstablishment.findAllBusinessEstablishments();
    }
    
    public List<BusinessEstablishment> findBusinessEstablishmentEntries(int firstResult, int maxResults) {
        return BusinessEstablishment.findBusinessEstablishmentEntries(firstResult, maxResults);
    }
    
    public void saveBusinessEstablishment(BusinessEstablishment businessEstablishment) {
        businessEstablishment.persist();
    }
    
    public BusinessEstablishment updateBusinessEstablishment(BusinessEstablishment businessEstablishment) {
        return businessEstablishment.merge();
    }
    
	
}
