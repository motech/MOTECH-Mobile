

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * @author joseph Djomeda (joseph@dreamoval.com)
 * Date Created 03-08-2009
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/core-config.xml"})
public class MessageDetailsDAOImplTest {
    
    @Autowired
    CoreManager coreManager;
    MessageDetailsDAO mDDAO;

    @Autowired
    private MessageDetails md1;
    @Autowired
    private MessageDetails md2;
    @Autowired
    private MessageDetails md3;
    @Autowired
    private MessageDetails md4;
    @Autowired
    private MessageDetails md5;
String text;
    
    @BeforeClass
    public static void setUpClass() throws Exception { 
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
         mDDAO = coreManager.createMessageDetailsDAO(coreManager.createMotechContext());
          text = "First";
          md1.setId(1L);
        md1.setMessageText(text);
        md1.setRecipientsNumbers("123445");
        md1.setGlobalStatus("Pending");

        md2.setId(2L);
        md2.setMessageText("Second");
        md2.setRecipientsNumbers("123445");
        md2.setGlobalStatus("Pending");

        md3.setId(3L);
        md3.setMessageText("Third");
        md3.setRecipientsNumbers("123445");
        md3.setGlobalStatus("TRIAL");

        md4.setId(4L);
        md4.setMessageText("Test for dummies 4");
        md4.setRecipientsNumbers("123445, 54321");
        md4.setGlobalStatus("Delivered");

        md5 = md1;
    }

    @After
    public void tearDown() {
    }
    
    
    

    public MessageDetailsDAOImplTest() {
    }

    /**
     * Test of getByStatus method, of class MessageDetailsDAOImpl.
     */
    @Test
    public void testGetByStatus() {
        System.out.println("getByStatus");
        String status = "Pending";
        Transaction tx = ((Session)mDDAO.getDBSession().getSession()).beginTransaction();

        mDDAO.save(md1);
        mDDAO.save(md2);
        mDDAO.save(md3);

        tx.commit();

        List<MessageDetails> expResult = new ArrayList<MessageDetails>();
        expResult.add(md1);
        expResult.add(md2);

        List<MessageDetails> result = mDDAO.getByStatus(status);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void testDelete() {
        System.out.println("Delete");
        
        Session session = ((Session)mDDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        mDDAO.delete(md2);
        tx.commit();

        session.beginTransaction();
        MessageDetails fromdb =(MessageDetailsImpl) session.get(MessageDetailsImpl.class, md2.getId());
        session.getTransaction().commit();
        Assert.assertNull(fromdb);


    }

@Test
    public void testUpdate() {
        System.out.println("Update");
       
        md1.setMessageText("First altered");
        md1.setGlobalStatus("Failed");
        md1.setNumberOfPages(2);
        Session session = ((Session)mDDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        mDDAO.save(md1);
        tx.commit();

        session.beginTransaction();
        MessageDetails fromdb =(MessageDetailsImpl) session.get(MessageDetailsImpl.class, md1.getId());
        session.getTransaction().commit();
        Assert.assertFalse(text.equals(fromdb.getMessageText()));



    }



}