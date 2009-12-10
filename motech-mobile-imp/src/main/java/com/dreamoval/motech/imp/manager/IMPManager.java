/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.manager;

import com.dreamoval.motech.imp.serivce.IMPService;
import com.dreamoval.motech.imp.util.CommandAction;
import com.dreamoval.motech.imp.util.IncomingMessageFormParameterValidator;
import com.dreamoval.motech.imp.util.IncomingMessageFormValidator;
import com.dreamoval.motech.imp.util.IncomingMessageParser;

/**
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public interface IMPManager {

    /**
     * Creates a wired IMPService instance
     * @return the created IMPService
     */
    IMPService createIMPService();

    /**
     * Creates a wired IncomingMessageParser instance
     * @return the created IncomingMessageParser
     */
    IncomingMessageParser createIncomingMessageParser();

    /**
     * Creates a wired IncomingMessageFormParameter instance
     * @return the created IncomingMessageFormParameter
     */
    IncomingMessageFormValidator createIncomingMessageFormValidator();

    /**
     * Creates a wired IncomingMessageFormParameterValidator instance
     * @return the created IncomingMessageFormParameterValidator
     */
    IncomingMessageFormParameterValidator createIncomingMessageFormParameterValidator();

    /**
     * Creates a wired CommandAction instance
     * @return the created CommandAction
     */
    CommandAction createCommandAction();

}
