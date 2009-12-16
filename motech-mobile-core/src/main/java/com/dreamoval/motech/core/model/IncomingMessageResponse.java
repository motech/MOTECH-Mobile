package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *
 * @author jojo
 */
public interface IncomingMessageResponse extends MotechEntity {

    /**
     * @return the content
     */
    public String getContent();

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the incomingMessage
     */
    public IncomingMessage getIncomingMessage();

    /**
     * @return the messageResponseStatus
     */
    public IncMessageResponseStatus getMessageResponseStatus();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @param content the content to set
     */
    public void setContent(String content);

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated);

    /**
     * @param incomingMessage the incomingMessage to set
     */
    public void setIncomingMessage(IncomingMessage incomingMessage);

    /**
     * @param messageResponseStatus the messageResponseStatus to set
     */
    public void setMessageResponseStatus(IncMessageResponseStatus messageResponseStatus);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();
}
