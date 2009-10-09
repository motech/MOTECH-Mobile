package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

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
    public GatewayRequestDetails constructMessage(MessageRequest messageData) {
        logger.info("Fetching message template");
        String template = fetchTemplate(messageData);
        
        logger.info("Initializing template parameters");
        Map<String, String> templateParams = new HashMap<String, String>();
        templateParams.put("RecipientName", messageData.getRecipientName());
        logger.debug(templateParams);
        
        logger.info("Parsing message template");
        String message = parseTemplate(template, templateParams);
        
        logger.info("Constructing GatewayRequest object");
       
        GatewayRequest gwReq = coreManager.createGatewayRequest(coreManager.createMotechContext());        
        gwReq.setDateFrom(messageData.getDateFrom());
        gwReq.setDateTo(messageData.getDateTo());
        gwReq.setRecipientsNumber(messageData.getRecipientNumber());
        gwReq.setMessage(message);
        gwReq.setMessageStatus(MStatus.SCHEDULED);
        //gwReq.setGatewayRequestDetails(gwReqDet);
        
        GatewayRequestDetails gwReqDet = coreManager.createGatewayRequestDetails(coreManager.createMotechContext());
        gwReqDet.setId(messageData.getId());
        gwReqDet.setMessage(message);
        gwReqDet.setMessageType(messageData.getMessageType());
        gwReqDet.setNumberOfPages(3);
        gwReqDet.getGatewayRequests().add(gwReq);
        
        logger.info("GatewayRequest object successfully constructed");
        logger.debug(gwReq);
            
        return gwReqDet;
    }

    /**
     * 
     * @see MessageStoreManager.parseTemplate
     */
    public String parseTemplate(String template, Map<String, String> templateParams) {
        String key, value;
        
        for(Entry<String, String> e : templateParams.entrySet()){
            key = "<"+ e.getKey() + ">";
            value = e.getValue();
                    
            template = template.replaceAll(key, value);
        }
        return template;
    }

    /**
     * 
     * @see MessageStoreManager.fetchTemplate
     */
    public String fetchTemplate(MessageRequest messageData) {
        MessageTemplate template = coreManager.createMessageTemplateDAO(coreManager.createMotechContext()).getTemplateByLangNotifMType(messageData.getLanguage(), messageData.getNotificationType(), messageData.getMessageType());
        
        if(template == null)
            throw new RuntimeException("No matching template found");
            
        return template.getTemplate();
    }

    public CoreManager getCoreManager() {
        return coreManager;
    }

    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    
}
