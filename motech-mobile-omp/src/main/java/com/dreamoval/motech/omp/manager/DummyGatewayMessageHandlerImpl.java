/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import java.util.Set;
import java.util.HashSet;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 */
public class DummyGatewayMessageHandlerImpl implements GatewayMessageHandler {
    CoreManager coreManager;

    public MessageDetails prepareMessage(String message) {
        return coreManager.createMessageDetails(coreManager.createMotechContext());
    }

    public Set<ResponseDetails> parseMessageResponse(MessageDetails message, String gatewayResponse) {
        Set<ResponseDetails> responseList = new HashSet<ResponseDetails>();
        ResponseDetails response = coreManager.createResponseDetails(coreManager.createMotechContext());
        response.setMessageId(message);
        response.setMessageStatus("delivered");
        response.setRecipientNumber("000000000000");
        responseList.add(response);
        return responseList;
    }

    public String parseMessageStatus(String messageStatus) {
        return "delivered";
    }

    public CoreManager getCoreManager() {
        return this.coreManager;
    }

    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

}
