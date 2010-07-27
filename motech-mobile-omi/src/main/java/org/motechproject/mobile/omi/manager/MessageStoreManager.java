package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageType;
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
    GatewayRequest constructMessage(MessageRequest messageData, Language defaultLang);

    /**
     * Constructs a personalized message from the provided template and parameters
     * 
     * @param template
     * @param templateParams
     * @return
     */
    String parseTemplate(String template, Set<NameValuePair> templateParams);
    
    /**
     * Fetches a template for specific message types from the message store
     * 
     * @param messageData information on the template to select
     * @return the template matching the message information
     */
    String fetchTemplate(MessageRequest messageData, Language defaultLang);

    /**
     * Converts a phone number in local format to international format
     * @param requesterPhone number to format
     * @return formatted number
     */
    String formatPhoneNumber(String requesterPhone, MessageType type);
    
    /**
     * 
     * @return the coreManager
     */
    CoreManager getCoreManager();

    /**
     * 
     * @param coreManager the CoreManager to set
     */
    void setCoreManager(CoreManager coreManager);

    /**
     * @param maxConcat the maxConcat to set
     */
    void setMaxConcat(int maxConcat);

    /**
     * @param charsPerSMS the charsPerSMS to set
     */
    void setCharsPerSMS(int charsPerSMS);

    /**
     * @param concatAllowance the concatAllowance to set
     */
    void setConcatAllowance(int concatAllowance);
}
