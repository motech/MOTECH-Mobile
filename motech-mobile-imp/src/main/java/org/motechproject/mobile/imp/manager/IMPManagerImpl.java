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
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Handles creation of IMP objects
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public class IMPManagerImpl implements ApplicationContextAware, IMPManager{
    ApplicationContext context;

    /**
     * Creates a wired IMPService instance
     * @return the created IMPService
     */
    public IMPService createIMPService(){
        return (IMPService)context.getBean("impService");
    }

    /**
     * Creates a wired IncomingMessageParser instance
     * @return the created IncomingMessageParser
     */
    public IncomingMessageParser createIncomingMessageParser(){
        return (IncomingMessageParser)context.getBean("imParser");
    }

    /**
     * Returns the spring managed instance of IncomingMessageXMLParser
     *
     * @return an instance of IncomingMessageXMLParser
     */
    public IncomingMessageXMLParser createIncomingMessageXMLParser(){
        return (IncomingMessageXMLParser)context.getBean("imXMLParser");
    }

    /**
     * Creates a wired IncomingMessageFormValidator instance
     * @return the created IncomingMessageFormValidator
     */
    public IncomingMessageFormValidator createIncomingMessageFormValidator(){
        return (IncomingMessageFormValidator)context.getBean("imFormValidator");
    }

    public CommandAction createCommandAction() {
        return (CommandAction)context.getBean("formCmdAxn");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
