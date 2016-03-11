package com.leqienglish.annotation.util;

import com.leqienglish.annotation.JSON;
import com.leqienglish.property.PropertyUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.activation.FileDataSource;

import net.sf.json.JSONObject;

/**
 * json与实体间的相互转换 不支持嵌套json 与嵌套实体 被转换的实体必须有set，get方法
 *
 * @author guona
 *
 */
public class EntityAndJSON {

    private final static String claz = "claz";

    /**
     * 将实体转换为json字符串
     *
     * @param t
     * @return
     * @throws Exception
     */
    public static <T> String toJSON(T t) throws Exception {
        JSONObject jsonObject = toJSONObject(t);
        return jsonObject.toString();
    }

    /**
     * 将实体转换为json字符串
     *
     * @param t
     * @return
     * @throws Exception
     */
    public static <T> JSONObject toJSONObject(T t) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(claz, t.getClass().getName());
        List<Field> fieldArr = ClassUtil.getFields(t.getClass());
        for (Field field : fieldArr) {
            JSON json = field.getAnnotation(JSON.class);
            if (json != null) {
                Object value = PropertyUtil.getValue(t, field);
                jsonObject.put(json.name(), value);
            }
        }
        return jsonObject;
    }

    /**
     * 通过JSON字符串获取对象
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    public static Object json2Object(String jsonStr) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String classStr = jsonObject.getString(claz);
        Class clasz = Class.forName(classStr);
        return json2Object(jsonObject, clasz);
    }

    public static <T> T json2Object(JSONObject jsonObject) throws Exception {
        String classStr = jsonObject.getString(claz);
        if (classStr == null) {
            throw new UnsupportedOperationException("没找到class项");
        }

        Class<T> claz = (Class<T>) Class.forName(classStr);

        return (T) json2Object(jsonObject, claz);
    }

    public static <T> T json2Object(JSONObject jsonObject, Class<T> clazT) throws Exception {
        if (clazT == null) {
            throw new UnsupportedOperationException("clazT 不能为空");
        }
        List<Field> fields = new ArrayList<Field>();//[] fieldArr = clazT.getDeclaredFields();
        getAllFields(clazT,fields);
        T t = clazT.newInstance();
        for (Field field : fields) {
            JSON json = field.getAnnotation(JSON.class);
            if (json == null) {
                continue;
            }
            if (!jsonObject.has(json.name())) {
                continue;
            }
            Object value = jsonObject.get(json.name());

            PropertyUtil.setValue(t, field, toObject(field, value.toString()));

        }
        return t;
    }
    
    private static void getAllFields(Class claz , List<Field> fields){
        fields.addAll(Arrays.asList(claz.getDeclaredFields()));
        Class clazSuper = claz.getSuperclass();
        if(clazSuper.equals(Object.class)){
            return;
        }
        getAllFields(clazSuper,fields);
    }

    /**
     * 将JSON转换为Object
     *
     * @param jsonObject
     * @param clazT
     * @return
     * @throws Exception
     */
    public static <T> T json2Object(String jsonStr, Class<T> clazT)
            throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.fromObject(jsonStr);

        return json2Object(jsonObject, clazT);
    }

  
  

   


    /**
     * 转换数据类型
     *
     * @param field
     * @param value
     * @return
     */
    private static Object toObject(Field field, String value) {
        if (value == null) {
            return null;
        }
        String typeName = field.getType().getSimpleName();
        if (typeName.equals("int") || typeName.equals("Integer")) {
            return Integer.valueOf(value);
        }
        if (typeName.equals("double") || typeName.equals("Double")) {
            return Double.valueOf(value);
        }
        if (typeName.equals("float") || typeName.equals("Float")) {
            return Float.valueOf(value);
        }

        if (typeName.equals("long") || typeName.equals("Long")) {
            return Long.valueOf(value);
        }

        return value;
    }

}
