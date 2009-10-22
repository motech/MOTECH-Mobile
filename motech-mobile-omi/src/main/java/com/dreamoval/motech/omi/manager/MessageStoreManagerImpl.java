package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.util.MotechException;
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

    /**
     * 
     * @see MessageStoreManager.constructMessage
     */
    public GatewayRequestDetails constructMessage(MessageRequest messageData, MotechContext context) {      
        
        logger.info("Constructing GatewayRequest object");       
        GatewayRequest gwReq = coreManager.createGatewayRequest(context);        
        gwReq.setDateFrom(messageData.getDateFrom());
        gwReq.setDateTo(messageData.getDateTo());
        gwReq.setRecipientsNumber(messageData.getRecipientNumber());
        gwReq.setRequestId(messageData.getRequestId());
        
        GatewayRequestDetails gwReqDet = coreManager.createGatewayRequestDetails(context);
        gwReqDet.setId(messageData.getId());
        gwReqDet.setMessage(gwReq.getMessage());
        gwReqDet.setMessageType(messageData.getMessageType());
        gwReqDet.setNumberOfPages(3);
        
        try{
            logger.info("Fetching message template");
            String template = fetchTemplate(messageData, context);

            logger.info("Parsing message template");
            String message = parseTemplate(template, messageData.getPersInfos());

            gwReq.setMessage(message);
            gwReq.setMessageStatus(MStatus.SCHEDULED);
        }
        catch(MotechException ex){
            gwReq.setMessageStatus(MStatus.FAILED);
            gwReq.setMessage(null);
            
            GatewayResponse gwResp = coreManager.createGatewayResponse(context);
            gwResp.setGatewayRequest(gwReq);
            gwResp.setMessageStatus(MStatus.FAILED);
            gwResp.setResponseText(ex.getMessage());
            
            gwReq.getResponseDetails().add(gwResp);
        }       
        
        gwReqDet.getGatewayRequests().add(gwReq);
        logger.info("GatewayRequest object successfully constructed");
            
        return gwReqDet;
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
                    
            template = template.replaceAll(tag, value);
        }
        
        return template;
    }

    /**
     * 
     * @see MessageStoreManager.fetchTemplate
     */
    public String fetchTemplate(MessageRequest messageData, MotechContext context) {
        MessageTemplate template = coreManager.createMessageTemplateDAO(context).getTemplateByLangNotifMType(messageData.getLanguage(), messageData.getNotificationType(), messageData.getMessageType());
        
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
    
}
