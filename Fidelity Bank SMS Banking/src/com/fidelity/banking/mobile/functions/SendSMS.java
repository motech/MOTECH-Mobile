/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fidelity.banking.mobile.functions;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 *
 * @author administrator
 */
public class SendSMS {
    private String message;
    private String phoneNumber;
    private final int portNumber = 50000;
    
    /**
     * Sends a non-null message to a non-null phone number
     * 
     * @throws IOException
     */
    public void sendMsg() throws IOException{
        if (getPhoneNumber() == null || getMessage() == null) {
            throw new IllegalArgumentException();
        }
        String address = "sms://" + getPhoneNumber() + ":" + getPortNumber();
        MessageConnection smsconn = null;
        /** Open the message connection. */
        smsconn = (MessageConnection) Connector.open(address);
        TextMessage txtmessage = (TextMessage) smsconn.newMessage(MessageConnection.TEXT_MESSAGE);
        txtmessage.setAddress(address);
        txtmessage.setPayloadText(getMessage());
        smsconn.send(txtmessage);
        smsconn.close();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPortNumber() {
        return portNumber;
    }
}
