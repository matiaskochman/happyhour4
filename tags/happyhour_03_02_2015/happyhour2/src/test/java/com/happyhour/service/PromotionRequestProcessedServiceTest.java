package com.happyhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "/META-INF/spring/applicationContext-tests.xml" })
public class PromotionRequestProcessedServiceTest {
	
    @Autowired
    PromotionInstanceProcessedService promotionInstanceProcessedService;

}
