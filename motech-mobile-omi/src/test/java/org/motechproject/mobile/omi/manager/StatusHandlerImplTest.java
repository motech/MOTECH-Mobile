package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.GatewayResponseImpl;
import org.motechproject.mobile.core.model.MStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the ReportStatusActionImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Oct 02, 2009
 */
public class StatusHandlerImplTest{
    Map<MStatus, List<StatusAction>> mockRegister;
    StatusHandlerImpl instance;
    StatusAction mockAction;
    
    public StatusHandlerImplTest() {
    }

    @Before
    public void setUp(){
        mockRegister = createMock(Map.class);
        mockAction = createMock(StatusAction.class);
        
        instance = new StatusHandlerImpl();        
        instance.setActionRegister(mockRegister);
    }
    
    @Test
    public void testHandleStatus(){
        System.out.println("handleStatus");       
                
        GatewayResponse response = new GatewayResponseImpl();
        response.setGatewayMessageId("DoActionwerfet54y56g645v4e");
        response.setMessageStatus(MStatus.DELIVERED);
        response.setRecipientNumber("000000000000");
        response.setResponseText("Some gateway response message");
        response.setId(18000000001l);
        
        List<StatusAction> actionList = new ArrayList<StatusAction>();
        actionList.add(mockAction);
        
        expect(
                mockRegister.get((MStatus) anyObject())
                ).andReturn(actionList);
        mockAction.doAction(response);
        expectLastCall();
        
        replay(mockRegister, mockAction);
        instance.handleStatus(response);
        verify(mockRegister, mockAction);
    }
    
    @Test
    public void testregisterStatusAction(){
        System.out.println("registerStatusAction");       
                
        List<StatusAction> actionList = new ArrayList<StatusAction>();
        
        expect(
                mockRegister.get((MStatus)anyObject())
                ).andReturn(actionList);       
        
        
        replay(mockRegister);
        instance.registerStatusAction(MStatus.PENDING, mockAction);
        verify(mockRegister);
    }
}