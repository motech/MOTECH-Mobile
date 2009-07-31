/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;
import com.dreamoval.motech.core.model.MessageDetails;
import java.util.Date;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  Date : Jul 31, 2009
 * @author joseph Djomeda
 *  Email : joseph@dreamoval.com
 */
public class testSession {

    public static void main(String[] args){
        MessageDetails m = new MessageDetails("text",2,"test for dummies 5","233244567890,23322334455","Failed",new Date());
        ApplicationContext act = new ClassPathXmlApplicationContext("motech-core.xml");
        HibernateUtils sessionManager = (HibernateUtils)act.getBean("sessionManager");
        Session session = sessionManager.requestSession();
        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();


    }

}
