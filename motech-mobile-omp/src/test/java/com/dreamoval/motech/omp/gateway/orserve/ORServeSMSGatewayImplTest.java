/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.gateway.orserve;

import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import com.dreamoval.motech.core.DataAccess.Domain.ResponseDetails;
import junit.framework.TestCase;

/**
 *
 * @author Yoofi
 */
public class ORServeSMSGatewayImplTest extends TestCase {
    
    public ORServeSMSGatewayImplTest(String testName) {
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
     * Test of send method, of class ORServeSMSGatewayImpl.
     */
    public void testSend() {
        System.out.println("send");
        MessageDetails messageDetails = new MessageDetails();
        messageDetails.setMessageText("A sample message to be sent from MoTeCH Mobile");
        messageDetails.setNumberOfPages(3);

        ORServeSMSGatewayImpl instance = new ORServeSMSGatewayImpl();
        ResponseDetails expResult = null;
        ResponseDetails result = instance.send(messageDetails);
        assertEquals(expResult, result);
    }

}
