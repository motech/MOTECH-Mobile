/*
 * ResponseDetailsImpl is the implementation class of the ResponseDetails interface.
 * This Class implements only MessageDetailsDAO specific persistent operation to the MessageDetails model.
 * 
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.model.ResponseDetailsImpl;

/**
 *
 * @author Joseph Djomeda (jojo@dreamoval.com)
 * @author Henry Sampson (henry@dreamoval.com)
 */
public class ResponseDetailsDAOImpl extends HibernateGenericDAOImpl <ResponseDetailsImpl> implements ResponseDetailsDAO<ResponseDetailsImpl> {
}
