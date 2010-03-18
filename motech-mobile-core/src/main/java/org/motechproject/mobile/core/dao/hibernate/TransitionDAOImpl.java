package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.TransitionDAO;
import org.motechproject.mobile.core.model.TransitionImpl;
import org.apache.log4j.Logger;


/*
 * TransitionDAOImpl is the implementation class of the TransitionDAO interface
 * This Class implements only TransitionDAO specific persistent operation to the Transition model.
 *  Date : Aug 4, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class TransitionDAOImpl extends HibernateGenericDAOImpl<TransitionImpl> implements TransitionDAO<TransitionImpl> {

    private static Logger logger = Logger.getLogger(TransitionDAOImpl.class);

    public TransitionDAOImpl() {
    }
}
