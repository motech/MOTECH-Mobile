package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.MStatus;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
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
        logger.debug(status);
        try {

            List<GatewayRequest> allbyStatus;
            allbyStatus = (List<GatewayRequest>) getDBSession().getSession().createCriteria(getPersistentClass()).add(Restrictions.eq("messageStatus", status)).list();

            logger.debug(allbyStatus);
            return allbyStatus;
        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in Method getByStatus", he);
            return null;
        } catch (Exception ex) {

            logger.error("Exception in Method getByStatus", ex);
            return null;
        }
    }

    public List<GatewayRequest> getByStatusAndSchedule(MStatus status, Date schedule) {
        logger.debug(status);

        try {

        List<GatewayRequest> allbystatandSdule;
        Criteria criteria = getDBSession().getSession().createCriteria(getPersistentClass());
        if (schedule == null) {
            criteria = criteria.add(Restrictions.isNull("dateTo")).add(Restrictions.isNull("dateFrom")).add(Restrictions.eq("messageStatus", status));
        } else {
            criteria = criteria.add(Restrictions.eq("messageStatus", status)).add(Restrictions.lt("dateFrom", schedule)).add(Restrictions.gt("dateTo", schedule));

        }

        allbystatandSdule = (List<GatewayRequest>) criteria.add(Restrictions.isNotNull("gatewayRequestDetails")).list();
        logger.debug(allbystatandSdule);
        return allbystatandSdule;
        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in Method getByStatusAndSchedule", he);
            return null;
        } catch (Exception ex) {

            logger.error("Exception in Method getByStatusAndSchedule", ex);
            return null;
        }
    }
}
