/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.omp.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.core.util.MotechIDGenerator;
import java.util.Set;

/**
 * <p>A dummy gateway manager for testing purposes</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 */
public class DummyGatewayManagerImpl implements GatewayManager{

	private static Log log = LogFactory.getLog(DummyGatewayManagerImpl.class);
	
    private GatewayMessageHandler messageHandler;
    /**
     *
     * @see GatewayManager.send
     */
    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails){
    	if(log.isInfoEnabled()) {
    		log.info(messageDetails.getId() + "|" + 
    				messageDetails.getRecipientsNumber() + "|" + 
    				messageDetails.getMessage());
    	}
        String msgResponse = (messageDetails.getRecipientsNumber().length() < 8) ? "failed" : "ID: " + MotechIDGenerator.generateID(10);
        return messageHandler.parseMessageResponse(messageDetails, msgResponse);
    }

    /**
     *
     * @see GatewayManager.getMessageStatus
     */
    public String getMessageStatus(GatewayResponse response) {
        return "004";
    }

    public MStatus mapMessageStatus(GatewayResponse response) {
        return messageHandler.parseMessageStatus(response.getResponseText());
    }

    /**
     * @return the messageHandler
     */
    public GatewayMessageHandler getMessageHandler() {
        return messageHandler;
    }

    /**
     * @param messageHandler the messageHandler to set
     */
    public void setMessageHandler(GatewayMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

}
