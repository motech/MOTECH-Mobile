package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageTemplate;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.core.util.MotechException;
import java.util.Set;
import org.apache.log4j.Logger;
import org.motechproject.ws.NameValuePair;

/**
 * An implementation of the MessageStore interface
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-JULY-2009
 *
 */
public class MessageStoreManagerImpl implements MessageStoreManager {
    private static Logger logger = Logger.getLogger(MessageStoreManagerImpl.class);
    private CoreManager coreManager;
    private int maxConcat;
    private int charsPerSMS;
    private int concatAllowance;

    /**
     * 
     * @see MessageStoreManager.constructMessage
     */
    public GatewayRequest constructMessage(MessageRequest messageData, MotechContext context, Language defaultLang) {      
        
        GatewayRequest gwReq = coreManager.createGatewayRequest(context);        
        gwReq.setDateFrom(messageData.getDateFrom());
        gwReq.setDateTo(messageData.getDateTo());
        gwReq.setRecipientsNumber(messageData.getRecipientNumber());
        gwReq.setRequestId(messageData.getRequestId());
        gwReq.setTryNumber(messageData.getTryNumber());
        gwReq.setMessageRequest(messageData);
        
        GatewayRequestDetails gatewayDetails = coreManager.createGatewayRequestDetails(context);
        gatewayDetails.setMessageType(messageData.getMessageType());
                
        try{
        	
        	if ( messageData.getMessageType() == MessageType.TEXT ) {

        		String template = fetchTemplate(messageData, context, defaultLang);
        		logger.debug("message template fetched");
        		logger.debug(template);

        		String message = parseTemplate(template, messageData.getPersInfos());
        		logger.debug("message contructed");
        		logger.debug(message);

        		int maxLength = (charsPerSMS - concatAllowance) * maxConcat - 1;
        		message = message.length() <= maxLength ? message : message.substring(0, maxLength);

        		int numPages = (int)Math.ceil(message.length() % (charsPerSMS - concatAllowance));
        		gatewayDetails.setNumberOfPages(numPages);

        		gwReq.setMessage(message);
                gatewayDetails.setMessage(message);
                
        	}

            gwReq.setMessageStatus(MStatus.SCHEDULED);
            gwReq.setGatewayRequestDetails(gatewayDetails);

        }
        catch(MotechException ex){
        	logger.error("MotechException: " + ex.getMessage());
            gwReq.setMessageStatus(MStatus.FAILED);
            gwReq.setMessage(null);
            
            GatewayResponse gwResp = coreManager.createGatewayResponse(context);
            gwResp.setGatewayRequest(gwReq);
            gwResp.setMessageStatus(MStatus.FAILED);
            gwResp.setResponseText(ex.getMessage());
            
            gwReq.getResponseDetails().add(gwResp);
        }       
        
        gatewayDetails.getGatewayRequests().add(gwReq);
        logger.debug("GatewayRequest object successfully constructed");
        logger.debug(gatewayDetails);
            
        return gwReq;
    }

    /**
     * 
     * @see MessageStoreManager.parseTemplate
     */
    public String parseTemplate(String template, Set<NameValuePair> templateParams) {
        String tag, value;  
        
        if(templateParams == null){
            return template;
        }
        
        for(NameValuePair detail : templateParams){
            tag = "<"+ detail.getName() + ">";
            value = detail.getValue();

            if(value != null && !value.isEmpty())
                template = template.replaceAll(tag, value);
        }
        
        return template;
    }

    /**
     * 
     * @see MessageStoreManager.fetchTemplate
     */
    public String fetchTemplate(MessageRequest messageData, MotechContext context, Language defaultLang) {        
        if(messageData.getNotificationType() == null)
            return "";
        
        MessageTemplate template = coreManager.createMessageTemplateDAO(context).getTemplateByLangNotifMType(messageData.getLanguage(), messageData.getNotificationType(), messageData.getMessageType(), defaultLang);
        
        if(template == null)
            throw new MotechException("No such NotificationType found");
            
        return template.getTemplate();
    }

    public CoreManager getCoreManager() {
        return coreManager;
    }

    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    /**
     * @param maxConcat the maxConcat to set
     */
    public void setMaxConcat(int maxConcat) {
        this.maxConcat = maxConcat;
    }

    /**
     * @param charsPerSMS the charsPerSMS to set
     */
    public void setCharsPerSMS(int charsPerSMS) {
        this.charsPerSMS = charsPerSMS;
    }

    /**
     * @param concatAllowance the concatAllowance to set
     */
    public void setConcatAllowance(int concatAllowance) {
        this.concatAllowance = concatAllowance;
    }
    
}
