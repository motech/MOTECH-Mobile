/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.model.imp;

import java.util.Date;

/**
 *
 * @author jojo
 */
public interface IncomingMessageResponse {

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
}
