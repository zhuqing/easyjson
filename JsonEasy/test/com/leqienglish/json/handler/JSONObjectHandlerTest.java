/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import com.leqienglish.date.DateUtil;
import com.leqienglish.entity.GradeEnum;
import com.leqienglish.entity.Person;
import java.util.Calendar;
import java.util.Date;
import junit.framework.Assert;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zhuleqi
 */
public class JSONObjectHandlerTest  extends JsonEasyTest{

    public JSONObjectHandlerTest() {
    }

    @Before
    public void setUp() {
    }
    
      @Test
    public void testToDate() {
        String format = "yyyy-MM-dd hh:mm:ss";
         JSONObjectHandler handler = new JSONObjectHandler();
        Calendar date = Calendar.getInstance();
        String dateStr = DateUtil.formatDate(date, format);
      
        JSONObject jsonObject = handler.toJSON(date);
        System.err.println(jsonObject);
        this.testClaz(date, jsonObject);
        this.testValue(dateStr, jsonObject);
        this.testValue("format", format, jsonObject);
    }

    @Test
    public void testToJSONSimpleClass() {
        JSONObjectHandler handler = new JSONObjectHandler();
        Integer number = 100;
        JSONObject jsonObject = handler.toJSON(number);
        System.err.println(jsonObject.toString());
        Assert.assertEquals("java.lang.Integer", jsonObject.getString("claz"));
        Assert.assertEquals("100", jsonObject.getString("value"));
    }
    
      @Test
    public void testToJSONEnum() {
        JSONObjectHandler handler = new JSONObjectHandler();
        
        JSONObject jsonObject = handler.toJSON(GradeEnum.FIRST);
        System.err.println(jsonObject.toString());
        Assert.assertEquals("com.leqienglish.entity.GradeEnum", jsonObject.getString("claz"));
        Assert.assertEquals("FIRST", jsonObject.getString("value"));
    }
    
          @Test
    public void testToJSONPersonEnum() {
        JSONObjectHandler handler = new JSONObjectHandler();
         Person person = new Person();
        person.setId(1000);
        person.setGrade(GradeEnum.FIRST);
        JSONObject jsonObject = handler.toJSON(person);
        System.err.println(jsonObject.toString());
        this.testClaz(person, jsonObject);
       
        Person person2 = (Person) handler.toObject(jsonObject);
        
        Assert.assertEquals(person.getGrade(), person2.getGrade());
         Assert.assertEquals(person.getId(), person2.getId());
    }

    @Test
    public void testToJSON() {
        JSONObjectHandler handler = new JSONObjectHandler();
        Person person = new Person();
        person.setId(1000);
        Date date = new Date();
        person.setBirthDay(date);
        JSONObject jsonObject = handler.toJSON(person);
        System.err.println(jsonObject.toString());
        Assert.assertEquals("com.leqienglish.entity.Person", jsonObject.getString("claz"));
        JSONObject child = jsonObject.getJSONObject("value");
        Assert.assertNotNull(child);
        Assert.assertEquals("1000", child.getString("id"));
    }

    @Test
    public void testToObject() {
        String json = "{\"claz\":\"java.lang.Integer\",\"value\":\"100\"}";
        JSONObjectHandler handler = new JSONObjectHandler();
        JSONObject jsonObject = JSONObject.fromObject(json);
        Integer number = (Integer) handler.toObject(jsonObject);
        Assert.assertEquals(100, number.intValue());
    }

}
