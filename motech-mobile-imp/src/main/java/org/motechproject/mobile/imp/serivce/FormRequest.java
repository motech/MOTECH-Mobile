/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.serivce;

import java.io.Serializable;

/**
 * Wrapper for requests recieved via OpenXData forms
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Feb 9, 2010
 */
public class FormRequest implements Serializable{
    private String message;
    private String senderNumber;
    private String response;
    private String requestId;

    public FormRequest(){

    }

    public FormRequest(String message, String senderNumber, String response, String requestId){
        this.message = message;
        this.senderNumber = senderNumber;
        this.response = response;
        this.requestId = requestId;
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
     * @return the senderNumber
     */
    public String getSenderNumber() {
        return senderNumber;
    }

    /**
     * @param senderNumber the senderNumber to set
     */
    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
