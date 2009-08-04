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
 *Date :Jul 24, 2009
 * @author Joseph Djomedd  (joseph@dreamoval.com)
 */
public class HibernateUtils implements SessionContainer {

    private final SessionFactory sessionFactory;
    private Session session;

    /**
     *This constructor is initially created to work without any kind of dependency injection
     * Thus meant for early functional testing.Might be removed from this class
     */
    public HibernateUtils() {
        this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    /**
     * This constructor has to be injected the sessionFactory dependency to work with this class
     * @param sessionFactory
     */
    public HibernateUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Opens a session and return it to the client
     * @return Hibernate Session
     */
    public Session requestSession() {
        return sessionFactory.openSession();
    }
}
