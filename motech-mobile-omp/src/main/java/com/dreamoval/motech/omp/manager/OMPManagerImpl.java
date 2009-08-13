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
        try{
            return (GatewayMessageHandler)context.getBean("messageHandler");
        }
        catch(Exception ex){
            logger.error("GatewayMessageHandler creation failed", ex);
            return null;
        }
    }

    /**
     * @see OMPManager.createSMSGatewayManager()
     */
    public GatewayManager createGatewayManager() {
        logger.info("Fetching a wired GatewayManager object");
        try{
            return (GatewayManager)context.getBean("gatewayManager");
        }
        catch(Exception ex){
            logger.fatal("GatewayManager creation failed", ex);
            throw new RuntimeException("Unable to create gateway");
        }
    }

    /**
     * @see OMPManager.createSMSCacheService()
     */
    public CacheService createCacheService() {
        logger.info("Fetching a wired CacheService object");
        try{
            return (CacheService)context.getBean("smsCache");
        }
        catch(Exception ex){
            logger.fatal("CacheService creation failed", ex);
            throw new RuntimeException("Unable to initialize cache");
        }
    }

    /**
     * @see OMPManager.createSMSService()
     */
    public MessagingService createMessagingService() {
        logger.info("Fetching a wired MessagingService object");
        try{
            return (MessagingService)context.getBean("smsService");
        }
        catch(Exception ex){
            logger.fatal("MessagingService creation failed", ex);
            throw new RuntimeException("Unable to initialize messaging service");
        }
    }

}
