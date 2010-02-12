package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.model.dao.imp.IncomingMessageDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncMessageFormStatus;
import com.dreamoval.motech.core.model.IncMessageResponseStatus;
import com.dreamoval.motech.core.model.IncMessageSessionStatus;
import com.dreamoval.motech.core.model.IncMessageStatus;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageForm;
import com.dreamoval.motech.core.model.IncomingMessageImpl;
import com.dreamoval.motech.core.model.IncomingMessageResponse;
import com.dreamoval.motech.core.model.IncomingMessageSession;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageSessionDAO;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class IncomingMessageDAOImplTest {

    @Autowired
    CoreManager coreManager;
    IncomingMessageDAO imDAO;
    IncomingMessageSessionDAO imsDAO;
    IncomingMessageFormDAO imfDAO;
    @Autowired
    private IncomingMessageForm imf2;
    @Autowired
    private IncomingMessageResponse imfr3;
    @Autowired
    private IncomingMessageSession ims1;
    @Autowired
    private IncomingMessage im1;
    @Autowired
    private IncomingMessage im2;
    @Autowired
    private IncomingMessage im3;

    public IncomingMessageDAOImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        MotechContext tc = coreManager.createMotechContext();
        imDAO = coreManager.createIncomingMessageDAO(tc);
        imsDAO = coreManager.createIncomingMessageSessionDAO(tc);


        im1.setId(987L);
        im1.setContent("content im1");
        im1.setLastModified(new Date());
        im1.setMessageStatus(IncMessageStatus.PROCESSING);

        im2.setId(17L);
        im2.setContent("content im2");
        im2.setLastModified(new Date());
        im2.setMessageStatus(IncMessageStatus.PROCESSING);

        im3.setId(171L);
        im3.setContent("content im3");
        im3.setLastModified(new Date());
        im3.setMessageStatus(IncMessageStatus.PROCESSING);

        imfr3.setId(786L);
        imfr3.setContent("response 3");
        imfr3.setDateCreated(new Date());
        imfr3.setMessageResponseStatus(IncMessageResponseStatus.SAVED);

        ims1.setId(923L);
        ims1.setFormCode("code_IM");
        ims1.setRequesterPhone("1122334455");
        ims1.setMessageSessionStatus(IncMessageSessionStatus.STARTED);
        ims1.setLastActivity(new Date());

        imf2.setId(243L);
        imf2.setDateCreated(new Date());
        imf2.setMessageFormStatus(IncMessageFormStatus.NEW);

        Session session = ((Session) imsDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imsDAO.save(ims1);
        tx.commit();

    }

    @After
    public void tearDown() {
        Session session = ((Session) imDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imsDAO.delete(ims1);
        imDAO.delete(im1);
        imDAO.delete(im2);
        tx.commit();

    }

    /**
     * Test of save method, of class IncomingMessageDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("IncomingMessageDAOImpl save");
        Session session = ((Session) imDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imDAO.save(im1);
        tx.commit();

        IncomingMessage fromdb = (IncomingMessage) session.get(IncomingMessageImpl.class, im1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(im1, fromdb);
        Assert.assertEquals(im1.getId(), fromdb.getId());
        System.out.println("the form content:\n " + fromdb.getContent());
        System.out.println(fromdb.toString());

    }

    /**
     * Test of save method with IncominingForm, of class IncomingMessageDAOImpl.
     */
    @Test
    public void testSaveWithIncomingForm() {
        System.out.println("IncomingMessageDAOImpl save with IncomingMessageForm");
        Session session = ((Session) imDAO.getDBSession().getSession());
        im2.setIncomingMessageForm(imf2);
        Transaction tx = session.beginTransaction();
        imDAO.save(im2);
        tx.commit();

        IncomingMessage fromdb = (IncomingMessage) session.get(IncomingMessageImpl.class, im2.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(im2, fromdb);
        Assert.assertEquals(im2.getId(), fromdb.getId());
        Assert.assertEquals(imf2, fromdb.getIncomingMessageForm());
        System.out.println(fromdb.toString());

    }

    /**
     * Test of save method with IncomingMessageSession, of class IncomingMessageDAOImpl.
     */
    @Test
    public void testSaveWithIncomingMessageSession() {
        System.out.println("IncomingMessageDAOImpl save with Session");
        Session session = ((Session) imDAO.getDBSession().getSession());
        ims1.addIncomingMessage(im1);
        Transaction tx = session.beginTransaction();
        imDAO.save(im1);
        tx.commit();

        IncomingMessage fromdb = (IncomingMessage) session.get(IncomingMessageImpl.class, im1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(im1, fromdb);
        Assert.assertEquals(im1.getId(), fromdb.getId());
        Assert.assertEquals(im1.getIncomingMsgSession(), fromdb.getIncomingMsgSession());
        Assert.assertEquals(im1.getIncomingMsgSession().getId(), fromdb.getIncomingMsgSession().getId());
        Assert.assertEquals(im1.getIncomingMsgSession().getIncomingMessages(), fromdb.getIncomingMsgSession().getIncomingMessages());
        System.out.println("the form content:\n " + fromdb.getContent());
        System.out.println(fromdb.toString());

    }

    /**
     * Test of save method with IncomingMessageResponse, of class IncomingMessageDAOImpl.
     */
    @Test
    public void testSaveWithIncomingMessageResponse() {
        System.out.println("IncomingMessageDAOImpl save with IncomingMessageResponse");
        Session session = ((Session) imDAO.getDBSession().getSession());
        im3.setIncomingMessageResponse(imfr3);
        Transaction tx = session.beginTransaction();
        imDAO.save(im3);
        tx.commit();

        IncomingMessage fromdb = (IncomingMessage) session.get(IncomingMessageImpl.class, im3.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(im3, fromdb);
        Assert.assertEquals(im3.getId(), fromdb.getId());
        Assert.assertEquals(imfr3, fromdb.getIncomingMessageResponse());
        System.out.println(fromdb.toString());


    }
}
