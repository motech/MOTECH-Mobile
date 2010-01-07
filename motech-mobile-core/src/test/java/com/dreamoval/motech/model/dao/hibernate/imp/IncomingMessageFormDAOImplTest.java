package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncMessageFormStatus;
import com.dreamoval.motech.core.model.IncomingMessageForm;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinition;
import com.dreamoval.motech.core.model.IncomingMessageFormImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDefinitionDAO;
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
 * @Date Dec 11, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class IncomingMessageFormDAOImplTest {

    @Autowired
    CoreManager coreManager;

    IncomingMessageFormDAO imfDAO;
    IncomingMessageFormDefinitionDAO imfdDAO;

    @Autowired
    private IncomingMessageFormDefinition imfd1;

    @Autowired
    private IncomingMessageForm imf1;
    @Autowired
    private IncomingMessageForm imf2;
    @Autowired
    private IncomingMessageForm imf3;

    public IncomingMessageFormDAOImplTest() {
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
        imfDAO = coreManager.createIncomingMessageFormDAO(tc);
        imfdDAO = coreManager.createIncomingMessageFormDefinitionDAO(tc);


        imf1.setId(987L);
        imf1.setDateCreated(new Date());
        imf1.setMessageFormStatus(IncMessageFormStatus.NEW);

        imfd1.setId(65L);
        imfd1.setFormCode("test_formdef");
        imfd1.setDateCreated(new Date());

        Session session = ((Session) imfdDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imfdDAO.save(imfd1);
        tx.commit();


    }

    @After
    public void tearDown() {
         Session session = ((Session) imfdDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction(); 
        imfDAO.delete(imf1);
        imfdDAO.delete(imfd1);
        tx.commit();

    }

     @Test
    public void testSave() {
        System.out.println("IncomingMessageFormDAOImpl save");
        Session session = ((Session) imfDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imfd1.addIncomingMessageForm(imf1);
        imfDAO.save(imf1);
        tx.commit();


        IncomingMessageForm fromdb = (IncomingMessageForm) session.get(IncomingMessageFormImpl.class, imf1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, imf1);
        Assert.assertEquals(fromdb.getId(), imf1.getId());
        System.out.println("the form date created: " + fromdb.getDateCreated());
        System.out.println(fromdb.toString());
    }
}
