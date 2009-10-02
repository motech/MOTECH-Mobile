/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

import java.util.HashSet;
import java.util.Set;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class NotificationTypeImpl extends MotechEntityImpl implements NotificationType {

    public NotificationTypeImpl() {
    }

    private String name;
    private String description;
    private Set<MessageTemplate> messageTemplates = new HashSet<MessageTemplate>();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the messageTemplates
     */
    public Set<MessageTemplate> getMessageTemplates() {
        return messageTemplates;
    }

    /**
     * @param messageTemplates the messageTemplates to set
     */
    public void setMessageTemplates(Set<MessageTemplate> messageTemplates) {
        this.messageTemplates = messageTemplates;
    }
    
}
