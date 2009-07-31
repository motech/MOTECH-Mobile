/*
 * ResponseDetailsImpl is the implementation class of the ResponseDetails interface.
 * This Class implements only MessageDetailsDAO specific persistent operation to the MessageDetails model.
 * 
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.dao.SessionContainer;
import com.dreamoval.motech.core.dao.hibernate.HibernateUtils;
import org.hibernate.Session;

/**
 *
 * @author Jojo
 */
public class ResponseDetailsDAOImpl extends GenericDAOImpl <ResponseDetails, Long> implements ResponseDetailsDAO {

    public boolean StoreResponse(ResponseDetails responseDetails) {

        try
        {
            SessionContainer sessionManager = new HibernateUtils();
            Session session = sessionManager.requestSession();
            session.beginTransaction();
            session.saveOrUpdate(responseDetails);
            session.getTransaction().commit();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

}
