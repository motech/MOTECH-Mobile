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

package org.motechproject.mobile.omp.manager.gupshup;

import org.apache.log4j.Logger;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SMSGupshupGateway implements GatewayManager {

    private String serviceURL;



    private Map<String, String> parameters = new HashMap<String, String>();
    private String sentMessageStatus;
    private GatewayMessageHandler messageHandler;
    private static Logger logger = Logger.getLogger(SMSGupshupGateway.class);
    private static final String HTTP_GET = "GET";

    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails) {

        String message = (messageDetails.getMessage().length() <= 459) ? messageDetails.getMessage() : messageDetails.getMessage().substring(0, 455) + "...";
        GatewayURL gatewayURL = createGatewayURL();
        gatewayURL.append(new URLParameter("msg", message, true));
        IndianMobileNumber recipient = new IndianMobileNumber(messageDetails.getRecipientsNumber());
        gatewayURL.append(new URLParameter("send_to", recipient.toString()));

        logger.info("sms gupshup url : " + gatewayURL.toString());

        URL url;
        HttpURLConnection conn ;
        BufferedReader bufferedReader ;
        try {
            url = new URL(gatewayURL.toString());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(HTTP_GET);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line).append("\n");
            }
            bufferedReader.close();
            conn.disconnect();
            messageDetails.setDateSent(new Date());
            return messageHandler.parseMessageResponse(messageDetails, response.toString());
        } catch (Exception ex) {
            logger.error("Error During message sending to SMS Gupshup",ex);
            throw new RuntimeException(ex);
        }
    }

    private GatewayURL createGatewayURL() {
        GatewayURL gatewayURL = new GatewayURL(serviceURL);
        for (String name : parameters.keySet()) {
            gatewayURL.append(new URLParameter(name, parameters.get(name)));
        }
        return gatewayURL;
    }

    public String getMessageStatus(GatewayResponse response) {
        return sentMessageStatus;
    }

    public MStatus mapMessageStatus(GatewayResponse response) {
        return messageHandler.parseMessageStatus(response.getResponseText());
    }

    public GatewayMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(GatewayMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void setSentMessageStatus(String sentMessageStatus) {
        this.sentMessageStatus = sentMessageStatus;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
