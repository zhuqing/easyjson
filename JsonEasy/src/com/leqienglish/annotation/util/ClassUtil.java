package com.leqienglish.annotation.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassUtil {

    public static List<Field> getAllFields(Class claz) {
        List<Field> fields = new ArrayList<Field>();
        getAllFields(claz, fields);

        return fields;
    }

    private static void getAllFields(Class claz, List<Field> fields) {
        fields.addAll(Arrays.asList(claz.getDeclaredFields()));
        Class clazSuper = claz.getSuperclass();
        if (clazSuper.equals(Object.class)) {
            return;
        }
        getAllFields(clazSuper, fields);
    }

    public static List<Field> getFields(Class claz) {
        List<Field> fieldList = new ArrayList<Field>();
        getFields(claz, fieldList);
        return fieldList;

    }

    private static void getFields(Class claz, List<Field> fieldList) {

        fieldList.addAll(Arrays.asList(claz.getDeclaredFields()));
        if (!claz.getSuperclass().equals(Object.class)) {
            getFields(claz.getSuperclass(), fieldList);
        }
    }

    public static Class getClass(String claz) throws ClassNotFoundException {
        return Class.forName(claz);
    }

    public static Class getSimpleClass(String claz) throws ClassNotFoundException {
        if (claz.contains("[")) {
            claz = claz.substring(2, claz.length() - 1);
        }

        return Class.forName(claz);
    }

    public static Object getObject(String claz) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clazz = getClass(claz);
        return clazz.newInstance();
    }

    public static Object toObject(Class claz, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        if (claz.isAssignableFrom(Integer.class) || claz.isAssignableFrom(int.class)) {
            return Integer.valueOf(value);
        }
        if (claz.isAssignableFrom(Double.class) || claz.isAssignableFrom(double.class)) {
            return Double.valueOf(value);
        }
        if (claz.isAssignableFrom(Float.class) || claz.isAssignableFrom(float.class)) {
            return Float.valueOf(value);
        }

        if (claz.isAssignableFrom(Long.class) || claz.isAssignableFrom(long.class)) {
            return Long.valueOf(value);
        }

        if (claz.isAssignableFrom(Character.class) || claz.isAssignableFrom(char.class)) {
            return Character.valueOf(value.charAt(0));
        }
        
        if(claz.isEnum()){
            return Enum.valueOf(claz, value);
        }

        if (claz.isAssignableFrom(String.class)) {
            return value;
        }

        return null;
    }

    private static boolean isSimpleClass(Class claz) {

        String simpleName = claz.getSimpleName();
        return false;

    }

}
