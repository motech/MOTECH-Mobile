package org.motechproject.mobile.model.dao.hibernate.imp;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageResponseStatus;
import org.motechproject.mobile.core.model.IncomingMessageResponse;
import org.motechproject.mobile.core.model.IncomingMessageResponseImpl;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.model.dao.imp.IncomingMessageResponseDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author joseph Djomeda (joseph@dreamoval.com)
 *  Date : Dec 11, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
@TransactionConfiguration
@Transactional
public class IncomingMessageResponseDAOImplTest {

     @Autowired
    CoreManager coreManager;
    IncomingMessageResponseDAO imrDAO;
    @Autowired
    private IncomingMessageResponse imr1;
    @Autowired
    private IncomingMessageResponse imr2;
    @Autowired
    private IncomingMessageResponse imr3;

     @Before
    public void setUp() {

        imrDAO = coreManager.createIncomingMessageResponseDAO();

        imr1.setId(12000000018l);
        imr1.setContent("content for imr1");
        imr1.setMessageResponseStatus(IncMessageResponseStatus.SAVED);



    }

    @After
    public void tearDown() {
    }


    /**
     * test save of class IncomingMessageResponseImpl
     */
    @Test
    public void testSave() {
        System.out.println("save IncomingMessageResponse");
        imrDAO.save(imr1);
        IncomingMessageResponse fromdb = (IncomingMessageResponse) imrDAO.getSessionFactory().getCurrentSession().get(IncomingMessageResponseImpl.class, imr1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, imr1);
        Assert.assertEquals(fromdb.getId(), imr1.getId());
        System.out.println("the form content: " + fromdb.getContent());
        System.out.println(fromdb.toString());
    }

}
