/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.manager;

import com.leqienglish.json.annotation.JSON;
import com.leqienglish.json.annotation.JSONClass;
import com.leqienglish.json.annotation.JSONDate;
import com.leqienglish.json.handler.JSONCollectionHandler;
import com.leqienglish.json.handler.JSONDateHandler;
import com.leqienglish.json.handler.JSONHandler;
import com.leqienglish.json.handler.JSONObjectHandler;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author zhuleqi
 */
public class JSONHandlerManager {

    private String dateFormat = "yyyy-MM-dd hh:mm:ss";
    private JSONObjectHandler jsonObjectHandler;
    private JSONCollectionHandler jsonCollectionHandler;
    private JSONDateHandler jsonDateHandler;

    private JSONHandlerManager() {

    }

    public static JSONHandlerManager newInstance() {
        return new JSONHandlerManager();
    }

    public JSONHandler getJSONHandler(Field field) {
        if (field.isAnnotationPresent(JSONClass.class)) {
            return this.getJsonObjectHandler();
        }
        if (Collection.class.isAssignableFrom(field.getType())) {
            return this.getJsonCollectionHandler();
        }

        if (field.isAnnotationPresent(JSONDate.class)) {
            JSONDate jsonDate = field.getAnnotation(JSONDate.class);
            return this.getJsonDateHandler(jsonDate.format());
        }

        return null;

    }

    public JSONHandler getJSONHandler(Class claz) {

        if (Date.class.isAssignableFrom(claz) || Calendar.class.isAssignableFrom(claz)) {
            return this.getJsonDateHandler();
        }

        if (Collection.class.isAssignableFrom(claz)) {
            return this.getJsonCollectionHandler();
        }

        if (claz.isAnnotationPresent(JSONClass.class)) {
            return this.getJsonObjectHandler();
        }

        return null;
    }

    /**
     * @return the jsonObjectHandler
     */
    public JSONObjectHandler getJsonObjectHandler() {
        if (this.jsonObjectHandler == null) {
            this.jsonObjectHandler = new JSONObjectHandler();
        }
        return jsonObjectHandler;
    }

    /**
     * @return the jsonCollectionHandler
     */
    public JSONCollectionHandler getJsonCollectionHandler() {
        if (this.jsonCollectionHandler == null) {
            this.jsonCollectionHandler = new JSONCollectionHandler();
        }
        return jsonCollectionHandler;
    }

    /**
     * @return the jsonDateHandler
     */
    public JSONDateHandler getJsonDateHandler() {

        return this.getJsonDateHandler(dateFormat);
    }

    /**
     * @return the jsonDateHandler
     */
    public JSONDateHandler getJsonDateHandler(String format) {
        if (this.jsonDateHandler == null) {
            this.jsonDateHandler = new JSONDateHandler();
        }
        this.jsonDateHandler.setDateFormat(format);
        return jsonDateHandler;
    }

}
