package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.LanguageDAO;
import org.motechproject.mobile.core.dao.MessageRequestDAO;
import org.motechproject.mobile.core.dao.NotificationTypeDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageRequestImpl;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationType;
import org.motechproject.mobile.core.service.MotechContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
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
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class MessageRequestDAOImplTest {

    MessageType t = MessageType.TEXT;
    MStatus sta = MStatus.PENDING;

    public MessageRequestDAOImplTest() {
    }
    @Autowired
    private CoreManager coreManager;
    @Autowired
    private MessageRequest mr1;
    @Autowired
    private MessageRequest mr2;
    @Autowired
    private MessageRequest mr3;
    @Autowired
    private MessageRequest mr4;
    @Autowired
    private MessageRequest mr5;
    @Autowired
    private Language lg1;
    @Autowired
    private NotificationType nt1;
    MessageRequestDAO mrDAO;
    LanguageDAO lDAO;
    NotificationTypeDAO ntDao;
    Date datefrom1;
    Date datefrom2;
    Date dateto1;
    Date dateto2;
    Date schedule;
    List expResult;

    @Before
    public void setUp() {

        MotechContext mc = coreManager.createMotechContext();
        lDAO = coreManager.createLanguageDAO(mc);
        lg1.setId(8234L);
        lg1.setCode("it");

        ntDao = coreManager.createNotificationTypeDAO(mc);
        nt1.setId(9878L);
        nt1.setName("pregnancy");



        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            datefrom1 = df.parse("2009-08-21");
            datefrom2 = df.parse("2009-09-04");
            dateto1 = df.parse("2009-10-30");
            dateto2 = df.parse("2009-11-04");
            schedule = df.parse("2009-10-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }




        mrDAO = coreManager.createMessageRequestDAO(mc);
        mr1.setId(1L);
        mr1.setDateCreated(new Date());
        mr1.setLanguage(lg1);
        mr1.setRecipientName("jlkj");
        mr1.setRecipientId("r1");
        mr1.setMessageType(t);

        mr2.setId(3L);
        mr2.setDateCreated(new Date());
        mr2.setLanguage(lg1);
        mr2.setRecipientName("jojo");
        mr2.setRecipientId("r2");
        mr2.setMessageType(t);
        mr2.setDateFrom(datefrom1);
        mr2.setDateTo(dateto1);
        mr2.setStatus(sta);
        mr2.setSchedule(schedule);

        mr3.setId(5L);
        mr3.setDateCreated(new Date());
        mr3.setLanguage(lg1);
        mr3.setRecipientName("joseph");
        mr3.setRecipientId("r3");
        mr3.setMessageType(t);
        mr3.setDateFrom(datefrom2);
        mr3.setDateTo(dateto2);
        mr3.setStatus(sta);
        mr3.setSchedule(schedule);

        mr4.setId(7L);
        mr4.setDateCreated(new Date());
        mr4.setLanguage(lg1);
        mr4.setRecipientName("jimmy hendrix");
        mr4.setRecipientId("r4");
        mr4.setMessageType(t);
        mr4.setDateFrom(datefrom2);
        mr4.setDateTo(dateto2);
        mr4.setStatus(MStatus.INVALIDNET);
        mr4.setSchedule(schedule);
        mr4.setTryNumber(2);

        mr5.setId(78L);
        mr5.setDateCreated(new Date());
        mr5.setLanguage(lg1);
        mr5.setRecipientName("Kodjo");
        mr5.setRecipientId("r5");
        mr5.setMessageType(t);
        mr5.setStatus(MStatus.INVALIDNET);
        mr5.setSchedule(schedule);
        mr5.setTryNumber(1);

        Session session = (Session) lDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        lDAO.save(lg1);
        ntDao.save(nt1);
        mrDAO.save(mr2);
        mrDAO.save(mr3);
        mrDAO.save(mr4);
        mrDAO.save(mr5);
        tx.commit();

        expResult = new ArrayList();
        expResult.add(mr2);
        expResult.add(mr3);
    }

    @After
    public void tearDown() {
        Session session = (Session) lDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mrDAO.delete(mr1);
        mrDAO.delete(mr2);
        mrDAO.delete(mr3);
        mrDAO.delete(mr4);
        mrDAO.delete(mr5);
        lDAO.delete(lg1);
        ntDao.delete(nt1);
        
        tx.commit();
    }

    /**
     * Test of save method, of class MessageRequestDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("test save MessageRequest Object");
        Session session = (Session) mrDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mrDAO.save(mr1);
        tx.commit();

        MessageRequest fromdb = (MessageRequestImpl) session.get(MessageRequestImpl.class, mr1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(t, fromdb.getMessageType());
        Assert.assertEquals(lg1.getCode(), fromdb.getLanguage().getCode());
    }

    /**
     * Test of getMsgRequestByStatusAndSchedule method, of class MessageRequestDAOImpl.
     */
    @Test
    public void testGetMsgRequestByStatusAndSchedule() {
        System.out.println("getMsgRequestByStatusAndSchedule");

        List result = mrDAO.getMsgRequestByStatusAndSchedule(sta, schedule);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(expResult.size(), result.size());
        Assert.assertTrue(result.contains(mr2));
        Assert.assertTrue(result.contains(mr3));
    }

    /**
     * Test of delete method, of class MessageRequestDAOImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("test MessageRequest delete");
        Session session = (Session) mrDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mrDAO.delete(mr4);
        tx.commit();
        MessageRequest fromdb = (MessageRequestImpl) session.get(MessageRequestImpl.class, mr4.getId());
        Assert.assertNull(fromdb);
    }

    /**
     * Test of getMsgRequestByStatusAndTryNumber method, of class MessageRequestDAOImpl.
     */
    @Test
    public void testGetMsgRequestByStatusAndTryNumber() {
        System.out.println("test MessageRequest getMsgRequestByStatusAndTryNumber");
        List expResult = new ArrayList();
        expResult.add(mr4);
        expResult.add(mr5);
        List result = mrDAO.getMsgRequestByStatusAndTryNumber(mr4.getStatus(), mr4.getTryNumber());
        Assert.assertNotNull(result);
        Assert.assertEquals(expResult.size(), result.size());
        Assert.assertEquals(expResult, result);
        Assert.assertTrue(result.contains(mr4));
        Assert.assertTrue(result.contains(mr5));

    }

    /**
     * Test of getById method, of class MessageRequestDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("test MessageRequest getById");
        MessageRequest fromdb = (MessageRequestImpl) mrDAO.getById(mr3.getId());
        Assert.assertNotNull(fromdb);
        System.out.print("test MessageRequest last modified field : " + fromdb.getLastModified().toString());
        Assert.assertEquals(mr3, fromdb);
    }

    /**
     * Test of getMsgByStatus method, of class MessageRequestDAOImpl.
     */
    @Test
    public void testGetMsgByStatus() {
        System.out.println("getMsgByStatus");
        MStatus status = MStatus.INVALIDNET;
        List<MessageRequest> expResult = new ArrayList<MessageRequest>();
        expResult.add(mr4);
        expResult.add(mr5);
        List<MessageRequest> result = mrDAO.getMsgByStatus(status);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(expResult.size(), result.size());
        Assert.assertEquals(true, result.contains(mr4));
        Assert.assertEquals(true, result.contains(mr5));

    }
    
    @Test
    public void testGetMsgByRecipientAndStatus() {
    	MStatus status = MStatus.PENDING;
    	String recipientID = "r2";
    	List<MessageRequest> expectedList = new ArrayList<MessageRequest>();
    	expectedList.add(mr2);
    	List<MessageRequest> actualList = mrDAO.getMsgRequestByRecipientAndStatus(recipientID, status);   	
    	Assert.assertEquals(expectedList, actualList);
    }
    
}
