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
import com.happyhour.entity.PromotionRequestProcessed;
import com.happyhour.service.PromotionRequestProcessedService;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/promotionrequestprocesseds")
@Controller
@RooWebScaffold(path = "promotionrequestprocesseds", formBackingObject = PromotionRequestProcessed.class)
@RooWebJson(jsonObject = PromotionRequestProcessed.class)
public class PromotionRequestProcessedController {

    @Autowired
    PromotionRequestProcessedService promotionRequestProcessedService;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PromotionRequestProcessed promotionRequestProcessed, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionRequestProcessed);
            return "promotionrequestprocesseds/create";
        }
        uiModel.asMap().clear();
        promotionRequestProcessed.persist();
        return "redirect:/promotionrequestprocesseds/" + encodeUrlPathSegment(promotionRequestProcessed.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new PromotionRequestProcessed());
        return "promotionrequestprocesseds/create";
    }

    @RequestMapping(value = "/show", produces = "text/html")
    public String show(@RequestParam("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("promotionrequestprocessed", PromotionRequestProcessed.findPromotionRequestProcessed(id));
        uiModel.addAttribute("itemId", id);
        return "promotionrequestprocesseds/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("promotionrequestprocesseds", PromotionRequestProcessed.findPromotionRequestProcessedEntries(firstResult, sizeNo));
            uiModel.addAttribute("promotionrequestprocesseds", promotionRequestProcessedService.findPromotionRequestProcessedEntriesByUser(firstResult, sizeNo));
            float nrOfPages = (float) PromotionRequestProcessed.countPromotionRequestProcesseds() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("promotionrequestprocesseds", PromotionRequestProcessed.findAllPromotionRequestProcesseds());
        }
        addDateTimeFormatPatterns(uiModel);
        return "promotionrequestprocesseds/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PromotionRequestProcessed promotionRequestProcessed, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionRequestProcessed);
            return "promotionrequestprocesseds/update";
        }
        uiModel.asMap().clear();
        promotionRequestProcessed.merge();
        return "redirect:/promotionrequestprocesseds/" + encodeUrlPathSegment(promotionRequestProcessed.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, PromotionRequestProcessed.findPromotionRequestProcessed(id));
        return "promotionrequestprocesseds/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PromotionRequestProcessed promotionRequestProcessed = PromotionRequestProcessed.findPromotionRequestProcessed(id);
        promotionRequestProcessed.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/promotionrequestprocesseds";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("promotionRequestProcessed_creationtimestamp_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, PromotionRequestProcessed promotionRequestProcessed) {
        uiModel.addAttribute("promotionRequestProcessed", promotionRequestProcessed);
        addDateTimeFormatPatterns(uiModel);
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
        PromotionRequestProcessed promotionRequestProcessed = promotionRequestProcessedService.findPromotionRequestProcessed(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (promotionRequestProcessed == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(promotionRequestProcessed.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/json/list",headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PromotionRequestProcessed> result = promotionRequestProcessedService.findAllPromotionRequestProcesseds();
        return new ResponseEntity<String>(PromotionRequestProcessed.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        PromotionRequestProcessed promotionRequestProcessed = PromotionRequestProcessed.fromJsonToPromotionRequestProcessed(json);
        promotionRequestProcessedService.savePromotionRequestProcessed(promotionRequestProcessed);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJsonArray(@RequestBody String json) {
        for (PromotionRequestProcessed promotionRequestProcessed: PromotionRequestProcessed.fromJsonArrayToPromotionRequestProcesseds(json)) {
            promotionRequestProcessedService.savePromotionRequestProcessed(promotionRequestProcessed);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        PromotionRequestProcessed promotionRequestProcessed = PromotionRequestProcessed.fromJsonToPromotionRequestProcessed(json);
        if (promotionRequestProcessedService.updatePromotionRequestProcessed(promotionRequestProcessed) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteFromJson(@PathVariable("id") Long id) {
        PromotionRequestProcessed promotionRequestProcessed = promotionRequestProcessedService.findPromotionRequestProcessed(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (promotionRequestProcessed == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        promotionRequestProcessedService.deletePromotionRequestProcessed(promotionRequestProcessed);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
}
