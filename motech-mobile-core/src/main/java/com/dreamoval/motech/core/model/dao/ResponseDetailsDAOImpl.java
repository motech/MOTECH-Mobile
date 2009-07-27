/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model.dao;

import com.dreamoval.motech.core.model.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.model.dao.hibernate.ISessionManager;
import com.dreamoval.motech.core.model.dao.hibernate.SessionManager;
import org.hibernate.Session;

/**
 *
 * @author Jojo
 */
public class ResponseDetailsDAOImpl implements ResponseDetailsDAO {

    public boolean StoreResponse(ResponseDetails responseDetails) {

        try
        {
            ISessionManager sessionManager = new SessionManager();
            Session session = sessionManager.RequestSession();
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
