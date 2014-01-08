package com.happyhour.controller;
import com.happyhour.entity.PromoInstanceProcessed;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/promoinstanceprocesseds")
@Controller
@RooWebScaffold(path = "promoinstanceprocesseds", formBackingObject = PromoInstanceProcessed.class)
public class PromoInstanceProcessedController {
}
