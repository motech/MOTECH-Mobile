/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

/**
 *  Date : Sep 24, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class GatewayRequestDetailsImpl extends MotechEntityImpl implements GatewayRequestDetails {

    public GatewayRequestDetailsImpl() {
    }

    private String messageType;
    private String message;
    private int numberOfPages;

    /**
     * @return the messageType
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * @param messageType the messageType to set
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the numberOfPages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


}
