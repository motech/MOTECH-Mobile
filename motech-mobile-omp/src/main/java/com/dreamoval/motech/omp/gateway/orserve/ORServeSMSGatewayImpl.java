/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.gateway.orserve;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Domain.ResponseDetails;
import com.dreamoval.motech.omp.gateway.SMSGateway;
import com.dreamoval.motech.omp.handler.orserve.ORServeSMSHandler;
import com.outreachcity.orserve.messaging.SMSMessenger;
import com.outreachcity.orserve.messaging.SMSMessengerSoap;

/**
 *
 * @author Yoofi
 */
public class ORServeSMSGatewayImpl implements SMSGateway {

    public ResponseDetails send(MessageDetails messageDetails) {
        if(messageDetails == null)
            return null;
        
        ORServeSMSHandler handler = new ORServeSMSHandler();
        SMSMessenger messenger = new SMSMessenger();
        SMSMessengerSoap soap = messenger.getSMSMessengerSoap();
        String gatewayResponse = soap.sendMessage(messageDetails.getMessageText(), "233244733999", "MoTeCH", "nu-ob-demo", String.valueOf(messageDetails.getNumberOfPages()));
        return handler.parseResponse(gatewayResponse);
    }
}
