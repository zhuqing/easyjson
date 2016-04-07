/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *出了数组类型的数据
 * @author zhuleqi
 */
public class JSONArrayHandler<T> extends JSONHandler<T>{

    @Override
    public JSONObject toJSON(T t) {
       if(t==null || !t.getClass().isArray()){
           return null;
       }
       
       JSONObject jsonObject = this.getJSONObject(t);
       
      
       jsonObject.put(this.getValueKey(), createJSONArray((Object[]) t));
       return jsonObject;
    }
    
    private JSONArray createJSONArray(Object[] coll ){
        JSONArray jsonArray = new JSONArray();
        for(Object item : coll){
            jsonArray.add(this.createJSONObject(item));
        }
        
        return jsonArray;
    }

    @Override
    public T toObject(JSONObject jsonObject) {
        try {
            JSONArray array = jsonObject.getJSONArray(this.getValueKey());
            Object[] t = (Object[]) Array.newInstance(this.getClass(jsonObject), array.size());
            
            for(int i = 0 ; i< array.size() ; i++){
                t[i] = this.getJsonManager().getJsonObjectHandler().toObject(array.getJSONObject(i));
            }
            
            return (T) t;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JSONArrayHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
