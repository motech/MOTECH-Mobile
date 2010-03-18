package org.motechproject.mobile.core.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * HibernateDBSessionImpl is the implementation of the contracts defined
 * by DBSession interface.Its provides Hibernate session and transaction
 *
 * @see {@link  org.motechproject.mobile.core.dao.DBSession}
 *
 * @author Henry Sampson
 * Date Created :
 */
public class HibernateDBSessionImpl implements DBSession<Session, Transaction> {

    Session session;

    public HibernateDBSessionImpl() {
    }

    public HibernateDBSessionImpl(Session session) {
        this.session = session;
    }

    /**
     * @see org.motechproject.mobile.core.dao.DBSession#releaseSession()
     */
    public void releaseSession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see org.motechproject.mobile.core.dao.DBSession#getTransaction()
     */
    public Transaction getTransaction() {
        return session.beginTransaction();
    }

    /**
     * @see org.motechproject.mobile.core.dao.DBSession#getSession()
     */
    public Session getSession() {
        return session;
    }

    /**
     * @see org.motechproject.mobile.core.dao.DBSession#setSession(java.lang.Object) 
     */
    public void setSession(Session session) {
        this.session = session;
    }
}
