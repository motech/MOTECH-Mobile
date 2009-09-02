package com.dreamoval.motech.omi.manager;

import java.util.Map;

/**
 * An interface for manipulating stored message templates
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-Apr-2009
 *
 */
public interface MessageStoreManager {

    /**
     *
     * @param key The identifier of the message to return
     * @return The message associated with the supplied key
     */
    String getMessage(String key);

    /**
     * @return the messageStore
     */
    Map<String, String> getMessageStore();

    /**
     * @param messageStore the messageStore to set
     */
    void setMessageStore(Map<String, String> messageStore);

}
