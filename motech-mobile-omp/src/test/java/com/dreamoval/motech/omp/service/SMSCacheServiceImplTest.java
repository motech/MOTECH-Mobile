/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import com.dreamoval.motech.omp.manager.MessageDetailsManager;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;


/**
 *
 * @author Yoofi
 */
public class SMSCacheServiceImplTest extends AbstractDependencyInjectionSpringContextTests {
    MessageDetailsImpl messageDetails;

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/omp-config.xml"};
    }

    public SMSCacheServiceImplTest(String testName) {
        super(testName);
    }

    /**
     * Test of getMessageDetailsDao method, of class SMSCacheImpl.
     */
    public void testGetMessageDetailsManager() {
        System.out.println("getMessageDetailsManager");
        SMSCacheServiceImpl instance = (SMSCacheServiceImpl)applicationContext.getBean("smsCache");
        MessageDetailsManager expResult = (MessageDetailsManager)applicationContext.getBean("messageManager");
        MessageDetailsManager result = instance.getMessageManager();
        //assertEquals(expResult, result);
    }

    /**
     * Test of setMessageDetailsDao method, of class SMSCacheImpl.
     */
    public void testSetMessageDetailsManager() {
        System.out.println("setMessageDetailsManager");
        MessageDetailsManager messageManager = null;
        SMSCacheServiceImpl instance = (SMSCacheServiceImpl)applicationContext.getBean("smsCache");
        instance.setMessageManager(messageManager);
        //assertEquals(instance.getMessageManager(), messageManager);
    }

    /**
     * Test of saveMessage method, of class SMSCacheImpl.
     */
    public void testSaveMessage() {
        System.out.println("saveMessage");

        //boolean expResult = true;
        SMSCacheServiceImpl instance = (SMSCacheServiceImpl)applicationContext.getBean("smsCache");
        instance.setMessageManager((MessageDetailsManager)applicationContext.getBean("messageManager"));
        instance.saveMessage((MessageDetails)applicationContext.getBean("messageDetails"));
        //assertEquals(expResult, result);
    }

    /**
     * Test of updateMessage method, of class SMSCacheImpl.
     */
//    public void testUpdateMessage() {
//        System.out.println("updateMessage");
//        MessageDetailsImpl messageDetails = null;
//        SMSCacheImpl instance = new SMSCacheImpl();
//        boolean expResult = false;
//        boolean result = instance.updateMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
