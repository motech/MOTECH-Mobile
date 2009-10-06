package com.dreamoval.motech.omi.ws.client;

import static org.easymock.EasyMock.*;

import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests to ensure that the web service client can successfully call the messaging web service methods
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Sep 19, 2009
 */
public class MessageClientITCase {

    static MessageServiceImpl msgWs;

    public MessageClientITCase() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        msgWs = new MessageServiceImplService().getMessageServiceImplPort();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        msgWs = null;
    }

    /**
     * Test of sendPatientMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendPatientMessage() {
        System.out.println("sendPatientMessage");
        long messageId = 0L;
        String clinic = "Test clinic";
        
        XMLGregorianCalendar serviceDate;
        GregorianCalendar inDate = new GregorianCalendar();
        try{
            serviceDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(inDate);
        }
        catch(Exception e){
            serviceDate = null;
        }
        
        String patientName = "Test Patient";
        String patientNumber = "000000000000";
        com.dreamoval.motech.omi.ws.client.ContactNumberType patientNumberType = ContactNumberType.PERSONAL;
        String langCode = "db_GH";
        long notificationType = 4L;
        MessageType messageType = MessageType.TEXT;

        String result = msgWs.sendPatientMessage(messageId, patientName, patientNumber, patientNumberType, langCode, messageType, notificationType, serviceDate, serviceDate);
        assertNotNull(result);
    }

    /**
     * Test of sendCHPSMessage method, of class MessageServiceImpl.
     */
    @Test
    public void testSendCHPSMessage() {
        System.out.println("sendCHPSMessage");
        long messageId = 0L;
        String workerName = "Test worker";
        String workerNumber = "000000000000";
        
        String langCode = "db_GH";
        long notificationType = 4L;
        MessageType messageType = MessageType.TEXT;
        
        XMLGregorianCalendar serviceDate;
        GregorianCalendar inDate = new GregorianCalendar();
	try{
            serviceDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(inDate);
        }
        catch(Exception e){
            serviceDate = null;
        }
        
        com.dreamoval.motech.omi.ws.client.ArrayList patientList = new com.dreamoval.motech.omi.ws.client.ArrayList();

        
        String result = msgWs.sendCHPSMessage(messageId, workerName, workerNumber, patientList, langCode, messageType, notificationType, serviceDate, serviceDate);
        assertEquals(result, "QUEUED");
    }

}