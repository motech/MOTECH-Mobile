/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.cache;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
/**
 *
 * @author Yoofi
 */
public interface SMSCache {
    public boolean saveMessage(MessageDetails messageDetails);
    public boolean updateMessage(MessageDetails messageDetails);
}
