/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.dao.SessionContainer;
import com.dreamoval.motech.core.dao.hibernate.SessionManagerImpl;
import org.hibernate.Session;

/**
 *
 * @author Jojo
 */
public class ResponseDetailsDAOImpl implements ResponseDetailsDAO {

    public boolean StoreResponse(ResponseDetails responseDetails) {

        try
        {
            SessionContainer sessionManager = new SessionManagerImpl();
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
