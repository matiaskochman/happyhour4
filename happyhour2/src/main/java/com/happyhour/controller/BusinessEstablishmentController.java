package com.happyhour.controller;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.happyhour.entity.BusinessEstablishment;
import com.happyhour.service.BusinessEstablishmentService;
import com.happyhour.service.PromotionInstanceService;

@RequestMapping("/businessestablishments")
@Controller
@RooWebScaffold(path = "businessestablishments", formBackingObject = BusinessEstablishment.class)
@RooWebJson(jsonObject = BusinessEstablishment.class)
public class BusinessEstablishmentController {

    @Autowired
    BusinessEstablishmentService businessEstablishmentService;

    @Autowired
    PromotionInstanceService promotionInstanceService;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid BusinessEstablishment businessEstablishment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, businessEstablishment);
            return "businessestablishments/create";
        }
        uiModel.asMap().clear();
        businessEstablishmentService.saveBusinessEstablishment(businessEstablishment);
        return "redirect:/businessestablishments/" + encodeUrlPathSegment(businessEstablishment.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        BusinessEstablishment businessEstablishment = new BusinessEstablishment();
        populateEditForm(uiModel, businessEstablishment);
        return "businessestablishments/create";
    }

    //@RequestMapping(value = "/{id}", produces = "text/html")
    //public String show(@PathVariable("id") Long id, Model uiModel) {
    @RequestMapping(value = "/show", produces = "text/html")
    public String show(@RequestParam("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        uiModel.addAttribute("businessestablishment", businessEstablishmentService.findBusinessEstablishment(id));
        uiModel.addAttribute("itemId", id);
        return "businessestablishments/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("businessestablishments", businessEstablishmentService.findBusinessEstablishmentEntries(firstResult, sizeNo));
            uiModel.addAttribute("businessestablishments", businessEstablishmentService.findBusinessEstablishmentEntriesByUser(firstResult, sizeNo));
            float nrOfPages = (float) businessEstablishmentService.countAllBusinessEstablishments() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("businessestablishments", businessEstablishmentService.findAllBusinessEstablishments());
        }
        return "businessestablishments/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid BusinessEstablishment businessEstablishment, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, businessEstablishment);
            return "businessestablishments/update";
        }
        uiModel.asMap().clear();
        businessEstablishmentService.updateBusinessEstablishment(businessEstablishment);
        return "redirect:/businessestablishments/" + encodeUrlPathSegment(businessEstablishment.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, businessEstablishmentService.findBusinessEstablishment(id));
        return "businessestablishments/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(id);
        businessEstablishmentService.deleteBusinessEstablishment(businessEstablishment);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/businessestablishments";
    }

    void populateEditForm(Model uiModel, BusinessEstablishment businessEstablishment) {
        uiModel.addAttribute("businessEstablishment", businessEstablishment);
        uiModel.addAttribute("promotioninstances", promotionInstanceService.findAllPromotionInstances());
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (businessEstablishment == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(businessEstablishment.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<BusinessEstablishment> result = businessEstablishmentService.findAllBusinessEstablishments();
        return new ResponseEntity<String>(BusinessEstablishment.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        BusinessEstablishment businessEstablishment = BusinessEstablishment.fromJsonToBusinessEstablishment(json);
        businessEstablishmentService.saveBusinessEstablishment(businessEstablishment);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (BusinessEstablishment businessEstablishment: BusinessEstablishment.fromJsonArrayToBusinessEstablishments(json)) {
            businessEstablishmentService.saveBusinessEstablishment(businessEstablishment);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        BusinessEstablishment businessEstablishment = BusinessEstablishment.fromJsonToBusinessEstablishment(json);
        if (businessEstablishmentService.updateBusinessEstablishment(businessEstablishment) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        BusinessEstablishment businessEstablishment = businessEstablishmentService.findBusinessEstablishment(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (businessEstablishment == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        businessEstablishmentService.deleteBusinessEstablishment(businessEstablishment);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
}
