package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.GatewayRequestDetailsDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayRequestDetailsImpl;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.service.MotechContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *  Date : Oct 1, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class GatewayRequestDetailsDAOImplTest {

    @Autowired
    CoreManager coreManager;
    GatewayRequestDetailsDAO grDao;
    @Autowired
    GatewayRequestDetails grd1;
    @Autowired
    GatewayRequest gr1;
    @Autowired
    GatewayRequest gr2;
    @Autowired
    GatewayRequestDetails grd2;
    @Autowired
    GatewayRequestDetails grd3;
    @Autowired
    GatewayRequestDetails grd4;
    @Autowired
    GatewayRequestDetails grd5;
    @Autowired
    GatewayRequestDetails grd6;

    @Before
    public void setUp() {
        MotechContext mc = coreManager.createMotechContext();
        grDao = coreManager.createGatewayRequestDetailsDAO(mc);
        grd1.setId(343L);
        grd1.setMessage("message to send");
        grd1.setNumberOfPages(2);

        gr1.setId(800L);
        gr1.setRecipientsNumber("234556");

        gr2.setId(801L);
        gr2.setRecipientsNumber("12345");

        grd2.setId(802L);
        grd2.setMessage("Message for id 802");
        grd2.setMessageType(MessageType.TEXT);
        grd2.setNumberOfPages(2);

        grd3.setId(803L);
        grd3.setMessage("Message for id 803");
        grd3.setMessageType(MessageType.TEXT);
        grd3.setNumberOfPages(2);

        grd4.setId(804L);
        grd4.setMessage("Message for id 803");
        grd4.setMessageType(MessageType.TEXT);
        grd4.setNumberOfPages(2);

        grd5.setId(805L);
        grd5.setMessage("Message for id 805");
        grd5.setMessageType(MessageType.TEXT);
        grd5.setNumberOfPages(2);



    }

    @After
    public void tearDown() {
        Session session = (Session) grDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        grDao.delete(grd1);
        tx.commit();
    }

    /**
     * Test of save method, of class GatewayRequestDetailsDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("test save GatewayRequestDetails object");
        Session session = (Session) grDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        grDao.save(grd1);
        tx.commit();

        session.beginTransaction();
        GatewayRequestDetails fromdb = (GatewayRequestDetailsImpl) session.get(GatewayRequestDetailsImpl.class, grd1.getId());
        session.getTransaction().commit();

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(grd1, fromdb);
        Assert.assertEquals(grd1.getId(), fromdb.getId());
        System.out.println(fromdb.toString());
    }

    /**
     * Test of save method with child persisting purpose, of class GatewayRequestDetailsDAOImpl.
     */
    @Test
    public void testSaveWithChild() {
        System.out.println("test save GatewayRequestDetails object");
        Session session = (Session) grDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        grd1.addGatewayRequest(gr1);
        grd1.addGatewayRequest(gr2);
        grDao.save(grd1);
        tx.commit();

        session.beginTransaction();
        GatewayRequestDetails fromdb = (GatewayRequestDetailsImpl) session.get(GatewayRequestDetailsImpl.class, grd1.getId());
        session.getTransaction().commit();

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(grd1, fromdb);
        Assert.assertEquals(2, fromdb.getGatewayRequests().size());
        Assert.assertEquals(true, fromdb.getGatewayRequests().contains(gr1));
        Assert.assertEquals(true, fromdb.getGatewayRequests().contains(gr2));

    }

    /**
     * Test of save method with updating purpose, of class GatewayRequestDetailsDAOImpl.
     */
    @Ignore
    public void testUpdate() {
        System.out.print("test GatewayRequestDetails Update");
        String alteredmsg = "Altered message";
        int altnumofpage = 5;
        grd5.setId(grd1.getId());
        grd5.setMessage(alteredmsg);
        grd5.setNumberOfPages(altnumofpage);

        Session session = (Session) grDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        session.save(grd5);
        tx.commit();

        session.beginTransaction();
        GatewayRequestDetails fromdb = (GatewayRequestDetailsImpl) session.get(GatewayRequestDetailsImpl.class, grd1.getId());
        session.getTransaction().commit();
        Assert.assertNotNull(fromdb);


    }
}
