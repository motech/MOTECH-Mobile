

package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.MessageDetails;
import java.util.List;

/*
 * MessageDetailsDao is an interface that defines only methods and attributes that are specific to MessageDetails entity
 * 
 *  Date: Jul 29, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface MessageDetailsDAO<T extends MessageDetails> extends GenericDAO<T> {

    /**
     * Returns the list of MessageDetails object whose status matches
     * the one provided as argument
     * @param status String indicating the status to fetch for
     * @return List of MessageDetails objects
     */
    public List<MessageDetails> getByStatus(String status);


}
