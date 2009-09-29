package com.dreamoval.motech.omi.ws;

import static org.easymock.EasyMock.*;

import java.util.Date;
import java.util.List;

import com.dreamoval.motech.omi.manager.OMIManager;
import com.dreamoval.motech.omi.service.ContactNumberType;
import com.dreamoval.motech.omi.service.OMIService;
import com.dreamoval.motech.omi.service.PatientImpl;

import org.junit.Before;
import org.junit.Test;
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

    MessageService instance;

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
        String messageType = "TEXT";

        expect(
                mockOMIService.savePatientMessageRequest((Long) anyObject(), (String) anyObject(), (String) anyObject(), (ContactNumberType) anyObject(), (String) anyObject(), (String) anyObject(), (String) anyObject(), (Date) anyObject(), (Date) anyObject())
                ).andReturn("QUEUED");
        replay(mockOMI, mockOMIService);
        
        String result = instance.sendPatientMessage(messageId, patientNumber, patientNumber, patientNumberType, "db_GH", messageType, patientNumber, null, null);
        assertEquals("QUEUED", result);
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
        List<PatientImpl> patientList = null;

        expect(
                mockOMIService.saveCHPSMessageRequest((Long) anyObject(), (String) anyObject(), (String) anyObject(), (List<PatientImpl>) anyObject(), (Date) anyObject(), (Date) anyObject())
                ).andReturn("QUEUED");    
        replay(mockOMI, mockOMIService);
        
        String result = instance.sendCHPSMessage(messageId, workerName, workerNumber, patientList, null, null);
        assertEquals(result, "QUEUED");
        verify(mockOMI, mockOMIService);
    }

}
