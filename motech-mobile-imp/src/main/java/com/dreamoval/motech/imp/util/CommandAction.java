/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.model.imp.IncomingMessageResponse;

/**
 * Processes a requested action on an IncomingMessage object
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */


public interface CommandAction {
    /**
     * Executes the associated command on the IncomingMessage object
     * @param message IncomingMessage on which the command should be executed
     * @param requesterPhone Phone number by which the request was made
     * @return IncomingMessageResponse generated as a result of command execution
     */
    IncomingMessageResponse execute(IncomingMessage message, String requesterPhone);
}
