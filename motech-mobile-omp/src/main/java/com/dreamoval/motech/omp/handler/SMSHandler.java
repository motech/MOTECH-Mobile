/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.handler;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Domain.ResponseDetails;
/**
 *
 * @author Yoofi
 */
public interface SMSHandler {
    public MessageDetails prepareMessage(String message);
    public ResponseDetails parseResponse(String[] gatewayResponse);
}
