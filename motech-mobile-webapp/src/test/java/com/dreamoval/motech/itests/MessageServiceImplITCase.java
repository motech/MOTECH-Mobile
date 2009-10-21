/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.itests;

import com.dreamoval.motech.omi.service.OMIService;
import com.dreamoval.motech.omp.service.MessagingService;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
import org.motechproject.ws.MessageStatus;
import org.motechproject.ws.NameValuePair;
import org.motechproject.ws.Patient;
import org.motechproject.ws.mobile.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Integration tests for the MessageServiceImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/client-config.xml"})
public class MessageServiceImplITCase {


    Properties testProps;

    @Autowired
    MessageService client;
    
    @Autowired
    OMIService omiService;
    
    @Autowired
    MessagingService smsService;
    

    public MessageServiceImplITCase() {
    }

    @Before
    public void setUp(){
        testProps = new Properties();

        try{
            testProps.load(getClass().getResourceAsStream("/test.properties"));
        }
        catch(IOException ex){
            System.out.print(ex);
        }
    }

    /**
     * Test of sendPatientMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendPatientMessage() {
        System.out.println("sendPatientMessage");
        String messageId = "tsid64";
        
        NameValuePair attrib = new NameValuePair("PatientFirstName", "Tester");
        NameValuePair attrib2 = new NameValuePair("DueDate", "now");
        NameValuePair[] personalInfo = new NameValuePair[]{attrib, attrib2};
        
        Date serviceDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date endDate = cal.getTime();
        String patientNumber = testProps.getProperty("patientNumber", "000000000000");
        ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        MediaType messageType = MediaType.TEXT;
        MessageStatus result = client.sendPatientMessage(messageId, personalInfo, patientNumber, patientNumberType, "ts_GH", messageType, 12L, null, null);
        assertNotNull(result);
    }

    /**
     * Test of sendCHPSMessage method of MessageServiceImpl class.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        String messageId = "5L";
        String workerNumber = testProps.getProperty("workerNumber", "000000000000");
        Date serviceDate = new Date();
        MediaType messageType = MediaType.TEXT;       
                
        NameValuePair attrib = new NameValuePair("Test", "Test");
        NameValuePair[] personalInfo = new NameValuePair[]{attrib};
        
        Patient patient = new Patient();
        patient.setName("Test patient");
        patient.setSerialNumber("TS000000001");
        Patient[] patientList = new Patient[]{patient};
        
        MessageStatus result = client.sendCHPSMessage(messageId, personalInfo, workerNumber, patientList, "Lang", messageType, 2L, serviceDate, serviceDate);
        assertEquals(result, MessageStatus.QUEUED);
    }
    
    /**
     * Test of processMessageRequests method of OMIService class.
     */
//    @Test
//    public void testProcessMessageRequests(){
//        System.out.println("processMessageRequests");
//        omiService.processMessageRequests();
//    }
//    
//    /**
//     * Test of sendScheduledMessages method of MessagingService class.
//     */
//    @Test
//    public void testSendScheduledMessages(){
//        System.out.println("sendScheduledMessages");
//        smsService.sendScheduledMessages();
//    }
//    
//    /**
//     * Test of updateMessageStatuses method of MessagingService class.
//     */
//    @Test
//    public void testUpdateMessageStatuses(){
//        System.out.println("updateMessageStatuses");
//        smsService.updateMessageStatuses();
//    }
//    
//    /**
//     * Test of getMessageResponses method of OMIService class.
//     */
//    @Test
//    public void testGetMessageResponses(){
//        System.out.println("getMessageResponses");
//        omiService.getMessageResponses();
//    }
//    
//    /**
//     * Test of processMessageRetries method of OMIService class.
//     */
//    @Test
//    public void testProcessMessageRetries(){
//        System.out.println("processMessageRetries");
//        omiService.processMessageRetries();
//    }
}