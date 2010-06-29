package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.GatewayResponseDAO;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.MStatus;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


/*
 * GatewayResponseDAOImpl is the implementation class of the GatewayResponseDAO interface.
 * This Class implements only GatewayResponseDAO specific persistent operation to the GatewayResponse model.
 *
 * Date :Jul 24, 2009
 * @author Joseph Djomeda (jojo@dreamoval.com)
 * @author Henry Sampson (henry@dreamoval.com)
 * 
 */
public class GatewayResponseDAOImpl extends HibernateGenericDAOImpl<GatewayResponseImpl> implements GatewayResponseDAO<GatewayResponseImpl> {

    private static Logger logger = Logger.getLogger(GatewayResponseDAOImpl.class);

    public GatewayResponseDAOImpl() {
    }
    /*
     * @see {@link org.motechproject.mobile.core.dao.GatewayResponseDAO#getMostRecentResponseByRequestId(java.lang.String) }
     */

    public GatewayResponse getMostRecentResponseByMessageId(long messageId) {
        logger.debug("variable passed to getMostRecentResponseByRequestId: " + messageId);

        try {

            Session session = this.getDBSession().getSession();
            GatewayResponse response = null;
            String query = "from GatewayResponseImpl g where g.gatewayRequest.messageRequest.id = :reqId and g.gatewayRequest.messageStatus != 'PENDING' and g.gatewayRequest.messageStatus != 'PROCESSING' ";

            List responses = session.createQuery(query).setParameter("reqId", messageId).list();

            logger.debug(responses);

            return responses != null && responses.size() > 0 ? (GatewayResponse) responses.get(0) : null;

        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in getMostRecentResponseByRequestId", he);
            return null;
        } catch (Exception ex) {

            logger.error("Exception in getMostRecentResponseByRequestId", ex);
            return new GatewayResponseImpl();
        }
    }

    /**
     * @see {@link org.motechproject.mobile.core.dao.GatewayResponseDAO#getByRequestIdAndTryNumber(java.lang.String, int) }
     */
    public GatewayResponse getByMessageIdAndTryNumber(long messageId, int tryNumber) {
        logger.debug("variable passed to getByRequestIdAndTryNumber. messageId: " + messageId + " and tryNumber: " + tryNumber);

        try {

            Session session = this.getDBSession().getSession();
            GatewayResponse response = null;
            String query = "from GatewayResponseImpl g where g.gatewayRequest.messageRequest.id = :reqId and g.gatewayRequest.messageStatus != :status and g.gatewayRequest.tryNumber= :trynum ";

            response = (GatewayResponse) session.createQuery(query).setParameter("reqId", messageId).setParameter("trynum", tryNumber).setParameter("status", MStatus.PENDING).setMaxResults(1).uniqueResult();
            logger.debug(response);

            return response;

        } catch (NonUniqueResultException nu) {
            logger.error("Exception in method getByRequestIdAndTryNumber.getByRequestIdAndTryNumber returns more than 1 result ", nu);
            return null;
        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in getByRequestIdAndTryNumber", he);
            return null;
        } catch (Exception ex) {

            logger.error("Exception in getByRequestIdAndTryNumber", ex);
            return null;
        }

    }
}
