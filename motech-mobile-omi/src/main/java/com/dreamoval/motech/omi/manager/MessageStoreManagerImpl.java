package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.util.MotechException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
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
    public GatewayRequestDetails constructMessage(MessageRequest messageData, MotechContext context) {
        logger.info("Fetching message template");
        String template = fetchTemplate(messageData, context);
        
        logger.info("Initializing template parameters");
        Map<String, String> templateParams = new HashMap<String, String>();
        templateParams.put("RecipientName", messageData.getRecipientName());
        logger.debug(templateParams);
        
        logger.info("Parsing message template");
        String message = parseTemplate(template, templateParams);
        
        logger.info("Constructing GatewayRequest object");
       
        GatewayRequest gwReq = coreManager.createGatewayRequest(context);        
        gwReq.setDateFrom(messageData.getDateFrom());
        gwReq.setDateTo(messageData.getDateTo());
        gwReq.setRecipientsNumber(messageData.getRecipientNumber());
        gwReq.setMessage(message);
        gwReq.setMessageStatus(MStatus.SCHEDULED);
        
        GatewayRequestDetails gwReqDet = coreManager.createGatewayRequestDetails(context);
        gwReqDet.setId(messageData.getId());
        gwReqDet.setMessage(message);
        gwReqDet.setMessageType(messageData.getMessageType());
        gwReqDet.setNumberOfPages(3);
        gwReqDet.getGatewayRequests().add(gwReq);
        
        logger.info("GatewayRequest object successfully constructed");
            
        return gwReqDet;
    }

    /**
     * 
     * @see MessageStoreManager.parseTemplate
     */
    public String parseTemplate(String template, Map<String, String> templateParams) {
        String key, value;  
        Pattern p = Pattern.compile("<.*>");
        
        for(Entry<String, String> e : templateParams.entrySet()){
            key = "<"+ e.getKey() + ">";
            value = e.getValue();
                    
            template = template.replaceAll(key, value);
        }
        
        if(p.matcher(template).find()){
            throw new MotechException("Parameters did not match template.");
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
            throw new MotechException("No matching template found");
            
        return template.getTemplate();
    }

    public CoreManager getCoreManager() {
        return coreManager;
    }

    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    
}
