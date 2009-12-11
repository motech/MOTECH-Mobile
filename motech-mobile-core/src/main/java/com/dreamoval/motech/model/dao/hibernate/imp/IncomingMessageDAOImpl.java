package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.dao.hibernate.HibernateGenericDAOImpl;
import com.dreamoval.motech.model.dao.imp.IncomingMessageDAO;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageImpl;
import com.dreamoval.motech.core.model.IncomingMessageSession;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/*
 * IncomingMessageDAOImpl is the implementation class of the  interface
 * This Class implements only IncomingMessageDAO specific persistent operation to the IncomingMessage model.
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageDAOImpl extends HibernateGenericDAOImpl<IncomingMessageImpl> implements IncomingMessageDAO<IncomingMessageImpl> {

    private static Logger logger = Logger.getLogger(IncomingMessageImpl.class);

    public IncomingMessage getLastMessagebySession(IncomingMessageSession session) {
        logger.debug("varaible passed to getLastMessageBySession " + session);
        try {

            List<IncomingMessage> allMsg;
            allMsg = (List<IncomingMessage>) getDBSession().getSession().createCriteria(getPersistentClass())
                    .add(Restrictions.eq("incomingMsgSession", session))
                    .addOrder(Order.desc("dateCreated"))
                    .list();

            logger.debug("List of all incomingMessage by Session: " + allMsg);
             return allMsg != null && allMsg.size() > 0 ? (IncomingMessage) allMsg.get(0) : null;
         
        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in Method getIncomingMsgSessionByRequestedPhone passed with the variable: " + session , he);
            return null;
        } catch (Exception ex) {

            logger.error("Exception in Method getIncomingMsgSessionByRequestedPhone", ex);
            return null;
        }
    
    }
}
