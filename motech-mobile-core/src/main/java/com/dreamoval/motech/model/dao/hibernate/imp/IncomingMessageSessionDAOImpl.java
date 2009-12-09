package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.dao.hibernate.HibernateGenericDAOImpl;
import com.dreamoval.motech.model.dao.imp.IncomingMessageSessionDAO;
import com.dreamoval.motech.model.imp.IncomingMessageSession;
import com.dreamoval.motech.model.imp.IncomingMessageSessionImpl;
import java.util.List;

/*
 * IncomingMessageSessionDAOImpl is the implementation class of the  interface
 * This Class implements only IncomingMessageSessionDAO specific persistent operation to the IncomingMessageSession model.
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageSessionDAOImpl extends HibernateGenericDAOImpl<IncomingMessageSessionImpl> implements IncomingMessageSessionDAO<IncomingMessageSessionImpl> {

    public List<IncomingMessageSession> getIncomingMsgSessionByRequestedPhone(String requesterPhone) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
