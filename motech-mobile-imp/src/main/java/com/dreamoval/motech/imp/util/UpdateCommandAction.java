/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.model.imp.IncomingMessage;
import com.dreamoval.motech.model.imp.IncomingMessageResponse;

/**
 * Resumes processing of an IncomingMessageForm
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public class UpdateCommandAction implements CommandAction{
    CoreManager coreManager;
    IncomingMessageParser parser;

    /**
     *
     * @see CommandAction.execute
     */
    public IncomingMessageResponse execute(IncomingMessage message, String requesterPhone) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}