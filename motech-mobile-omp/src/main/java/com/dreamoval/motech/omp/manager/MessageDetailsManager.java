/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.model.MessageDetails;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 30-Apr-2009
 */
public interface MessageDetailsManager {

    /**
     * @return the handler
     */
    GatewayMessageHandler getHandler();

    /**
     * @return the messageDetailsDao
     */
    MessageDetailsDAO getMessageDetailsDao();

    /**
     *
     * @see MessageDetailsManager.saveMessage
     */
    boolean saveMessage(MessageDetails messageDetails);

    /**
     *
     * @see MessageDetailsManager.saveMessage
     */
    boolean saveMessage(String messageDetails);

    /**
     * @param handler the handler to set
     */
    void setHandler(GatewayMessageHandler handler);

    /**
     * @param messageDetailsDao the messageDetailsDao to set
     */
    void setMessageDetailsDao(MessageDetailsDAO messageDetailsDao);

    /**
     *
     * @see MessageDetailsManager.updateMessage
     */
    boolean updateMessage(MessageDetails messageDetails);

    /**
     *
     * @see MessageDetailsManager.updateMessage
     */
    boolean updateMessage(String messageDetails);

}
