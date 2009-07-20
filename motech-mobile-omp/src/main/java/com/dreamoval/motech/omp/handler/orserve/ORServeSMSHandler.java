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
        System.out.println(message);
        return null;
    }

    public ResponseDetails parseResponse(String gatewayResponse) {
        System.out.println(gatewayResponse);
        return null;
    }

}
