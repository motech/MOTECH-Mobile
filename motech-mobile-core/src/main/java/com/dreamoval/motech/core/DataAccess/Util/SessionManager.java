/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dataaccess.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Jojo
 */
public class SessionManager  implements ISessionManager{
    private final SessionFactory sessionFactory;
    private Session session;

    public SessionManager()
    {
          this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }
    
    public SessionManager(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
      
    }


    public  Session RequestSession() {
//        if(session != null || session.isOpen())
//            return session;
//        else
//        {
            session = sessionFactory.openSession();
            return session;
//        }

    }

}
