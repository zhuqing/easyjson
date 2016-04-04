///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.leqienglish.annotation.util;
//
//import com.leqienglish.json.JSON;
//import com.leqienglish.json.annotation.JSONDate;
//import com.leqienglish.json.annotation.JSONInner;
//import com.leqienglish.annotation.util.ClassUtil;
//import com.leqienglish.date.DateUtil;
//import com.leqienglish.property.PropertyUtil;
//import java.lang.reflect.Field;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
///**
// *
// * @author zhuleqi
// */
//public class ToJSONObjectUtil {
//
//    private final static String claz = "claz";
//    private final static String mapClass = "mapClaz";
//
//    /**
//     * 将实体转换为json字符串
//     *
//     * @param t
//     * @return
//     * @throws Exception
//     */
//    public static <T> String toJSON(T t) throws Exception {
//        JSONObject jsonObject = toJSONObject(t);
//        return jsonObject.toString();
//    }
//
//    /**
//     * 将实体转换为json字符串
//     *
//     * @param t
//     * @return
//     * @throws Exception
//     */
//    public static <T> JSONObject toJSONObject(T t) throws Exception {
//        if (t == null) {
//            return null;
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(claz, t.getClass().getName());
//        List<Field> fieldArr = ClassUtil.getFields(t.getClass());
//        for (Field field : fieldArr) {
//            toJSONObject(jsonObject, field, t);
//        }
//        return jsonObject;
//    }
//
//    /**
//     *
//     * @param <T>
//     * @param jsonObject
//     * @param field
//     * @param t
//     * @throws Exception
//     */
//    private static <T> void toJSONObject(JSONObject jsonObject, Field field, T t) throws Exception {
//        if (field.isAnnotationPresent(JSON.class)) {
//            JSON json = field.getAnnotation(JSON.class);
//            toJSONObject(jsonObject, json, field, t);
//            return;
//        }
//
//        if (field.isAnnotationPresent(JSONDate.class)) {
//            JSONDate jsonDate = field.getAnnotation(JSONDate.class);
//            toJSONObject(jsonObject, jsonDate, field, t);
//        }
//
//        if (field.isAnnotationPresent(JSONInner.class)) {
//            JSONInner jsonInner = field.getAnnotation(JSONInner.class);
//            toJSONObject(jsonObject, jsonInner, field, t);
//        }
//    }
//
//    private static <T> void toJSONObject(JSONObject jsonObject, JSONInner jsonInner, Field field, T t) throws Exception {
//        if (jsonInner == null) {
//            return;
//        }
//
//        Object value = PropertyUtil.getValue(t, field);
//        if (value == null) {
//            return;
//        }
//
//        if (value instanceof Collection) {
//             jsonObject.put(jsonInner.name(),toJSONArray((Collection) value));
//        }else if(value instanceof Map ){
//            
//        }
//
//        jsonObject.put(jsonInner.name(), toJSONObject(value));
//    }
//    
//    private static JSONObject toJSONObject(Map map){
//        JSONObject mapJson = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//        mapJson.put("mapClass", map.getClass().getName());
//        for(Object key : map.keySet()){
//            JSONObject mapJsonObject = new JSONObject();
//            
//        }
//        
//        return mapJson;
//    }
//    
//    private static JSONArray toJSONArray(Collection col) throws Exception{
//        JSONArray jsonArray = new JSONArray();
//        for(Object item : col){
//            jsonArray.add(toJSONObject(item));
//        }
//        
//        return jsonArray;
//    }
//
//    /**
//     * JSON的日期格式
//     *
//     * @param <T>
//     * @param jsonObject
//     * @param jsonDate
//     * @param field
//     * @param t
//     * @throws Exception
//     */
//    private static <T> void toJSONObject(JSONObject jsonObject, JSONDate jsonDate, Field field, T t) throws Exception {
//        if (jsonDate == null || t == null) {
//            return;
//        }
//        Object value = PropertyUtil.getValue(t, field);
//        String dateStr = DateUtil.formaterDate(value, jsonDate.format());
//        JSONObject dateJsonObject = new JSONObject();
//        dateJsonObject.put("format", jsonDate.format());
//        dateJsonObject.put("value", dateStr);
//        jsonObject.put(jsonDate.name(), dateJsonObject);
//    }
//
//    /**
//     * 转换普通的json
//     *
//     * @param <T>
//     * @param jsonObject
//     * @param json
//     * @param field
//     * @param t
//     * @throws Exception
//     */
//    private static <T> void toJSONObject(JSONObject jsonObject, JSON json, Field field, T t) throws Exception {
//        if (json == null) {
//            return;
//        }
//
//        Object value = PropertyUtil.getValue(t, field);
//        jsonObject.put(json.name(), value);
//    }
//
//}
