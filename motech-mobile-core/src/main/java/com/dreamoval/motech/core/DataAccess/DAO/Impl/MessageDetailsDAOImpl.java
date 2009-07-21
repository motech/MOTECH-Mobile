/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dataaccess.dao.impl;

import com.dreamoval.motech.core.dataaccess.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.dataaccess.domain.MessageDetails;
import com.dreamoval.motech.core.dataaccess.domain.ResponseDetails;
import com.dreamoval.motech.core.dataaccess.util.ISessionManager;
import com.dreamoval.motech.core.dataaccess.util.SessionManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jojo
 */
public class MessageDetailsDAOImpl implements MessageDetailsDAO {

  SessionManager sessionManager;

  public MessageDetailsDAOImpl(){}

  public MessageDetailsDAOImpl(SessionManager sessionManager)
  {
    this.sessionManager = sessionManager;
  }

    public boolean StoreMessage(MessageDetails messageDetails) {
        try
        {
         ISessionManager sessionManager = new SessionManager();
        Session session = sessionManager.RequestSession();
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


//    public boolean StoreMessageAndResponse(MessageDetails messageDetails, ResponseDetails[] responseDetails) {
//         try
//         {
//           ISessionManager sessionManager = new SessionManager();
//        Session session = sessionManager.ResquestSession();
//        session.beginTransaction();
//        session.save(messageDetails);
//        Long id = messageDetails.getMessageId();
//        for(ResponseDetails dt: responseDetails)
//        {
//            dt.setMessageId(messageDetails);
//            messageDetails.getResponseDetails().add(dt);
//        }
//
//        session.saveOrUpdate(messageDetails);
//        session.getTransaction().commit();
//             return true;
//         }
//         catch(Exception ex)
//         {
//             ex.printStackTrace();
//             return false;
//         }
//    }


    public boolean DeleteMessageById(Long id) {
        try
        {
             ISessionManager sessionManager = new SessionManager();
             Session session = sessionManager.RequestSession();
             session.beginTransaction();
             MessageDetails todelete = (MessageDetails) session.get(MessageDetails.class, id);
             session.delete(todelete);
             session.getTransaction().commit();

             return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }

       
    }


    public boolean Delete(MessageDetails messageDetails) {
        try
        {
           ISessionManager sessionManager = new SessionManager();
           Session session = sessionManager.RequestSession();

           session.beginTransaction();
           session.delete(messageDetails);
           session.getTransaction().commit();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MessageDetails> GetAllByStatus(String status) {
        try
        {
            ISessionManager  sessionManager = new SessionManager();
            Session session = sessionManager.RequestSession();
            return (List<MessageDetails>) session.createCriteria(MessageDetails.class)
                    .add(Restrictions.eq("globalStatus", status)).list();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


}
