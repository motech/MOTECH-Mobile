package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.dao.hibernate.HibernateGenericDAOImpl;
import com.dreamoval.motech.model.dao.imp.IncomingMessageSessionDAO;
import com.dreamoval.motech.model.imp.IncomingMessageSession;
import com.dreamoval.motech.model.imp.IncomingMessageSessionImpl;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/*
 * IncomingMessageSessionDAOImpl is the implementation class of the  interface
 * This Class implements only IncomingMessageSessionDAO specific persistent operation to the IncomingMessageSession model.
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageSessionDAOImpl extends HibernateGenericDAOImpl<IncomingMessageSessionImpl> implements IncomingMessageSessionDAO<IncomingMessageSessionImpl> {

    private static Logger logger = Logger.getLogger(IncomingMessageSessionImpl.class);

    public List<IncomingMessageSession> getIncomingMsgSessionByRequestedPhone(String requesterPhone) {
        
         logger.debug("varaible passed to getIncomingMsgSessionByRequestedPhone " + requesterPhone);
        try {

            List<IncomingMessageSession> allMsgSession;
            allMsgSession = (List<IncomingMessageSession>) getDBSession().getSession().createCriteria(getPersistentClass())
                    .add(Restrictions.eq("requesterPhone", requesterPhone))
                    .list();

            logger.debug(allMsgSession);
            return allMsgSession;
        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in Method getIncomingMsgSessionByRequestedPhone passed with the variable: " + requesterPhone , he);
            return null;
        } catch (Exception ex) {

            logger.error("Exception in Method getIncomingMsgSessionByRequestedPhone", ex);
            return null;
        }
    }
}
