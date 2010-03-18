package org.motechproject.mobile.model.dao.hibernate.imp;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormDefinition;
import org.motechproject.mobile.core.model.IncomingMessageFormImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormDAO;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormDefinitionDAO;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormParameterDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    private IncomingMessageFormDefinition imfd3;
    @Autowired
    private IncomingMessageFormDefinition imfd4;
    @Autowired
    private IncomingMessageFormDefinition imfd5;
    @Autowired
    private IncomingMessageForm imf1;
    @Autowired
    private IncomingMessageForm imf2;
    @Autowired
    private IncomingMessageForm imf3;
    @Autowired
    private IncomingMessageForm imf4;
    @Autowired
    private IncomingMessageForm imf5;
    @Autowired
    private IncomingMessageForm exampleForm;
    @Autowired
    private IncomingMessageFormParameter imfP1;
    @Autowired
    private IncomingMessageFormParameter imfP2;
    @Autowired
    private IncomingMessageFormParameter imfP3;
    @Autowired
    private IncomingMessageFormParameter imfP4;
    @Autowired
    private IncomingMessageFormParameter imfP5;

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

        imf3.setId(656L);
        imf3.setDateCreated(new Date());
        imf3.setMessageFormStatus(IncMessageFormStatus.NEW);

        imf4.setId(984L);
        imf4.setDateCreated(new Date());
        imf4.setMessageFormStatus(IncMessageFormStatus.NEW);

        imf5.setId(454L);
        imf5.setDateCreated(new Date());
        imf5.setMessageFormStatus(IncMessageFormStatus.INVALID);

        imfd1.setId(65L);
        imfd1.setFormCode("test_formdef");
        imfd1.setDateCreated(new Date());

        imfd2.setId(825L);
        imfd2.setFormCode("test_formdef 2");
        imfd2.setDateCreated(new Date());

        imfd3.setId(828L);
        imfd3.setFormCode("test_formdef 3");
        imfd3.setDateCreated(new Date());

        imfd4.setId(100L);
        imfd4.setFormCode("test_formdef 4");
        imfd4.setDateCreated(new Date());

        imfd5.setId(103L);
        imfd5.setFormCode("test_formdef 5");
        imfd5.setDateCreated(new Date());

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

        imfP3.setId(9842L);
        imfP3.setDateCreated(new Date());
        imfP3.setErrCode(125);
        imfP3.setErrText("err_code125");
        imfP3.setName("param3");
        imfP3.setValue("value3");

        imfP4.setId(300L);
        imfP4.setDateCreated(new Date());
        imfP4.setErrCode(200);
        imfP4.setErrText("err_code200");
        imfP4.setName("param4");
        imfP4.setValue("value4");

        imfP5.setId(202L);
        imfP5.setDateCreated(new Date());
        imfP5.setErrCode(201);
        imfP5.setErrText("err_code201");
        imfP5.setName("param5");
        imfP5.setValue("value5");

        imf3.addIncomingMsgFormParam(imfP3.getName(), imfP3);
        imfd3.addIncomingMessageForm(imf3);

        imf4.addIncomingMsgFormParam(imfP4.getName(), imfP4);
        imfd4.addIncomingMessageForm(imf4);

        imf5.addIncomingMsgFormParam(imfP5.getName(), imfP5);
        imfd5.addIncomingMessageForm(imf5);

        Session session = ((Session) imfdDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imfdDAO.save(imfd1);
        imfdDAO.save(imfd2);
        imfdDAO.save(imfd3);
        imfdDAO.save(imfd4);
        imfdDAO.save(imfd5);
        imfDAO.save(imf3);
        imfDAO.save(imf4);
        imfDAO.save(imf5);
        tx.commit();


    }

    @After
    public void tearDown() {
        Session session = ((Session) imfdDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        imfDAO.delete(imf1);
        imfDAO.delete(imf2);
        imfDAO.delete(imf4);
        imfDAO.delete(imf5);
        imfdDAO.delete(imfd1);
        imfdDAO.delete(imfd2);
        imfdDAO.delete(imfd3);
        imfdDAO.delete(imfd4);
        imfdDAO.delete(imfd5);
        imfDAO.delete(imf3);
        tx.commit();

    }

    /**
     * Test of save method, of class IncomingMessageFormDAOImpl.
     */
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

    /**
     * Test of save method with form parameters, of class IncomingMessageFormDAOImpl.
     */
    @Test
    public void testSaveWithParam() {
        System.out.println("IncomingMessageFormDAO save with param");
        Session session = ((Session) imfDAO.getDBSession().getSession());
        Map<String, IncomingMessageFormParameter> expMap = new HashMap<String, IncomingMessageFormParameter>();
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

    /**
     * Test of findByExample method, of class IncomingMessageFormDAOImpl.
     */
    @Test
    public void testFindByExample() {
        System.out.println("IncomingMessageFormDAO findByExample");
        Session session = ((Session) imfDAO.getDBSession().getSession());
        List expResult = new ArrayList();
        expResult.add(imf3);
        expResult.add(imf4);
        exampleForm.setMessageFormStatus(IncMessageFormStatus.NEW);
        Transaction tx = session.beginTransaction();
        List result = imfDAO.findByExample(exampleForm);
        tx.commit();
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(expResult.size(), result.size());



    }


}
