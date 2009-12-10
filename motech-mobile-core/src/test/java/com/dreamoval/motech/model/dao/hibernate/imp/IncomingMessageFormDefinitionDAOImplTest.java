package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDefinitionDAO;
import com.dreamoval.motech.model.imp.IncomingMessageFormDefinition;
import org.hibernate.Transaction;
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

    public IncomingMessageFormDefinitionDAOImplTest() {
    }

    @Before
    public void setUp() {
        MotechContext mCtx = coreManager.createMotechContext();
        imfDAO = coreManager.createIncomingMessageFormDefinitionDAO(mCtx);

        IncomingMessageFormDefinition fdef = coreManager.createIncomingMessageFormDefinition();
        fdef.setFormCode("TC");

        Transaction tx = (Transaction) imfDAO.getDBSession().getTransaction();
        tx.begin();
        imfDAO.save(fdef);
        tx.commit();
    }

    /**
     * Test of getByCode method, of class IncomingMessageFormDefinitionDAOImpl.
     */
    @Test
    public void testGetByCode() {
        System.out.println("getByCode");
        String formCode = "TC";

        IncomingMessageFormDefinition result = imfDAO.getByCode(formCode);
        assertNotNull(result);
        assertEquals(result.getFormCode(), "TC");
    }
}
