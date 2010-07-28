package org.motechproject.mobile.omp.service;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.util.MotechException;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * An SMS specific implementation of the MessagingService interface
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSMessagingServiceImpl implements MessagingService {

    private CacheService cache;
    private GatewayManager gatewayManager;
    private CoreManager coreManager;
    private static Logger logger = Logger.getLogger(SMSMessagingServiceImpl.class);

    /**
     * 
     * @see MessageService.scheduleMessage
     */
    public void scheduleMessage(GatewayRequest message) {
        cache.saveMessage(message.getGatewayRequestDetails());
    }

    /**
     * 
     * @see MessageService.scheduleMessage
     */
    public void scheduleMessage(GatewayRequestDetails message) {
        cache.saveMessage(message);
    }

    /**
     *
     * @see MessagingService.sendScheduledMessages
     */
    @Transactional
    public void sendScheduledMessages() {

        logger.info("Fetching cached GatewayRequests");

        List<GatewayRequest> scheduledMessages = cache.getMessagesByStatusAndSchedule(MStatus.SCHEDULED, new Date());

        if (scheduledMessages != null && scheduledMessages.size() > 0) {
            logger.info("Sending messages");
            for (GatewayRequest message : scheduledMessages) {
                sendMessage(message);
            }
            logger.info("Sending completed successfully");
        } else {
            logger.info("No scheduled messages Found");
        }

    }

    /**
     *
     * @see MessagingService.sendMessage(MessageDetails messageDetails)
     */
    public Map<Boolean, Set<GatewayResponse>> sendMessage(GatewayRequest messageDetails) {
        logger.info("Sending message to gateway");
        Set<GatewayResponse> responseList = null;
        Map<Boolean, Set<GatewayResponse>> result = new HashMap<Boolean, Set<GatewayResponse>>();
        try {
            if (messageDetails.getRecipientsNumber() == null || messageDetails.getRecipientsNumber().isEmpty()) {
                messageDetails.setMessageStatus(MStatus.FAILED);
            } else {
                responseList = this.gatewayManager.sendMessage(messageDetails);
                result.put(new Boolean(true), responseList);
                logger.debug(responseList);
                logger.info("Updating message status");
                messageDetails.setResponseDetails(responseList);
                messageDetails.setMessageStatus(MStatus.SENT);
            }
        } catch (MotechException me) {
            logger.error("Error sending message", me);
            messageDetails.setMessageStatus(MStatus.SCHEDULED);

            GatewayMessageHandler orHandler = gatewayManager.getMessageHandler();
            responseList = orHandler.parseMessageResponse(messageDetails, "error: 901 - Cannot Connect to gateway | Details: " + me.getMessage());
            result.put(new Boolean(false), responseList);
        }
        this.cache.saveMessage(messageDetails.getGatewayRequestDetails());

        return result;
    }

    /**
     *
     * @see MessagingService.sendMessage(MessageDetails messageDetails)
     */
    public Long sendMessage(GatewayRequestDetails messageDetails) {
        logger.info("Sending message to gateway");
        GatewayRequest message = (GatewayRequest) messageDetails.getGatewayRequests().toArray()[0];

        if (message.getRecipientsNumber() != null || message.getRecipientsNumber().isEmpty()) {
            message.setMessageStatus(MStatus.FAILED);
        } else {
            try {
                Set<GatewayResponse> responseList = this.gatewayManager.sendMessage(message);
                logger.debug(responseList);
                logger.info("Updating message status");
                message.setResponseDetails(responseList);
                message.setMessageStatus(MStatus.SENT);
            } catch (MotechException me) {
                logger.error("Error sending message", me);
                message.setMessageStatus(MStatus.SCHEDULED);
            }
        }
        this.cache.saveMessage(messageDetails);

        return message.getId();
    }

    /**
     * 
     */
    public void updateMessageStatuses() {
        logger.info("Updating GatewayResponse objects");

        GatewayResponse gwResp = coreManager.createGatewayResponse();
        gwResp.setMessageStatus(MStatus.PENDING);

        List<GatewayResponse> pendingMessages = cache.getResponses(gwResp);

        for (GatewayResponse response : pendingMessages) {
            response.setResponseText(gatewayManager.getMessageStatus(response));
            response.setMessageStatus(gatewayManager.mapMessageStatus(response));


            cache.saveResponse(response);
        }

    }

    /**
     * 
     * @see MessageService.getMessageStatus
     */
    public String getMessageStatus(GatewayResponse response) {
        logger.info("Calling GatewayManager.getMessageStatus");
        return gatewayManager.getMessageStatus(response);
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
        logger.debug("Setting SMSMessagingServiceImpl.cache:");
        logger.debug(cache);
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
        logger.debug("Setting SMSMessagingServiceImpl.gatewayManager:");
        logger.debug(gatewayManager);
        this.gatewayManager = gatewayManager;
    }

    public CoreManager getCoreManager() {
        return coreManager;
    }

    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }
}
