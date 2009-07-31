/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.core.model.MessageDetails;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 *
 * @author administrator
 */
public class OMPManagerTest extends AbstractDependencyInjectionSpringContextTests {

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/omp-config.xml"}; 
    }
    
    public OMPManagerTest(String testName) {
        super(testName);
    }

    /**
     * Test of sendTextMessage method, of class OMPManager.
     */
    public void testSendTextMessage_MessageDetails() {
        System.out.println("sendTextMessage");
        MessageDetails messageDetails = (MessageDetails)applicationContext.getBean("messageDetails");
        OMPManager instance = (OMPManager)applicationContext.getBean("ompManager");
        //Long expResult = 0L;
        Long result = instance.sendTextMessage(messageDetails);
        //assertEquals(expResult, result);
    }

    /**
     * Test of sendTextMessage method, of class OMPManager.
     */
//    public void testSendTextMessage_String() {
//        System.out.println("sendTextMessage");
//        String messageDetails = "";
//        OMPManager instance = (OMPManager)applicationContext.getBean("ompManager");
//        Long expResult = null;
//        Long result = instance.sendTextMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of saveMessage method, of class OMPManager.
     */
    public void testSaveMessage_MessageDetails() {
        System.out.println("saveMessage");
        MessageDetails messageDetails = (MessageDetails)applicationContext.getBean("messageDetails");
        messageDetails.setMessageId(1L);
        OMPManager instance = (OMPManager)applicationContext.getBean("ompManager");
        //boolean expResult = true;
        instance.saveMessage(messageDetails);
        //assertEquals(expResult, result);
    }

    /**
     * Test of saveMessage method, of class OMPManager.
     */
//    public void testSaveMessage_String() {
//        System.out.println("saveMessage");
//        String messageDetails = "";
//        OMPManager instance = (OMPManager)applicationContext.getBean("ompManager");
//        boolean expResult = false;
//        boolean result = instance.saveMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of updateMessage method, of class OMPManager.
     */
//    public void testUpdateMessage_MessageDetails() {
//        System.out.println("updateMessage");
//        MessageDetails messageDetails = null;
//        OMPManager instance = new OMPManager();
//        boolean expResult = false;
//        boolean result = instance.updateMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of updateMessage method, of class OMPManager.
     */
//    public void testUpdateMessage_String() {
//        System.out.println("updateMessage");
//        String messageDetails = "";
//        OMPManager instance = new OMPManager();
//        boolean expResult = false;
//        boolean result = instance.updateMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
