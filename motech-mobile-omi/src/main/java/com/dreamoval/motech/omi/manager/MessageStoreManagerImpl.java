package com.dreamoval.motech.omi.manager;

import java.util.Map;
import org.apache.log4j.Logger;

/**
 * An implementation of the MessageStore interface
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-Apr-2009
 *
 */
public class MessageStoreManagerImpl implements MessageStoreManager {
    private Map<String, String> messageStore;
    private static Logger logger = Logger.getLogger(MessageStoreManagerImpl.class);

    /**
     * Retrieves a message with a specified key from the message store
     *
     * @param key The identifier of the message to return
     * @return The message associated with the supplied key
     */
    public String getMessage(String key){
        logger.info("Retrieving message from store");
        logger.debug("Key: " + key);
        try{
            return messageStore.get(key);
        }
        catch(Exception e){
            logger.error("Error retrieving message with key: " + key, e);
            logger.debug(messageStore);
            return null;
        }
    }

    /**
     * @return the messageStore
     */
    public Map<String, String> getMessageStore() {
        return messageStore;
    }

    /**
     * @param messageStore the messageStore to set
     */
    public void setMessageStore(Map<String, String> messageStore) {
        logger.debug("Setting MessageStoreManagerImpl.messageStore");
        logger.debug(messageStore);
        this.messageStore = messageStore;
    }
}
