/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageTemplate extends MotechEntity{

    /**
     * @return the date_created
     */
    Date getDate_created();

    /**
     * @return the language
     */
    String getLanguage();

    /**
     * @return the notification_type
     */
    int getNotification_type();

    /**
     * @param date_created the date_created to set
     */
    void setDate_created(Date date_created);

    /**
     * @param language the language to set
     */
    void setLanguage(String language);

    /**
     * @param notification_type the notification_type to set
     */
    void setNotification_type(int notification_type);

}
