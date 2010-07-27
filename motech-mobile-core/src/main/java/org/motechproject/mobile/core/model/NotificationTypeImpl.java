package org.motechproject.mobile.core.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * NotificationTypeImpl class in an implementation of NotificationType which is actually mapped
 * in hibernate.it provides properties to handle NotificationType operations
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class NotificationTypeImpl implements NotificationType {

    public NotificationTypeImpl() {
    }

    private Long id;
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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");

        if (this != null) {
            sb.append((this.getId() != null) ? "key=Id--------------------value=" + this.getId().toString() : "Id is null ");
            sb.append(newLine);
            sb.append((this.name != null) ? "key=recipientsNumber value=" + this.name : "name is null ");
            sb.append(newLine);
            sb.append((this.description != null) ? "key=description value=" + this.description : "description is null ");
            sb.append(newLine);

            sb.append((this.messageTemplates.isEmpty()) ? "key=messageTemplates length=" + Integer.toString(this.messageTemplates.size()) : "responseDetails is empty ");
            sb.append(newLine);

            for (Iterator it = this.messageTemplates.iterator(); it.hasNext();) {
                MessageTemplate resp = (MessageTemplate) it.next();
                sb.append((resp != null) ? "key=MessageTemplate.Id value=" + resp.getId().toString() : "MessageTemplate.Id is null ");
                sb.append(newLine);
            }


            return sb.toString();

        } else {
            return "Object is null";
        }


    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
