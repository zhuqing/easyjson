/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 处理map类型的数据
 *
 * @author zhuleqi
 */
public class JSONMapHandler<T extends Map> extends JSONHandler<T> {

    private static String KEY = "key";

    @Override
    public JSONObject toJSON(T t) {
        JSONObject jsonObject = this.getJSONObject(t);
        jsonObject.put(this.getValueKey(), this.createJSONArray(t));

        return jsonObject;

    }

    private JSONArray createJSONArray(T t) {
        JSONArray jsonArray = new JSONArray();
        Set keySet = t.keySet();
        for (Object key : keySet) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(KEY, this.createJSONObject(key));
            jsonObject.put(this.getValueKey(), this.createJSONObject(t.get(key)));
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    @Override
    public T toObject(JSONObject jsonObject) {
        try {
            T t = (T) this.getObject(jsonObject);
            JSONArray array = jsonObject.getJSONArray(this.getValueKey());
            for(int i = 0 ; i < array.size() ; i++){
                JSONObject item = array.getJSONObject(i);

                Object key = this.getJsonManager().getJsonObjectHandler().toObject(item.getJSONObject(KEY));
                Object value = this.getJsonManager().getJsonObjectHandler().toObject(item.getJSONObject(this.getValueKey()));
                t.put(key, value);
                
            }
            
            return t;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JSONMapHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JSONMapHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JSONMapHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

}
