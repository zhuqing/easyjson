/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author zhuleqi
 */
public class JSONArrayHandlerTest extends JsonEasyTest {

    public JSONArrayHandlerTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testToJSON() throws ClassNotFoundException {
        Integer[] arr = {1, 22, 33, 44};
        JSONArrayHandler handler = new JSONArrayHandler();
        JSONObject json = handler.toJSON(arr);
        this.testClaz(arr, json);
        this.printf(json);
        JSONArray jsonArray = json.getJSONArray("value");
        Assert.assertEquals(arr.length, jsonArray.size());

    }

    @Test
    public void testToObject() {

        String json = "{\"claz\":\"[Ljava.lang.Integer;\",\"value\":[{\"claz\":\"java.lang.Integer\",\"value\":\"1\"},{\"claz\":\"java.lang.Integer\",\"value\":\"22\"},{\"claz\":\"java.lang.Integer\",\"value\":\"33\"},{\"claz\":\"java.lang.Integer\",\"value\":\"44\"}]}";
        JSONArrayHandler< Integer[]> handler = new JSONArrayHandler<Integer[]>();
        Integer[] arr = handler.toObject(this.getJSONObject(json));
        Assert.assertEquals(4, arr.length);
        Assert.assertEquals(1, arr[0].intValue());
        Assert.assertEquals(22, arr[1].intValue());
        Assert.assertEquals(33, arr[2].intValue());
        Assert.assertEquals(44, arr[3].intValue());

    }

}
