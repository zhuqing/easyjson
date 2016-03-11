package com.leqienglish.annotation.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassUtil {
	public static List<Field> getFields(Class claz){
		List<Field> fieldList = new ArrayList<Field>();
		getFields(claz,fieldList);
		return fieldList;
		
	}
	
	public static void getFields(Class claz , List<Field> fieldList){
	
		fieldList.addAll(Arrays.asList(claz.getDeclaredFields()));
		if(!claz.getSuperclass().equals(Object.class)){
			getFields(claz.getSuperclass(),fieldList);
		}
	}
}
