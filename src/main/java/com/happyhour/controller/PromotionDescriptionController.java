package com.happyhour.controller;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.happyhour.entity.PromotionDescription;

@RequestMapping("/promotiondescriptions")
@Controller
@RooWebScaffold(path = "promotiondescriptions", formBackingObject = PromotionDescription.class)
@RooWebJson(jsonObject = PromotionDescription.class)
public class PromotionDescriptionController {
}
