package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.GatewayRequestDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.model.MessageType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class GatewayRequestDAOImplTest {

    @Autowired
    CoreManager coreManager;
    GatewayRequestDAO mDDAO;
    @Autowired
    private GatewayRequest md1;
    @Autowired
    private GatewayRequest md2;
    @Autowired
    private GatewayRequest md3;
    @Autowired
    private GatewayRequest md4;
    @Autowired
    private GatewayRequest md5;
    @Autowired
    private GatewayRequest md6;
    String text;
    @Autowired
    private GatewayRequest md7;
    @Autowired
    private GatewayResponse rd1;
    @Autowired
    private GatewayResponse rd2;
    @Autowired
    private GatewayRequestDetails grd4;
    @Autowired
    private GatewayRequestDetails grd5;

     Date dateFrom1;
        Date dateFrom2;
        Date dateTo1;
        Date dateTo2;
        Date schedule;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        mDDAO = coreManager.createGatewayRequestDAO(coreManager.createMotechContext());



        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateFrom1 = df.parse("2009-08-21");
            dateFrom2 = df.parse("2009-09-04");
            dateTo1 = df.parse("2009-10-30");
            dateTo2 = df.parse("2009-11-04");
            schedule = df.parse("2009-10-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        text = "First";
        md1.setId(1L);
        md1.setMessage(text);
        md1.setRecipientsNumber("123445");
        md1.setDateFrom(new Date());

        md2.setId(2L);
        md2.setMessage("Second");
        md2.setRecipientsNumber("123445");
        md2.setDateFrom(new Date());

        md3.setId(3L);
        md3.setMessage("Third");
        md3.setRecipientsNumber("123445");
        md3.setDateFrom(new Date());

        md4.setId(4L);
        md4.setMessage("Test for dummies 4");
        md4.setRecipientsNumber("123445, 54321");
        md4.setDateFrom(dateFrom1);
        md4.setDateTo(dateTo1);
        md4.setMessageStatus(MStatus.FAILED);

        md5.setId(5L);
        md5.setDateSent(new Date());
        md5.setRecipientsNumber("12345,54321");
        md5.setMessage("insertion with responsedetailsobject");
        md5.setDateFrom(dateFrom2);
        md5.setDateTo(dateTo2);
        md5.setMessageStatus(MStatus.FAILED);

        md6.setId(6L);
        md6.setDateSent(new Date());
        md6.setRecipientsNumber("12345,54321");
        md6.setMessage("another test for dummies");
        md6.setDateFrom(new Date());


        rd1.setId(8L);
        rd1.setMessageStatus(MStatus.PENDING);
        rd1.setRecipientNumber("123445");

        rd2.setId(9L);
        rd2.setMessageStatus(MStatus.FAILED);
        rd2.setRecipientNumber("54321");

        grd4.setId(12L);
        grd4.setMessage("some messaege 4");
        grd4.setMessageType(MessageType.TEXT);
        grd4.setNumberOfPages(1);
        Set<GatewayRequest> gwrs4 = new HashSet<GatewayRequest>();
        gwrs4.add(md4);
        grd4.setGatewayRequests(gwrs4);

        grd5.setId(13L);
        grd5.setMessage("some messaege 5");
        grd5.setMessageType(MessageType.TEXT);
        grd5.setNumberOfPages(1);
        Set<GatewayRequest> gwrs5 = new HashSet<GatewayRequest>();
        gwrs5.add(md4);
        grd5.setGatewayRequests(gwrs5);

        setUpInitialData();
    }

    @After
    public void tearDown() {
        Transaction tx = ((Session) mDDAO.getDBSession().getSession()).beginTransaction();

        mDDAO.delete(md1);
        mDDAO.delete(md2);
        mDDAO.delete(md3);
        mDDAO.delete(md4);
        mDDAO.delete(md5);
        mDDAO.delete(md6);

        tx.commit();

    }

    public void setUpInitialData() {
        Transaction tx = ((Session) mDDAO.getDBSession().getSession()).beginTransaction();

        mDDAO.save(md1);
        mDDAO.save(md2);
        mDDAO.save(md3);
        mDDAO.save(md4);
        mDDAO.save(md5);
        mDDAO.save(md6);

        tx.commit();

    }

    public GatewayRequestDAOImplTest() {
    }

    /**
     * Test of delete method, of class GatewayRequestDAOImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("Delete");

        Session session = ((Session) mDDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        mDDAO.delete(md2);
        tx.commit();

        session.beginTransaction();
        GatewayRequest fromdb = (GatewayRequestImpl) session.get(GatewayRequestImpl.class, md2.getId());
        session.getTransaction().commit();
        Assert.assertNull(fromdb);


    }

    /**
     * Test of save method with update purpose, of class GatewayRequestDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("Update");

        md1.setMessage("First altered");
        md1.setDateFrom(new Date());
        md1.setDateTo(new Date());
        Session session = ((Session) mDDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        mDDAO.save(md1);
        tx.commit();

        session.beginTransaction();
        GatewayRequest fromdb = (GatewayRequestImpl) session.get(GatewayRequestImpl.class, md1.getId());
        session.getTransaction().commit();
        Assert.assertFalse(text.equals(fromdb.getMessage()));
    }

/**
     * Test of save method with child saving purpose, of class GatewayRequestDAOImpl.
     */
    @Test
    public void testSaveWithResponse() {
        System.out.println("saving with response object");
        List<GatewayResponse> res = new ArrayList<GatewayResponse>();
        res.add(rd1);
        res.add(rd2);
        md5.addResponse(res);
        Session session = (Session) mDDAO.getDBSession().getSession();
        
        Transaction tx = session.beginTransaction();
        mDDAO.save(md5);
        tx.commit();

        GatewayRequest fromdb = (GatewayRequestImpl) session.get(GatewayRequestImpl.class, md5.getId());
        Set<GatewayResponse> fromdbchild = fromdb.getResponseDetails();
        ArrayList<GatewayResponse> children = new ArrayList<GatewayResponse>();

        for (Iterator it = fromdbchild.iterator(); it.hasNext();) {
            children.add((GatewayResponse) it.next());
        }

        Assert.assertEquals(2, fromdbchild.size());
//Needs to find out the order issue inside the List return by hibernate

//            Assert.assertEquals(rd1.getId(),children.get(0).getId());
//            Assert.assertEquals(children.get(0).getMessageStatus(), rd1.getMessageStatus());
//            Assert.assertEquals(children.get(0).getRecipientNumber(), rd1.getRecipientNumber());
//
//            Assert.assertEquals(children.get(1).getId(), rd1.getId());
//            Assert.assertEquals(children.get(1).getMessageStatus(), rd1.getMessageStatus());
//            Assert.assertEquals(children.get(1).getRecipientNumber(), rd1.getRecipientNumber());

    }

    /**
     * Test of getById method, of class GatewayRequestDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("testing FindById");
        Transaction tx = ((Session) mDDAO.getDBSession().getSession()).beginTransaction();
        GatewayRequest result = (GatewayRequest) mDDAO.getById(md1.getId());
           System.out.println("the date lastModified: " + result.getLastModified());
        Assert.assertSame(md1, result);
        Assert.assertEquals(md1.getId(), result.getId());
        Assert.assertEquals(md1.getMessage(), result.getMessage());
        Assert.assertEquals(md1.getRecipientsNumber(), result.getRecipientsNumber());
        Assert.assertEquals(md1.getDateFrom(), result.getDateFrom());

         System.out.println(result.toString());
    }

    /**
     * Test of findByExample method, of class GatewayRequestDAOImpl.
     */
    @Test
    public void testFindByExample() {
        System.out.println("testing findByExample");

        List<GatewayRequest> expResult = new ArrayList<GatewayRequest>();
        expResult.add(md1);
        expResult.add(md2);
        expResult.add(md3);

        md7.setRecipientsNumber("123445");
        Transaction tx = ((Session) mDDAO.getDBSession().getSession()).beginTransaction();
        List<GatewayRequest> result = mDDAO.findByExample(md7);
        tx.commit();
        Assert.assertEquals(expResult.size(), result.size());
        Assert.assertEquals(true, result.contains(md1));
        Assert.assertEquals(true, result.contains(md2));
        Assert.assertEquals(true, result.contains(md3));

    }


    /**
     * Test of getByStatusAndSchedule method, of class GatewayRequestDAOImpl.
     */
    @Ignore
    @Test
    public void testGetByStatusAndSchedule() {
        System.out.println("getByStatusAndSchedule");
        List<GatewayRequest> expResult = new ArrayList<GatewayRequest>();
        md4.setGatewayRequestDetails(grd4);
        md5.setGatewayRequestDetails(grd5);
        expResult.add(md4);
        expResult.add(md5);
        MStatus status = MStatus.FAILED;
        List result = mDDAO.getByStatusAndSchedule(status, schedule);
        Assert.assertFalse(result.isEmpty());
        Assert.assertEquals(expResult.size(), result.size());
        Assert.assertEquals(true, result.contains(md4));
        Assert.assertEquals(true, result.contains(md5));
      
    }
}
