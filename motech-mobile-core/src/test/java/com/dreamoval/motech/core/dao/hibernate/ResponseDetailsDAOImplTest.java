

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.model.ResponseDetailsImpl;
import com.dreamoval.motech.core.model.Transition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;







/**
 *  Date : Aug 4, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/core-config.xml"})
public class ResponseDetailsDAOImplTest {

    public ResponseDetailsDAOImplTest() {
    }

      @Autowired
    CoreManager coreManager;
    ResponseDetailsDAO rDDAO;

    @Autowired
    private ResponseDetails rd1;
    @Autowired
    private ResponseDetails rd2;
    @Autowired
    private ResponseDetails rd3;
    @Autowired
    private ResponseDetails rd4;
    @Autowired
    private ResponseDetails rd5;
    @Autowired
    private ResponseDetails rd6;
    @Autowired
    private ResponseDetails rd7;

    @Autowired
    private Transition t1;
    @Autowired
    private Transition t2;


    @Before
    public void setUp() {
         rDDAO = coreManager.createResponseDetailsDAO(coreManager.createMotechContext());

        rd1.setId(1L);
        rd1.setRecipientNumber("123445");
        rd1.setMessageStatus("Pending");

        rd2.setId(2L);
        rd2.setRecipientNumber("123445");
        rd2.setMessageStatus("Pending");

        rd3.setId(3L);
        rd3.setRecipientNumber("234567");
        rd3.setMessageStatus("Delivered");

        rd4.setId(4L);
        rd4.setRecipientNumber("345678");
        rd4.setMessageStatus("Failed");

        rd5.setId(5L);
        rd5.setRecipientNumber("765432");
        rd5.setMessageStatus("Failed");

        rd7.setId(9L);
        rd7.setRecipientNumber("23459");
        rd7.setMessageStatus("Delivered");
        setUpInitialData();

    }



    public void setUpInitialData() {

        Session session = (Session)rDDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        rDDAO.save(rd2);
        rDDAO.save(rd3);
        rDDAO.save(rd4);
        rDDAO.save(rd5);
        tx.commit();

    }



    @Test
    public void  testSave() {
    System.out.println("Save Response");
    Session session = (Session) rDDAO.getDBSession().getSession();
    Transaction tx = session.beginTransaction();
    rDDAO.save(rd1);
    tx.commit();
    session.beginTransaction();
    ResponseDetails fromdb = (ResponseDetails) session.get(ResponseDetailsImpl.class, rd1.getId());
    session.getTransaction().commit();

    Assert.assertEquals(rd1.getId(), fromdb.getId());
    Assert.assertEquals(rd1.getRecipientNumber(), fromdb.getRecipientNumber());
    Assert.assertEquals(rd1.getRecipientNumber(), fromdb.getRecipientNumber());

    }


    @Test
    public void testDelete() {
    System.out.println("Delete Response");
    Session session = (Session) rDDAO.getDBSession().getSession();
    Transaction tx = session.beginTransaction();
    rDDAO.delete(rd2);
    tx.commit();
    session.beginTransaction();
   ResponseDetails fromdb= (ResponseDetailsImpl) session.get(ResponseDetailsImpl.class, rd2.getId());
   session.getTransaction().commit();
   Assert.assertNull(fromdb);
    }


    @Test
    public void testUpdate() {
        System.out.println("Update Response");
        rd3.setMessageStatus("Failed");
        rd3.setRecipientNumber("4444444");
        Session session = (Session) rDDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        rDDAO.save(rd3);
        tx.commit();
        session.beginTransaction();
       ResponseDetails rd3fromdb = (ResponseDetailsImpl) session.get(ResponseDetailsImpl.class, rd3.getId());
       session.getTransaction().commit();
       Assert.assertEquals(rd3.getId(), rd3fromdb.getId());
       Assert.assertEquals("Failed", rd3fromdb.getMessageStatus());
       Assert.assertEquals("4444444", rd3fromdb.getRecipientNumber());
    }


        @Test
        public void testGetById() {
            System.out.println("Find by ResponseDetails id ");
            Transaction tx = ((Session)rDDAO.getDBSession().getSession()).beginTransaction();
            ResponseDetails fromdb = (ResponseDetailsImpl) rDDAO.getById(rd4.getId());
            tx.commit();
            Assert.assertEquals(rd4.getId(), fromdb.getId());
            Assert.assertEquals(rd4.getMessageStatus(), fromdb.getMessageStatus());
            Assert.assertEquals(rd4.getRecipientNumber(), fromdb.getRecipientNumber());
        }

        @Ignore
        @Test
        public void testFindByExample() {
            System.out.println("Find byResponseDetails example");
            rd6.setMessageStatus("Failed");
            List<ResponseDetails> expResult = new ArrayList<ResponseDetails>();
            expResult.add(rd4);
            expResult.add(rd5);
            Transaction tx = ((Session) rDDAO.getDBSession().getSession()).beginTransaction();
       
            List<ResponseDetails> result = rDDAO.findByExample(rd6);
            tx.commit();
            Assert.assertNotNull(result);
            Assert.assertEquals(expResult.size(), result.size());
            Assert.assertEquals(true, result.contains(rd4));
            Assert.assertEquals(true, result.contains(rd5));
                 
        }

        @Ignore
        @Test
        public void testSaveWithTransition() {
            System.out.println("Save with Transition");
            t1.setId(7L);
            t1.setTransactionDate(new Date());
            t1.setTransactionType("insert");

            t2.setId(8L);
            t2.setTransactionDate(new Date());
            t2.setTransactionType("Update");
            rd7.addTransition(t1);
            rd7.addTransition(t2);
            Session session = (Session) rDDAO.getDBSession().getSession();
            Transaction tx = session.beginTransaction();
            rDDAO.save(rd7);
            tx.commit();
            Assert.assertTrue(true);

        }
}
