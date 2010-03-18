package org.motechproject.mobile.model.dao.hibernate.imp;

import org.motechproject.mobile.core.dao.hibernate.HibernateGenericDAOImpl;
import org.motechproject.mobile.core.model.IncomingMessage;
import org.motechproject.mobile.model.dao.imp.IncomingMessageDAO;
import org.motechproject.mobile.core.model.IncomingMessageImpl;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * IncomingMessageDAOImpl is the implementation class of the  interface
 * This Class implements only IncomingMessageDAO specific persistent operation to the IncomingMessage model.
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageDAOImpl extends HibernateGenericDAOImpl<IncomingMessageImpl> implements IncomingMessageDAO<IncomingMessageImpl> {

    private static Logger logger = Logger.getLogger(IncomingMessageDAOImpl.class);

    public IncomingMessage getByContent(String content) {
        logger.debug("variable passed to IncomingMessage.getByContent: " + content);

        try {

            Session sess = this.getDBSession().getSession();
            Criterion code = Restrictions.eq("content", content);

            IncomingMessage message = (IncomingMessage)sess.createCriteria(this.getPersistentClass())
                    .add(code)
                    .setMaxResults(1)
                    .uniqueResult();

            logger.debug(message);

            return message;

        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in getByCode", he);
            return null;
        } catch (Exception ex) {
            logger.error("Exception in getByCode", ex);
            return null;
        }
    }

    public IncomingMessage getByContentBefore(String content, Date beforeDate) {
        logger.debug("variable passed to IncomingMessage.getByContent: " + content);

        try {

            Session sess = this.getDBSession().getSession();

            IncomingMessage message = (IncomingMessage)sess.createCriteria(this.getPersistentClass())
                    .add(Restrictions.eq("content", content))
                    .add(Restrictions.gt("dateCreated", beforeDate))
                    .setMaxResults(1)
                    .uniqueResult();

            logger.debug(message);

            return message;

        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in getByCode", he);
            return null;
        } catch (Exception ex) {
            logger.error("Exception in getByCode", ex);
            return null;
        }
    }

    public IncomingMessage getByContentNonDuplicatable(String content) {
        logger.debug("variable passed to IncomingMessage.getByContent: " + content);

        try {

            Session sess = this.getDBSession().getSession();

            IncomingMessage message = (IncomingMessage)sess.createCriteria(this.getPersistentClass())
                    .add(Restrictions.eq("content", content))
                    .addOrder(Order.desc("dateCreated"))
                    .setMaxResults(1)
                    .uniqueResult();

            logger.debug(message);

            return message;

        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in getByCode", he);
            return null;
        } catch (Exception ex) {
            logger.error("Exception in getByCode", ex);
            return null;
        }
    }
}
