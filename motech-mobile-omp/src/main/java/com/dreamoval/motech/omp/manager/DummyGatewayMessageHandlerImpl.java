/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.model.ResponseDetailsImpl;

/**
 *
 * @author Henry Sampson
 * Date Created: Jul 31, 2009
 */
public class DummyGatewayMessageHandlerImpl implements GatewayMessageHandler {

    public MessageDetails prepareMessage(String message) {
        return new MessageDetailsImpl();
    }

    public ResponseDetails parseResponse(String gatewayResponse) {
        ResponseDetailsImpl response = new ResponseDetailsImpl();
        response.setMessageStatus(gatewayResponse);
        return response;
    }

}
