package org.motechproject.mobile.model.dao.hibernate.imp;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncomingMessageSession;
import org.motechproject.mobile.core.model.IncomingMessageSessionImpl;
import org.motechproject.mobile.model.dao.imp.IncomingMessageSessionDAO;
import java.util.Date;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joseph Djomeda (joseph@dreamoval.com)
 * @Date
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
@TransactionConfiguration
@Transactional
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
;
        imsDAO = coreManager.createIncomingMessageSessionDAO();

        ims1.setId("12000000019");
        ims1.setDateStarted(new Date());
        ims1.setFormCode("fc_ims1");
        ims1.setLastActivity(new Date());
        ims1.setRequesterPhone("233243667788");


        ims2.setId("12000000020");
        ims2.setDateStarted(new Date());
        ims2.setFormCode("fc_ims2");
        ims2.setLastActivity(new Date());
        ims2.setRequesterPhone(requesterPhone);

        ims3.setId("12000000021");
        ims3.setDateStarted(new Date());
        ims3.setFormCode("fc_ims3");
        ims3.setLastActivity(new Date());
        ims3.setRequesterPhone(requesterPhone);


        imsDAO.save(ims2);
        imsDAO.save(ims3);

    }

    @After
    public void tearDown() {
   
        imsDAO.delete(ims2);
        imsDAO.delete(ims3);
    }


    /**
     * Test of save method, of class IncomingMessageSessionDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save IncominMessageSession");

        imsDAO.save(ims1);

        IncomingMessageSession fromdb = (IncomingMessageSession) imsDAO.getSessionFactory().getCurrentSession().get(IncomingMessageSessionImpl.class, ims1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, ims1);
        Assert.assertEquals(fromdb.getId(), ims1.getId());
        System.out.println("the formcode: " + fromdb.getFormCode());
        System.out.println(fromdb.toString());


    }

}
