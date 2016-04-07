/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import com.leqienglish.annotation.util.ClassUtil;
import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;
import com.leqienglish.json.annotation.JSONDate;
import com.leqienglish.json.manager.JSONHandlerManager;
import java.lang.reflect.Field;

import net.sf.json.JSONObject;

/**
 *
 * @author zhuleqi
 */
public abstract class JSONHandler<T> {

    private static final String clazKey = "claz";
    private static final String valueKey = "value";

    private JSONHandlerManager jsonManager;

    public abstract JSONObject toJSON(T t);

    public abstract T toObject(JSONObject jsonObject);

    protected JSONObject getJSONObject(T t) {
        if (t == null) {
            return null;
        }
        JSONObject json = new JSONObject();
        json.put(clazKey, t.getClass().getName());
        return json;
    }

    protected Class getClass(JSONObject json) throws ClassNotFoundException {
        String clazName = json.getString(clazKey);

        return ClassUtil.getSimpleClass(clazName);
    }

    protected Object getObject(JSONObject json) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String clazName = json.getString(clazKey);

        return ClassUtil.getObject(clazName);
    }

    protected JSONObject createJSONObject(Object value) {

        return this.getJsonManager().getJsonObjectHandler().toJSON(value);

    }

    protected Boolean isJSONClass(T t) {
        return t.getClass().isAnnotationPresent(JSONClass.class);
    }

    protected String getValueKey() {
        return valueKey;
    }

    public String getJSONName(Field field) {
        if (field.isAnnotationPresent(JSONClass.class)) {
            JSONClass jsonClass = field.getAnnotation(JSONClass.class);
            return jsonClass.name();
        }

        if (field.isAnnotationPresent(JSONDate.class)) {
            JSONDate jsonDate = field.getAnnotation(JSONDate.class);
            return jsonDate.name();
        }

        if (field.isAnnotationPresent(JSON.class)) {
            JSON json = field.getAnnotation(JSON.class);
            return json.name();
        }

        return null;
    }

    /**
     * @return the jsonManager
     */
    public JSONHandlerManager getJsonManager() {
        if (jsonManager == null) {
            this.jsonManager = JSONHandlerManager.newInstance();
        }
        return jsonManager;
    }
}
