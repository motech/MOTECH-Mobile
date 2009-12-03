/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import java.util.Set;
import org.motechproject.ws.NameValuePair;

/**
 *
 * @author user
 */
public interface IncomingMessageParser {
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
    Set<NameValuePair> getParams(String message);
}
