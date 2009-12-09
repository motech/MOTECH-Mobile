package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageSessionDAO;
import com.dreamoval.motech.model.imp.IncomingMessageSession;
import com.dreamoval.motech.model.imp.IncomingMessageSessionImpl;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
 * @author joseph Djomeda (joseph@dreamoval.com)
 * @Date 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class IncomingMessageSessionDAOImplTest {


    @Autowired
    CoreManager coreManager;
    IncomingMessageSessionDAO imsDAO;

    @Autowired
    private IncomingMessageSession ims1;
    @Autowired
    private IncomingMessageSession ims2;
    @Autowired
    private IncomingMessageSession ims3;


    public IncomingMessageSessionDAOImplTest() {
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
        imsDAO = coreManager.createIncomingMessageSessionDAO(tc);

        ims1.setId(9087L);
        ims1.setDateStarted(new Date());
        ims1.setFormCode("fc_ims1");
        ims1.setLastActivity(new Date());
        ims1.setRequesterPhone("2233445566");


        ims2.setId(9088L);
        ims2.setDateStarted(new Date());
        ims2.setFormCode("fc_ims2");
        ims2.setLastActivity(new Date());
        ims2.setRequesterPhone("2233445566");


        Transaction tx = (Transaction) imsDAO.getDBSession().getTransaction();
        tx.begin();
        imsDAO.save(ims2);
        tx.commit();

    }

    @After
    public void tearDown() {
    }
    
    
    
    /**
     * Test of save method, of class IncomingMessageSessionDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save IncominMessaeSesion");

        Session session = ((Session) imsDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imsDAO.delete(ims1);
        tx.commit();

        session.beginTransaction();
        IncomingMessageSession fromdb = (IncomingMessageSession) session.get(IncomingMessageSessionImpl.class, ims1.getId());
        session.getTransaction().commit();
        Assert.assertNull(fromdb);


    }


    /**
     * Test of getIncomingMdgSessionByRequestedPhone method, of class IncomingMessageSessionDAOImpl.
     */
//    @Ignore
//    @Test
//    public void testGetIncomingMdgSessionByRequestedPhone() {
//        System.out.println("getIncomingMdgSessionByRequestedPhone");
//        String requesterPhone = "";
//        IncomingMessageSessionDAOImpl instance = new IncomingMessageSessionDAOImpl();
//        List expResult = null;
//        List result = instance.getIncomingMdgSessionByRequestedPhone(requesterPhone);
//
//    }
//
//
//    /**
//     * Test of delete method, of class IncomingMessageSessionDAOImpl.
//     */
//    @Ignore
//    @Test
//    public void testDelete() {
//        System.out.println("Delete");
//
//        Session session = ((Session) mDDAO.getDBSession().getSession());
//        Transaction tx = session.beginTransaction();
//        mDDAO.delete(md2);
//        tx.commit();
//
//        session.beginTransaction();
//        GatewayRequest fromdb = (GatewayRequestImpl) session.get(GatewayRequestImpl.class, md2.getId());
//        session.getTransaction().commit();
//        Assert.assertNull(fromdb);
//
//
//    }
    
    
}
