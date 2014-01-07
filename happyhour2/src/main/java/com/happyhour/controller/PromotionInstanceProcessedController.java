package com.happyhour.controller;
import com.happyhour.entity.PromotionInstanceProcessed;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/promotioninstanceprocesseds")
@Controller
@RooWebScaffold(path = "promotioninstanceprocesseds", formBackingObject = PromotionInstanceProcessed.class)
public class PromotionInstanceProcessedController {
}
