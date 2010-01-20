/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.core.model;

import java.util.Date;

/**
 *
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessage extends MotechEntity {

    /**
     * @return the content
     */
    public String getContent();

    /**
     * @return the dateCreated
     */
    public Date getDateCreated();

    /**
     * @return the incomingMessageForm
     */
    public IncomingMessageForm getIncomingMessageForm();

    /**
     * @return the incomingMessageResponse
     */
    public IncomingMessageResponse getIncomingMessageResponse();

    /**
     * @return the incomingMsgSession
     */
    public IncomingMessageSession getIncomingMsgSession();

    /**
     * @return the lastModified
     */
    public Date getLastModified();

    /**
     * @return the messageStatus
     */
    public IncMessageStatus getMessageStatus();

    /**
     * @param content the content to set
     */
    public void setContent(String content);

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated);

    /**
     * @param incomingMessageForm the incomingMessageForm to set
     */
    public void setIncomingMessageForm(IncomingMessageForm incomingMessageForm);

    /**
     * @param incomingMessageResponse the incomingMessageResponse to set
     */
    public void setIncomingMessageResponse(IncomingMessageResponse incomingMessageResponse);

    /**
     * @param incomingMsgSession the incomingMsgSession to set
     */
    public void setIncomingMsgSession(IncomingMessageSession incomingMsgSession);

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified);

    /**
     * @param messageStatus the messageStatus to set
     */
    public void setMessageStatus(IncMessageStatus messageStatus);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();
}
