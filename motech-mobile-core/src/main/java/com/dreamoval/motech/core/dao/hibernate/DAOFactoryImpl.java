/*
 * This Class extends the abstract class DAOFactory and return a specitic DAO based on the method called
 *
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.*;
import org.hibernate.Session;


/**
 *  Date : Jul 31, 2009
 * @author joseph Djomeda
 *  Email : joseph@dreamoval.com
 */
public class DAOFactoryImpl extends DAOFactory {

    /**
     * Creates new instance of the MessageDetailsDAO ready to be used
     * @return MessageDetailsDAOImpl concrete class but actually cast to its interface type MessageDetailsDAO
     */
    public MessageDetailsDAO getMessageDetailsDAO() {
        return (MessageDetailsDAO) instantiateDAO(MessageDetailsDAOImpl.class);
    }

    /**
     * Creates new instance of the ResponseDetailsDAO ready to be used
     * @return ResponseDetailsDAO concrete class but actally cast to its interfacet type ResponseDetailsDAO
     */
    public ResponseDetailsDAO getResponseDetailsDAO() {
     return (ResponseDetailsDAO) instantiateDAO(ResponseDetailsDAOImpl.class);
    }

/**
 * Takes a  concrete class type instantiate its object and returns it
 * @param daoClass concrete dao class which object has to be created and instantiated
 * @return GenericDAOImpl instance
 */
    private GenericDAOImpl instantiateDAO(Class daoClass) {

        try {
            GenericDAOImpl dao = (GenericDAOImpl)daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }

    /**
     *  Get Session From our SessionContainer and set it to the GenericDAOImpl session property
     * for persistence operations
     * @return Session
     */
    //TODO wire session  to this getCurrentSession()
    protected Session getCurrentSession() {
        return null;
    }

}
