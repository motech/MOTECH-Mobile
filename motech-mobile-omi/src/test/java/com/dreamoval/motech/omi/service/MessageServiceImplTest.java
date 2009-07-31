/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omi.wrapper.ContactNumberType;
import com.dreamoval.motech.omi.wrapper.MessageType;
import com.dreamoval.motech.omi.wrapper.Patient;
import com.dreamoval.motech.omp.manager.OMPManager;
import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 *
 * @author administrator
 */
public class MessageServiceImplTest extends AbstractDependencyInjectionSpringContextTests {

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/omi-config.xml"};
    }
    
    public MessageServiceImplTest(String testName) {
        super(testName);
    }

    /**
     * Test of sendPatientMessage method, of class MessageServiceImpl.
     */
    public void testSendPatientMessage() {
        System.out.println("sendPatientMessage");
        Long messageId = 0L;
        String clinic = "Test Clinic";
        Date serviceDate = null;
        String patientNumber = "000000000000";
        ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        MessageType messageType = MessageType.TEXT;
        MessageService instance = (MessageService)applicationContext.getBean("messageService");
        Long expResult = 0L;
        Long result = instance.sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendCHPSMessage method, of class MessageServiceImpl.
     */
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = "000000000000";
        List<Patient> patientList = null;
        MessageService instance = (MessageService)applicationContext.getBean("messageService");
        Long expResult = 0L;
        Long result = instance.sendCHPSMessage(messageId, workerName, workerNumber, patientList);
        assertEquals(expResult, result);
    }

    /**
     * Test of getStoreManager method, of class MessageServiceImpl.
     */
    public void testGetStoreManager() {
        System.out.println("getStoreManager");
        MessageService instance = (MessageService)applicationContext.getBean("messageService");
        MessageStoreManager expResult = (MessageStoreManager)applicationContext.getBean("storeManager");
        MessageStoreManager result = instance.getStoreManager();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStoreManager method, of class MessageServiceImpl.
     */
    public void testSetStoreManager() {
        System.out.println("setStoreManager");
        MessageStoreManager storeManager = null;
        MessageService instance = (MessageService)applicationContext.getBean("messageService");
        instance.setStoreManager(storeManager);
        assertNull(instance.getStoreManager());
    }

    /**
     * Test of getOmpManager method, of class MessageServiceImpl.
     */
    public void testGetOmpManager() {
        System.out.println("getOmpManager");
        MessageService instance = (MessageService)applicationContext.getBean("messageService");
        OMPManager expResult = (OMPManager)applicationContext.getBean("ompManager");
        OMPManager result = instance.getOmpManager();
        assertEquals(expResult, result);
    }

    /**
     * Test of setOmpManager method, of class MessageServiceImpl.
     */
    public void testSetOmpManager() {
        System.out.println("setOmpManager");
        OMPManager ompManager = null;
        MessageService instance = (MessageService)applicationContext.getBean("messageService");
        instance.setOmpManager(ompManager);
        assertNull(instance.getOmpManager());
    }

}
