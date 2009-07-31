/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.ResponseDetails;
import com.dreamoval.motech.omp.manager.GatewayMessageHandler;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 *
 * @author Yoofi
 */
public class ORServeGatewayMessageHandlerImplTest extends AbstractDependencyInjectionSpringContextTests {
    
    public ORServeGatewayMessageHandlerImplTest(String testName) {
        super(testName);
    }

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/omp-config.xml"};
    }

    /**
     * Test of prepareMessage method, of class ORServeGatewayMessageHandler.
     */
    public void testPrepareMessage() {
        System.out.println("prepareMessage");
        String message = "A sample message";
        GatewayMessageHandler instance = (GatewayMessageHandler)applicationContext.getBean("messageHandler");
        MessageDetails expResult = null;
        MessageDetails result = instance.prepareMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseResponse method, of class ORServeGatewayMessageHandler.
     */
    public void testParseResponse() {
        System.out.println("parseResponse");
        String gatewayResponse = "A sample gateway response";
        GatewayMessageHandler instance = (GatewayMessageHandler)applicationContext.getBean("messageHandler");
        ResponseDetails expResult = null;
        ResponseDetails result = instance.parseResponse(gatewayResponse);
        assertEquals(expResult, result);
    }

}
