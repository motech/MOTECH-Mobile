package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

/**
 *  MessageRequestDAOImpl is the implementation class of the MessageRequestDAO interface
 * This Class implements only MessageRequestDAO specific persistent operation to the MessageRequest F model.
 *  Date : Sep 25, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class MessageRequestDAOImpl extends HibernateGenericDAOImpl<MessageRequestImpl> implements MessageRequestDAO<MessageRequestImpl> {

    private static Logger logger = Logger.getLogger(MessageRequestDAOImpl.class);

    public MessageRequestDAOImpl() {
    }

    /**
     * @see  {@link com.dreamoval.motech.core.dao.MessageRequestDAO#getMsgRequestByStatusAndSchedule(com.dreamoval.motech.core.model.MStatus, java.util.Date)  }
     */
    public List getMsgRequestByStatusAndSchedule(MStatus status, Date schedule) {
        logger.info("getMsgRequestByStatusAndSchedule");
        logger.debug(status);
        logger.debug(schedule);

        try {
            Session sess = this.getDBSession().getSession();
            List msgRequest = sess.createCriteria(this.getPersistentClass())
                    .add(Restrictions.eq("status", status))
                    .add(Restrictions.lt("dateFrom", schedule))
                    .add(Restrictions.gt("dateTo", schedule))
                    .list();
            logger.debug(msgRequest);
            return msgRequest;

        } catch (HibernateException he) {
            logger.debug("Persistence or JDBC Exception in Method getMsgRequestByStatusAndSchedule", he);
            return null;
        } catch (Exception ex) {
            logger.debug("Exception in Method getMsgRequestByStatusAndSchedule", ex);
            return null;
        }

    }

    /**
     * @see  {@link com.dreamoval.motech.core.dao.MessageRequestDAO#getMsgRequestByStatusAndTryNumber(com.dreamoval.motech.core.model.MStatus, int)   }
     */
    public List getMsgRequestByStatusAndTryNumber(MStatus status, int tryNumber) {
        logger.info("getMsgRequestByStatusAndTryNumber");
        logger.debug(status);
        logger.debug(tryNumber);
        try {

            Session session = this.getDBSession().getSession();
            Criterion eqStatus = Restrictions.eq("status", status);
            Criterion leTrynumb = Restrictions.le("tryNumber", tryNumber);
            LogicalExpression exp = Restrictions.and(eqStatus, leTrynumb);
            List msgRequest = session.createCriteria(this.getPersistentClass()).add(exp).list();

            logger.debug(msgRequest);
            return msgRequest;
        } catch (HibernateException he) {
            logger.debug("Persistence or JDBC Exception in Method getMsgRequestByStatusAndTryNumber", he);
            return null;
        } catch (Exception ex) {
            logger.debug("Exception in Method getMsgRequestByStatusAndTryNumber", ex);
            return null;
        }

    }
}
