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
    Date getDateCreated();

    /**
     * @return the language
     */
    String getLanguage();

    /**
     * @return the notification_type
     */
    Long getNotificationType();

    /**
     *
     * @return the message_type
     */
   MessageType getMessageType();

   /**
    *
    * @return the template
    */
   String getTemplate();

    /**
     * @param date_created the date_created to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param language the language to set
     */
    void setLanguage(String language);

    /**
     * @param notification_type the notification_type to set
     */
    void setNotificationType(Long notificationType);

    /**
     *
     * @param message_type the message_tpe to set
     */
    void setMessageType(MessageType messageType);

    /**
     * 
     * @param template the template to set
     */
    void setTemplate(String template);
}
