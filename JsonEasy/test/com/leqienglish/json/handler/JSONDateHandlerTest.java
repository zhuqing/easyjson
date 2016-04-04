/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import com.leqienglish.date.DateUtil;
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
public class JSONDateHandlerTest extends JsonEasyTest {

    public JSONDateHandlerTest() {
    }

    @Before
    public void setUp() {
    }
    
       @Test
    public void testToCalendarJSON() {
       
        String format = "yyyy-MM-dd";
         JSONDateHandler handler = new JSONDateHandler(format);
        Calendar date = Calendar.getInstance();
        String dateStr = DateUtil.formatDate(date, format);
      
        JSONObject jsonObject = handler.toJSON(date);
        System.err.println(jsonObject);
        this.testClaz(date, jsonObject);
        this.testValue(dateStr, jsonObject);
        this.testValue("format", format, jsonObject);

    }
    
       @Test
    public void testToNull() {
       
        String format = "yyyy-MM-dd";
         JSONDateHandler handler = new JSONDateHandler(format);
       
      
        JSONObject jsonObject = handler.toJSON(null);
        System.err.println(jsonObject);
      
        Assert.assertNull(jsonObject);

    }

    @Test
    public void testToJSON() {
       
        String format = "yyyy-MM-dd hh:mm:ss";
         JSONDateHandler handler = new JSONDateHandler(format);
        Date date = new Date();
        String dateStr = DateUtil.formatDate(date, format);
      
        JSONObject jsonObject = handler.toJSON(date);
        System.err.println(jsonObject);
        this.testClaz(date, jsonObject);
        this.testValue(dateStr, jsonObject);
        this.testValue("format", format, jsonObject);

    }

    @Test
    public void testToDate() {
        String json = "{\"claz\":\"java.util.Date\",\"format\":\"yyyy-MM-dd hh:mm:ss\",\"value\":\"2016-04-03 09:10:22\"}";
         String format = "yyyy-MM-dd hh:mm:ss";
         JSONDateHandler<Date> handler = new JSONDateHandler<Date>(format);
         Date date = handler.toObject(this.getJSONObject(json));
         Assert.assertEquals(2016, date.getYear()+1900);
         Assert.assertEquals(4, date.getMonth()+1);
         Assert.assertEquals(3, date.getDate());
         Assert.assertEquals(9, date.getHours());
         Assert.assertEquals(10, date.getMinutes());
    }
    
     @Test
    public void testToCalendar() {
        String json = "{\"claz\":\"java.util.Calendar\",\"format\":\"yyyy-MM-dd hh:mm:ss\",\"value\":\"2016-04-03 09:10:22\"}";
         String format = "yyyy-MM-dd hh:mm:ss";
         JSONDateHandler<Calendar> handler = new JSONDateHandler<Calendar>(format);
         Calendar date = handler.toObject(this.getJSONObject(json));
         Assert.assertEquals(2016, date.get(Calendar.YEAR));
         Assert.assertEquals(4,  date.get(Calendar.MONTH)+1);
         Assert.assertEquals(3,  date.get(Calendar.DAY_OF_MONTH));
         Assert.assertEquals(9,  date.get(Calendar.HOUR_OF_DAY));
         Assert.assertEquals(10,  date.get(Calendar.MINUTE));
    }

    @Test
    public void testGetDateFormat() {
    }

    @Test
    public void testSetDateFormat() {
    }

}
