package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.MessageRequestDAO;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageRequestImpl;
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
     * @see  {@link org.motechproject.mobile.core.dao.MessageRequestDAO#getMsgRequestByStatusAndSchedule(org.motechproject.mobile.core.model.MStatus, java.util.Date)  }
     */
    public List getMsgRequestByStatusAndSchedule(MStatus status, Date schedule) {
        logger.debug("variables passed to getMsgRequestByStatusAndSchedule. status: " + status + "And schedule: " + schedule);


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
            logger.error("Persistence or JDBC Exception in Method getMsgRequestByStatusAndSchedule", he);
            return null;
        } catch (Exception ex) {
            logger.error("Exception in Method getMsgRequestByStatusAndSchedule", ex);
            return null;
        }

    }

    /**
     * @see  {@link org.motechproject.mobile.core.dao.MessageRequestDAO#getMsgRequestByStatusAndTryNumber(org.motechproject.mobile.core.model.MStatus, int)   }
     */
    public List getMsgRequestByStatusAndTryNumber(MStatus status, int tryNumber) {
   
        logger.debug("variables passed to getMsgRequestByStatusAndTryNumber.status " + status + "And tryNumber: " + tryNumber);

        try {

            Session session = this.getDBSession().getSession();
            Criterion eqStatus = Restrictions.eq("status", status);
            Criterion leTrynumb = Restrictions.le("tryNumber", tryNumber);
            LogicalExpression exp = Restrictions.and(eqStatus, leTrynumb);
            List msgRequest = session.createCriteria(this.getPersistentClass()).add(exp).list();

            logger.debug(msgRequest);
            return msgRequest;
        } catch (HibernateException he) {
            logger.error("Persistence or JDBC Exception in Method getMsgRequestByStatusAndTryNumber", he);
            return null;
        } catch (Exception ex) {
            logger.error("Exception in Method getMsgRequestByStatusAndTryNumber", ex);
            return null;
        }

    }

    public List<MessageRequest> getMsgByStatus(MStatus status) {
   
        logger.debug("variable passed to getMsgRequestByStatusAndTryNumber. status: " + status);

        try {

            Session session = this.getDBSession().getSession();
            Criterion eqStatus = Restrictions.eq("status", status);
            List msgRequest = session.createCriteria(this.getPersistentClass())
                    .add(eqStatus)
                    .list();

            logger.debug(msgRequest);
            return msgRequest;
        } catch (HibernateException he) {
            logger.error("Persistence or JDBC Exception in Method getMsgRequestByStatusAndTryNumber", he);
            return null;
        } catch (Exception ex) {
            logger.error("Exception in Method getMsgRequestByStatusAndTryNumber", ex);
            return null;
        }

    }

    public List<MessageRequest> getMsgRequestByRecipientAndStatus(
    		String recipientID, MStatus status) {

    	logger.debug("variable passed to getMsgRequestByRecipientAndStatus.  recipientID: " + recipientID + " status: " + status);

    	try {

    		Session session = this.getDBSession().getSession();
    		Criterion eqStatus = Restrictions.eq("status", status);
    		Criterion eqRecipient = Restrictions.eq("recipientId", recipientID);
    		List msgRequest = session.createCriteria(this.getPersistentClass())
    				.add(eqRecipient)
    				.add(eqStatus)
    				.list();

    		logger.debug(msgRequest);
    		return msgRequest;
    	} catch (HibernateException he) {
    		logger.error("Persistence or JDBC Exception in Method getMsgRequestByRecipientAndStatus", he);
    		return null;
    	} catch (Exception ex) {
    		logger.error("Exception in Method getMsgRequestByStatusAndTryNumber", ex);
    		return null;

    	}
    }

	public List<MessageRequest> getMsgRequestByRecipientAndSchedule(
			String recipientID, Date schedule) {
        logger.debug("variables passed to getMsgRequestByStatusAndSchedule. recipientID: " + recipientID + "And schedule: " + schedule);


        try {
            Session sess = this.getDBSession().getSession();
            List msgRequest = sess.createCriteria(this.getPersistentClass())
                    .add(Restrictions.eq("recipientId", recipientID))
                    .add(Restrictions.lt("dateFrom", schedule))
                    .add(Restrictions.gt("dateTo", schedule))
                    .list();
            logger.debug(msgRequest);
            return msgRequest;

        } catch (HibernateException he) {
            logger.error("Persistence or JDBC Exception in Method getMsgRequestByStatusAndSchedule", he);
            return null;
        } catch (Exception ex) {
            logger.error("Exception in Method getMsgRequestByStatusAndSchedule", ex);
            return null;
        }
	}
}
