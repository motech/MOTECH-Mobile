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
    private String message_type;
    private String p13n_data;
    private String notification_type;
    private Date date_created;
    private Date date_processed;
    private String recipient_name;
    private String recipient_number;
    private Date date_from;
    private Date date_to;
    private String status;
    private int max_try_number;
    

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
    public String getMessage_type() {
        return message_type;
    }

    /**
     * @param message_type the message_type to set
     */
    public void setMessage_type(String message_type) {
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
    public String getNotification_type() {
        return notification_type;
    }

    /**
     * @param notification_type the notification_type to set
     */
    public void setNotification_type(String notification_type) {
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

    /**
     * @return the recipient_number
     */
    public String getRecipient_number() {
        return recipient_number;
    }

    /**
     * @param recipient_number the recipient_number to set
     */
    public void setRecipient_number(String recipient_number) {
        this.recipient_number = recipient_number;
    }

    /**
     * @return the date_from
     */
    public Date getDate_from() {
        return date_from;
    }

    /**
     * @param date_from the date_from to set
     */
    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    /**
     * @return the date_to
     */
    public Date getDate_to() {
        return date_to;
    }

    /**
     * @param date_to the date_to to set
     */
    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the max_try_number
     */
    public int getMax_try_number() {
        return max_try_number;
    }

    /**
     * @param max_try_number the max_try_number to set
     */
    public void setMax_try_number(int max_try_number) {
        this.max_try_number = max_try_number;
    }




}
