/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.omp.service.SMSCacheService;
import com.dreamoval.motech.omp.service.SMSService;
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
        return (GatewayMessageHandler)context.getBean("messageHandler");
    }

    /**
     * @see OMPManager.createSMSGatewayManager()
     */
    public SMSGatewayManager createSMSGatewayManager() {
        return (SMSGatewayManager)context.getBean("smsGatewayManager");
    }

    /**
     * @see OMPManager.createSMSCacheService()
     */
    public SMSCacheService createSMSCacheService() {
        return (SMSCacheService)context.getBean("smsCache");
    }

    /**
     * @see OMPManager.createSMSService()
     */
    public SMSService createSMSService() {
        return (SMSService)context.getBean("smsService");
    }

}
