/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zhuleqi
 */
public class JSONMapHandlerTest  extends JsonEasyTest{
    
    public JSONMapHandlerTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testToJSON() {
        Map map = new HashMap();
        map.put("1", "1111");
        map.put("2", "2222");
        JSONMapHandler jsonMapHandler = new JSONMapHandler();
        JSONObject jsonObject = jsonMapHandler.toJSON(map);
        this.testClaz(map, jsonObject);
        System.err.println(jsonObject);
        JSONArray jsonArray = jsonObject.getJSONArray("value");
        Assert.assertEquals(2, jsonArray.size());
        
    }

    @Test
    public void testToObject() {
        String json = "{\"claz\":\"java.util.HashMap\",\"value\":[{\"key\":{\"claz\":\"java.lang.String\",\"value\":\"1\"},\"value\":{\"claz\":\"java.lang.String\",\"value\":\"1111\"}},{\"key\":{\"claz\":\"java.lang.String\",\"value\":\"2\"},\"value\":{\"claz\":\"java.lang.String\",\"value\":\"2222\"}}]}";
         JSONMapHandler<HashMap> jsonMapHandler = new JSONMapHandler<HashMap>();
         Map map = jsonMapHandler.toObject(this.getJSONObject(json));
         Assert.assertEquals(2, map.size());
         Assert.assertEquals("1111", map.get("1"));
         Assert.assertEquals("2222", map.get("2"));
    }
    
}
