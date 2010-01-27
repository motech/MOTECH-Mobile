package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.model.IncomingMessageSession;
import com.dreamoval.motech.core.model.IncomingMessageSessionImpl;
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

    String requesterPhone = "2233445566";

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
        ims1.setRequesterPhone("233243667788");


        ims2.setId(9088L);
        ims2.setDateStarted(new Date());
        ims2.setFormCode("fc_ims2");
        ims2.setLastActivity(new Date());
        ims2.setRequesterPhone(requesterPhone);

        ims3.setId(9089L);
        ims3.setDateStarted(new Date());
        ims3.setFormCode("fc_ims3");
        ims3.setLastActivity(new Date());
        ims3.setRequesterPhone(requesterPhone);


        Transaction tx = (Transaction) imsDAO.getDBSession().getTransaction();
        tx.begin();
        imsDAO.save(ims2);
        imsDAO.save(ims3);
        tx.commit();

    }

    @After
    public void tearDown() {
        Transaction tx = (Transaction) imsDAO.getDBSession().getTransaction();
        tx.begin();
        imsDAO.delete(ims2);
        imsDAO.delete(ims3);
        tx.commit();
    }


    /**
     * Test of save method, of class IncomingMessageSessionDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save IncominMessageSession");

        Session session = ((Session) imsDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imsDAO.save(ims1);
        tx.commit();


        IncomingMessageSession fromdb = (IncomingMessageSession) session.get(IncomingMessageSessionImpl.class, ims1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, ims1);
        Assert.assertEquals(fromdb.getId(), ims1.getId());
        System.out.println("the formcode: " + fromdb.getFormCode());
        System.out.println(fromdb.toString());


    }

}
