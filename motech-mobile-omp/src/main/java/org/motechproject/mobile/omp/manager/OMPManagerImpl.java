package org.motechproject.mobile.omp.manager;

import org.motechproject.mobile.omp.service.CacheService;
import org.motechproject.mobile.omp.service.MessagingService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * An implementation of the OMPManager interface
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
        try{
            return (GatewayMessageHandler)context.getBean("orserveHandler");
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
        try{
            return (GatewayManager)context.getBean("orserveGateway");
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
        try{
            return (MessagingService)context.getBean("smsService");
        }
        catch(Exception ex){
            logger.fatal("MessagingService creation failed", ex);
            throw new RuntimeException("Unable to initialize messaging service");
        }
    }

}
