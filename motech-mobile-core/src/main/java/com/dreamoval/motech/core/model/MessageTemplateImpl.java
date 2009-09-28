/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class MessageTemplateImpl extends MotechEntityImpl implements MessageTemplate  {
    public MessageTemplateImpl() {
    }

    private int notification_type;
    private String language;
    private Date date_created;

    /**
     * @return the notification_type
     */
    public int getNotification_type() {
        return notification_type;
    }

    /**
     * @param notification_type the notification_type to set
     */
    public void setNotification_type(int notification_type) {
        this.notification_type = notification_type;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the date_created
     */
    public Date getDate_created() {
        return date_created;
    }

    /**
     * @param date_created the date_created to set
     */
    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
    
}
