/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.omp.service.CacheService;
import com.dreamoval.motech.omp.service.MessagingService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Aug 3, 2009
 */
public class OMPManagerImpl implements OMPManager, ApplicationContextAware {
    ApplicationContext context;
    private static Logger logger = Logger.getLogger(OMPManagerImpl.class);

    /**
     * sets the current application context
     * @param applicationContext the ApplicationContext object to set
     * @throws org.springframework.beans.BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * @see OMPManager.createGatewayMessageHandler()
     */
    public GatewayMessageHandler createGatewayMessageHandler() {
        logger.info("Fetching a wired GatewayMessageHandler object");
        return (GatewayMessageHandler)context.getBean("messageHandler");
    }

    /**
     * @see OMPManager.createSMSGatewayManager()
     */
    public GatewayManager createGatewayManager() {
        logger.info("Fetching a wired GatewayManager object");
        return (GatewayManager)context.getBean("gatewayManager");
    }

    /**
     * @see OMPManager.createSMSCacheService()
     */
    public CacheService createCacheService() {
        logger.info("Fetching a wired CacheService object");
        return (CacheService)context.getBean("smsCache");
    }

    /**
     * @see OMPManager.createSMSService()
     */
    public MessagingService createMessagingService() {
        logger.info("Fetching a wired MessagingService object");
        return (MessagingService)context.getBean("smsService");
    }

}
