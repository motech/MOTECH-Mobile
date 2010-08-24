/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.omp.service;

import java.util.Map;
import java.util.Set;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.omp.manager.GatewayManager;

/**
 *
 * @author Kweku
 */
public interface SMSMessagingServiceWorker {

    Map<Boolean, Set<GatewayResponse>> sendMessage(GatewayRequest messageDetails);

    void updateMessageStatus(GatewayResponse response);

    /**
     * @return the cache
     */
    CacheService getCache();

    /**
     * @return the gatewayManager
     */
    GatewayManager getGatewayManager();

    /**
     * @param cache the cache to set
     */
    void setCache(CacheService cache);

    /**
     * @param gatewayManager the gatewayManager to set
     */
    void setGatewayManager(GatewayManager gatewayManager);

}
