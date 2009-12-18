package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDefinitionDAO;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinition;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterDefinition;
import java.util.Date;
import java.util.HashSet;
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

    @Test
    public void testSave(){
        System.out.println("save");

        MotechContext mCtx = coreManager.createMotechContext();

        IncomingMessageFormDefinition formDef = coreManager.createIncomingMessageFormDefinition();
        formDef.setIncomingMsgParamDefinitions(new HashSet<IncomingMessageFormParameterDefinition>());
        formDef.setFormCode("GeneralOPD");

        IncomingMessageFormParameterDefinition paramDef1 = coreManager.createIncomingMessageFormParameterDefinition();
        paramDef1.setDateCreated(new Date());
        paramDef1.setIncomingMsgFormDefinition(formDef);
        paramDef1.setLength(10);
        paramDef1.setName("FacilityID");
        paramDef1.setParamType("NUMERIC");
        paramDef1.setRequired(true);
        formDef.getIncomingMsgParamDefinitions().add(paramDef1);

        IncomingMessageFormParameterDefinition paramDef2 = coreManager.createIncomingMessageFormParameterDefinition();
        paramDef2.setDateCreated(new Date());
        paramDef2.setIncomingMsgFormDefinition(formDef);
        paramDef2.setLength(10);
        paramDef2.setName("Date");
        paramDef2.setParamType("DATE");
        paramDef2.setRequired(true);
        formDef.getIncomingMsgParamDefinitions().add(paramDef2);

        IncomingMessageFormParameterDefinition paramDef3 = coreManager.createIncomingMessageFormParameterDefinition();
        paramDef3.setDateCreated(new Date());
        paramDef3.setIncomingMsgFormDefinition(formDef);
        paramDef3.setLength(20);
        paramDef3.setName("SerialNo");
        paramDef3.setParamType("ALPHANUM");
        paramDef3.setRequired(true);
        formDef.getIncomingMsgParamDefinitions().add(paramDef3);

        IncomingMessageFormParameterDefinition paramDef4 = coreManager.createIncomingMessageFormParameterDefinition();
        paramDef4.setDateCreated(new Date());
        paramDef4.setIncomingMsgFormDefinition(formDef);
        paramDef4.setLength(10);
        paramDef4.setName("DoB");
        paramDef4.setParamType("DATE");
        paramDef4.setRequired(true);
        formDef.getIncomingMsgParamDefinitions().add(paramDef4);

        IncomingMessageFormParameterDefinition paramDef5 = coreManager.createIncomingMessageFormParameterDefinition();
        paramDef5.setDateCreated(new Date());
        paramDef5.setIncomingMsgFormDefinition(formDef);
        paramDef5.setLength(1);
        paramDef5.setName("Sex");
        paramDef5.setParamType("GENDER");
        paramDef5.setRequired(true);
        formDef.getIncomingMsgParamDefinitions().add(paramDef5);

        IncomingMessageFormParameterDefinition paramDef6 = coreManager.createIncomingMessageFormParameterDefinition();
        paramDef6.setDateCreated(new Date());
        paramDef6.setIncomingMsgFormDefinition(formDef);
        paramDef6.setLength(3);
        paramDef6.setName("Diagnosis");
        paramDef6.setParamType("NUMERIC");
        paramDef6.setRequired(true);
        formDef.getIncomingMsgParamDefinitions().add(paramDef6);

        IncomingMessageFormParameterDefinition paramDef7 = coreManager.createIncomingMessageFormParameterDefinition();
        paramDef7.setDateCreated(new Date());
        paramDef7.setIncomingMsgFormDefinition(formDef);
        paramDef7.setLength(1);
        paramDef7.setName("Referral");
        paramDef7.setParamType("BOOLEAN");
        paramDef7.setRequired(true);
        formDef.getIncomingMsgParamDefinitions().add(paramDef7);

        Transaction tx = (Transaction)imfDAO.getDBSession().getTransaction();
        tx.begin();
        IncomingMessageFormDefinition result = (IncomingMessageFormDefinition) imfDAO.save(formDef);
        tx.commit();

        assertNotNull(result);
        assertEquals(result.getId(), formDef.getId());
        assertEquals(result.getIncomingMsgParamDefinitions().size(),result.getIncomingMsgParamDefinitions().size());
    }
}
