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

package org.motechproject.mobile.omi.service;

import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MessageRequest;

/**
 * <p>
 * This class enables the OMIService to properly propagate transactions. This was necessitated by the Spring AOP
 * </p>
 *
 * <p>
 * The methods define are annotated with the <code>REQUIRES_NEW</code> propagation option. This ensures that all
 * handled in these methods are saved on exiting.
 * </p>
 *
 * @author Henry Sampson(henry@dreamoval.com)
 */
public interface OMIServiceWorker {

    /**
     * Sends a {@link MessageRequest} object immediately to recipient.
     *
     * <p>
     * This involves
     * <li>Parsing the {@link MessageRequest} to a {@link org.motechproject.mobile.core.model.GatewayRequest}</li>
     * <li>Sending the <code>GatewayRequest</code> through the {@link org.motechproject.mobile.omp.MessagingService}</li>
     * <li>Update <code>GatewayRequest</code> and <code>MessageRequest</code></li>
     *
     * @param message <code>MessageRequest</code> to send
     * @param defaultLanguage language preference
     *
     * 
     */
    void processMessageRequest(MessageRequest message, Language defaultLanguage);

    /**
     * Syncs the status of a <code>GatewayRequest</code> to its corresponding <code>MessageRequest</code>
     * 
     * @param response the <code>Gateway</code> to sync
     */
    void processMessageResponse(GatewayResponse response);

    /**
     * Reschedules a <code>MessageRequest</code> for sending
     *
     * The schedule is only added if <code>maxRetries</code> on the <code>MessageRequest</code> has not been reached
     *
     * @param message <code>MessageRequest</code> to reschedule
     */
    void processMessageRetry(MessageRequest message);

    /**
     * Merges a <code>MessageRequest</code> to the persistent store.This is needed if a dirty version of the objects exists
     * within the same persistet context.
     *
     * @param mr <code>MessageReques<./code> to merge
     *
     */
    void mergeMessageNow(MessageRequest mr);

}
