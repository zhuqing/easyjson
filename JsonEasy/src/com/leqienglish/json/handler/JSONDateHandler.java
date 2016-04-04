/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.json.handler;

import com.leqienglish.date.DateUtil;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;

/**
 *
 * @author zhuleqi
 */
public class JSONDateHandler<T> extends JSONHandler<T> {

    private String dateFormat = "yyyy-MM-dd hh:mm:ss";
    private static String formatKey = "format";
    public JSONDateHandler(){
        
    }

    public JSONDateHandler(String dateFormat){
        this.dateFormat = dateFormat;
    }
    @Override
    public JSONObject toJSON(T t) {

        if(t == null){
            return null;
        }
        JSONObject dateJsonObject = this.getJSONObject(t);

        String dateStr = DateUtil.formatDate(t, getDateFormat());
        dateJsonObject.put(formatKey, getDateFormat());
        dateJsonObject.put(this.getValueKey(), dateStr);
        return dateJsonObject;
    }

    @Override
    public T toObject(JSONObject jsonObject) {
        try {
            Class claz = this.getClass(jsonObject);
            return (T) DateUtil.toDate(jsonObject.getString(this.getValueKey()), jsonObject.getString(formatKey), claz);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JSONDateHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JSONDateHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * @return the dateFormat
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

}
