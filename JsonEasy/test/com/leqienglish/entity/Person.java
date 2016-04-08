/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.entity;


import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;
import com.leqienglish.json.annotation.JSONDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author zhuleqi
 */
@JSONClass()
public class Person {
    @JSON(name="id")
    private Integer id;
    @JSONDate(name="birthDate")
    private Date birthDay;
    @JSONClass(name="friend")
    private Person friend;
      @JSON(name="name")
    private String name;
    @JSON(name="familys")
    private List<Person> familys;
    @JSON(name="grade")
    private GradeEnum grade;
    

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

    /**
     * @return the familys
     */
    public List<Person> getFamilys() {
        return familys;
    }

    /**
     * @param familys the familys to set
     */
    public void setFamilys(List<Person> familys) {
        this.familys = familys;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the grade
     */
    public GradeEnum getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(GradeEnum grade) {
        this.grade = grade;
    }
    
    
}
