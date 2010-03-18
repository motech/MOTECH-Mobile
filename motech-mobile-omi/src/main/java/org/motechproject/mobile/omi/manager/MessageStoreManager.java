package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.service.MotechContext;
import java.util.Set;
import org.motechproject.ws.NameValuePair;

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
    public GatewayRequest constructMessage(MessageRequest messageData, MotechContext context, Language defaultLang);

    /**
     * Constructs a personalized message from the provided template and parameters
     * 
     * @param template
     * @param templateParams
     * @return
     */
    public String parseTemplate(String template, Set<NameValuePair> templateParams);
    
    /**
     * Fetches a template for specific message types from the message store
     * 
     * @param messageData information on the template to select
     * @return the template matching the message information
     */
    public String fetchTemplate(MessageRequest messageData, MotechContext context, Language defaultLang);
    
    
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
