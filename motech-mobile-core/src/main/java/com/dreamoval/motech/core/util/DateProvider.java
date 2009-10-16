/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Date : Oct 16, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class DateProvider {
   static DateFormat df;
    static Date date;

    /**
     *
     * @param stringDate
     * @return
     */
    public static Date convertToDateTime(String stringDate) {
        df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = df.parse(stringDate);
        } catch(ParseException e){
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 
     * @return
     */
    public static Date getNowDateTime() {
         df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          try {
            date = df.parse("");
        } catch(ParseException e){
            e.printStackTrace();
        }
        return  date;
       
    }

}
