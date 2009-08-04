/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.omi.service.OMIService;
import com.dreamoval.motech.omi.service.Patient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author Henry Sampson
 * Date Created: Jul 31, 2009
 */
public class OMIManagerImpl implements OMIManager, ApplicationContextAware{
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
     * creates a OMIService object
     * @return the created OMIService object
     */
    public OMIService createOMIService() {
        return (OMIService)context.getBean("omiService");
    }

    /**
     * creates a MessageStoreManager object
     * @return the created MessageStoreManager object
     */
    public MessageStoreManager createMessageStoreManager() {
        return (MessageStoreManager)context.getBean("storeManager");
    }

    /**
     * creates a new Patient object
     * @return the created Patient object
     */
    public Patient createPatient() {
        return (Patient)context.getBean("patient");
    }
    

}
