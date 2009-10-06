
package com.dreamoval.motech.core.model;

import java.util.Set;

/**
 *  Date : Sep 24, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface GatewayRequestDetails  extends MotechEntity{

    /**
     *
     * @return
     */
    MessageType getMessageType();
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
    Set getGatewayRequests();
    void setGatewayRequests(Set gatewayRequests);
    void setMessageType(MessageType messageType);
    void setMessage(String message);
    void setNumberOfPages(int numberOfPages);

    void addGatewayRequest(GatewayRequest gatewayRequest);
    void removeGatewayRequest(GatewayRequest gatewayRequest);

}
