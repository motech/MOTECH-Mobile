package org.motechproject.mobile.model.dao.hibernate.imp;

import org.springframework.transaction.annotation.Transactional;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.model.dao.imp.IncomingMessageFormDefinitionDAO;
import org.motechproject.mobile.core.model.IncomingMessageFormDefinition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import static org.junit.Assert.*;

/**
 *
 * @author joseph Djomeda (joseph@dreamoval.com)
 * @Date Dec 11, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
@TransactionConfiguration
@Transactional
public class IncomingMessageFormDefinitionDAOImplTest {

    @Autowired
    CoreManager coreManager;
    IncomingMessageFormDefinitionDAO imfDAO;
    @Autowired
    IncomingMessageFormDefinition fdef1;

    String formCode = "TESTFORM";

    public IncomingMessageFormDefinitionDAOImplTest() {
    }

    @Before
    public void setUp() {

        imfDAO = coreManager.createIncomingMessageFormDefinitionDAO();

        fdef1 = coreManager.createIncomingMessageFormDefinition();
        fdef1.setFormCode(formCode);

        imfDAO.save(fdef1);

    }

    @After
    public void tearDown() {
  
        imfDAO.delete(fdef1);

    }
    /**
     * Test of getByCode method, of class IncomingMessageFormDefinitionDAOImpl.
     */
    @Test
    public void testGetByCode() {
        System.out.println("getByCode");

        IncomingMessageFormDefinition result = imfDAO.getByCode(formCode);
        assertNotNull(result);
        assertEquals(result.getFormCode(), formCode);
        System.out.println(result.toString());
    }
}
