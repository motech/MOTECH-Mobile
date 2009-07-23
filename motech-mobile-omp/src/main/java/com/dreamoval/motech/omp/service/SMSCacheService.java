/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
/**
 *
 * @author Yoofi
 */
public interface SMSCacheService {
    public boolean saveMessage(MessageDetails messageDetails);
    public boolean updateMessage(MessageDetails messageDetails);
}
