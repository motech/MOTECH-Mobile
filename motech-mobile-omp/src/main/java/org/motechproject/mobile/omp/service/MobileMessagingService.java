/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2010-11 The Trustees of Columbia University in the City of
 * New York and Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, Columbia University, or
 * their respective contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA, COLUMBIA UNIVERSITY
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION
 * USA, COLUMBIA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.motechproject.mobile.omp.service;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.omp.manager.GatewayManager;
import java.util.Map;
import java.util.Set;

/**
 * Handles all SMS related functions including cachine, sending and lookup
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface MobileMessagingService {

    /**
     * Queues a message for delivery
     * 
     * @param message The {@link org.motechproject.mobile.core.model.GatewayRequest} object to be scheduled
     */
    public void scheduleMessage(GatewayRequest message);
    
    /**
     * Queues a message for delivery
     * 
     * @param message The {@link org.motechproject.mobile.core.model.GatewayRequestDetails} object to be scheduled
     */
    public void scheduleMessage(GatewayRequestDetails message);
    
    /**
     * Sends all messages due for delivery
     *
     */
    public void sendScheduledMessages();
    
    /**
     * Sends a message with the provided details
     *
     * @param messageDetails The {@link org.motechproject.mobile.core.model.GatewayRequest} object containing information about the message to be sent
     * @return The id of the message sent
     */
    public Map<Boolean, Set<GatewayResponse>> sendTransactionalMessage(GatewayRequest messageDetails);

    /**
     * Queries a message gateway and updates the delivery status of all pending messages
     */
    public void updateMessageStatuses();


    /**
     * @return the cache
     */
    public CacheService getCache();

    /**
     * @param cache the cache to set
     */
    public void setCache(CacheService cache);

    /**
     * @return the gatewayManager
     */
    public GatewayManager getGatewayManager();

    /**
     * @param gatewayManager the gatewayManager to set
     */
    public void setGatewayManager(GatewayManager gatewayManager);
    Map<Boolean, Set<GatewayResponse>> sendMessage(GatewayRequest messageDetails);

    void scheduleTransactionalMessage(GatewayRequest message);
}
