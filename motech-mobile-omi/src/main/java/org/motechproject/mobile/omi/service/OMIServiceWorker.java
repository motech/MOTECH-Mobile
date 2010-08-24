/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.omi.service;

import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MessageRequest;

/**
 *
 * @author Kweku
 */
public interface OMIServiceWorker {

    void processMessageRequest(MessageRequest message, Language defaultLanguage);

    void processMessageResponse(GatewayResponse response);

    void processMessageRetry(MessageRequest message);

    void mergeMessageNow(MessageRequest mr);

}
