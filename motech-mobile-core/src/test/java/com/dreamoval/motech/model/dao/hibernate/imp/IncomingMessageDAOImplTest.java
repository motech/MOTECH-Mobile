/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.model.dao.imp.IncomingMessageDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncMessageStatus;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageImpl;
import com.dreamoval.motech.core.service.MotechContext;
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


        im1.setId(987L);
        im1.setContent("content im1");
        im1.setLastModified(new Date());
        im1.setMessageStatus(IncMessageStatus.PROCESSING);
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
        Transaction tx = session.beginTransaction();
        imDAO.save(im1);
        tx.commit();


        IncomingMessage fromdb = (IncomingMessage) session.get(IncomingMessageImpl.class, im1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, im1);
        Assert.assertEquals(fromdb.getId(), im1.getId());
        System.out.println("the form content: " + fromdb.getContent());
    }

}