/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.OMPManager;
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
@ContextConfiguration(locations = {"file:src/main/resources/omp-config.xml"})
public class SMSCacheServiceImplTest {

    @Autowired
    SMSCacheServiceImpl smsCache;
    @Autowired
    CoreManager coreManager;

    public SMSCacheServiceImplTest() {
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
     * Test of saveMessage method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testSaveMessage_MessageDetails() {
        System.out.println("saveMessage");

        MessageDetails messageDetails = coreManager.createMessageDetails(coreManager.createMotechContext());
        messageDetails.setGlobalStatus("pending");
        messageDetails.setMessageText("a message for testing");
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers("000000000000");
        messageDetails.setMessageType("TEXT");
        
        SMSCacheServiceImpl instance = smsCache;
        instance.setCoreManager(coreManager);

        instance.saveMessage(messageDetails);
    }

    /**
     * Test of saveMessage method, of class SMSCacheServiceImpl.
     */
//    @Test
//    public void testSaveMessage_String() {
//        System.out.println("saveMessage");
//        String messageDetails = "";
//        SMSCacheServiceImpl instance = smsCache;
//        instance.saveMessage(messageDetails);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getCoreManager method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testGetCoreManager() {
        System.out.println("getCoreManager");
        SMSCacheServiceImpl instance = smsCache;
        CoreManager result = instance.getCoreManager();
        assertNotNull(result);
    }

    /**
     * Test of setCoreManager method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testSetCoreManager() {
        System.out.println("setCoreManager");
        CoreManager cm = null;
        SMSCacheServiceImpl instance = smsCache;
        instance.setCoreManager(cm);
        assertEquals(cm, instance.getCoreManager());
    }

    /**
     * Test of getOmpManager method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testGetOmpManager() {
        System.out.println("getOmpManager");
        SMSCacheServiceImpl instance = smsCache;
        OMPManager result = instance.getOmpManager();
        assertNotNull(result);
    }

    /**
     * Test of setOmpManager method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testSetOmpManager() {
        System.out.println("setOmpManager");
        OMPManager ompManager = null;
        SMSCacheServiceImpl instance = smsCache;
        instance.setOmpManager(ompManager);
        assertEquals(ompManager, instance.getOmpManager());
    }

}