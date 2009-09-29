package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageTemplate;
import java.util.HashMap;
import java.util.List;
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
    public GatewayRequest constructMessage(MessageRequest messageData) {
        logger.info("Fetching message template");
        String template = fetchTemplate(messageData);
        
        logger.info("Initializing template parameters");
        Map<String, String> templateParams = new HashMap<String, String>();
        templateParams.put("RecipientName", messageData.getRecipient_name());
        logger.debug(templateParams);
        
        logger.info("Parsing message template");
        String message = parseTemplate(template, templateParams);
        
        logger.info("Constructing GatewayRequest object");
        GatewayRequest gwReq = coreManager.createGatewayRequest(coreManager.createMotechContext());
        gwReq.setDateFrom(messageData.getDate_from());
        gwReq.setDateTo(messageData.getDate_to());
        gwReq.setRecipientsNumber(messageData.getRecipient_number());
        gwReq.setMessage(message);
        gwReq.setRequestId(messageData.getId());
        
        logger.info("GatewayRequest object successfully constructed");
        logger.debug(gwReq);
            
        return gwReq;
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
        MessageTemplate template = coreManager.createMessageTemplate(coreManager.createMotechContext());
        template.setLanguage(messageData.getLanguage());
        //template.setNotification_type(messageData.getMessage_type());
        
        List<MessageTemplate> templates = coreManager.createMessageTemplateDAO(coreManager.createMotechContext()).findByExample(template);
        return templates.get(0).getLanguage();
    }

    public CoreManager getCoreManager() {
        return coreManager;
    }

    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    
}
