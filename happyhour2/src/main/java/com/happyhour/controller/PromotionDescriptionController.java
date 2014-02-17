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

import com.happyhour.entity.PromotionDescription;
import com.happyhour.service.PromotionDescriptionService;

@RequestMapping("/promotiondescriptions")
@Controller
@RooWebScaffold(path = "promotiondescriptions", formBackingObject = PromotionDescription.class)
@RooWebJson(jsonObject = PromotionDescription.class)
public class PromotionDescriptionController {

    @Autowired
    PromotionDescriptionService promotionDescriptionService;
	
	
    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("promotiondescriptions", promotionDescriptionService.findPromotionDescriptionEntries(firstResult, sizeNo));
            uiModel.addAttribute("promotiondescriptions", promotionDescriptionService.findPromotionDescriptionEntriesByUser(firstResult, sizeNo));
            float nrOfPages = (float) promotionDescriptionService.countAllPromotionDescriptions() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("promotiondescriptions", promotionDescriptionService.findAllPromotionDescriptions());
        }
        return "promotiondescriptions/list";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> showJson(@PathVariable("id") Long id) {
        PromotionDescription promotionDescription = promotionDescriptionService.findPromotionDescription(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (promotionDescription == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(promotionDescription.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PromotionDescription> result = promotionDescriptionService.findAllPromotionDescriptions();
        return new ResponseEntity<String>(PromotionDescription.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        PromotionDescription promotionDescription = PromotionDescription.fromJsonToPromotionDescription(json);
        promotionDescriptionService.savePromotionDescription(promotionDescription);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (PromotionDescription promotionDescription: PromotionDescription.fromJsonArrayToPromotionDescriptions(json)) {
            promotionDescriptionService.savePromotionDescription(promotionDescription);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        PromotionDescription promotionDescription = PromotionDescription.fromJsonToPromotionDescription(json);
        if (promotionDescriptionService.updatePromotionDescription(promotionDescription) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        PromotionDescription promotionDescription = promotionDescriptionService.findPromotionDescription(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (promotionDescription == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        promotionDescriptionService.deletePromotionDescription(promotionDescription);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PromotionDescription promotionDescription, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionDescription);
            return "promotiondescriptions/create";
        }
        uiModel.asMap().clear();
        promotionDescriptionService.savePromotionDescription(promotionDescription);
        return "redirect:/promotiondescriptions/" + encodeUrlPathSegment(promotionDescription.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new PromotionDescription());
        return "promotiondescriptions/create";
    }
    
    @RequestMapping(value = "/show", produces = "text/html")
    public String show(@RequestParam("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        uiModel.addAttribute("promotiondescription", promotionDescriptionService.findPromotionDescription(id));
        uiModel.addAttribute("itemId", id);
        return "promotiondescriptions/show";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PromotionDescription promotionDescription, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionDescription);
            return "promotiondescriptions/update";
        }
        uiModel.asMap().clear();
        promotionDescriptionService.updatePromotionDescription(promotionDescription);
        return "redirect:/promotiondescriptions/" + encodeUrlPathSegment(promotionDescription.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, promotionDescriptionService.findPromotionDescription(id));
        return "promotiondescriptions/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PromotionDescription promotionDescription = promotionDescriptionService.findPromotionDescription(id);
        promotionDescriptionService.deletePromotionDescription(promotionDescription);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/promotiondescriptions";
    }
    
    void populateEditForm(Model uiModel, PromotionDescription promotionDescription) {
        uiModel.addAttribute("promotionDescription", promotionDescription);
    }
    
    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
    
}
