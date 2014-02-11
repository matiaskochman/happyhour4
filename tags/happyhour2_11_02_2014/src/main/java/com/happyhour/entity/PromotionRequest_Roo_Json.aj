// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.PromotionRequest;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect PromotionRequest_Roo_Json {
    
    public String PromotionRequest.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public String PromotionRequest.toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }
    
    public static PromotionRequest PromotionRequest.fromJsonToPromotionRequest(String json) {
        return new JSONDeserializer<PromotionRequest>().use(null, PromotionRequest.class).deserialize(json);
    }
    
    public static String PromotionRequest.toJsonArray(Collection<PromotionRequest> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static String PromotionRequest.toJsonArray(Collection<PromotionRequest> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<PromotionRequest> PromotionRequest.fromJsonArrayToPromotionRequests(String json) {
        return new JSONDeserializer<List<PromotionRequest>>().use(null, ArrayList.class).use("values", PromotionRequest.class).deserialize(json);
    }
    
}
