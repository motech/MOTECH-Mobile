/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.NotificationTypeDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.NotificationType;
import com.dreamoval.motech.core.model.NotificationTypeImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class NotificationTypeDAOImplTest {

    public NotificationTypeDAOImplTest() {
    }

    NotificationTypeDAO nDao;
    @Autowired
    CoreManager coreManager;
    
    @Autowired
    NotificationType nt1;


    @Before
    public void setUp() {
        nDao = coreManager.createNotificationTypeDAO(coreManager.createMotechContext());
        nt1.setId(Long.MIN_VALUE);
        nt1.setName("the name");
        nt1.setDescription("the description");
        
    }

    @Test
    public void testSave(){
            System.out.println("test save NotificationType");
            Session session = (Session) nDao.getDBSession().getSession();
            Transaction tx = session.beginTransaction();
            nDao.save(nt1);
            tx.commit();

            session.beginTransaction();
            NotificationType fromdb = (NotificationType) session.get(NotificationTypeImpl.class, nt1.getId());
            session.getTransaction().commit();
            Assert.assertNotNull(fromdb);
    }



}
