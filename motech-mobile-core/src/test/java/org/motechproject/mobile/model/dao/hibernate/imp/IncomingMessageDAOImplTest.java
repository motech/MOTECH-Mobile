package org.motechproject.mobile.model.dao.hibernate.imp;

import org.motechproject.mobile.model.dao.imp.IncomingMessageDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncMessageResponseStatus;
import org.motechproject.mobile.core.model.IncMessageSessionStatus;
import org.motechproject.mobile.core.model.IncMessageStatus;
import org.motechproject.mobile.core.model.IncomingMessage;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageImpl;
import org.motechproject.mobile.core.model.IncomingMessageResponse;
import org.motechproject.mobile.core.model.IncomingMessageSession;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormDAO;
import org.motechproject.mobile.model.dao.imp.IncomingMessageSessionDAO;
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
    private IncomingMessageForm imf4;
    @Autowired
    private IncomingMessageForm imf5;

    @Autowired
    private IncomingMessageResponse imfr3;
    @Autowired
    private IncomingMessageResponse imfr4;
    @Autowired
    private IncomingMessageResponse imfr5;

    @Autowired
    private IncomingMessageSession ims1;
    @Autowired
    private IncomingMessageSession ims4;
    @Autowired
    private IncomingMessageSession ims5;

    @Autowired
    private IncomingMessage im1;
    @Autowired
    private IncomingMessage im2;
    @Autowired
    private IncomingMessage im3;
    @Autowired
    private IncomingMessage im4;
    @Autowired
    private IncomingMessage im5;

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

        im4.setId(178L);
        im4.setContent("content im4");
        im4.setDateCreated(new Date());
        im4.setMessageStatus(IncMessageStatus.PROCESSING);

        imfr3.setId(786L);
        imfr3.setContent("response 3");
        imfr3.setDateCreated(new Date());
        imfr3.setMessageResponseStatus(IncMessageResponseStatus.SAVED);

        imfr4.setId(156L);
        imfr4.setContent("response 4");
        imfr4.setDateCreated(new Date());
        imfr4.setMessageResponseStatus(IncMessageResponseStatus.SAVED);

        ims1.setId(923L);
        ims1.setFormCode("code_IM");
        ims1.setRequesterPhone("1122334455");
        ims1.setMessageSessionStatus(IncMessageSessionStatus.STARTED);
        ims1.setLastActivity(new Date());

        ims4.setId(654L);
        ims4.setFormCode("code_IM654");
        ims4.setRequesterPhone("1122334455");
        ims4.setMessageSessionStatus(IncMessageSessionStatus.STARTED);
        ims4.setLastActivity(new Date());

        imf2.setId(243L);
        imf2.setDateCreated(new Date());
        imf2.setMessageFormStatus(IncMessageFormStatus.NEW);

        imf4.setId(9875L);
        imf4.setDateCreated(new Date());
        imf4.setMessageFormStatus(IncMessageFormStatus.NEW);

        im4.setIncomingMessageForm(imf4);
        im4.setIncomingMessageResponse(imfr4);
        ims4.addIncomingMessage(im4);

        Session session = ((Session) imsDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imsDAO.save(ims1);
        imsDAO.save(ims4);
        imDAO.save(im4);
        tx.commit();

    }

    @After
    public void tearDown() {
        Session session = ((Session) imDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imsDAO.delete(ims1);
        imsDAO.delete(ims4);
        imDAO.delete(im1);
        imDAO.delete(im2);
        imDAO.delete(im4);
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
       


    }

    /**
     * Test of delete method, of class IncomingMessageDAOImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("IncomingMessageDAOImpl delete");
        Session session = ((Session) imDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imDAO.delete(im4);
        tx.commit();

        IncomingMessage fromdb = (IncomingMessage) session.get(IncomingMessageImpl.class, im4.getId());

        Assert.assertNull(fromdb);
    }

    /**
     * Test of update method, of class IncomingMessageDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("IncomingMessageDAOImpl update"); 
        Session session = ((Session) imDAO.getDBSession().getSession());
        String updated ="updated content";
        IncMessageStatus updatedStat = IncMessageStatus.PROCESSED;
        IncomingMessage fromdb1 = (IncomingMessage) session.get(IncomingMessageImpl.class, im4.getId());
        fromdb1.setContent(updated);
        fromdb1.setMessageStatus(updatedStat);
        
        Transaction tx = session.beginTransaction();
        imDAO.save(fromdb1);
        tx.commit();

        IncomingMessage fromdb = (IncomingMessage) session.get(IncomingMessageImpl.class, fromdb1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb1, fromdb);
        Assert.assertEquals(fromdb1.getId(), fromdb.getId());
        Assert.assertEquals(updated, fromdb.getContent());
        Assert.assertEquals(updatedStat, fromdb.getMessageStatus());
        System.out.println(fromdb.toString());
      
    }
}
