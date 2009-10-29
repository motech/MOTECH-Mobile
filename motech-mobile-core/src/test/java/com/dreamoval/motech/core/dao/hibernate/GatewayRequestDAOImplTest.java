package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import java.util.ArrayList;
import java.util.Date;
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

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        mDDAO = coreManager.createGatewayRequestDAO(coreManager.createMotechContext());
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
        md4.setDateFrom(new Date());

        md5.setId(5L);
        md5.setDateSent(new Date());
        md5.setRecipientsNumber("12345,54321");
        md5.setMessage("insertion with responsedetailsobject");
        md5.setDateFrom(new Date());

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


        setUpInitialData();
    }

    @After
    public void tearDown() {
    }

    public void setUpInitialData() {
        Transaction tx = ((Session) mDDAO.getDBSession().getSession()).beginTransaction();

        mDDAO.save(md1);
        mDDAO.save(md2);
        mDDAO.save(md3);
        mDDAO.save(md4);
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
        Assert.assertSame(md1, result);
        Assert.assertEquals(md1.getId(), result.getId());
        Assert.assertEquals(md1.getMessage(), result.getMessage());
        Assert.assertEquals(md1.getRecipientsNumber(), result.getRecipientsNumber());
        Assert.assertEquals(md1.getDateFrom(), result.getDateFrom());
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
}
