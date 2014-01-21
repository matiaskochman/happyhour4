package com.happyhour.controller;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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

import com.happyhour.entity.PromotionRequest;
import com.happyhour.exception.BusinessException;
import com.happyhour.service.PromotionRequestProcessedService;
import com.happyhour.service.PromotionRequestService;

@RequestMapping("/promotionrequests")
@Controller
@RooWebScaffold(path = "promotionrequests", formBackingObject = PromotionRequest.class)
@RooWebJson(jsonObject = PromotionRequest.class)
public class PromotionRequestController {

    @Autowired
    PromotionRequestService promotionRequestService;
    
    @Autowired
    PromotionRequestProcessedService promotionRequestProcessedService;

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        PromotionRequest promotionRequest = PromotionRequest.fromJsonToPromotionRequest(json);
        promotionRequest.setCreationTimeStamp(new Date());
        try {
            promotionRequestService.createToken(promotionRequest);
            promotionRequestService.savePromotionRequest(promotionRequest);
            headers.add("Content-Type", "application/json");
            headers.add("token", promotionRequest.getToken());
        } catch (BusinessException e) {
            e.printStackTrace();
            e.getMessage();
            return new ResponseEntity<String>(e.getMessage(), headers, HttpStatus.I_AM_A_TEAPOT);
        } catch (RuntimeException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("promotionRequest_creationtimestamp_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    
    @RequestMapping(value = "/processPromotionRequest", produces = "text/html")
    public String processRequest(@Valid PromotionRequest promotionRequest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest){
    	
    	promotionRequestProcessedService.processPromotionRequest(promotionRequest);
    	
    	return "redirect:/promotionrequests/";
    }
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PromotionRequest promotionRequest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionRequest);
            return "promotionrequests/create";
        }
        uiModel.asMap().clear();
        promotionRequestService.savePromotionRequest(promotionRequest);
        return "redirect:/promotionrequests/" + encodeUrlPathSegment(promotionRequest.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new PromotionRequest());
        return "promotionrequests/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("promotionrequest", promotionRequestService.findPromotionRequest(id));
        uiModel.addAttribute("itemId", id);
        return "promotionrequests/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("promotionrequests", promotionRequestService.findPromotionRequestEntries(firstResult, sizeNo));
            float nrOfPages = (float) promotionRequestService.countAllPromotionRequests() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("promotionrequests", promotionRequestService.findAllPromotionRequests());
        }
        return "promotionrequests/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PromotionRequest promotionRequest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionRequest);
            return "promotionrequests/update";
        }
        uiModel.asMap().clear();
        promotionRequestService.updatePromotionRequest(promotionRequest);
        return "redirect:/promotionrequests/" + encodeUrlPathSegment(promotionRequest.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, promotionRequestService.findPromotionRequest(id));
        return "promotionrequests/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PromotionRequest promotionRequest = promotionRequestService.findPromotionRequest(id);
        promotionRequestService.deletePromotionRequest(promotionRequest);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/promotionrequests";
    }

    void populateEditForm(Model uiModel, PromotionRequest promotionRequest) {
        uiModel.addAttribute("promotionRequest", promotionRequest);
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
        PromotionRequest promotionRequest = promotionRequestService.findPromotionRequest(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (promotionRequest == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(promotionRequest.toJson(), headers, HttpStatus.OK);
    }

    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PromotionRequest> result = promotionRequestService.findAllPromotionRequests();
        return new ResponseEntity<String>(PromotionRequest.toJsonArray(result), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (PromotionRequest promotionRequest : PromotionRequest.fromJsonArrayToPromotionRequests(json)) {
            promotionRequestService.savePromotionRequest(promotionRequest);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        PromotionRequest promotionRequest = PromotionRequest.fromJsonToPromotionRequest(json);
        if (promotionRequestService.updatePromotionRequest(promotionRequest) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        PromotionRequest promotionRequest = promotionRequestService.findPromotionRequest(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (promotionRequest == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        promotionRequestService.deletePromotionRequest(promotionRequest);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
