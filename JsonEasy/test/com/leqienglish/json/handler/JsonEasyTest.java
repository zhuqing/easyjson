/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import junit.framework.Assert;
import net.sf.json.JSONObject;

/**
 *
 * @author zhuleqi
 */
public class JsonEasyTest {

    public void testClaz(Object value, JSONObject jsonObject) {
        Assert.assertEquals(value.getClass().getName(), jsonObject.getString("claz"));
    }

    public void testValue(String value, JSONObject jsonObject) {
        Assert.assertEquals(value, jsonObject.getString("value"));
    }
    public void testValue(String key , String value, JSONObject jsonObject) {
        Assert.assertEquals(value, jsonObject.getString(key));
    }
    
    public JSONObject getJSONObject(String json){
        return JSONObject.fromObject(json);
    }
        
}
