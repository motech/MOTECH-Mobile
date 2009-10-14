package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;

/*
 * GatewayRequestDAOImpl is the implementation class of the GatewayRequestDAO interface
 * This Class implements only GatewayRequestDAO specific persistent operation to the GatewayRequest model.
 * 
 * Date: Jul 24, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 * @author Henry Sampson (henry@dreamoval.com)
 */
public class GatewayRequestDAOImpl extends HibernateGenericDAOImpl<GatewayRequestImpl> implements GatewayRequestDAO<GatewayRequestImpl> {

    private static Logger logger = Logger.getLogger(GatewayRequestImpl.class);

    public GatewayRequestDAOImpl() {
    }

    /**
     * Searches for all messages with status: <code>status</code>
     *
     * @param status The status to use as criteria for the search
     * @return The list of GatewayRequest object with status: <code>status</code>
     */
    public List<GatewayRequest> getByStatus(String status) {
        logger.info("Calling getByStatus");
        try {
            return (List<GatewayRequest>) getDBSession().getSession().createCriteria(getPersistentClass()).add(Restrictions.eq("globalStatus", status)).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
