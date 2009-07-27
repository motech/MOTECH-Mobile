/*
 * HibernateUtil class provides session to client based on conditions to be added.
 * This is a simple implemention to build on and it has 2 constructors.
 * the first constructor is for the implementation of the sessionFactory interface hard coded with the sessionfactory dependency
 * on purpose for testing
 * the second constructor takes sessionFactory object as argument which is supposed to be wired by spring.
 */
package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.SessionContainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Joseph Djomeda <joseph@dreamoval.com>
 */
public class HibernateUtils implements SessionContainer {

    private final SessionFactory sessionFactory;
    private Session session;

    /**
     *
     */
    public HibernateUtils() {
        this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    /**
     *
     * @param sessionFactory
     */
    public HibernateUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     * @return
     */
    public Session requestSession() {
        if (session != null || session.isOpen()) {
            return session;
        } else {
            session = sessionFactory.openSession();

        }
        return session;
    }
}
