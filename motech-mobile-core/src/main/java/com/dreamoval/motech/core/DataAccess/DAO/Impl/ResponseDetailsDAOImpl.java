/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dataaccess.dao.impl;

import com.dreamoval.motech.core.dataaccess.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.dataaccess.domain.ResponseDetails;
import com.dreamoval.motech.core.dataaccess.util.ISessionManager;
import com.dreamoval.motech.core.dataaccess.util.SessionManager;
import org.hibernate.Session;

/**
 *
 * @author Jojo
 */
public class ResponseDetailsDAOImpl implements ResponseDetailsDAO {

    @Override
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
