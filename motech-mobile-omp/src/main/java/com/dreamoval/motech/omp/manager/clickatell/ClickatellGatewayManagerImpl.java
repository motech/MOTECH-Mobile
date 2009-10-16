package com.dreamoval.motech.omp.manager.clickatell;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.omp.manager.GatewayManager;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * Handles all interactions with the OutReach Server message gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Sep 11, 2009
 */
public class ClickatellGatewayManagerImpl implements GatewayManager{

    private static String BASEURL = "https://api.clickatell.com/http/";

    private String postData;
    private String apiId;
    private String user;
    private String password;
    private String sender;
    private String deliveryAcknowledge;
    private String callback;
    private Properties clickProps;
    private GatewayMessageHandler messageHandler;
    private static Logger logger = Logger.getLogger(ClickatellGatewayManagerImpl.class);

    public ClickatellGatewayManagerImpl(){
    }

    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails) {
        try {
            postData = "api_id=" + URLEncoder.encode(apiId, "UTF-8");
            postData += "&user=" + URLEncoder.encode(user, "UTF-8");
            postData += "&password=" + URLEncoder.encode(password, "UTF-8");
            postData += "&to=" + URLEncoder.encode(messageDetails.getRecipientsNumber(), "UTF-8");
            postData += "&text=" + URLEncoder.encode(messageDetails.getMessage(), "UTF-8");
            postData += "&from=" + URLEncoder.encode(sender, "UTF-8");
            postData += "&concat=" + URLEncoder.encode(String.valueOf(messageDetails.getDateTo()), "UTF-8");
            postData += "&deliv_ack=" + URLEncoder.encode(deliveryAcknowledge, "UTF-8");
            postData += "&callback=" + URLEncoder.encode(callback, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            logger.fatal("Error constructing request: parameter encoding failed", ex);
            throw new RuntimeException("Error constructing message");
        }

            //Create a url and open a connection to it
        URL url;
        URLConnection conn;
        
        try {
            url = new URL(BASEURL + "sendmsg");
            conn = url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        } 
        catch (MalformedURLException ex) {
            logger.fatal("Error initializing Clikatell Gateway: invalid url", ex);
            throw new RuntimeException("Invalid gatewat URL");
        }
        catch (IOException ex) {
            logger.fatal("Error iitializing Clickatell Gateway: unable to open URL connection", ex);
            throw new RuntimeException("Could not connect to gateway");
        } 
        //Read in the gateway response
        BufferedReader in;
        String data = "";
        String gatewayResponse = "";

        //Flush the post data to the url
        try {
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(postData);
            out.flush();
        
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((data = in.readLine()) != null)
                gatewayResponse += data + "\n";

            //Close the connections to the url reader and writer
            out.close();
            in.close();
        } 
        catch (IOException ex) {
            logger.fatal("Error processing gateway request", ex);
            throw new RuntimeException("Unable to communicate with gateway");
        }
        
        //Convert the response to a standard format
        return messageHandler.parseMessageResponse(messageDetails, gatewayResponse);
    }

    public String getMessageStatus(GatewayResponse response) {
        try {
            postData = "api_id=" + URLEncoder.encode(apiId, "UTF-8");
            postData += "&user=" + URLEncoder.encode(user, "UTF-8");
            postData += "&password=" + URLEncoder.encode(password, "UTF-8");
            postData += "&apimsgid=" + URLEncoder.encode(response.getGatewayMessageId(), "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            logger.fatal("Error constructing request: parameter encoding failed", ex);
            throw new RuntimeException("Error constructing message");
        }

            //Create a url and open a connection to it
        URL url;
        URLConnection conn;

        try {
            url = new URL(BASEURL + "querymsg");
            conn = url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        }
        catch (MalformedURLException ex) {
            logger.fatal("Error initializing Clikatell Gateway: invalid url", ex);
            throw new RuntimeException("Invalid gatewat URL");
        }
        catch (IOException ex) {
            logger.fatal("Error iitializing Clickatell Gateway: unable to open URL connection", ex);
            throw new RuntimeException("Could not connect to gateway");
        }
        //Read in the gateway response
        BufferedReader in;
        String data = "";
        String gatewayResponse = "";

        //Flush the post data to the url
        try {
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(postData);
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((data = in.readLine()) != null)
                gatewayResponse += data + "\n";

            //Close the connections to the url reader and writer
            out.close();
            in.close();
        }
        catch (IOException ex) {
            logger.fatal("Error processing gateway request", ex);
            throw new RuntimeException("Unable to communicate with gateway");
        }

        return gatewayResponse;
    }

    public MStatus mapMessageStatus(GatewayResponse response) {
        return messageHandler.parseMessageStatus(response.getResponseText());
    }

    public GatewayMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(GatewayMessageHandler messageHandler) {
        logger.debug("Setting ClickatellGatewayManagerImpl.messageHandler");
        logger.debug(messageHandler);
        this.messageHandler = messageHandler;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
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

    public String getDeliveryAcknowledge() {
        return deliveryAcknowledge;
    }

    public void setDeliveryAcknowledge(String deliveryAcknowledge) {
        this.deliveryAcknowledge = deliveryAcknowledge;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

}
