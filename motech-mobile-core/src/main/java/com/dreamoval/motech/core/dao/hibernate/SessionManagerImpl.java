/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.SessionContainer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Jojo
 */
public class SessionManagerImpl implements SessionContainer {

    private final SessionFactory sessionFactory;
    private Session session;

    public SessionManagerImpl() {
        this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    public SessionManagerImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session requestSession() {
        if (session != null || session.isOpen()) {
            return session;
        } else {
            session = sessionFactory.openSession();

        }
        return session;
    }
}
