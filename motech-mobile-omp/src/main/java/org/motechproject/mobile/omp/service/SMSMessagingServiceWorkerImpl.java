/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.omp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.util.MotechException;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import org.motechproject.ws.ContactNumberType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Henry Sampson
 */
public class SMSMessagingServiceWorkerImpl implements SMSMessagingServiceWorker {

    private CacheService cache;
    private GatewayManager gatewayManager;
    private static Logger logger = Logger.getLogger(SMSMessagingServiceWorkerImpl.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Map<Boolean, Set<GatewayResponse>> sendMessage(GatewayRequest messageDetails) {
        logger.debug("Sending message to gateway");
        Set<GatewayResponse> responseList = null;
        Map<Boolean, Set<GatewayResponse>> result = new HashMap<Boolean, Set<GatewayResponse>>();
        try {
            if ((messageDetails.getRecipientsNumber() == null || messageDetails.getRecipientsNumber().isEmpty())
                    && !ContactNumberType.PUBLIC.toString().equals(messageDetails.getMessageRequest().getPhoneNumberType())) {
                messageDetails.setMessageStatus(MStatus.INVALIDNUM);
            } else {
                responseList = this.getGatewayManager().sendMessage(messageDetails);
                result.put(true, responseList);
                logger.debug(responseList);
                logger.debug("Updating message status");
                messageDetails.setResponseDetails(responseList);
                messageDetails.setMessageStatus(MStatus.SENT);
            }
        } catch (MotechException me) {
            logger.error("Error sending message", me);
            messageDetails.setMessageStatus(MStatus.SCHEDULED);

            GatewayMessageHandler orHandler = getGatewayManager().getMessageHandler();
            responseList = orHandler.parseMessageResponse(messageDetails, "error: 901 - Cannot Connect to gateway | Details: " + me.getMessage());
            result.put(false, responseList);
        }
        this.getCache().mergeMessage(messageDetails);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateMessageStatus(GatewayResponse response) {
        if (response != null) {
            response.setResponseText(getGatewayManager().getMessageStatus(response));
            response.setMessageStatus(getGatewayManager().mapMessageStatus(response));


            getCache().saveResponse(response);
        }
    }

    /**
     * @return the cache
     */
    public CacheService getCache() {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(CacheService cache) {
        this.cache = cache;
    }

    /**
     * @return the gatewayManager
     */
    public GatewayManager getGatewayManager() {
        return gatewayManager;
    }

    /**
     * @param gatewayManager the gatewayManager to set
     */
    public void setGatewayManager(GatewayManager gatewayManager) {
        this.gatewayManager = gatewayManager;
    }
}
