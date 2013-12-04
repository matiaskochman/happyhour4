// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.controller;

import com.happyhour.controller.PromotionDescriptionController;
import com.happyhour.entity.PromotionDescription;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

privileged aspect PromotionDescriptionController_Roo_Controller_Json {
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> PromotionDescriptionController.showJson(@PathVariable("id") Long id) {
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
    public ResponseEntity<String> PromotionDescriptionController.listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<PromotionDescription> result = promotionDescriptionService.findAllPromotionDescriptions();
        return new ResponseEntity<String>(PromotionDescription.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> PromotionDescriptionController.createFromJson(@RequestBody String json) {
        PromotionDescription promotionDescription = PromotionDescription.fromJsonToPromotionDescription(json);
        promotionDescriptionService.savePromotionDescription(promotionDescription);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> PromotionDescriptionController.createFromJsonArray(@RequestBody String json) {
        for (PromotionDescription promotionDescription: PromotionDescription.fromJsonArrayToPromotionDescriptions(json)) {
            promotionDescriptionService.savePromotionDescription(promotionDescription);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> PromotionDescriptionController.updateFromJson(@RequestBody String json, @PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        PromotionDescription promotionDescription = PromotionDescription.fromJsonToPromotionDescription(json);
        if (promotionDescriptionService.updatePromotionDescription(promotionDescription) == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> PromotionDescriptionController.deleteFromJson(@PathVariable("id") Long id) {
        PromotionDescription promotionDescription = promotionDescriptionService.findPromotionDescription(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (promotionDescription == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        promotionDescriptionService.deletePromotionDescription(promotionDescription);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
}
