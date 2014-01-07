package com.happyhour.controller;
import com.happyhour.entity.PromotionRequestProcessed;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/promotionrequestprocesseds")
@Controller
@RooWebScaffold(path = "promotionrequestprocesseds", formBackingObject = PromotionRequestProcessed.class)
public class PromotionRequestProcessedController {
}
