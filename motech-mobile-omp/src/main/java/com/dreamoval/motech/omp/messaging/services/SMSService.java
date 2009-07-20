/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.messaging.services;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
/**
 *
 * @author Yoofi
 */
public interface SMSService {
    public boolean sendTextMessage(MessageDetails messageDetails);
}