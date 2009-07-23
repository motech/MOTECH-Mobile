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
public class ORServeSMSGatewayImplTest extends AbstractDependencyInjectionSpringContextTests {
    
    public ORServeSMSGatewayImplTest(String testName) {
        super(testName);
    }

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/motech-omp.xml"};
    }

    /**
     * Test of send method, of class ORServeSMSGatewayManagerImpl.
     */
    public void testSend() {
        System.out.println("send");

        ORServeSMSGatewayManagerImpl instance = (ORServeSMSGatewayManagerImpl)applicationContext.getBean("smsGateway");//new ORServeSMSGatewayManagerImpl();
        ResponseDetails expResult = null;
        ResponseDetails result = instance.send((MessageDetails)applicationContext.getBean("messageDetails"));
        assertEquals(expResult, result);
    }

}
