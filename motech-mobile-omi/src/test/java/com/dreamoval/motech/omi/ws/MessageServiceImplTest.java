package com.dreamoval.motech.omi.ws;

import static org.easymock.EasyMock.*;

import java.util.Date;

import com.dreamoval.motech.omi.manager.OMIManager;
import com.dreamoval.motech.omi.service.OMIService;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.MessageStatus;
import org.motechproject.ws.Patient;
import static org.junit.Assert.*;

/**
 * Unit test for the MessagingServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:META-INF/omi-config.xml"})
public class MessageServiceImplTest{

    OMIManager mockOMI;
    OMIService mockOMIService;

    MessageServiceImpl instance;

    public MessageServiceImplTest() {
    }

    @Before
    public void setUp(){
        instance = new MessageServiceImpl();
        mockOMI = createMock(OMIManager.class);
        mockOMIService = createMock(OMIService.class);
        instance.setOmiManager(mockOMI);

        expect(
               mockOMI.createOMIService()
               ).andReturn(mockOMIService);
    }

    /**
     * Test of sendPatientMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendPatientMessage() {
        System.out.println("sendPatientMessage");
        Long messageId = 0L;
        String clinic = "Test clinic";
        Date serviceDate = null;
        String patientNumber = "000000000000";
        ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        MediaType messageType = MediaType.TEXT;

        expect(
                mockOMIService.savePatientMessageRequest((Long) anyObject(), (String) anyObject(), (String) anyObject(), (ContactNumberType) anyObject(), (String) anyObject(), (MediaType) anyObject(), (Long) anyObject(), (Date) anyObject(), (Date) anyObject())
                ).andReturn(MessageStatus.QUEUED);
        replay(mockOMI, mockOMIService);
        
        MessageStatus result = instance.sendPatientMessage(messageId, patientNumber, patientNumber, patientNumberType, "db_GH", messageType, 13L, null, null);
        assertEquals(MessageStatus.QUEUED, result);
        verify(mockOMI, mockOMIService);
    }

    /**
     * Test of sendCHPSMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = "000000000000";
        MediaType messageType = MediaType.TEXT;
        Patient[] patientList = null;

        expect(
                mockOMIService.saveCHPSMessageRequest((Long) anyObject(), (String) anyObject(), (String) anyObject(), (Patient[]) anyObject(), (String) anyObject(), (MediaType) anyObject(), (Long) anyObject(), (Date) anyObject(), (Date) anyObject())
                ).andReturn(MessageStatus.QUEUED);    
        replay(mockOMI, mockOMIService);
        
        MessageStatus result = instance.sendCHPSMessage(messageId, workerName, workerNumber, patientList, "Lang", messageType, 13L, null, null);
        assertEquals(result, MessageStatus.QUEUED);
        verify(mockOMI, mockOMIService);
    }

}
