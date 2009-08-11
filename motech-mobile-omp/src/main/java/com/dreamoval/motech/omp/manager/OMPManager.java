/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.omp.service.CacheService;
import com.dreamoval.motech.omp.service.MessagingService;

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
    public GatewayManager createGatewayManager();

    /**
     * creates a new CacheService object
     * @return the created CacheService object
     */
    public CacheService createCacheService();

    /**
     * creates a new MessagingService object
     * @return the created MessagingService object
     */
    public MessagingService createMessagingService();
}
