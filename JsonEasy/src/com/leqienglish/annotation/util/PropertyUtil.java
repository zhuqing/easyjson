/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leqienglish.annotation.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guona
 */
public class PropertyUtil {
    public static Object getValue(Object obj , String propertyName){
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);//.getField(propertyName);
            
            return getValue(obj,field);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(PropertyUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PropertyUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PropertyUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
  
    
     /**
     * 获取该Field的值
     *
     * @param t
     * @param field
     * @return
     * @throws Exception
     */
    public static <T> Object getValue(T t, Field field) throws Exception {
        Method method = getGetMethod(field.getName(), t.getClass());
        if (method == null) {
            throw new Exception("没有field.getName()的get方法");
        }
        return method.invoke(t);
    }
    
    
    /**
     * 调用Set方法将值set到T中
     *
     * @param t
     * @param field
     * @param value
     * @throws Exception
     */
    public static <T> void setValue(T t, Field field, Object value)
            throws Exception {
        Method method = getSetMethod(field, t.getClass());
        if (method == null) {
            throw new Exception("没有field.getName()的set方法");
        }
        method.invoke(t, value);
    }
    
      /**
     * 获取Set方法
     *
     * @param filedName
     * @param claz
     * @return
     * @throws Exception
     */
    private static Method getSetMethod(Field field, Class claz)
            throws Exception {
        return claz.getMethod(createMethodName("set", field.getName()),
                field.getType());
    }

    
      /**
     * 获取get方法
     *
     * @param fileName
     * @param claz
     * @return
     * @throws Exception
     */
    private static Method getGetMethod(String filedName, Class claz)
            throws Exception {

        return claz.getMethod(createMethodName("get", filedName));
    }

    /**
     * 生成方法名称
     *
     * @param startWith
     * @param fieldName
     * @return
     */
    private static String createMethodName(String startWith, String fieldName) {
        String methodName = startWith + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1, fieldName.length());
        return methodName;
    }
}
