/*
 * MessageDetailsDAO is the implementation class of the MessageDetailsDAO interface
 * This Class implements only MessageDetailsDAO specific persistent operation to the MessageDetails model.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.dao.SessionContainer;
import com.dreamoval.motech.core.dao.hibernate.HibernateUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jojo
 */
public class MessageDetailsDAOImpl extends GenericDAOImpl<MessageDetails, Long> implements MessageDetailsDAO {

  SessionContainer sessionManager;

  public MessageDetailsDAOImpl(){}

  public MessageDetailsDAOImpl(HibernateUtils sessionManager){
    this.sessionManager = sessionManager;
  }


    public List<MessageDetails> GetAllByStatus(String status) {
        try
        {
            sessionManager = new HibernateUtils();
            Session session = sessionManager.requestSession();
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
