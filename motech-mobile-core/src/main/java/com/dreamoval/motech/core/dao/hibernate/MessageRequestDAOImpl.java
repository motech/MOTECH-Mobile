package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.model.MessageRequestImpl;

/**
 *  MessageRequestDAOImpl is the implementation class of the MessageRequestDAO interface
 * This Class implements only MessageRequestDAO specific persistent operation to the MessageRequest F model.
 *  Date : Sep 25, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class MessageRequestDAOImpl extends HibernateGenericDAOImpl<MessageRequestImpl> implements MessageRequestDAO<MessageRequestImpl> {

    public MessageRequestDAOImpl() {
    }
}
