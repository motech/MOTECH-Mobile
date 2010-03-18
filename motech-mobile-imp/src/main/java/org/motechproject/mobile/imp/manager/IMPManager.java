/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.manager;

import org.motechproject.mobile.imp.serivce.IMPService;
import org.motechproject.mobile.imp.util.CommandAction;
import org.motechproject.mobile.imp.util.IncomingMessageFormValidator;
import org.motechproject.mobile.imp.util.IncomingMessageParser;
import org.motechproject.mobile.imp.util.IncomingMessageXMLParser;

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
     * Creates a wired CommandAction instance
     * @return the created CommandAction
     */
    CommandAction createCommandAction();

    /**
     * Returns the spring managed instance of IncomingMessageXMLParser
     *
     * @return an instance of IncomingMessageXMLParser
     */
    IncomingMessageXMLParser createIncomingMessageXMLParser();

}
