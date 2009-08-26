/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.service;

import java.util.Date;
import java.util.List;
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
@ContextConfiguration(locations = {"classpath:META-INF/omi-config.xml"})
public class OMIServiceImplTest {

    @Autowired
    OMIServiceImpl omiService;

    public OMIServiceImplTest() {
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
        OMIServiceImpl instance = omiService;
        Long result = instance.sendPatientMessage(messageId, clinic, serviceDate, patientNumber, patientNumberType, messageType);
        assertNotNull(result);
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
        OMIServiceImpl instance = omiService;
        Long result = instance.sendCHPSMessage(messageId, workerName, workerNumber, patientList);
        assertNotNull(result);
    }

}