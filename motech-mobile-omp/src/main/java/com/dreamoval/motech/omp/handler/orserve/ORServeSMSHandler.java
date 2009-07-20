/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.handler.orserve;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Domain.ResponseDetails;
import com.dreamoval.motech.omp.handler.SMSHandler;

/**
 *
 * @author Yoofi
 */
public class ORServeSMSHandler implements SMSHandler {

    public MessageDetails prepareMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ResponseDetails parseResponse(String[] gatewayResponse) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
