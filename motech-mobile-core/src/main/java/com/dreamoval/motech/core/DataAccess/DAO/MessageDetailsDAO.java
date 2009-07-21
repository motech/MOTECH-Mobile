/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dataaccess.dao;

import com.dreamoval.motech.core.dataaccess.domain.MessageDetails;
import java.util.List;

/**
 *
 * @author Jojo
 */
public interface MessageDetailsDAO {
    public boolean StoreMessage(MessageDetails messageDetails);
//    public boolean StoreMessageAndResponse(MessageDetails messageDetails, ResponseDetails[] responseDetails);
    public boolean DeleteMessageById(Long id);
    public boolean Delete(MessageDetails messageDetails);
    public List<MessageDetails> GetAllByStatus(String status);


}
