/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.omp.manager;

import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;

/**
 * Handles all interactions with a remote sms gateway
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public interface SMSGatewayManager extends GatewayManager {

    /**
     * Sends an sms message
     * @param messageDetails details of message to be sent
     * @return
     */
    public GatewayResponse send(GatewayRequest messageDetails);
}
