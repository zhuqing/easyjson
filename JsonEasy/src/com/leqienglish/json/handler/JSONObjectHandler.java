/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import com.leqienglish.annotation.util.ClassUtil;
import com.leqienglish.json.manager.JSONHandlerManager;
import com.leqienglish.annotation.util.PropertyUtil;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;

/**
 * 出来注解为JSONClass的类
 *
 * @author zhuleqi
 */
public class JSONObjectHandler<T> extends JSONHandler<T> {

    @Override
    public JSONObject toJSON(T t) {
        if (t == null) {
            return null;
        }

        JSONObject jsonObject = this.getJSONObject(t);
        if (this.isJSONClass(t)) {

            jsonObject.put(this.getValueKey(), createJSONObject(t));

        } else {
            JSONHandler handler = this.getJsonManager().getJSONHandler(t.getClass());
            if (handler == null) {
                jsonObject.put(this.getValueKey(), t.toString());
            } else {
                return handler.toJSON(t);
            }

        }

        return jsonObject;

    }

    /**
     * 标注为JSONclass的对象转换为json
     *
     * @param t
     * @return
     * @throws java.lang.Exception
     */
    protected JSONObject createJSONObject(Object t) {
        JSONObject jsonObject = new JSONObject();
        List<Field> fieldArr = ClassUtil.getFields(t.getClass());
        for (Field field : fieldArr) {
            try {
                String jsonName = this.getJSONName(field);
                if (jsonName == null) {
                    continue;
                }
                Object value = PropertyUtil.getValue(t, field);
                if (value == null) {
                    continue;
                }
                JSONHandler handler = this.getJsonManager().getJSONHandler(field);
                if (handler == null) {
                    jsonObject.put(jsonName, value.toString());
                } else {
                    jsonObject.put(jsonName, handler.toJSON(value));
                }
            } catch (Exception ex) {
                Logger.getLogger(JSONObjectHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return jsonObject;
    }

    @Override
    public T toObject(JSONObject j) {
        if (j == null) {
            return null;
        }
        try {
            
            Object value = j.get(this.getValueKey());
            Object entity = null;
            if (value instanceof String) {
                entity = ClassUtil.toObject(this.getClass(j), value.toString());

            } else {
                entity = this.getObject(j);
                setEntityProperty(entity, (JSONObject) value);

            }
            return (T) entity;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JSONObjectHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JSONObjectHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void setEntityProperty(Object entity, JSONObject jsonObject) throws Exception {

        List<Field> fields = ClassUtil.getAllFields(entity.getClass());

        for (Field field : fields) {

            String jsonName = this.getJSONName(field);
            if (jsonName == null) {
                continue;
            }

            if (!jsonObject.has(jsonName)) {
                continue;
            }

            JSONHandler handler = this.getJsonManager().getJSONHandler(field);
            if (handler == null) {
                PropertyUtil.setValue(entity, field, ClassUtil.toObject(field.getType(), jsonObject.getString(jsonName)));
            } else {
                PropertyUtil.setValue(entity, field, handler.toObject(jsonObject.getJSONObject(jsonName)));
            }
        }
    }

}
