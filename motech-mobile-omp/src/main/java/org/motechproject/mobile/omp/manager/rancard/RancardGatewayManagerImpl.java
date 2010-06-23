package org.motechproject.mobile.omp.manager.rancard;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.omp.manager.GatewayManager;
import org.motechproject.mobile.omp.manager.GatewayMessageHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * Handles all interactions with the OutReach Server message gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * @date Sep 11, 2009
 */
public class RancardGatewayManagerImpl implements GatewayManager {

    private String serviceURL = "http://app.rancardmobility.com/rmcs/sendMessage.jsp?";
    private String user;
    private String password;
    private String sender;
    private String sentMessageStatus;
    private GatewayMessageHandler messageHandler;
    private static Logger logger = Logger.getLogger(RancardGatewayManagerImpl.class);

    public RancardGatewayManagerImpl() {
    }

    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails, MotechContext context) {
        String postData = "";
        try {
            postData += "&username=" + URLEncoder.encode(user, "UTF-8");
            postData += "&password=" + URLEncoder.encode(password, "UTF-8");
            postData += "&text=" + URLEncoder.encode(URLEncoder.encode(messageDetails.getMessage(), "UTF-8"), "UTF-8");
            postData += "&from=" + URLEncoder.encode(sender, "UTF-8");
            postData += "&concat=" + URLEncoder.encode(String.valueOf(messageDetails.getGatewayRequestDetails().getNumberOfPages()), "UTF-8");
            String recipients = "";
            String numbers = messageDetails.getRecipientsNumber();
            String[] nums = numbers.split(",");
            for (String num : nums) {
                if (num.startsWith("23320")) {
                    num += "+";
                }
                if (!recipients.isEmpty()) {
                    recipients += ":";
                }
                recipients += num;
            }
            postData += "&to=" + URLEncoder.encode(recipients, "UTF-8");
            logger.debug("Post Data:\n"+postData);
        } catch (UnsupportedEncodingException ex) {
            logger.fatal("Error building request params: invalid encoding", ex);
        }

        //Create a url and open a connection to it
        URL url;
        URLConnection conn;
        try {
            url = new URL(serviceURL);
            conn = url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        } catch (MalformedURLException ex) {
            logger.fatal("Error initializing Rancard Gateway: invalid url", ex);
            throw new RuntimeException("Invalid gateway URL");
        } catch (IOException ex) {
            logger.fatal("Error iitializing Rancard Gateway: unable to open URL connection", ex);
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
            while ((data = in.readLine()) != null) {
                gatewayResponse += data + "\n";
            }
            //Close the connections to the url reader and writer
            out.close();
            in.close();
        } catch (IOException ex) {
            logger.error("Error processing gateway request", ex);
            gatewayResponse = ex.getMessage();
        }
        messageDetails.setDateSent(new Date());
        //Convert the response to a standard format
        return messageHandler.parseMessageResponse(messageDetails, gatewayResponse, context);

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
     * @param serviceURL the serviceURL to set
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
