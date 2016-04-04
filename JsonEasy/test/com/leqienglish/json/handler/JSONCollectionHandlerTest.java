/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import com.leqienglish.entity.Person;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class JSONCollectionHandlerTest extends JsonEasyTest {

    public JSONCollectionHandlerTest() {
    }

    @Before
    public void setUp() {
    }

      @Test
    public void testToJSON() {
        JSONCollectionHandler handler = new JSONCollectionHandler();
        List<String> list = new ArrayList<String>();
        list.add("1111");
        list.add("22222");
        JSONObject jsonObject = handler.toJSON(list);
        System.err.println(jsonObject);
        this.testClaz(list, jsonObject);
        JSONArray array = jsonObject.getJSONArray("value");
        Assert.assertEquals(2, array.size());
    }

    private Person createPerson(Integer id, String name) {
        Person p = new Person();
        p.setId(id);
        p.setName(name);
        p.setBirthDay(new Date());
        return p;
    }

     @Test
    public void testToPerson() {
        JSONCollectionHandler handler = new JSONCollectionHandler();
        List list = new ArrayList();
        list.add(this.createPerson(1, "张三"));
        list.add(this.createPerson(2, "李四"));
        list.add("test");
        JSONObject jsonObject = handler.toJSON(list);
        System.err.println(jsonObject);
        this.testClaz(list, jsonObject);
        JSONArray array = jsonObject.getJSONArray("value");
        Assert.assertEquals(list.size(), array.size());
    }

   @Test
    public void testToObject() {
        String json = "{\"claz\":\"java.util.ArrayList\",\"value\":[{\"claz\":\"java.lang.String\",\"value\":\"1111\"},{\"claz\":\"java.lang.String\",\"value\":\"22222\"}]}";
        JSONCollectionHandler<List> handler = new JSONCollectionHandler<List>();
        List list = handler.toObject(this.getJSONObject(json));
        Assert.assertEquals(2, list.size());
        System.err.println(list);
        Assert.assertEquals("1111", list.get(0));
        Assert.assertEquals("22222", list.get(1));
    }
    
       @Test
    public void testToEmpty() {
        String json = "{\"claz\":\"java.util.ArrayList\",\"value\":[]}";
        JSONCollectionHandler<List> handler = new JSONCollectionHandler<List>();
        List list = handler.toObject(this.getJSONObject(json));
        Assert.assertEquals(0, list.size());
        
    }
    @Test
    public void testToPersonObject() {
        String json = "{\"claz\":\"java.util.ArrayList\",\"value\":[{\"claz\":\"com.leqienglish.entity.Person\",\"value\":{\"id\":\"1\",\"birthDate\":{\"claz\":\"java.util.Date\",\"format\":\"yyyy-MM-dd hh:mm:ss\",\"value\":\"2016-04-04 08:35:09\"},\"name\":\"张三\"}},{\"claz\":\"com.leqienglish.entity.Person\",\"value\":{\"id\":\"2\",\"birthDate\":{\"claz\":\"java.util.Date\",\"format\":\"yyyy-MM-dd hh:mm:ss\",\"value\":\"2016-04-04 08:35:09\"},\"name\":\"李四\"}},{\"claz\":\"java.lang.String\",\"value\":\"test\"}]}";
        JSONCollectionHandler<List> handler = new JSONCollectionHandler<List>();
        List list = handler.toObject(this.getJSONObject(json));
        Assert.assertEquals(3, list.size());
        System.err.println(list);
        Person person = (Person) list.get(0);
        Assert.assertEquals(1, person.getId().intValue());
        Assert.assertEquals("张三", person.getName());
        person = (Person) list.get(1);
        Assert.assertEquals(2, person.getId().intValue());
        Assert.assertEquals("李四", person.getName());
        String test = (String) list.get(2);
        Assert.assertEquals("test", test);
    }

}
