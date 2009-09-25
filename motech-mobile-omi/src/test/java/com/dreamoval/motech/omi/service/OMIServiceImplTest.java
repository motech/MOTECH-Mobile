package com.dreamoval.motech.omi.service;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import com.dreamoval.motech.omi.manager.MessageStoreManager;
import com.dreamoval.motech.omp.manager.OMPManager;
import com.dreamoval.motech.omp.service.MessagingService;
import static org.easymock.EasyMock.*;

import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the OMIServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class OMIServiceImplTest {

    OMIService instance;
    CoreManager mockCore;
    OMPManager mockOMP;
    MessagingService mockMessagingService;
    MessageStoreManager mockStore;

    public OMIServiceImplTest() {
    }

    @Before
    public void setUp(){
        mockCore = createMock(CoreManager.class);
        mockOMP = createMock(OMPManager.class);
        mockMessagingService = createMock(MessagingService.class);
        mockStore = createMock(MessageStoreManager.class);
        
        instance = new OMIServiceImpl();
        instance.setCoreManager(mockCore);
        instance.setOmpManager(mockOMP);
        instance.setStoreManager(mockStore);
    }

    /**
     * Test of sendPatientMessage method, of class OMIServiceImpl.
     */
    @Test
    public void testSendPatientMessage() {
        System.out.println("sendPatientMessage");
        Long messageId = 0L;
        String clinic = "Test clinic";
        Date serviceDate = null;
        String patientNumber = "000000000000";
        ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        MessageType messageType = MessageType.TEXT;
        Long expResult = 1L;

        expect(
                mockCore.createMessageDetails((MotechContext)anyObject())
                ).andReturn(new GatewayRequestImpl());
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockStore.getMessage((String) anyObject())
                ).andReturn("Test Message");
        expect(
                mockOMP.createMessagingService()
                ).andReturn(mockMessagingService);
        expect(
                mockMessagingService.sendTextMessage((GatewayRequest) anyObject())
                ).andReturn(1L);
        replay(mockCore, mockStore, mockOMP, mockMessagingService);

        Long result = instance.sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
        assertEquals(expResult, result);
        verify(mockCore, mockStore, mockOMP, mockMessagingService);
    }

    /**
     * Test of sendCHPSMessage method, of class OMIServiceImpl.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = "000000000000";
        List<PatientImpl> patientList = null;
        Long expResult = 1L;

        expect(
                mockCore.createMessageDetails((MotechContext)anyObject())
                ).andReturn(new GatewayRequestImpl());
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockStore.getMessage((String) anyObject())
                ).andReturn("Test Message");
        expect(
                mockOMP.createMessagingService()
                ).andReturn(mockMessagingService);
        expect(
                mockMessagingService.sendTextMessage((GatewayRequest) anyObject())
                ).andReturn(1L);
        replay(mockCore, mockStore, mockOMP, mockMessagingService);

        Long result = instance.sendCHPSMessage(messageId, workerName, workerNumber, patientList);
        assertEquals(expResult, result);
        verify(mockCore, mockStore, mockOMP, mockMessagingService);
    }

}