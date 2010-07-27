package org.motechproject.mobile.core.model;

import java.util.Set;

/**
 * MessageRequestDetails interface is a POJO to hold MessageRequestDetails information for data storage and manipulation
 *  Date : Sep 24, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface GatewayRequestDetails {


    /**
     * 
     * @return
     */
    public void setId(String id);

    /**
     * 
     * @return
     */
    public String getId();

    /**
     * @return the messageType
     */
    MessageType getMessageType();

    /**
     * @return the message
     */
    String getMessage();

    /**
     * @return the numberOfPages
     */
    int getNumberOfPages();

    /**
     * @return the gatewayRequest set
     */
    Set getGatewayRequests();

    /**
     * @param gatewayRequests the gatewayRequest to set
     */
    void setGatewayRequests(Set gatewayRequests);

    /**
     * @param messageType the messageType to set
     */
    void setMessageType(MessageType messageType);

    /**
     * @param message the message to set
     */
    void setMessage(String message);

    /**
     * @param numberOfPages the numberOFPages to set
     */
    void setNumberOfPages(int numberOfPages);

    /**
     * Helper method to add GatewayRequest to GatewayRequestDetails
     * @param gatewayRequest GatewayRequest to add
     */
    void addGatewayRequest(GatewayRequest gatewayRequest);

    /**
     * Helper method to remove GatewayRequest from GatewayRequestDetails
     * @param gatewayRequest GatewayRequest to remove
     */
    void removeGatewayRequest(GatewayRequest gatewayRequest);

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
