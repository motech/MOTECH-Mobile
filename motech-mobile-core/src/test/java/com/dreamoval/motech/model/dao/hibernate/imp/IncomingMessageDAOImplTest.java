/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.model.dao.imp.IncomingMessageDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncMessageSessionStatus;
import com.dreamoval.motech.core.model.IncMessageStatus;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageImpl;
import com.dreamoval.motech.core.model.IncomingMessageSession;
import com.dreamoval.motech.core.service.MotechContext;
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

    @Autowired
    private IncomingMessageSession ims1;


    @Autowired
    private IncomingMessage im1;
    @Autowired
    private IncomingMessage im2;
    @Autowired
    private IncomingMessage im3;

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

        ims1.setId(923L);
        ims1.setFormCode("code_IM");
        ims1.setRequesterPhone("1122334455");
        ims1.setMessageSessionStatus(IncMessageSessionStatus.STARTED);
        ims1.setLastActivity(new Date());

//        Session session = ((Session) imsDAO.getDBSession().getSession());
//        Transaction tx = session.beginTransaction();
//        imsDAO.save(ims1);
//        tx.commit();

    }

    @After
    public void tearDown() {
    }

     /**
     * Test of save method, of class IncomingMessageDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("IncomingMessageDAOImpl save");
        Session session = ((Session) imDAO.getDBSession().getSession());
//        ims1.addIncomingMessage(im1);
        Transaction tx = session.beginTransaction();
        imDAO.save(im1);
        tx.commit();


        IncomingMessage fromdb = (IncomingMessage) session.get(IncomingMessageImpl.class, im1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, im1);
        Assert.assertEquals(fromdb.getId(), im1.getId());
//        Assert.assertEquals(fromdb.getIncomingMsgSession(), im1.getIncomingMsgSession());
//        Assert.assertEquals(fromdb.getIncomingMsgSession().getId(), im1.getIncomingMsgSession().getId());
//        Assert.assertEquals(fromdb.getIncomingMsgSession().getIncomingMessages(), im1.getIncomingMsgSession().getIncomingMessages());
        System.out.println("the form content:\n " + fromdb.getContent());

    }

}