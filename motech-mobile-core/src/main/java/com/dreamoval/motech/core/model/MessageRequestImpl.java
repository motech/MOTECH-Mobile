/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *  Date : Sep 25, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class MessageRequestImpl extends MotechEntityImpl implements MessageRequest{

    private String language;
    private Date schedule;
    private int message_type;
    private String p13n_data;
    private int notification_type;
    private Date date_created;
    private Date date_processed;
    private String recipient_name;

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
     * @return the schedule
     */
    public Date getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    /**
     * @return the message_type
     */
    public int getMessage_type() {
        return message_type;
    }

    /**
     * @param message_type the message_type to set
     */
    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    /**
     * @return the p13n_data
     */
    public String getP13n_data() {
        return p13n_data;
    }

    /**
     * @param p13n_data the p13n_data to set
     */
    public void setP13n_data(String p13n_data) {
        this.p13n_data = p13n_data;
    }

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

    /**
     * @return the date_processed
     */
    public Date getDate_processed() {
        return date_processed;
    }

    /**
     * @param date_processed the date_processed to set
     */
    public void setDate_processed(Date date_processed) {
        this.date_processed = date_processed;
    }

    /**
     * @return the recipient_name
     */
    public String getRecipient_name() {
        return recipient_name;
    }

    /**
     * @param recipient_name the recipient_name to set
     */
    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }



}
