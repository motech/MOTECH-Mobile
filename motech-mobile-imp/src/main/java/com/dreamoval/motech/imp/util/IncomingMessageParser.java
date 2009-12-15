/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public interface IncomingMessageParser {

    /**
     * Generates an IncomingMessage from a message request
     * @param message content of request
     * @return the generated IncomingMessag object
     */
    IncomingMessage parseRequest(String message);

    /**
     * Extracts command part of message
     * @param message the text to parse
     * @return the command string
     */
    String getCommand(String message);

    /**
     * Extracts form code from message
     * @param message the text to parse
     * @return the form code
     */
    String getFormCode(String message);

    /**
     * Extracts parameters from message
     * @param message the text to parse
     * @return the message parameters
     */
    Map<String,IncomingMessageFormParameter> getParams(String message);
}
