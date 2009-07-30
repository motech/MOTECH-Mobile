/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.omp.manager.GatewayManager;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 *
 * @author Yoofi
 */
public class SMSServiceImplTest extends AbstractDependencyInjectionSpringContextTests {

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/omp-config.xml"};
    }

    public SMSServiceImplTest(String testName) {
        super(testName);
    }

    /**
     * Test of sendTextMessage method, of class SMSServiceImpl.
     */
    public void testSendTextMessage_MessageDetails() {
        System.out.println("sendTextMessage");
        MessageDetails messageDetails = (MessageDetails)applicationContext.getBean("messageDetails");
        SMSServiceImpl instance = (SMSServiceImpl)applicationContext.getBean("smsService");
        String expResult = "sent";
        Long result = instance.sendTextMessage(messageDetails);
        assertNotNull(result);
    }

    /**
     * Test of sendTextMessage method, of class SMSServiceImpl.
     */
//    public void testSendTextMessage_String() {
//        System.out.println("sendTextMessage");
//        String messageDetails = "";
//        SMSServiceImpl instance = new SMSServiceImpl();
//        String expResult = "";
//        String result = instance.sendTextMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getCache method, of class SMSServiceImpl.
     */
    public void testGetCache() {
        System.out.println("getCache");
        SMSServiceImpl instance = (SMSServiceImpl)applicationContext.getBean("smsService");
        SMSCacheService expResult = (SMSCacheService)applicationContext.getBean("smsCache");
        SMSCacheService result = instance.getCache();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCache method, of class SMSServiceImpl.
     */
    public void testSetCache() {
        System.out.println("setCache");
        SMSCacheService cache = null;
        SMSServiceImpl instance = (SMSServiceImpl)applicationContext.getBean("smsService");
        instance.setCache(cache);
        assertEquals(cache, instance.getCache());
    }

    /**
     * Test of getGatewayManager method, of class SMSServiceImpl.
     */
    public void testGetGatewayManager() {
        System.out.println("getGatewayManager");
        SMSServiceImpl instance = (SMSServiceImpl)applicationContext.getBean("smsService");
        GatewayManager expResult = (GatewayManager)applicationContext.getBean("orserveManager");
        GatewayManager result = instance.getGatewayManager();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGatewayManager method, of class SMSServiceImpl.
     */
    public void testSetGatewayManager() {
        System.out.println("setGatewayManager");
        GatewayManager gatewayManager = null;
        SMSServiceImpl instance = (SMSServiceImpl)applicationContext.getBean("smsService");
        instance.setGatewayManager(gatewayManager);
        assertEquals(gatewayManager, instance.getGatewayManager());
    }

}
