/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;

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
