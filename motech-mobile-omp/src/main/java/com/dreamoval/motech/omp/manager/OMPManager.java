/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.omp.service.SMSCacheService;
import com.dreamoval.motech.omp.service.SMSService;

/**
 * <p>Provides access to OMP services and managers to external modules</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 */
public interface OMPManager{

    /**
     * creates a new GatewayManager object
     * @return the created GatewayManager object
     */
    //public GatewayManager createGatewayManager();

    /**
     * creates a new GatewayMessageHandler object
     * @return the created GatewayMessageHandler object
     */
    public GatewayMessageHandler createGatewayMessageHandler();

    /**
     * creates a new SMSGatewayManager object
     * @return the created SMSGatewayManager object
     */
    public SMSGatewayManager createSMSGatewayManager();

    /**
     * creates a new SMSCacheService object
     * @return the created SMSCacheService object
     */
    public SMSCacheService createSMSCacheService();

    /**
     * creates a new SMSService object
     * @return the created SMSService object
     */
    public SMSService createSMSService();
}
