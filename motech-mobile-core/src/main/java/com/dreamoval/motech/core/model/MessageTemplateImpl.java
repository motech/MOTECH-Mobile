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

    private Long notificationType;
    private String language;
    private Date dateCreated;
    private MessageType messageType;
    private String template;

    /**
     * @return the notification_type
     */
    public Long getNotificationType() {
        return notificationType;
    }

    /**
     * @param notification_type the notification_type to set
     */
    public void setNotificationType(Long notificationType) {
        this.notificationType = notificationType;
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
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param date_created the date_created to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the message_type
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * @param message_type the message_type to set
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
    }
    
}
