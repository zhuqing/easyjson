/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoneasy.entity;

import com.leqienglish.annotation.JSON;
import com.leqienglish.annotation.JSONDate;
import com.leqienglish.annotation.JSONInner;
import java.util.Date;

/**
 *
 * @author zhuleqi
 */
public class Person {
    @JSON(name="id")
    private Integer id;
    @JSONDate(name="birthDate")
    private Date birthDay;
    @JSONInner(name="friend")
    private Person friend;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the birthDay
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * @param birthDay the birthDay to set
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * @return the friend
     */
    public Person getFriend() {
        return friend;
    }

    /**
     * @param friend the friend to set
     */
    public void setFriend(Person friend) {
        this.friend = friend;
    }
    
    
}
