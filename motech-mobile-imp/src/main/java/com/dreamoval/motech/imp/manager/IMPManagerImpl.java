/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.manager;

import com.dreamoval.motech.imp.serivce.IMPService;
import com.dreamoval.motech.imp.util.CommandAction;
import com.dreamoval.motech.imp.util.IncomingMessageFormValidator;
import com.dreamoval.motech.imp.util.IncomingMessageParser;
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
