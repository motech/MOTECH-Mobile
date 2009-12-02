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
    String getContent();

    /**
     * @return the dateCreated
     */
    Date getDateCreated();

    /**
     * @return the incomingMessage
     */
    IncomingMessage getIncomingMessage();

    /**
     * @return the messageResponseStatus
     */
    IncMessageResponseStatus getMessageResponseStatus();

    /**
     * @param content the content to set
     */
    void setContent(String content);

    /**
     * @param dateCreated the dateCreated to set
     */
    void setDateCreated(Date dateCreated);

    /**
     * @param incomingMessage the incomingMessage to set
     */
    void setIncomingMessage(IncomingMessage incomingMessage);

    /**
     * @param messageResponseStatus the messageResponseStatus to set
     */
    void setMessageResponseStatus(IncMessageResponseStatus messageResponseStatus);

}
