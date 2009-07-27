/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.webapp.webservices;

import com.dreamoval.motech.core.model.MessageDetails;
import javax.jws.WebService;

/**
 *
 * @author Yoofi
 */
@WebService(endpointInterface = "com.dreamoval.motech.webapp.webservices.MessageService", serviceName = "messagingService")
public class MessageServiceImpl implements MessageService {

    public MessageDetails getMessageDetails(String msgId) {
        return new MessageDetails();
    }

}
