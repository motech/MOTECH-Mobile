package org.motechproject.mobile.omp.manager;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import java.util.Set;

/**
 * Handles preparation and parsing of messages and responses from a message gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface GatewayMessageHandler {

    /**
     * Constructs ResponseDetails objects from a response recieved from a message gateway
     * @param gatewayResponse
     * @return
     */
    public Set<GatewayResponse> parseMessageResponse(GatewayRequest message, String gatewayResponse);

    /**
     *
     * @param messageStatus the status of the message returned by the message gateway
     * @return a more reader-friendly message status
     */
    public MStatus parseMessageStatus(String messageStatus);

    /**
     * Looks up an MStatus value based on the provided code
     * 
     * @param code the status code to lookup
     * @return the MStatus corresponding to the code
     */
    public MStatus lookupStatus(String code);
    
    /**
     * Looks up an MStatus value based on the provided code
     * 
     * @param code the response code to lookup
     * @return the MStatus corresponding to the code
     */
    public MStatus lookupResponse(String code);
            
    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager();

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager);
}
