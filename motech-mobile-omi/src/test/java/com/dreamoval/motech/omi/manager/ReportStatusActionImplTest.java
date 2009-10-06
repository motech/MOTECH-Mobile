package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestDetailsImpl;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
import java.util.Date;
import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;
import org.motech.ws.client.RegistrarWebService;

/**
 * Unit test for the ReportStatusActionImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Oct 02, 2009
 */
public class ReportStatusActionImplTest{
    ReportStatusActionImpl instance;
    RegistrarWebService mockService;
    GatewayRequestDetails mockGatewayRequestDetails;

    public ReportStatusActionImplTest() {
    }

    @Before
    public void setUp(){
        mockService = createMock(RegistrarWebService.class);
        mockGatewayRequestDetails = createMock(GatewayRequestDetails.class);
        mockGatewayRequestDetails.setId(2L);
        instance = new ReportStatusActionImpl();        
        instance.setRegWs(mockService);
    }
    
    @Test
    public void testDoAction(){
        System.out.println("doAction");

        GatewayRequestDetails grd = new GatewayRequestDetailsImpl();
        grd.setId(3L);
        
        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(grd);
        
        GatewayResponse response = new GatewayResponseImpl();
        response.setGatewayRequest(messageDetails);
        response.setGatewayMessageId("werfet54y56g645v4e");
        response.setMessageStatus(MStatus.DELIVERED);
        response.setRecipientNumber("000000000000");
        response.setResponseText("Some gateway response message");
        response.setId(5L);
        
        //mockService.setMessageStatus((String) anyObject(), anyBoolean());
        //expectLastCall();
        
        //replay(mockService);
        instance.doAction(response);
        //verify(mockService);
    }
}