/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-Apr-2009
 *
 * <p>Handles all operations associated with the message store</p>
 */
public class MessageStoreManagerImpl implements MessageStoreManager {
    private Map<String, String> messageStore;
    private static Logger logger = Logger.getLogger(MessageStoreManagerImpl.class);
    /**
     *
     * @param key The identifier of the message to return
     * @return The message associated with the supplied key
     */
    public String getMessage(String key){
        logger.info("Retrieving message with key: " + key);
        return messageStore.get(key);
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
