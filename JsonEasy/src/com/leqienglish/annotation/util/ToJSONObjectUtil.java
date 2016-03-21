/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.annotation.util;

import com.leqienglish.annotation.JSON;
import com.leqienglish.annotation.util.ClassUtil;
import com.leqienglish.property.PropertyUtil;
import java.lang.reflect.Field;
import java.util.List;
import net.sf.json.JSONObject;

/**
 *
 * @author zhuleqi
 */
public class ToJSONObjectUtil {
    
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

}
