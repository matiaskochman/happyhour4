// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.happyhour.json;

import com.happyhour.json.JsonRequestPromotionInstance;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect JsonRequestPromotionInstance_Roo_Json {
    
    public String JsonRequestPromotionInstance.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public String JsonRequestPromotionInstance.toJson(String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
    }
    
    public static JsonRequestPromotionInstance JsonRequestPromotionInstance.fromJsonToJsonRequestPromotionInstance(String json) {
        return new JSONDeserializer<JsonRequestPromotionInstance>().use(null, JsonRequestPromotionInstance.class).deserialize(json);
    }
    
    public static String JsonRequestPromotionInstance.toJsonArray(Collection<JsonRequestPromotionInstance> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static String JsonRequestPromotionInstance.toJsonArray(Collection<JsonRequestPromotionInstance> collection, String[] fields) {
        return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<JsonRequestPromotionInstance> JsonRequestPromotionInstance.fromJsonArrayToJsonRequestPromotionInstances(String json) {
        return new JSONDeserializer<List<JsonRequestPromotionInstance>>().use(null, ArrayList.class).use("values", JsonRequestPromotionInstance.class).deserialize(json);
    }
    
}
