/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.webapp.webservices;

import com.dreamoval.motech.omi.manager.OMIManager;
import com.dreamoval.motech.omi.service.ContactNumberType;
import com.dreamoval.motech.omi.service.MessageType;
import com.dreamoval.motech.omi.service.PatientImpl;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/webapp-config.xml"})
public class MessageServiceImplTest{

    @Autowired
    MessageServiceImpl messageWebServiceBean;

    public MessageServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sendPatientMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendPatientMessage() {
        System.out.println("sendPatientMessage");
        Long messageId = 0L;
        String clinic = "Test clinic";
        Date serviceDate = null;
        String patientNumber = "000000000000";
        ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        MessageType messageType = MessageType.TEXT;
        MessageServiceImpl instance = messageWebServiceBean;
        Long result = instance.sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
        assertNotNull(result);
    }

    /**
     * Test of sendCHPSMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = "000000000000";
        List<PatientImpl> patientList = null;
        MessageServiceImpl instance = messageWebServiceBean;
        Long result = instance.sendCHPSMessage(messageId, workerName, workerNumber, patientList);
        assertNotNull(result);
    }

    /**
     * Test of getOmiManager method, of class MessageServiceImpl.
     */
    @Test
    public void testGetOmiManager() {
        System.out.println("getOmiManager");
        MessageServiceImpl instance = messageWebServiceBean;
        OMIManager result = instance.getOmiManager();
        assertNotNull(result);
    }

    /**
     * Test of setOmiManager method, of class MessageServiceImpl.
     */
    @Test
    public void testSetOmiManager() {
        System.out.println("setOmiManager");
        OMIManager omiManager = null;
        MessageServiceImpl instance = messageWebServiceBean;
        instance.setOmiManager(omiManager);
        assertEquals(omiManager, instance.getOmiManager());
    }

}
