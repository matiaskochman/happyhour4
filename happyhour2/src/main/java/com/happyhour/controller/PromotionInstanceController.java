package com.happyhour.controller;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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

import com.happyhour.entity.PromotionInstance;
import com.happyhour.service.BusinessEstablishmentService;
import com.happyhour.service.PromotionDescriptionService;
import com.happyhour.service.PromotionInstanceProcessedService;
import com.happyhour.service.PromotionInstanceService;
import com.happyhour.service.PromotionRequestService;

@RequestMapping("/promotioninstances")
@Controller
@RooWebScaffold(path = "promotioninstances", formBackingObject = PromotionInstance.class)
@RooWebJson(jsonObject = PromotionInstance.class)
public class PromotionInstanceController {

    @Autowired
    PromotionInstanceService promotionInstanceService;

    @Autowired
    PromotionInstanceProcessedService promotionInstanceProcessedService;
    
    @Autowired
    BusinessEstablishmentService businessEstablishmentService;

    @Autowired
    PromotionDescriptionService promotionDescriptionService;

    @Autowired
    PromotionRequestService promotionRequestService;

    
    @RequestMapping(value = "/json/list", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PromotionInstance> result = promotionInstanceService.findPromotionInstanceEntriesByBusinessEstablishment(json);
        return new ResponseEntity<String>(PromotionInstance.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PromotionInstance promotionInstance, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionInstance);
            return "promotioninstances/create";
        }
        uiModel.asMap().clear();
        promotionInstanceService.savePromotionInstance(promotionInstance);
        return "redirect:/promotioninstances/" + encodeUrlPathSegment(promotionInstance.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new PromotionInstance());
        return "promotioninstances/create";
    }

    //@RequestMapping(value = "/{id}", produces = "text/html")
    @RequestMapping(value = "/show", produces = "text/html")
    public String show(@RequestParam("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("promotioninstance", promotionInstanceService.findPromotionInstance(id));
        uiModel.addAttribute("itemId", id);
        return "promotioninstances/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("promotioninstances", promotionInstanceService.findPromotionInstanceEntries(firstResult, sizeNo));
            uiModel.addAttribute("promotioninstances", promotionInstanceService.findPromotionInstanceEntriesByUser(firstResult, sizeNo));
            float nrOfPages = (float) promotionInstanceService.countAllPromotionInstances() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("promotioninstances", promotionInstanceService.findAllPromotionInstances());
        }
        addDateTimeFormatPatterns(uiModel);
        return "promotioninstances/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PromotionInstance promotionInstance, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionInstance);
            return "promotioninstances/update";
        }
        uiModel.asMap().clear();
        promotionInstanceService.updatePromotionInstance(promotionInstance);
        return "redirect:/promotioninstances/" + encodeUrlPathSegment(promotionInstance.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, promotionInstanceService.findPromotionInstance(id));
        return "promotioninstances/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PromotionInstance promotionInstance = promotionInstanceService.findPromotionInstance(id);
        promotionInstanceService.deletePromotionInstance(promotionInstance);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/promotioninstances";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("promotionInstance_promotionvaliddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, PromotionInstance promotionInstance) {
        uiModel.addAttribute("promotionInstance", promotionInstance);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("businessestablishments", businessEstablishmentService.findAllBusinessEstablishments());
        uiModel.addAttribute("promotiondescriptions", promotionDescriptionService.findAllPromotionDescriptions());
        uiModel.addAttribute("promotionrequests", promotionRequestService.findAllPromotionRequests());
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
        PromotionInstance promotionInstance = promotionInstanceService.findPromotionInstance(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (promotionInstance == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(promotionInstance.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        PromotionInstance promotionInstance = PromotionInstance.fromJsonToPromotionInstance(json);
        promotionInstanceService.savePromotionInstance(promotionInstance);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (PromotionInstance promotionInstance: PromotionInstance.fromJsonArrayToPromotionInstances(json)) {
            promotionInstanceService.savePromotionInstance(promotionInstance);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        PromotionInstance promotionInstance = PromotionInstance.fromJsonToPromotionInstance(json);
        if (promotionInstanceService.updatePromotionInstance(promotionInstance) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        PromotionInstance promotionInstance = promotionInstanceService.findPromotionInstance(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (promotionInstance == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        promotionInstanceService.deletePromotionInstance(promotionInstance);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "processPromotionInstance/{id}")
    public String processPromotionInstance(@PathVariable("id") Long id){
    	
    	promotionInstanceProcessedService.processPromotionInstance(id);
    	
    	return "redirect:/promotioninstances/";
    }
    
    
}
