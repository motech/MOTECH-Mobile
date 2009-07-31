/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;
import com.dreamoval.motech.core.dao.hibernate.*;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import java.util.Date;
import org.hibernate.Session;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 *  Date : Jul 31, 2009
 * @author joseph Djomeda
 *  Email : joseph@dreamoval.com
 */
public class HibernateInitTest  extends AbstractDependencyInjectionSpringContextTests  {

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/motech-core.xml"};
    }

    public void testSave()throws Exception{
        MessageDetailsImpl m = new MessageDetailsImpl("text",2,"test for dummies 5","233244567890,23322334455","Failed",new Date());
        m.setMessageId(18829L);
        HibernateUtils sessionManager = (HibernateUtils)applicationContext.getBean("sessionManager");
        Session session = sessionManager.requestSession();
        session.beginTransaction();
        session.persist(m);
        session.getTransaction().commit();


    }

}
