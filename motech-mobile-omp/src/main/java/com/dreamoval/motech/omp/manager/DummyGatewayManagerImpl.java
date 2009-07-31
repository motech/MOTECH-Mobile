/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.model.ResponseDetailsImpl;

/**
 * A dummy gateway manager for testing purposes
 *
 * @author Kofi A. Asamoah
 * Date Created: Jul 31, 2009
 */
public class DummyGatewayManagerImpl implements GatewayManager{
    private GatewayMessageHandler handler;

    /**
     *
     * @see GatewayManager.send
     */
    public ResponseDetails send(MessageDetails messageDetails) {
        ResponseDetails response = new ResponseDetailsImpl();
        response.setMessageStatus("sent");
        return response;
    }

    /**
     *
     * @see GatewayManager.send
     */
    public ResponseDetails send(String messageDetails) {
        return new ResponseDetailsImpl();
    }

    /**
     * @return the handler
     */
    public GatewayMessageHandler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(GatewayMessageHandler handler) {
        this.handler = handler;
    }

}
