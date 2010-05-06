package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.GatewayRequestDAO;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.MStatus;
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
     *  @see {@link org.motechproject.mobile.core.dao.GatewayRequestDAO#getByStatus}
     */
    public List<GatewayRequest> getByStatus(MStatus status) {
        logger.debug("varaible passed to getByStatus " + status);
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

    /**
     *  @see {@link org.motechproject.mobile.core.dao.GatewayRequestDAO#getByStatusAndSchedule}
     */
    public List<GatewayRequest> getByStatusAndSchedule(MStatus status, Date schedule) {
        logger.debug("variables passed to getByStatusAndSchedule. status: " + status + "And schedule: " + schedule);

        try {

            List<GatewayRequest> allbystatandSdule;
            Criteria criteria = getDBSession().getSession().createCriteria(getPersistentClass());
            if (schedule == null) {
                criteria = criteria.add(Restrictions.isNull("dateTo")).add(Restrictions.isNull("dateFrom")).add(Restrictions.eq("messageStatus", status));
            } else {
                criteria = criteria.add(Restrictions.eq("messageStatus", status)).add(Restrictions.or(Restrictions.isNull("dateFrom"),Restrictions.lt("dateFrom", schedule))).add(Restrictions.or(Restrictions.isNull("dateTo"),Restrictions.gt("dateTo", schedule)));
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
