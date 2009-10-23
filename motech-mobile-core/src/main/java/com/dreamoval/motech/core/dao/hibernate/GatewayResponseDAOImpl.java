package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
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

    public GatewayResponse getMostRecentResponseByRequestId(String requestId) {
        Session session = this.getDBSession().getSession();
        List responses = session.createCriteria(this.getPersistentClass())
                .add(Restrictions.ne("messageStatus", MStatus.PENDING))
                .add(Restrictions.ne("messageStatus", MStatus.PROCESSING))
                .add(Restrictions.eq("requestId", requestId))
                .addOrder(Order.desc("dateCreated"))
                .list();

        return responses != null && responses.size() > 0 ? (GatewayResponse) responses.get(0) : null;
    }
}
