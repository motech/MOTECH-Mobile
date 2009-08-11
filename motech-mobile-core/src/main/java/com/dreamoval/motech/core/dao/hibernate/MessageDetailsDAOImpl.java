

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;




/*
 * MessageDetailsDAO is the implementation class of the MessageDetailsDAO interface
 * This Class implements only MessageDetailsDAO specific persistent operation to the MessageDetails model.
 * 
 * Date: Jul 24, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 * @author Henry Sampson (henry@dreamoval.com)
 */
public class MessageDetailsDAOImpl extends HibernateGenericDAOImpl<MessageDetailsImpl> implements MessageDetailsDAO<MessageDetailsImpl> {

private static Logger logger = Logger.getLogger(MessageDetailsImpl.class);
  public MessageDetailsDAOImpl(){}

  /**
   * Searches for all messages with status: <code>status</code>
   *
   * @param status The status to use as criteria for the search
   * @return The list of MessageDetails object with status: <code>status</code>
   */
    public List<MessageDetails> getByStatus(String status) {
        logger.info("Calling getByStatus");
        try
        {
            return (List<MessageDetails>) getDBSession().getSession().createCriteria(getPersistentClass())
                    .add(Restrictions.eq("globalStatus", status)).list();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
