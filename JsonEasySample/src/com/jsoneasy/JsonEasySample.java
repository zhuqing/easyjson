/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoneasy;

import com.jsoneasy.entity.Person;
import com.leqienglish.annotation.util.ToEntityUtil;
import com.leqienglish.annotation.util.ToJSONObjectUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger; 

/**
 *
 * @author zhuleqi
 */
public class JsonEasySample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Person person = new Person();
        person.setId(100);
        person.setBirthDay(Calendar.getInstance().getTime());
        
        Person firend = new Person();
        firend.setId(10000);
        firend.setBirthDay(Calendar.getInstance().getTime());
        person.setFriend(firend);
          
        try {
            String jsonStr = ToJSONObjectUtil.toJSON(person);
            System.out.println(jsonStr);
            Person taPerson = (Person) ToEntityUtil.toObject(jsonStr);
            assert(taPerson.equals(person));
        } catch (Exception ex) {
            Logger.getLogger(JsonEasySample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
