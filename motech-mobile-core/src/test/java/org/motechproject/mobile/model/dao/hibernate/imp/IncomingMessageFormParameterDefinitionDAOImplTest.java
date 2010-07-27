package org.motechproject.mobile.model.dao.hibernate.imp;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncomingMessageFormParameterDefinition;
import org.motechproject.mobile.core.model.IncomingMessageFormParameterDefinitionImpl;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormParameterDefinitionDAO;
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
 * @Date Dec 11, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
@TransactionConfiguration
@Transactional
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


        impdDAO = coreManager.createIncomingMessageFormParameterDefinitionDAO();


        impd1.setId("12000000017");
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

        impdDAO.save(impd1);

        IncomingMessageFormParameterDefinition fromdb = (IncomingMessageFormParameterDefinition) impdDAO.getSessionFactory().getCurrentSession().get(IncomingMessageFormParameterDefinitionImpl.class, impd1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, impd1);
        Assert.assertEquals(fromdb.getId(), impd1.getId());
        System.out.println("the form content: " + fromdb.getId());
        System.out.println(fromdb.toString());

    }
}
