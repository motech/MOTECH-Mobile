package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.service.MotechContext;
import java.util.Map;

/**
 * An interface for manipulating stored message templates
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-Apr-2009
 *
 */
public interface MessageStoreManager {

    /**
     * Builds a GatewayRequest object out of a MessageRequest
     * 
     * @param messageData the MessageRequest object containing details of the message to construct
     * @return the constructed GatewayRequest object
     */
    public GatewayRequestDetails constructMessage(MessageRequest messageData, MotechContext context);

    /**
     * Constructs a personalized message from the provided template and parameters
     * 
     * @param template
     * @param templateParams
     * @return
     */
    public String parseTemplate(String template, Map<String, String> templateParams);
    
    /**
     * Fetches a template for specific message types from the message store
     * 
     * @param messageData information on the template to select
     * @return the template matching the message information
     */
    public String fetchTemplate(MessageRequest messageData, MotechContext context);
    
    
    /**
     * 
     * @return the coreManager
     */
    public CoreManager getCoreManager();

    /**
     * 
     * @param coreManager the CoreManager to set
     */
    public void setCoreManager(CoreManager coreManager);
}
