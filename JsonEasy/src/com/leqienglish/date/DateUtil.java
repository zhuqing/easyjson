/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author zhuleqi
 */
public class DateUtil {

    private static SimpleDateFormat dateFormater = new SimpleDateFormat();

    public synchronized static String toDateFormat(Object date, String format) {
        if (date == null || format == null) {
            return "";
        }
        dateFormater.applyPattern(format);
        if (date instanceof Date) {
            return toDateFormat((Date) date, dateFormater);
        } else if (date instanceof Calendar) {
            return toDateFormat((Calendar) date, dateFormater);
        }

        return "";

    }

    private static String toDateFormat(Date date, SimpleDateFormat dateFormater) {

        return dateFormater.format(date);

    }

    private static String toDateFormat(Calendar date, SimpleDateFormat dateFormater) {
        return dateFormater.format(date.getTime());
    }

    private static Date toDate(String date, SimpleDateFormat dateFormater) throws ParseException {
        return dateFormater.parse(date);
    }

    public synchronized static Object toDate(String date, String format, Class claz) throws ParseException {
        if (date == null || format == null || claz == null) {
            return null;
        }
        dateFormater.applyPattern(format);
        if (claz.getSimpleName().equals("Date")) {

            return toDate(date, dateFormater);
        } else if (claz.getSimpleName().equals("Calendar")) {
            Date dateD = toDate(date, dateFormater);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateD);
            return calendar;
        }

        return null;
    }
}
