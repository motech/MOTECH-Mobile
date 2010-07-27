package org.motechproject.mobile.core.model;

import java.util.Date;

/**
 * MessageTemplate interface is a POJO to hold information for storage and manipulations
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageTemplate {

     /**
     *
     * @param id the id to set
     */
    public void setId(String id);

    /**
     *
     * @return id to get
     */
    public String getId();

    /**
     * @return the date_created
     */
    Date getDateCreated();

    /**
     * @return the language
     */
    Language getLanguage();

    /**
     * @return the notification_type
     */
    NotificationType getNotificationType();

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
    void setLanguage(Language language);

    /**
     * @param notification_type the notification_type to set
     */
    void setNotificationType(NotificationType notificationType);

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

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();

      /**
     * @return the version
     */
    int getVersion();

    /**
     * @param version the version to set
     */
    void setVersion(int version);
}
