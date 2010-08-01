package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.GatewayResponseDAO;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.MStatus;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;


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

    public GatewayResponse getMostRecentResponseByMessageId(Long messageId) {
        logger.debug("variable passed to getMostRecentResponseByRequestId: " + messageId);

        try {

            
            GatewayResponse response = null;
            String query = "from GatewayResponseImpl g where g.gatewayRequest.messageRequest.id = :reqId and g.gatewayRequest.messageStatus != 'PENDING' and g.gatewayRequest.messageStatus != 'PROCESSING' ";

            List responses = this.getSessionFactory().getCurrentSession().createQuery(query).setParameter("reqId", messageId).list();

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
    public List getByPendingMessageAndMaxTries(int maxTries) {
        logger.debug("variable passed to getByRequestIdAndTryNumber. maxTries: " + maxTries);

        try {
            List<GatewayResponse> response = null;
            String query = "from GatewayResponseImpl g where g.gatewayRequest.messageRequest.status = :messageStatus and g.gatewayRequest.messageRequest.tryNumber <= :maxTries and g.gatewayRequest.messageStatus != :requestStatus and g.gatewayRequest.tryNumber = g.gatewayRequest.messageRequest.tryNumber";

            response = this.getSessionFactory().getCurrentSession().createQuery(query).setParameter("messageStatus", MStatus.PENDING).setParameter("maxTries", maxTries).setParameter("requestStatus", MStatus.PENDING).list();
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
