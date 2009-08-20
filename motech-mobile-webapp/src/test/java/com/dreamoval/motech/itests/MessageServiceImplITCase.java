/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.itests;

import com.dreamoval.motech.omi.service.ContactNumberType;
import com.dreamoval.motech.omi.service.MessageType;
import com.dreamoval.motech.omi.service.PatientImpl;
import com.dreamoval.motech.web.webservices.MessageService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/client-config.xml"})
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
            testProps.load(new FileInputStream("src/test/resources/test.properties"));
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
        Long messageId = 0L;
        String clinic = "Test clinic";
        Date serviceDate = new Date();
        String patientNumber = testProps.getProperty("patientNumber", "000000000000");
        ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        MessageType messageType = MessageType.TEXT;
        Long result = client.sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
        assertNotNull(result);
    }

    /**
     * Test of sendCHPSMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        Long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = testProps.getProperty("workerNumber", "000000000000");
        List<PatientImpl> patientList = new ArrayList<PatientImpl>();
        PatientImpl patient = new PatientImpl();
        patient.setName("Test patient");
        patient.setSerialNumber("TS000000001");
        Long result = client.sendCHPSMessage(messageId, workerName, workerNumber, patientList);
        assertNotNull(result);
    }

}