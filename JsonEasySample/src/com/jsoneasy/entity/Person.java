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
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.birthDay, other.birthDay)) {
            return false;
        }
        if (!Objects.equals(this.friend, other.friend)) {
            return false;
        }
        return true;
    }
    
    
}
