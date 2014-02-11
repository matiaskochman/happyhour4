package com.happyhour.controller;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import com.happyhour.entity.PromotionInstanceProcessed;
import com.happyhour.service.BusinessEstablishmentService;
import com.happyhour.service.PromotionDescriptionService;
import com.happyhour.service.PromotionInstanceProcessedService;
import com.happyhour.service.PromotionRequestProcessedService;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/promotioninstanceprocesseds")
@Controller
@RooWebScaffold(path = "promotioninstanceprocesseds", formBackingObject = PromotionInstanceProcessed.class)
@RooWebJson(jsonObject = PromotionInstanceProcessed.class)
public class PromotionInstanceProcessedController {

    @Autowired
    PromotionInstanceProcessedService promotionInstanceProcessedService;

    @Autowired
    BusinessEstablishmentService businessEstablishmentService;

    @Autowired
    PromotionDescriptionService promotionDescriptionService;

    @Autowired
    PromotionRequestProcessedService promotionRequestProcessedService;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PromotionInstanceProcessed promotionInstanceProcessed, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionInstanceProcessed);
            return "promotioninstanceprocesseds/create";
        }
        uiModel.asMap().clear();
        promotionInstanceProcessedService.savePromotionInstanceProcessed(promotionInstanceProcessed);
        return "redirect:/promotioninstanceprocesseds/" + encodeUrlPathSegment(promotionInstanceProcessed.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new PromotionInstanceProcessed());
        return "promotioninstanceprocesseds/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("promotioninstanceprocessed", PromotionInstanceProcessed.findPromotionInstanceProcessed(id));
        uiModel.addAttribute("itemId", id);
        return "promotioninstanceprocesseds/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            //uiModel.addAttribute("promotioninstanceprocesseds", PromotionInstanceProcessed.findPromotionInstanceProcessedEntries(firstResult, sizeNo));
            uiModel.addAttribute("promotioninstanceprocesseds", promotionInstanceProcessedService.findPromotionInstanceProcessedEntriesByUser(firstResult, sizeNo));
            float nrOfPages = (float) PromotionInstanceProcessed.countPromotionInstanceProcesseds() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("promotioninstanceprocesseds", PromotionInstanceProcessed.findAllPromotionInstanceProcesseds());
        }
        addDateTimeFormatPatterns(uiModel);
        return "promotioninstanceprocesseds/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PromotionInstanceProcessed promotionInstanceProcessed, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, promotionInstanceProcessed);
            return "promotioninstanceprocesseds/update";
        }
        uiModel.asMap().clear();
        promotionInstanceProcessedService.updatePromotionInstanceProcessed(promotionInstanceProcessed);
        return "redirect:/promotioninstanceprocesseds/" + encodeUrlPathSegment(promotionInstanceProcessed.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, promotionInstanceProcessedService.findPromotionInstanceProcessed(id));
        return "promotioninstanceprocesseds/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PromotionInstanceProcessed promotionInstanceProcessed = promotionInstanceProcessedService.findPromotionInstanceProcessed(id);
        promotionInstanceProcessed.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/promotioninstanceprocesseds";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("promotionInstanceProcessed_promotionvaliddate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, PromotionInstanceProcessed promotionInstanceProcessed) {
        uiModel.addAttribute("promotionInstanceProcessed", promotionInstanceProcessed);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("businessestablishments", businessEstablishmentService.findAllBusinessEstablishments());
        uiModel.addAttribute("promotiondescriptions", promotionDescriptionService.findAllPromotionDescriptions());
        uiModel.addAttribute("promotionrequestprocesseds", promotionRequestProcessedService.findAllPromotionRequestProcesseds());
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
}
