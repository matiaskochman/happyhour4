package com.happyhour.controller;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.happyhour.entity.BusinessEstablishment;

@RequestMapping("/businessestablishments")
@Controller
@RooWebScaffold(path = "businessestablishments", formBackingObject = BusinessEstablishment.class)
@RooWebJson(jsonObject = BusinessEstablishment.class)
public class BusinessEstablishmentController {
}
