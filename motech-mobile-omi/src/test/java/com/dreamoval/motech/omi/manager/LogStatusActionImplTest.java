package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
import java.util.Date;
import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.ws.LogType;
import org.motechproject.ws.server.RegistrarService;

/**
 * Unit test for the LogStatusActionImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Oct 02, 2009
 */
public class LogStatusActionImplTest{
    LogStatusActionImpl instance;
    RegistrarService mockService;
    GatewayRequestDetails mockGatewayRequestDetails;
    
    public LogStatusActionImplTest() {
    }

    @Before
    public void setUp(){
        mockService = createMock(RegistrarService.class);
        mockGatewayRequestDetails = createMock(GatewayRequestDetails.class);
        mockGatewayRequestDetails.setId(2L);
        instance = new LogStatusActionImpl();        
        instance.setRegWs(mockService);
    }
    
    @Test
    public void testDoAction(){
        System.out.println("doAction");
        
        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(mockGatewayRequestDetails);
        
        GatewayResponse response = new GatewayResponseImpl();
        response.setGatewayRequest(messageDetails);
        response.setGatewayMessageId("werfet54y56g645v4e");
        response.setMessageStatus(MStatus.PENDING);
        response.setRecipientNumber("000000000000");
        response.setResponseText("Some gateway response message");
        response.setId(5L);
        
        mockService.log((LogType) anyObject(), (String) anyObject());
        expectLastCall();
        
        replay(mockService);
        instance.doAction(response);
        verify(mockService);
    }
}