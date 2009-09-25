/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 *  Date : Sep 25, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/core-config.xml"})
public class MessageRequestDAOImplTest {

    public MessageRequestDAOImplTest() {
    }

   
   

    @Autowired
    private CoreManager coreManager;
    @Autowired
    private MessageRequest mr1; 
    
    MessageRequestDAO mrDAO;

    @Before
    public void setUp() {
        mrDAO = coreManager.createMessageRequestDAO(coreManager.createMotechContext());
        mr1.setId(1L);
        mr1.setDate_created(new Date());
        mr1.setLanguage("en");
        mr1.setRecipient_name("jlkj");


    }

    @Test
    public void testSave() {
        System.out.println("test save MessageRequest Object");
        Session session =(Session) mrDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mrDAO.save(mr1);
        tx.commit();
        session.beginTransaction();
        MessageRequest fromdb = (MessageRequestImpl) session.get(MessageRequestImpl.class, mr1.getId());
        session.getTransaction().commit();
        Assert.assertNotNull(fromdb);
    }

}
