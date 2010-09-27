/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.omi.manager;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.motechproject.ws.Care;
import org.motechproject.ws.Gender;
import org.motechproject.ws.Patient;
import static org.easymock.EasyMock.*;

/**
 *
 * @author Yoofi
 */
public class SMSMessageFormatterImplTest {
    MessageStoreManagerImpl storeManager;
    OMIManager mockOMI;
    SMSMessageFormatterImpl instance;
    Care c, c1, c2;
    Patient p, p1, p2, p3;
    
    public SMSMessageFormatterImplTest() {
    }

    @Before
    public void setUp() {
        storeManager = new MessageStoreManagerImpl();
        mockOMI = createMock(OMIManager.class);

        instance = new SMSMessageFormatterImpl();
        instance.setOmiManager(mockOMI);
        instance.setDateFormat("dd/MM/yyyy");
        
        c = new Care();
        c.setDate(new Date());
        c.setName("Care");

        c1 = new Care();
        c1.setDate(new Date());
        c1.setName("Care1");

        c2 = new Care();
        c2.setDate(new Date());
        c2.setName("Care2");

        p = new Patient();
        p.setPreferredName("Test");
        p.setLastName("Patient");
        p.setCommunity("Community");
        p.setMotechId("1234567");
        p.setSex(Gender.FEMALE);
        p.setEstimateDueDate(new Date());
        p.setDeliveryDate(new Date());

        p1 = new Patient();
        p1.setLastName("Patient");
        p1.setCommunity("Community");
        p1.setMotechId("1234561");
        p1.setSex(Gender.MALE);
        p1.setBirthDate(new Date());

        p2 = new Patient();
        p2.setPreferredName("Little");
        p2.setLastName("Patient");
        p2.setCommunity("Community");
        p2.setMotechId("1234562");
        p2.setSex(Gender.FEMALE);
        p2.setBirthDate(new Date());

        p3 = new Patient();
        p3.setPreferredName("Man");
        p3.setLastName("Patient");
        p3.setCommunity("Community");
        p3.setMotechId("1234563");
        p3.setSex(Gender.MALE);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of formatDefaulterMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatDefaulterMessage_Care() {
        System.out.println("formatDefaulterMessage");

        String expResult = "No Care defaulters found for this clinic";
        String result = instance.formatDefaulterMessage(c);
        assertEquals(expResult, result);

        c.setPatients(new Patient[]{p, p1});
        expResult = "Care Defaulters:\nTest Patient-1234567 (Community)\nBaby Boy Patient-1234561 (Community)";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager);

        replay(mockOMI);
        result = instance.formatDefaulterMessage(c);
        verify(mockOMI);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of formatDefaulterMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatDefaulterMessage_CareArr() {
        System.out.println("formatDefaulterMessage");

        c.setPatients(new Patient[]{p, p1});
        c1.setPatients(new Patient[]{p2});

        Care[] cares = new Care[]{c, c1};

        String expResult = "Care Defaulters:\nTest Patient-1234567 (Community)\nBaby Boy Patient-1234561 (Community)\n\nCare1 Defaulters:\nLittle Patient-1234562 (Community)";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager).times(cares.length);

        replay(mockOMI);
        String result = instance.formatDefaulterMessage(cares);
        verify(mockOMI);

        assertEquals(expResult, result);
    }

    /**
     * Test of formatDeliveriesMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatDeliveriesMessage() {
        System.out.println("formatDeliveriesMessage");
        String type = "Upcoming";
        Patient[] patients = new Patient[]{p};

        String expResult = "Upcoming Deliveries:\n<27/09/2010>: Test Patient - 1234567 (Community)";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager);

        replay(mockOMI);
        String result = instance.formatDeliveriesMessage(type, patients);
        verify(mockOMI);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatUpcomingCaresMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatUpcomingCaresMessage() {
        System.out.println("formatUpcomingCaresMessage");

        p3.setCares(new Care[]{c1, c2});
        
        String expResult = "Upcoming care for  Patient:\nCare1:27/09/2010\nCare2:27/09/2010";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager);

        replay(mockOMI);
        String result = instance.formatUpcomingCaresMessage(p3);
        verify(mockOMI);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of formatBulkCaresMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatBulkCaresMessage() {
        System.out.println("formatBulkCaresMessage");

        c.setPatients(new Patient[]{p1, p3});
        c1.setPatients(new Patient[]{p, p2});

        Care[] cares = new Care[]{c, c1};

        String expResult = "Upcoming care:\nCare - 27/09/2010:\nBaby Boy Patient-1234561 (Community)\nMan Patient-1234563 (Community)\n\nCare1 - 27/09/2010:\nTest Patient-1234567 (Community)\nLittle Patient-1234562 (Community)";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager);

        replay(mockOMI);
        String result = instance.formatBulkCaresMessage(cares);
        verify(mockOMI);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of formatMatchingPatientsMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatMatchingPatientsMessage() {
        System.out.println("formatMatchingPatientsMessage");
        Patient[] patients = new Patient[]{p,p3};

        String expResult = "Matching Patients:\nTest Patient <DoB0> FEMALE Community MoTeCH ID:1234567\nMan Patient <DoB1> MALE Community MoTeCH ID:1234563";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager);

        replay(mockOMI);
        String result = instance.formatMatchingPatientsMessage(patients);
        verify(mockOMI);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of formatPatientDetailsMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatPatientDetailsMessage() {
        System.out.println("formatPatientDetailsMessage");

        String expResult = "MoTeCH ID 1234567\nFirstName=<FirstName>\nLastName=Patient\nPreferredName=Test\nSex=FEMALE\nCommunity=Community\nPhoneNumber=None\nEDD=27/09/2010";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager);

        replay(mockOMI);
        String result = instance.formatPatientDetailsMessage(p);
        verify(mockOMI);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of formatBabyRegistrationMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatBabyRegistrationMessage() {
        System.out.println("formatBabyRegistrationMessage");
        Patient[] patients = new Patient[]{p1,p2};

        String expResult = "Your request for a new MoTeCH ID was successful\nName: Baby Boy Patient\nMoTeCH ID: 1234561\nName: Little Patient\nMoTeCH ID: 1234562";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager);

        replay(mockOMI);
        String result = instance.formatBabyRegistrationMessage(patients);
        verify(mockOMI);
        
        assertEquals(expResult, result);

    }

    /**
     * Test of formatPatientRegistrationMessage method, of class SMSMessageFormatterImpl.
     */
    @Test
    public void testFormatPatientRegistrationMessage() {
        System.out.println("formatPatientRegistrationMessage");

        String expResult = "Your request for a new MoTeCH ID was successful\nName: Man Patient\nMoTeCH ID: 1234563";

        expect(
                mockOMI.createMessageStoreManager()
                ).andReturn(storeManager);

        replay(mockOMI);
        String result = instance.formatPatientRegistrationMessage(p3);
        verify(mockOMI);
        
        assertEquals(expResult, result);

    }
}