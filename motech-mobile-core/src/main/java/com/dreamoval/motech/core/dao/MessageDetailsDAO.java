/*
 * MessageDetailsDao is an interface that defines only methods and attributes that are specific to MessageDetails entity
 */

package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.MessageDetails;
import java.util.List;

/**
 * Date: 
 * @author Joseoh Djomeda
 * Email: joseph@dreamoval.com
 */
public interface MessageDetailsDAO<T extends MessageDetails> extends GenericDAO<T> {
   
    public List<MessageDetails> getByStatus(String status);


}
