// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.entity;

import com.happyhour.entity.PromotionInstance;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect PromotionInstance_Roo_Json {
    
    public String PromotionInstance.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public String PromotionInstance.toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }
    
    public static PromotionInstance PromotionInstance.fromJsonToPromotionInstance(String json) {
        return new JSONDeserializer<PromotionInstance>().use(null, PromotionInstance.class).deserialize(json);
    }
    
    public static String PromotionInstance.toJsonArray(Collection<PromotionInstance> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static String PromotionInstance.toJsonArray(Collection<PromotionInstance> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<PromotionInstance> PromotionInstance.fromJsonArrayToPromotionInstances(String json) {
        return new JSONDeserializer<List<PromotionInstance>>().use(null, ArrayList.class).use("values", PromotionInstance.class).deserialize(json);
    }
    
}
