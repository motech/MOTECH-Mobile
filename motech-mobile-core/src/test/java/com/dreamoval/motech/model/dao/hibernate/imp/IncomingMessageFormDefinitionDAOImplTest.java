package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDefinitionDAO;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinition;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class IncomingMessageFormDefinitionDAOImplTest {

    @Autowired
    CoreManager coreManager;
    IncomingMessageFormDefinitionDAO imfDAO;
    IncomingMessageFormDefinition fdef;
    String formCode = "TESTFORM";

    public IncomingMessageFormDefinitionDAOImplTest() {
    }

    @Before
    public void setUp() {
        MotechContext mCtx = coreManager.createMotechContext();
        imfDAO = coreManager.createIncomingMessageFormDefinitionDAO(mCtx);

        fdef = coreManager.createIncomingMessageFormDefinition();
        fdef.setFormCode(formCode);

        Transaction tx = (Transaction)imfDAO.getDBSession().getTransaction();
        tx.begin();
        imfDAO.save(fdef);
        tx.commit();
    }

    @After
    public void tearDown() {
        Transaction tx = (Transaction)imfDAO.getDBSession().getTransaction();
        tx.begin();
        imfDAO.delete(fdef);
        tx.commit();
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
