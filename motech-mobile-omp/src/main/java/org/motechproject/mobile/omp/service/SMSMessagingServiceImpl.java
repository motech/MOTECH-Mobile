/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2010-11 The Trustees of Columbia University in the City of
 * New York and Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, Columbia University, or
 * their respective contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA, COLUMBIA UNIVERSITY
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION
 * USA, COLUMBIA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.motechproject.mobile.omp.service;

import org.apache.log4j.Logger;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.util.MotechException;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import org.motechproject.ws.ContactNumberType;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * An SMS specific implementation of the MobileMessagingService interface
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSMessagingServiceImpl implements MobileMessagingService {

    private CacheService cache;
    private GatewayManager gatewayManager;
    private CoreManager coreManager;
    private SMSMessagingServiceWorker worker;
    private static Logger logger = Logger.getLogger(SMSMessagingServiceImpl.class);


    @Transactional
    public void scheduleMessage(GatewayRequest gatewayRequest) {
        cache.saveMessage(gatewayRequest.getGatewayRequestDetails());
    }

    @Transactional
    public void scheduleMessage(GatewayRequestDetails gatewayRequestDetails) {
        cache.saveMessage(gatewayRequestDetails);
    }

    @Transactional
    public void scheduleTransactionalMessage(GatewayRequest gatewayRequest) {
        cache.mergeMessage(gatewayRequest);
    }

   
    @Transactional(readOnly = true)
    public void sendScheduledMessages() {

        logger.info("Fetching cached GatewayRequests");

        List<GatewayRequest> scheduledMessages = cache.getMessagesByStatusAndSchedule(MStatus.SCHEDULED, new Date());

        if (scheduledMessages != null && scheduledMessages.size() > 0) {
            logger.info("Sending messages");
            for (GatewayRequest message : scheduledMessages) {
                try {
                    sendTransactionalMessage(message);
                } catch (Exception e) {
                    logger.error("SMS message sending error: ", e);
                }
            }
            logger.info("Sending completed successfully");
        } else {
            logger.info("No scheduled messages Found");
        }

    }

    public Map<Boolean, Set<GatewayResponse>> sendTransactionalMessage(GatewayRequest gatewayRequest) {
        return getWorker().sendMessage(gatewayRequest);
    }


    @Transactional
    public Map<Boolean, Set<GatewayResponse>> sendMessage(GatewayRequest gatewayRequest) {
        logger.debug("Sending message to gateway");
        Set<GatewayResponse> responseList = null;
        Map<Boolean, Set<GatewayResponse>> result = new HashMap<Boolean, Set<GatewayResponse>>();
        try {
            if ((gatewayRequest.getRecipientsNumber() == null || gatewayRequest.getRecipientsNumber().isEmpty())
                    && !ContactNumberType.PUBLIC.toString().equals(gatewayRequest.getMessageRequest().getPhoneNumberType())) {
                gatewayRequest.setMessageStatus(MStatus.INVALIDNUM);
            } else {
                responseList = this.getGatewayManager().sendMessage(gatewayRequest);
                result.put(true, responseList);
                logger.debug(responseList);
                logger.debug("Updating message status");
                gatewayRequest.setResponseDetails(responseList);
                gatewayRequest.setMessageStatus(MStatus.SENT);
            }
        } catch (MotechException me) {
            logger.error("Error sending message", me);
            gatewayRequest.setMessageStatus(MStatus.SCHEDULED);

            GatewayMessageHandler orHandler = getGatewayManager().getMessageHandler();
            responseList = orHandler.parseMessageResponse(gatewayRequest, "error: 901 - Cannot Connect to gateway | Details: " + me.getMessage());
            result.put(false, responseList);
        }
        this.getCache().saveMessage(gatewayRequest);

        return result;
    }

    /**
     * 
     */
    @Transactional(readOnly = true)
    public void updateMessageStatuses() {
        logger.info("Updating GatewayResponse objects");

        GatewayResponse gwResp = coreManager.createGatewayResponse();
        gwResp.setMessageStatus(MStatus.PENDING);

        List<GatewayResponse> pendingMessages = cache.getResponses(gwResp);

        for (GatewayResponse response : pendingMessages) {
            try {
                updateMessageStatus(response);
            } catch (Exception e) {
                logger.error("SMS message update error");
            }
        }

    }

    private void updateMessageStatus(GatewayResponse response){
        getWorker().updateMessageStatus(response);
    }

    String getMessageStatus(GatewayResponse response) {
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

    /**
     * @return the worker
     */
    public SMSMessagingServiceWorker getWorker() {
        return worker;
    }

    /**
     * @param worker the worker to set
     */
    public void setWorker(SMSMessagingServiceWorker worker) {
        this.worker = worker;
    }
}
