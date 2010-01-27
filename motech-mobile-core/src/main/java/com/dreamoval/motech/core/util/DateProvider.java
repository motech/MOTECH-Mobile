package com.dreamoval.motech.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateProvider is a helper class that provides methods to manipulate Date instances.
 * Giving formating pattens and having date expressions in pure string etc
 *  Date : Oct 16, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class DateProvider {

    static DateFormat df;
    static Date date;

    /**
     * Method to convert a string representation of a Date of object and parse based on the formating pattern specified.
     * @param stringDate Date to parse
     * @return A Date Object with the new formating.
     */
    public static Date convertToDateTime(String stringDate) {
        df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = df.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

   /**
    * Method to provide new Date based on a certain formating pattern
    * @return new Date with the specified formating.
    */
    public static Date getNowDateTime() {
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = df.parse("");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * Method to provide a String representation of new Date bassed on certain formating pattern
     * @return String reprensentation of new Date
     */
     public static String getNowStringDateTime() {
         
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String stringdate;
            stringdate = df.format(new Date());
            return stringdate;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
      

    }

}
