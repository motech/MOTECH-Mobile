/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;

/**
 *
 * @author Kofi A. Asamoah
 * @email yoofi@dreamoval.com
 * @date 15-JUL-2009
 *
 * <p>Handles preparation and parsing of messages and responses from the OutReach Server message gateway</p>
 */
public class ORServeGatewayMessageHandlerImpl implements GatewayMessageHandler {

    /**
     *
     * @see GatewayMessageHandler.prepareMessage
     */
    public MessageDetails prepareMessage(String message) {
        System.out.println(message);
        return null;
    }

    /**
     *
     * @see GatewayMessageHandler.parseResponse
     */
    public ResponseDetails parseResponse(String gatewayResponse) {
        System.out.println(gatewayResponse);
        return null;
    }

}
