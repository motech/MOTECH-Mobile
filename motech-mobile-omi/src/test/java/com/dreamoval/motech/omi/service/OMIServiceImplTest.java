/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omp.manager.OMPManager;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
@ContextConfiguration(locations = {"file:src/main/resources/omi-config.xml"})
public class OMIServiceImplTest {

    @Autowired
    OMIServiceImpl omiService;

    public OMIServiceImplTest() {
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
     * Test of sendPatientMessage method, of class OMIServiceImpl.
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
        OMIServiceImpl instance = omiService;
        Long result = instance.sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
        assertNotNull(result);
    }

    /**
     * Test of sendCHPSMessage method, of class OMIServiceImpl.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = "000000000000";
        List<PatientImpl> patientList = null;
        OMIServiceImpl instance = omiService;
        Long result = instance.sendCHPSMessage(messageId, workerName, workerNumber, patientList);
        assertNotNull(result);
    }

    /**
     * Test of getStoreManager method, of class OMIServiceImpl.
     */
    @Test
    public void testGetStoreManager() {
        System.out.println("getStoreManager");
        OMIServiceImpl instance = omiService;
        MessageStoreManager result = instance.getStoreManager();
        assertNotNull(result);
    }

    /**
     * Test of setStoreManager method, of class OMIServiceImpl.
     */
    @Test
    public void testSetStoreManager() {
        System.out.println("setStoreManager");
        MessageStoreManager storeManager = null;
        OMIServiceImpl instance = omiService;
        instance.setStoreManager(storeManager);
        assertEquals(storeManager, instance.getStoreManager());
    }

    /**
     * Test of getOmpManager method, of class OMIServiceImpl.
     */
    @Test
    public void testGetOmpManager() {
        System.out.println("getOmpManager");
        OMIServiceImpl instance = omiService;
        OMPManager result = instance.getOmpManager();
        assertNotNull(result);
    }

    /**
     * Test of setOmpManager method, of class OMIServiceImpl.
     */
    @Test
    public void testSetOmpManager() {
        System.out.println("setOmpManager");
        OMPManager ompManager = null;
        OMIServiceImpl instance = omiService;
        instance.setOmpManager(ompManager);
        assertEquals(ompManager, instance.getOmpManager());
    }

    /**
     * Test of geCoreManager method, of class OMIServiceImpl.
     */
    @Test
    public void testGetCoreManager() {
        System.out.println("geCoreManager");
        OMIServiceImpl instance = omiService;
        CoreManager result = instance.getCoreManager();
        assertNotNull(result);
    }

    /**
     * Test of setCoreManager method, of class OMIServiceImpl.
     */
    @Test
    public void testSetCoreManager() {
        System.out.println("setCoreManager");
        CoreManager coreManager = null;
        OMIServiceImpl instance = new OMIServiceImpl();
        instance.setCoreManager(coreManager);
        assertEquals(coreManager, instance.getCoreManager());
    }

}