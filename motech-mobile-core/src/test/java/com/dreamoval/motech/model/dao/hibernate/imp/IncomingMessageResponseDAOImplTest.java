package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncMessageResponseStatus;
import com.dreamoval.motech.core.model.IncomingMessageResponse;
import com.dreamoval.motech.core.model.IncomingMessageResponseImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageResponseDAO;
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

/**
 * @author joseph Djomeda (joseph@dreamoval.com)
 *  Date : Dec 11, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
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

        MotechContext tc = coreManager.createMotechContext();
        imrDAO = coreManager.createIncomingMessageResponseDAO(tc);

        imr1.setId(876L);
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
        Session session = ((Session) imrDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imrDAO.save(imr1);
        tx.commit();


        IncomingMessageResponse fromdb = (IncomingMessageResponse) session.get(IncomingMessageResponseImpl.class, imr1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, imr1);
        Assert.assertEquals(fromdb.getId(), imr1.getId());
        System.out.println("the form content: " + fromdb.getContent());
        System.out.println(fromdb.toString());
    }

}
