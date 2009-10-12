/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.itests;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ws.ContactNumberType;
import org.motechproject.ws.MediaType;
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
        Long messageId = 2L;
        Date serviceDate = new Date();
        String patientNumber = testProps.getProperty("patientNumber", "000000000000");
        ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        MediaType messageType = MediaType.TEXT;
        String result = client.sendPatientMessage(messageId, "Test Patient", patientNumber, patientNumberType, "ts_GH", messageType, 4L, serviceDate, serviceDate);
        assertNotNull(result);
    }

    /**
     * Test of sendCHPSMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 5L;
        String workerName = "Test worker";
        String workerNumber = testProps.getProperty("workerNumber", "000000000000");
        Date serviceDate = new Date();
        MediaType messageType = MediaType.TEXT;
        
        Patient patient = new Patient();
        patient.setName("Test patient");
        patient.setSerialNumber("TS000000001");
        Patient[] patientList = new Patient[]{patient};
        
        String result = client.sendCHPSMessage(messageId, workerName, workerNumber, patientList, "Lang", messageType, 1L, serviceDate, serviceDate);
        assertNotNull(result);
    }
}