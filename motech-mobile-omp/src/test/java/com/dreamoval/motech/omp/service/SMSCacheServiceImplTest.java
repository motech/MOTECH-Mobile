package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

/**
 * Unit test for the SMSCacheServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class SMSCacheServiceImplTest {

    SMSCacheServiceImpl instance;

    CoreManager mockCore;
    GatewayRequestDAO mockMessageDAO;

    public SMSCacheServiceImplTest() {
    }

    @Before
    public void setUp(){
        mockCore = createMock(CoreManager.class);
        mockMessageDAO = createMock(GatewayRequestDAO.class);
        
        instance = new SMSCacheServiceImpl();
        instance.setCoreManager(mockCore);
    }

    /**
     * Test of saveMessage method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testSaveMessage() {
        System.out.println("saveMessage");

        GatewayRequest messageDetails = new GatewayRequestImpl();
//       TODO to yoofi conflick here
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setRequestId(2L);
        
        expect(
                mockCore.createGatewayRequestDAO((MotechContext) anyObject())
                ).andReturn(mockMessageDAO);
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockMessageDAO.save((GatewayRequest) anyObject())
                ).andReturn(messageDetails);
        replay(mockCore, mockMessageDAO);

        instance.saveMessage(messageDetails);
        verify(mockCore, mockMessageDAO);
    }

}