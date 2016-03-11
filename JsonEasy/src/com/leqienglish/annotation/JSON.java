package com.leqienglish.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented 
public @interface JSON {
	/**
	 * json 的key值
	 * @return
	 */
	String name();
}
