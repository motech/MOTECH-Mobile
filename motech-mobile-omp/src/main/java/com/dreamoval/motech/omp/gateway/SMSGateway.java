/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.gateway;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Domain.ResponseDetails;
/**
 *
 * @author Yoofi
 */
public interface SMSGateway extends MessageGateway {
    public ResponseDetails send(MessageDetails messageDetails);
}
