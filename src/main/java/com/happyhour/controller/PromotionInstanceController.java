package com.happyhour.controller;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.happyhour.entity.PromotionInstance;

@RequestMapping("/promotioninstances")
@Controller
@RooWebScaffold(path = "promotioninstances", formBackingObject = PromotionInstance.class)
@RooWebJson(jsonObject = PromotionInstance.class)
public class PromotionInstanceController {
}
