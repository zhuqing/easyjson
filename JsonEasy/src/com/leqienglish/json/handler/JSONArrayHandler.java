/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import net.sf.json.JSONObject;

/**
 *出了数组类型的数据
 * @author zhuleqi
 */
public class JSONArrayHandler<T> extends JSONHandler<T>{

    @Override
    public JSONObject toJSON(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T toObject(JSONObject jsonObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
