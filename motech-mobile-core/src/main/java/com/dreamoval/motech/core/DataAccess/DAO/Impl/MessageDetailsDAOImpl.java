/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.DataAccess.DAO.Impl;

import com.dreamoval.motech.core.DataAccess.DAO.MessageDetailsDAO;
import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Util.ISessionManager;
import com.dreamoval.motech.core.DataAccess.Util.SessionManager;
import org.hibernate.Session;

/**
 *
 * @author Jojo
 */
public class MessageDetailsDAOImpl implements MessageDetailsDAO {

    public boolean StoreMessage(MessageDetails messageDetails) {

        try
        {
         ISessionManager sessionManager = new SessionManager();
        Session session = sessionManager.ResquestSession();
        session.beginTransaction();
        session.save(messageDetails);
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
