package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterDefinition;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterDefinitionImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormParameterDefinitionDAO;
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
public class IncomingMessageFormParameterDefinitionDAOImplTest {

    @Autowired
    CoreManager coreManager;
    IncomingMessageFormParameterDefinitionDAO impdDAO;
    @Autowired
    private IncomingMessageFormParameterDefinition impd1;
    @Autowired
    private IncomingMessageFormParameterDefinition impd2;
    @Autowired
    private IncomingMessageFormParameterDefinition impd3;

    public IncomingMessageFormParameterDefinitionDAOImplTest() {
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
        impdDAO = coreManager.createIncomingMessageFormParameterDefinitionDAO(tc);


        impd1.setId(726L);
        impd1.setDateCreated(new Date());
        impd1.setLength(34);
        impd1.setName("paramdefinition name");
        impd1.setRequired(true);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class IncomingMessageFormParameterDefinitionDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save IncomingMessageFromParameterDefinition");
        Session session = ((Session) impdDAO.getDBSession().getSession());
        Transaction tx = session.beginTransaction();
        impdDAO.save(impd1);
        tx.commit();


        IncomingMessageFormParameterDefinition fromdb = (IncomingMessageFormParameterDefinition) session.get(IncomingMessageFormParameterDefinitionImpl.class, impd1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, impd1);
        Assert.assertEquals(fromdb.getId(), impd1.getId());
        System.out.println("the form content: " + fromdb.getId());
        System.out.println(fromdb.toString());

    }
}
