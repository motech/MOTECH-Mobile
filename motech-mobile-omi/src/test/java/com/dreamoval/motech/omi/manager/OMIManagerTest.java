/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.omi.service.MessageService;
import com.dreamoval.motech.omi.wrapper.ContactNumberType;
import com.dreamoval.motech.omi.wrapper.MessageType;
import com.dreamoval.motech.omi.wrapper.Patient;
import java.util.Date;
import java.util.List;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 *
 * @author administrator
 */
public class OMIManagerTest extends AbstractDependencyInjectionSpringContextTests {

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/omi-config.xml"};
    }
    
    public OMIManagerTest(String testName) {
        super(testName);
    }

    /**
     * Test of sendPatientMessage method, of class OMIManager.
     */
    public void testSendPatientMessage() {
        System.out.println("sendPatientMessage");
        Long messageId = 0L;
        String clinic = "Test Clinic";
        Date serviceDate = null;
        String patientNumber = "000000000000";
        ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        MessageType messageType = MessageType.TEXT;
        OMIManager instance = (OMIManager)applicationContext.getBean("omiManager");
        Long expResult = 0L;
        Long result = instance.sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendCHPSMessage method, of class OMIManager.
     */
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = "000000000000";
        List<Patient> patientList = null;
        OMIManager instance = (OMIManager)applicationContext.getBean("omiManager");
        Long expResult = 0L;
        Long result = instance.sendCHPSMessage(messageId, workerName, workerNumber, patientList);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageService method, of class OMIManager.
     */
    public void testGetMessageService() {
        System.out.println("getMessageService");
        OMIManager instance = (OMIManager)applicationContext.getBean("omiManager");
        MessageService expResult = (MessageService)applicationContext.getBean("messageService");
        MessageService result = instance.getMessageService();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessageService method, of class OMIManager.
     */
    public void testSetMessageService() {
        System.out.println("setMessageService");
        MessageService messageService = null;
        OMIManager instance = (OMIManager)applicationContext.getBean("omiManager");
        instance.setMessageService(messageService);
        assertNull(instance.getMessageService());
    }

}
