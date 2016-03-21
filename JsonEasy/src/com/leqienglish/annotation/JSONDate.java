/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *日期类型数据的标注
 * @author zhuleqi
 */
@Target(ElementType.FIELD) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented 
public @interface JSONDate {
    	/**
	 * json 的key值
	 * @return
	 */
	String name();
        String format() default "yyyy-MM-dd hh:mm:ss";
}
