package com.dreamoval.motech.model.dao.imp;

import com.dreamoval.motech.core.dao.GenericDAO;
import com.dreamoval.motech.core.model.IncomingMessageSession;
import java.util.List;

/*
 * IncomingMessageSessionDAO is an interface that defines Operations on IncomingMessageSession Pojo
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageSessionDAO<T extends IncomingMessageSession> extends GenericDAO<T> {

    /**
     * Method to provide a list of IncomingMessageSession object based on requestedphone
     * @param requesterPhone the requestedphone to pass
     * @return List of IncomingMessageSession
     */
    public List<IncomingMessageSession> getIncomingMsgSessionByRequestedPhone(String requesterPhone);
}
