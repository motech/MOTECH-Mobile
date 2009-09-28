/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *  MessageRequest class is a POJO to hold MessageRequest information for data storage and manipulation
 *  Date : Sep 25, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageRequest extends MotechEntity {

    String getRecipient_name();
    String getLanguage();
    Date getSchedule();
    int getMessage_type();
    String getP13n_data();
    int getNotification_type();
    Date getDate_created();
    Date getDate_processed();
    String getRecipient_number();

    void setRecipient_name(String recipient_name);
    void setLanguage(String language);
    void setSchedule(Date schedule);
    void setMessage_type(int message_type);
    void setP13n_data(String p13n_data);
    void setNotification_type(int notification_type);
    void setDate_created(Date date_created);
    void setDate_processed(Date date_processed);
    void setRecipient_number(String recipient_number);
}
