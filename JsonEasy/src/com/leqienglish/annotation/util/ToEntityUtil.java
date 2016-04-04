//package com.leqienglish.annotation.util;
//
//import com.leqienglish.json.JSON;
//import com.leqienglish.json.annotation.JSONDate;
//import com.leqienglish.json.annotation.JSONInner;
//import com.leqienglish.date.DateUtil;
//import com.leqienglish.property.PropertyUtil;
//import java.lang.reflect.Field;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import net.sf.json.JSONObject;
//
///**
// * json与实体间的相互转换 不支持嵌套json 与嵌套实体 被转换的实体必须有set，get方法
// *
// * @author guona
// *
// */
//public class ToEntityUtil {
//
//    private final static String claz = "claz";
//
//    /**
//     * 通过JSON字符串获取对象
//     *
//     * @param jsonStr
//     * @return
//     * @throws Exception
//     */
//    public static Object toObject(String jsonStr) throws Exception {
//        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
//        String classStr = jsonObject.getString(claz);
//        Class clasz = Class.forName(classStr);
//        return toObject(jsonObject, clasz);
//    }
//
//    public static <T> T toObject(JSONObject jsonObject) throws Exception {
//        String classStr = jsonObject.getString(claz);
//        if (classStr == null) {
//            throw new UnsupportedOperationException("没找到class项");
//        }
//
//        Class<T> claz = (Class<T>) Class.forName(classStr);
//
//        return (T) toObject(jsonObject, claz);
//    }
//
//    public static <T> T toObject(JSONObject jsonObject, Class<T> clazT) throws Exception {
//        if (clazT == null) {
//            throw new UnsupportedOperationException("clazT 不能为空");
//        }
//        List<Field> fields = new ArrayList<Field>();//[] fieldArr = clazT.getDeclaredFields();
//        getAllFields(clazT, fields);
//        T t = clazT.newInstance();
//        for (Field field : fields) {
//
//            if (field.isAnnotationPresent(JSON.class)) {
//                JSON json = field.getAnnotation(JSON.class);
//                ToEntityUtil.toObject(field, t, json, jsonObject);
//                continue;
//            }
//
//            if (field.isAnnotationPresent(JSONDate.class)) {
//                JSONDate json = field.getAnnotation(JSONDate.class);
//                ToEntityUtil.toObject(field, t, json, jsonObject);
//                continue;
//            }
//
//            if (field.isAnnotationPresent(JSONInner.class)) {
//                JSONInner json = field.getAnnotation(JSONInner.class);
//                ToEntityUtil.toObject(field, t, json, jsonObject);
//                continue;
//            }
//
//        }
//        return t;
//    }
//
//    private static <T> void toObject(Field field, T t, JSONInner jsonInner, JSONObject jsonObject) throws Exception {
//        if (jsonInner == null) {
//            return;
//        }
//        if (!jsonObject.has(jsonInner.name())) {
//            return;
//        }
//
//        JSONObject jsonObjectInner = jsonObject.getJSONObject(jsonInner.name());
//        Object value = ToEntityUtil.toObject(jsonObjectInner);
//        PropertyUtil.setValue(t, field, value);
//    }
//
//    private static <T> void toObject(Field field, T t, JSONDate jsonDate, JSONObject jsonObject) throws ParseException, Exception {
//        if (jsonDate == null) {
//            return;
//        }
//        if (!jsonObject.has(jsonDate.name())) {
//            return;
//        }
//
//        JSONObject jsonObjectDate = jsonObject.getJSONObject(jsonDate.name());
//        String format = jsonObjectDate.getString("format");
//        String date = jsonObjectDate.getString("value");
//        Object value = DateUtil.toDate(date, format, field.getType());
//
//        PropertyUtil.setValue(t, field, value);
//    }
//
//    private static <T> void toObject(Field field, T t, JSON json, JSONObject jsonObject) throws Exception {
//        if (json == null) {
//            return;
//        }
//        if (!jsonObject.has(json.name())) {
//            return;
//        }
//        Object value = jsonObject.get(json.name());
//
//        PropertyUtil.setValue(t, field, ToEntityUtil.toObject(field, value.toString()));
//    }
//
//  
//    /**
//     * 将JSON转换为Object
//     *
//     * @param jsonObject
//     * @param clazT
//     * @return
//     * @throws Exception
//     */
//    public static <T> T json2Object(String jsonStr, Class<T> clazT)
//            throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.fromObject(jsonStr);
//
//        return toObject(jsonObject, clazT);
//    }
//
//    /**
//     * 转换数据类型
//     *
//     * @param field
//     * @param value
//     * @return
//     */
//  
//
//}
