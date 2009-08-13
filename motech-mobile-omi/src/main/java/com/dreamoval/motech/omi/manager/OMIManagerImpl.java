/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.omi.service.OMIService;
import com.dreamoval.motech.omi.service.Patient;
import com.dreamoval.motech.omi.service.PatientImpl;
import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(OMIManagerImpl.class);

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
        logger.info("Fetching a wired OMIService object");

        try{
            return (OMIService)context.getBean("omiService");
        }
        catch(Exception ex){
            logger.fatal("OMIService creation failed", ex);
            throw new RuntimeException("Service creation failure: OMI service unavailable.");
        }
    }

    /**
     * creates a MessageStoreManager object
     * @return the created MessageStoreManager object
     */
    public MessageStoreManager createMessageStoreManager() {
        logger.info("Fetching a wired MessageStore object");
        
        try{
            return (MessageStoreManager)context.getBean("storeManager");
        }
        catch(Exception ex){
            logger.error("MessageStoreManager creation failed", ex);
            return null;
        }
    }

    /**
     * creates a new Patient object
     * @return the created Patient object
     */
    public Patient createPatient() {
        logger.info("Creating a new Patient object");
        return new PatientImpl();
    }
    

}
