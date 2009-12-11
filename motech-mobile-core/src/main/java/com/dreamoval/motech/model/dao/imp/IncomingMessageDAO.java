package com.dreamoval.motech.model.dao.imp;

import com.dreamoval.motech.core.dao.GenericDAO;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageSession;

/*
 * IncomingMessageDAO is an interface that defines Operations on IncomingMessage Pojo
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageDAO<T extends IncomingMessage> extends GenericDAO<T> {
    
     /**
     * Methosd to select the last IncomingMessage object based on its parent's session
     * @param session the session to pass
     * @return IncomingMessage
     */
    public IncomingMessage getLastMessagebySession(IncomingMessageSession session);
}
