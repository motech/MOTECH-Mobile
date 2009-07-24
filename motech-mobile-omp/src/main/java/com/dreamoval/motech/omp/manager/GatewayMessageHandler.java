/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
/**
 *
 * @author Yoofi
 */
public interface GatewayMessageHandler {
    public MessageDetails prepareMessage(String message);
    public ResponseDetails parseResponse(String gatewayResponse);
}
