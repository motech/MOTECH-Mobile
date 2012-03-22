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

package org.motechproject.mobile.omp.manager.rancard;

import org.apache.log4j.Logger;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import org.motechproject.mobile.omp.manager.utils.PostData;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Set;

/**
 * Handles all interactions with the OutReach Server message gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Sep 11, 2009
 */
public class RancardGatewayManagerImpl implements GatewayManager {

    private String serviceURL;
    private String user;
    private String password;
    private String sender;
    private String sentMessageStatus;
    private GatewayMessageHandler messageHandler;
    private static Logger logger = Logger.getLogger(RancardGatewayManagerImpl.class);

    public RancardGatewayManagerImpl() {
    }

    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails) {
        URL url = getURL(messageDetails);
        URLConnection urlConnection = openURLConnection();
        String gatewayResponse = sendMessageAndCaptureGatewayResponse(messageDetails, url.toString().split("\\?")[1], urlConnection);
        return messageHandler.parseMessageResponse(messageDetails, gatewayResponse);
    }

    private String sendMessageAndCaptureGatewayResponse(GatewayRequest messageDetails, String postData, URLConnection urlConnection) {
        BufferedReader inBufferedReader;
        String data;
        String gatewayResponse = "";
        try {
            urlConnection.setDoOutput(true);
            OutputStreamWriter connectionOutputStream = new OutputStreamWriter(urlConnection.getOutputStream());
            connectionOutputStream.write(postData);
            connectionOutputStream.flush();
            inBufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((data = inBufferedReader.readLine()) != null) {
                gatewayResponse += data + "\n";
            }
            //Close the connections to the url reader and writer
            connectionOutputStream.close();
            inBufferedReader.close();
        } catch (IOException ex) {
            logger.error("Error processing gateway request", ex);
            gatewayResponse = "";
        }
        messageDetails.setDateSent(new Date());
        return gatewayResponse;
    }

    private URLConnection openURLConnection() {
        URL url;
        URLConnection urlConnection;
        try {
            url = new URL(serviceURL);
            urlConnection = url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        } catch (MalformedURLException ex) {
            logger.fatal("Error initializing Rancard Gateway: invalid url", ex);
            throw new RuntimeException("Invalid gateway URL");
        } catch (IOException ex) {
            logger.fatal("Error iitializing Rancard Gateway: unable to open URL connection", ex);
            throw new RuntimeException("Could not connect to gateway");
        }
        return urlConnection;
    }

    private URL getURL(GatewayRequest messageDetails) {
        URI serviceURI = null;
        try {
             serviceURI = new URI(serviceURL);
        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        URI uri = null;
        URL url = null;
        String recipients = "";
        String numbers = messageDetails.getRecipientsNumber();
        String msg = (messageDetails.getMessage().length() <= 459) ? messageDetails.getMessage() : messageDetails.getMessage().substring(0, 455) + "...";
        String[] phoneNumbers = numbers.split(",");
        for (String number : phoneNumbers) {
            if (!recipients.isEmpty()) {
                recipients += ":";
            }
            recipients += number;
        }            
            
            try {
                uri = new URI(
                        serviceURI.getScheme(), 
                        serviceURI.getAuthority(), 
                        serviceURI.getPath(),
                        "username=" +user+
                        "&password="+password+
                        "&from="+ sender + 
                        "&to=" +recipients +
                        "&text=" + msg +
                        "&concat=" + String.valueOf(messageDetails.getGatewayRequestDetails().getNumberOfPages()), 
                        null);

                String request = uri.toString();
                System.out.println(request);
                System.out.println(uri.getQuery());
                url = uri.toURL();
                logger.debug("Post Data:\n"+uri.getSchemeSpecificPart());

            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       
        return url;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @param baseUrl the serviceURL to set
     */
    public void setBaseUrl(String baseUrl) {
        this.setServiceURL(baseUrl);
    }

    /**
     * @param sentMessageStatus the sentMessageStatus to set
     */
    public void setSentMessageStatus(String sentMessageStatus) {
        this.sentMessageStatus = sentMessageStatus;
    }

    /**
     * @param serviceURL the serviceURL to set
     */
    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }
}
