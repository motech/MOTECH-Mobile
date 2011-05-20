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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.omp.manager.gupshup;

import org.apache.commons.lang.NotImplementedException;
import org.apache.log4j.Logger;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class SMSGupshupGatewayMessageHandler implements GatewayMessageHandler {
    private CoreManager coreManager;

    private static Logger logger = Logger.getLogger(SMSGupshupGatewayMessageHandler.class);


    public Set<GatewayResponse> parseMessageResponse(GatewayRequest message, String gatewayResponse) {
        logger.debug("Parsing message gateway response");
        logger.debug(gatewayResponse);

        if (message == null)
            return null;

        if (gatewayResponse.isEmpty())
            return null;

        Set<GatewayResponse> responses = new HashSet<GatewayResponse>();
        SMSGupshupResponse gupshupResponse = new SMSGupshupResponse(gatewayResponse);

        GatewayResponse response = getCoreManager().createGatewayResponse();
        response.setRequestId(message.getRequestId());
        response.setGatewayRequest(message);
        response.setDateCreated(new Date());

        if (gupshupResponse.isSuccess()) {
            response.setMessageStatus(MStatus.DELIVERED);
            response.setRecipientNumber(gupshupResponse.recipient());
        } else if (gupshupResponse.isError()) {
            logger.error("Gateway returned error: " + gupshupResponse);
            response.setMessageStatus(MStatus.FAILED);
            response.setRecipientNumber(gupshupResponse.recipient());
        } else {
            response.setMessageStatus(MStatus.RETRY);
        }

        response.setResponseText(gupshupResponse.toString());
        responses.add(response);

        logger.debug(responses);
        return responses;
    }


    public MStatus parseMessageStatus(String gatewayResponse) {
        SMSGupshupResponse response = new SMSGupshupResponse(gatewayResponse);

        if (response.isSuccess()) return MStatus.DELIVERED;

        if (response.isError()) return MStatus.FAILED;

        return MStatus.RETRY;
    }

    public MStatus lookupStatus(String code) {
       throw new NotImplementedException();
    }

    public MStatus lookupResponse(String code) {
        throw new NotImplementedException();
    }


    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }


}
