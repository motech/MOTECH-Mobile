/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.model.MessageDetails;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;


/**
 *
 * @author Yoofi
 */
public class SMSCacheServiceImplTest extends AbstractDependencyInjectionSpringContextTests {
    MessageDetails messageDetails;

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/motech-omp.xml"};
    }

    public SMSCacheServiceImplTest(String testName) {
        super(testName);
    }
    

    /**
     * Test of getMessageDetailsDao method, of class SMSCacheImpl.
     */
    public void testGetMessageDetailsDao() {
        System.out.println("getMessageDetailsDao");
        SMSCacheServiceImpl instance = new SMSCacheServiceImpl();
        MessageDetailsDAO expResult = null;
        MessageDetailsDAO result = instance.getMessageDetailsDao();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessageDetailsDao method, of class SMSCacheImpl.
     */
    public void testSetMessageDetailsDao() {
        System.out.println("setMessageDetailsDao");
        MessageDetailsDAO messageDetailsDao = null;
        SMSCacheServiceImpl instance = new SMSCacheServiceImpl();
        instance.setMessageDetailsDao(messageDetailsDao);
        assertEquals(instance.getMessageDetailsDao(), null);
    }


    /**
     * Test of saveMessage method, of class SMSCacheImpl.
     */
    public void testSaveMessage() {
        System.out.println("saveMessage");
        SMSCacheServiceImpl instance = new SMSCacheServiceImpl();
        //instance.setMessageDetailsDao(new MessageDetailsDAOImpl());
        instance.setMessageDetailsDao((MessageDetailsDAO)applicationContext.getBean("messageDetailsDao"));
        boolean expResult = true;
        boolean result = instance.saveMessage((MessageDetails)applicationContext.getBean("messageDetails"));
        assertEquals(expResult, result);        
    }

    /**
     * Test of updateMessage method, of class SMSCacheImpl.
     */
//    public void testUpdateMessage() {
//        System.out.println("updateMessage");
//        MessageDetails messageDetails = null;
//        SMSCacheImpl instance = new SMSCacheImpl();
//        boolean expResult = false;
//        boolean result = instance.updateMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
