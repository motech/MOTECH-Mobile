package org.motechproject.mobile.core.model;

import java.util.Set;

/**
 * Notification interface is a POJO that holds NotificationType information for storage and manipulation
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface NotificationType extends MotechEntity {

    /**
     * @return the description
     */
    String getDescription();

    /**
     * @return the name
     */
    String getName();

    /**
     * @param description the description to set
     */
    void setDescription(String description);

    /**
     * @param name the name to set
     */
    void setName(String name);

    Set<MessageTemplate> getMessageTemplates();

    void setMessageTemplates(Set<MessageTemplate> messageTemplate);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();
}
