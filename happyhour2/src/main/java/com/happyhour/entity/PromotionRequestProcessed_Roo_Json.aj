// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.PromotionRequestProcessed;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect PromotionRequestProcessed_Roo_Json {
    
    public String PromotionRequestProcessed.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public String PromotionRequestProcessed.toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }
    
    public static PromotionRequestProcessed PromotionRequestProcessed.fromJsonToPromotionRequestProcessed(String json) {
        return new JSONDeserializer<PromotionRequestProcessed>().use(null, PromotionRequestProcessed.class).deserialize(json);
    }
    
    public static String PromotionRequestProcessed.toJsonArray(Collection<PromotionRequestProcessed> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static String PromotionRequestProcessed.toJsonArray(Collection<PromotionRequestProcessed> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<PromotionRequestProcessed> PromotionRequestProcessed.fromJsonArrayToPromotionRequestProcesseds(String json) {
        return new JSONDeserializer<List<PromotionRequestProcessed>>().use(null, ArrayList.class).use("values", PromotionRequestProcessed.class).deserialize(json);
    }
    
}