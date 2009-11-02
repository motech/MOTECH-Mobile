package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.MStatus;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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

    private static Logger logger = Logger.getLogger(GatewayRequestDAOImpl.class);

    public GatewayRequestDAOImpl() {
    }

    /**
     * Searches for all messages with status: <code>status</code>
     *
     * @param status The status to use as criteria for the search
     * @return The list of GatewayRequest object with status: <code>status</code>
     */
    public List<GatewayRequest> getByStatus(MStatus status) {
        logger.info("getByStatus");
        logger.debug(status);
        try {

            List<GatewayRequest> allbyStatus;
            allbyStatus = (List<GatewayRequest>) getDBSession().getSession().createCriteria(getPersistentClass())
                    .add(Restrictions.eq("messageStatus", status))
                    .list();

            logger.debug(allbyStatus);
            return allbyStatus;
        } catch (HibernateException he) {

            logger.debug("Persistence or JDBC Exception in Method getByStatus", he);
            return null;
        } catch (Exception ex) {

            logger.debug("Exception in Method getByStatus", ex);
            return null;
        }
    }
}
