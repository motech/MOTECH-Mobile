/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.handler.orserve;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Domain.ResponseDetails;
import junit.framework.TestCase;

/**
 *
 * @author Yoofi
 */
public class ORServeSMSHandlerTest extends TestCase {
    
    public ORServeSMSHandlerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of prepareMessage method, of class ORServeSMSHandler.
     */
    public void testPrepareMessage() {
        System.out.println("prepareMessage");
        String message = "A sample message";
        ORServeSMSHandler instance = new ORServeSMSHandler();
        MessageDetails expResult = null;
        MessageDetails result = instance.prepareMessage(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseResponse method, of class ORServeSMSHandler.
     */
    public void testParseResponse() {
        System.out.println("parseResponse");
        String gatewayResponse = "A sample gateway response";
        ORServeSMSHandler instance = new ORServeSMSHandler();
        ResponseDetails expResult = null;
        ResponseDetails result = instance.parseResponse(gatewayResponse);
        assertEquals(expResult, result);
    }

}
