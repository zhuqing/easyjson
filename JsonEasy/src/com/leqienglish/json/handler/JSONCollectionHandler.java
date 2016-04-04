/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import com.leqienglish.annotation.util.ClassUtil;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * json转换为集合, 集合转换为JSON
 *
 * @author zhuleqi
 */
public class JSONCollectionHandler<T extends Collection> extends JSONHandler<T> {

    @Override
    public JSONObject toJSON(T t) {
        JSONObject jsonObject = this.getJSONObject(t);
        jsonObject.put(this.getValueKey(), getValueJSON(t));

        return jsonObject;
    }

    private JSONArray getValueJSON(Collection t) {
        JSONArray jsonArray = new JSONArray();

        for (Object value : t) {
            JSONHandler handler = this.getJsonManager().getJSONHandler(value.getClass());
            if (handler != null) {
                jsonArray.add(handler.toJSON(value));
            } else {
                jsonArray.add(this.getJsonManager().getJsonObjectHandler().toJSON(value));
            }

        }

        return jsonArray;
    }

    @Override
    public T toObject(JSONObject jsonObject) {
        try {
            T c = (T) this.getObject(jsonObject);
            JSONArray jsonArray = jsonObject.getJSONArray(this.getValueKey());
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                
                Object value = json.get(this.getValueKey());
                Class claz = this.getClass(json);
                
                if (value instanceof JSONObject) {
                    JSONHandler handler = this.getJsonManager().getJSONHandler(claz);
                    c.add(handler.toObject(json));
                } else {

                    c.add(ClassUtil.toObject(claz, value.toString()));
                }
            }

            return c;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JSONCollectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JSONCollectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JSONCollectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
