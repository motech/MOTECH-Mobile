package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.util.MotechException;
import com.dreamoval.motech.omp.manager.GatewayManager;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.Session;

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
    public void scheduleMessage(GatewayRequest message, MotechContext context) {
        cache.saveMessage(message.getGatewayRequestDetails(), context);
    }

    /**
     * 
     * @see MessageService.scheduleMessage
     */
    public void scheduleMessage(GatewayRequestDetails message, MotechContext context) {
        cache.saveMessage(message, context);
    }

    /**
     *
     * @see MessagingService.sendScheduledMessages
     */
    public void sendScheduledMessages() {
        MotechContext mc = coreManager.createMotechContext();

        logger.info("Fetching cached GatewayRequests");

        List<GatewayRequest> scheduledMessages = cache.getMessagesByStatus(MStatus.SCHEDULED, mc);

        if (scheduledMessages != null) {
            logger.info("Sending messages");
            for (GatewayRequest message : scheduledMessages) {
                sendMessage(message, mc);
            }
        }
        mc.cleanUp();

        logger.info("Sending completed successfully");
    }

    /**
     *
     * @see MessagingService.sendMessage(MessageDetails messageDetails)
     */
    public Long sendMessage(GatewayRequest messageDetails, MotechContext context) {
        logger.info("Sending message to gateway");
        
        try{
            Set<GatewayResponse> responseList = this.gatewayManager.sendMessage(messageDetails, context);     
            logger.debug(responseList);
            logger.info("Updating message status");
            messageDetails.setResponseDetails(responseList);
            messageDetails.setMessageStatus(MStatus.SENT);
        }
        catch(MotechException me){          
            logger.error("Error sending message", me);
            messageDetails.setMessageStatus(MStatus.SCHEDULED);
        }
        this.cache.saveMessage(messageDetails.getGatewayRequestDetails(), context);
        return messageDetails.getId();
    }

    /**
     *
     * @see MessagingService.sendMessage(MessageDetails messageDetails)
     */
    public Long sendMessage(GatewayRequestDetails messageDetails, MotechContext context) {
        logger.info("Sending message to gateway");
        GatewayRequest message = (GatewayRequest) messageDetails.getGatewayRequests().toArray()[0];
        
        try{        
            Set<GatewayResponse> responseList = this.gatewayManager.sendMessage(message, context);
            logger.debug(responseList);
            logger.info("Updating message status");
            message.setResponseDetails(responseList);
            message.setMessageStatus(MStatus.SENT);
        }
        catch(MotechException me){
            logger.error("Error sending message", me);
            message.setMessageStatus(MStatus.SCHEDULED);
        }
        this.cache.saveMessage(messageDetails, context);

        return message.getId();
    }

    /**
     * 
     */
    public void updateMessageStatuses() {
        logger.info("Updating GatewayResponse objects");
        MotechContext mc = getCoreManager().createMotechContext();

        GatewayResponse gwResp = coreManager.createGatewayResponse(mc);
        gwResp.setMessageStatus(MStatus.PENDING);

        List<GatewayResponse> pendingMessages = cache.getResponses(gwResp, mc);

        for (GatewayResponse response : pendingMessages) {
            response.setResponseText(gatewayManager.getMessageStatus(response));
            response.setMessageStatus(gatewayManager.mapMessageStatus(response));

            if (mc.getDBSession() != null) {
                ((Session) mc.getDBSession().getSession()).evict(response);
            }

            cache.saveResponse(response, mc);
        }
        mc.cleanUp();
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
