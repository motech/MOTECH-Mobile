/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.manager.orserve;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Domain.ResponseDetails;
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
        return new String[]{"file:src/main/resources/motech-omp.xml"};
    }

    /**
     * Test of prepareMessage method, of class ORServeGatewayMessageHandler.
     */
    public void testPrepareMessage() {
        System.out.println("prepareMessage");
        String message = "A sample message";
        ORServeGatewayMessageHandlerImpl instance = new ORServeGatewayMessageHandlerImpl();
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
        ORServeGatewayMessageHandlerImpl instance = new ORServeGatewayMessageHandlerImpl();
        ResponseDetails expResult = null;
        ResponseDetails result = instance.parseResponse(gatewayResponse);
        assertEquals(expResult, result);
    }

}
