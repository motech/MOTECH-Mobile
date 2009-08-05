

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.core.model.ResponseDetailsImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
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

    @Autowired
    private ResponseDetails rd1;

    @Autowired
    private ResponseDetails rd2;



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

        md5.setId(5L);
        md5.setDateSent(new Date());
        md5.setRecipientsNumbers("12345,54321");
        md5.setMessageText("insertion with responsedetailsobject");
        md5.setGlobalStatus("Failed");


        rd1.setId(1L);
        rd1.setMessageStatus("Pending");
        rd1.setRecipientNumber("123445");

        rd2.setId(2L);
        rd2.setMessageStatus("Failed");
        rd2.setRecipientNumber("54321");


        setUpInitialData();
    }

    @After
    public void tearDown() {
    }


    public void setUpInitialData(){
         Transaction tx = ((Session)mDDAO.getDBSession().getSession()).beginTransaction();

        mDDAO.save(md1);
        mDDAO.save(md2);
        mDDAO.save(md3);
        mDDAO.save(md4);

        tx.commit();

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

        tx.commit();

        List<MessageDetails> expResult = new ArrayList<MessageDetails>();
        expResult.add(md1);
        expResult.add(md2);

        List<MessageDetails> result = mDDAO.getByStatus(status);
        Assert.assertEquals(expResult, result);
    }

    /**
     *
     */
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

   /**
    *
    */
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

        @Ignore
        @Test
        public void testSaveWithResponse() {
            System.out.println("saving with response object");
            md5.addResponse((ResponseDetailsImpl)rd1);
            md5.addResponse((ResponseDetailsImpl)rd2);
            Session session =(Session)mDDAO.getDBSession().getSession();
            Transaction tx = session.beginTransaction();
            mDDAO.save(md5);
            tx.commit();
            Assert.assertTrue(true);

        }

        @Test
        public void testGetById() {
            System.out.println("testing FindById");
            Transaction tx = ((Session)mDDAO.getDBSession().getSession()).beginTransaction();
            MessageDetails result = (MessageDetails) mDDAO.getById(md1.getId());
            Assert.assertSame(md1, result);
            Assert.assertEquals(md1.getId(), result.getId());
            Assert.assertEquals(md1.getMessageText(), result.getMessageText());
            Assert.assertEquals(md1.getRecipientsNumbers(), result.getRecipientsNumbers());
            Assert.assertEquals(md1.getGlobalStatus(), result.getGlobalStatus());
        }

        @Ignore
        @Test
        public void testFindByCriteria(){
            System.out.println("testing findByCriteria");

        }

}