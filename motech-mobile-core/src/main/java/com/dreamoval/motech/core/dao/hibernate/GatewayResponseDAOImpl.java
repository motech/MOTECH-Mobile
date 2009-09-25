

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import org.apache.log4j.Logger;


/*
 * ResponseDetailsImpl is the implementation class of the ResponseDetails interface.
 * This Class implements only MessageDetailsDAO specific persistent operation to the MessageDetails model.
 *
 * Date :Jul 24, 2009
 * @author Joseph Djomeda (jojo@dreamoval.com)
 * @author Henry Sampson (henry@dreamoval.com)
 * 
 */
public class GatewayResponseDAOImpl extends HibernateGenericDAOImpl <GatewayResponseImpl> implements GatewayResponseDAO<GatewayResponseImpl> {
private static Logger logger = Logger.getLogger(GatewayResponseDAOImpl.class);

    public GatewayResponseDAOImpl() {
    }
    
}
