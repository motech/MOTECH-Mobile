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

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import java.util.Date;
import java.util.List;

/**
 * Handles all message saving and fetching operations
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface CacheService {

    /**
     * Saves a message to the database
     *
     * @param messageDetails {@link org.motechproject.mobile.core.model.GatewayRequest} object to be saved
     * @return void
     */
    public void saveMessage(GatewayRequest messageDetails);
    
    /**
     * Saves a message to the database
     *
     * @param messageDetails {@link org.motechproject.mobile.core.model.GatewayRequestDetails} object to be saved
     * @return void
     */
    public void saveMessage(GatewayRequestDetails messageDetails);
    
    /**
     * Saves a message response to the database
     * @param messageDetails {@link org.motechproject.mobile.core.model.GatewayResponse} object to be saved
     * @return void
     */
    public void saveResponse(GatewayResponse responseDetails);

    /**
     * Fetches messages matching specified criteria
     * 
     * @param criteria A {@link org.motechproject.mobile.core.model.GatewayRequest} object with property values matching the messages to be fetched
     * @return A list of {@link org.motechproject.mobile.core.model.GatewayRequest} objects
     */
    public List<GatewayRequest> getMessages(GatewayRequest criteria);
      
    /**
     * Fetches messages with specified status
     * 
     * @param status The status of messages to be fetched
     * @return A list of {@link org.motechproject.mobile.core.model.GatewayRequest} objects matching specified criteria
     */
    public List<GatewayRequest> getMessagesByStatus(MStatus status);

    /**
     * Fetches messages with specified status and schedule
     *
     * @param status The status of messages to be fetched
     * @param schedule The date for which scheduled messages should be fetched
     * @return A list of {@link org.motechproject.mobile.core.model.GatewayRequest} objects matching the specified criteria
     */
    public List<GatewayRequest> getMessagesByStatusAndSchedule(MStatus status, Date schedule);
            
    /**
     * Fetches all GatewayResponse objects matching the specified criteria
     *
     * @param criteria A {@link org.motechproject.mobile.core.model.GatewayResponse} object with property values matching the messages to be fetched
     * @return A list of {@link org.motechproject.mobile.core.model.GatewayResponse} objects matching the specified criteria
     */
    public List<GatewayResponse> getResponses(GatewayResponse criteria);
    
    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager();

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager);

    void mergeMessage(GatewayRequest messageDetails);

    void mergeMessage(MessageRequest messageDetails);
}
