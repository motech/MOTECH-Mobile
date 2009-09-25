/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

/**
 *  Date : Sep 24, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface GatewayRequestDetails  extends MotechEntity{

    /**
     *
     * @return
     */
    String getMessageType();
    /**
     *
     * @return
     */
    String getMessage();
    /**
     *
     * @return
     */
    int getNumberOfPages();

    void setMessageType(String messageType);
    void setMessage(String message);
    void setNumberOfPages(int numberOfPages);

}
