package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *  MessageRequestDAOImpl is the implementation class of the MessageRequestDAO interface
 * This Class implements only MessageRequestDAO specific persistent operation to the MessageRequest F model.
 *  Date : Sep 25, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class MessageRequestDAOImpl extends HibernateGenericDAOImpl<MessageRequestImpl> implements MessageRequestDAO<MessageRequestImpl> {

    public MessageRequestDAOImpl() {
    }

    /**
     * @see  {@link com.dreamoval.motech.core.dao.MessageRequestDAO#getMsgRequestByStatusAndSchedule(com.dreamoval.motech.core.model.MStatus, java.util.Date)  }
     */
    public List getMsgRequestByStatusAndSchedule(MStatus status, Date schedule) {
        Session sess = this.getDBSession().getSession();
        List msgRequest = sess.createCriteria(this.getPersistentClass())
                .add(Restrictions.eq("status", status))
                .add(Restrictions.lt("dateFrom", schedule))
                .add(Restrictions.gt("dateTo", schedule))
                .list();
        return msgRequest;
    }
}
