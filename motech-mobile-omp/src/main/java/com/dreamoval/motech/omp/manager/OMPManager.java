/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.service.SMSCacheService;
import com.dreamoval.motech.omp.service.SMSService;

/**
 *
 * @author Kofi A. Asamoah
 * Date Created: Jul 31, 2009
 *
 * <p>Provides access to OMP services and managers to external modules</p>
 */
public class OMPManager{
    private SMSService smsService;
    private SMSCacheService cacheService;

    /**
     * Calls the sendTextMessage method of the OMP SMSService
     * @see SMSService.sendTextMessage
     */
    public Long sendTextMessage(MessageDetails messageDetails) {
        return this.getSmsService().sendTextMessage(messageDetails);
    }

    /**
     * Calls the sendTextMessage method of the OMP SMSService
     * @see SMSService.sendTextMessage
     */
    public Long sendTextMessage(String messageDetails) {
        return this.getSmsService().sendTextMessage(messageDetails);
    }

    /**
     * Calls the saveMessage method of the OMP SMSCacheService
     * @see SMSCacheService.saveMessage
     */
    public void saveMessage(MessageDetails messageDetails) {
        this.getCacheService().saveMessage(messageDetails);
    }

    /**
     * Calls the saveMessage method of the OMP SMSCacheService
     * @see SMSCacheService.saveMessage
     */
    public void saveMessage(String messageDetails) {
        this.getCacheService().saveMessage(messageDetails);
    }

    /**
     * Calls the updateMessage method of the OMP SMSCacheService
     * @see SMSCacheService.updateMessage
     */
    public boolean updateMessage(MessageDetails messageDetails) {
        return this.getCacheService().updateMessage(messageDetails);
    }

    /**
     * Calls the updateMessage method of the OMP SMSCacheService
     * @see SMSCacheService.updateMessage
     */
    public boolean updateMessage(String messageDetails) {
        return this.getCacheService().updateMessage(messageDetails);
    }

    /**
     * @return the smsService
     */
    public SMSService getSmsService() {
        return smsService;
    }

    /**
     * @param smsService the smsService to set
     */
    public void setSmsService(SMSService smsService) {
        this.smsService = smsService;
    }

    /**
     * @return the cacheService
     */
    public SMSCacheService getCacheService() {
        return cacheService;
    }

    /**
     * @param cacheService the cacheService to set
     */
    public void setCacheService(SMSCacheService cacheService) {
        this.cacheService = cacheService;
    }
    
}
