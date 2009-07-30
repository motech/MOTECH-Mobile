/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import java.util.Map;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-Apr-2009
 *
 * <p>Handles all operations associated with the message store</p>
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
