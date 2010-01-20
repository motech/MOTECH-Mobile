package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncMessageFormStatus;
import com.dreamoval.motech.core.model.IncomingMessageForm;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinition;
import com.dreamoval.motech.core.model.IncomingMessageFormImpl;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDefinitionDAO;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormParameterDAO;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    IncomingMessageFormParameterDAO imfPDAO;

    @Autowired
    private IncomingMessageFormDefinition imfd1;
    @Autowired
    private IncomingMessageFormDefinition imfd2;

    @Autowired
    private IncomingMessageForm imf1;
    @Autowired
    private IncomingMessageForm imf2;
    @Autowired
    private IncomingMessageForm imf3;
    @Autowired
    private IncomingMessageFormParameter imfP1;
    @Autowired
    private IncomingMessageFormParameter imfP2;

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
        imfPDAO = coreManager.createIncomingMessageFormParameterDAO(tc);

        imf1.setId(987L);
        imf1.setDateCreated(new Date());
        imf1.setMessageFormStatus(IncMessageFormStatus.NEW);

        imf2.setId(156L);
        imf2.setDateCreated(new Date());
        imf2.setMessageFormStatus(IncMessageFormStatus.NEW);

        imfd1.setId(65L);
        imfd1.setFormCode("test_formdef");
        imfd1.setDateCreated(new Date());
        
        imfd2.setId(825L);
        imfd2.setFormCode("test_formdef 2");
        imfd2.setDateCreated(new Date());

        imfP1.setId(965L);
        imfP1.setDateCreated(new Date());
        imfP1.setErrCode(12);
        imfP1.setErrText("err_code1");
        imfP1.setName("param1");
        imfP1.setValue("value1");

        imfP2.setId(535L);
        imfP2.setDateCreated(new Date());
        imfP2.setErrCode(159);
        imfP2.setErrText("err_code159");
        imfP2.setName("param2");
        imfP2.setValue("value2");

        Session session = ((Session) imfdDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
//        imfPDAO.save(imfP1);
        imfdDAO.save(imfd1);
        imfdDAO.save(imfd2);
        tx.commit();


    }

    @After
    public void tearDown() {
         Session session = ((Session) imfdDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
//        imfPDAO.delete(imfP1);
        imfDAO.delete(imf1);
        imfdDAO.delete(imfd1);
        imfdDAO.delete(imfd2);
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


     @Test
     public void testSaveWithParam(){
      System.out.println("IncomingMessageFormDAO save with param");
      Session session = ((Session) imfDAO.getDBSession().getSession());
      Map<String ,IncomingMessageFormParameter> expMap = new HashMap<String, IncomingMessageFormParameter>();
      expMap.put(imfP2.getName(), imfP2);
        Transaction tx = session.beginTransaction();
        imf2.addIncomingMsgFormParam(imfP2.getName(), imfP2);
        imfd2.addIncomingMessageForm(imf2);
        imfDAO.save(imf2);
        tx.commit();

        IncomingMessageForm fromdb = (IncomingMessageForm) session.get(IncomingMessageFormImpl.class, imf2.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, imf2);
        Assert.assertEquals(fromdb.getId(), imf2.getId());
        Assert.assertEquals(fromdb.getId(), imf2.getId());
        Assert.assertEquals(expMap.size(), imf2.getIncomingMsgFormParameters().size());
        Assert.assertEquals(expMap, imf2.getIncomingMsgFormParameters());
        Assert.assertEquals(expMap.get(imfP2.getName()), imf2.getIncomingMsgFormParameters().get(imfP2.getName()));
        System.out.println("the form date created: " + fromdb.getDateCreated());
        System.out.println(fromdb.toString());

     }


}
