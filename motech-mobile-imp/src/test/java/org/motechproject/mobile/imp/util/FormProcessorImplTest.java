/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2010-11 The Trustees of Columbia University in the City of
 * New York and Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, Columbia University, or
 * their respective contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA, COLUMBIA UNIVERSITY
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION
 * USA, COLUMBIA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageFormDefinitionImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import org.motechproject.mobile.omi.manager.OMIManager;
import org.motechproject.ws.*;
import org.motechproject.ws.server.RegistrarService;
import org.motechproject.ws.server.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author user
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/imp-test-config.xml"})
public class FormProcessorImplTest {

    @Autowired
    FormProcessorImpl instance;

    private OMIManager mockOMI;
    private CoreManager mockCore;
    private RegistrarService mockWebService;
    private IncomingMessageFormImpl form;

    public FormProcessorImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        mockOMI = createMock(OMIManager.class);
        mockCore = createMock(CoreManager.class);
        mockWebService = createMock(RegistrarService.class);
        instance.setRegWS(mockWebService);
        form = new IncomingMessageFormImpl();
        form.setIncomingMsgFormParameters(new HashMap<String, IncomingMessageFormParameter>());
        form.setIncomingMsgFormDefinition(new IncomingMessageFormDefinitionImpl());
    }

    @Test
    public void shouldFindMotechId() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("FINDMOTECHID-JF");

        expect(
                mockWebService.queryMotechId((Integer) anyObject(), (Integer) anyObject(), (String) anyObject(), (String) anyObject(), (String) anyObject(), (Date) anyObject(), (String) anyObject(), (String) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldViewPatientDetails() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("VIEWPATIENT-JF");

        expect(
                mockWebService.queryPatient((Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldUpcomingCare() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("UPCOMINGCARE-JF");

        expect(
                mockWebService.queryUpcomingCare((Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldOverDueDeliveries() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("OVERDUEDELIVERIES-JF");

        expect(
                mockWebService.queryOverdueDeliveries((Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessRecentDeliveries() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("RECENTDELIVERIES-JF");

        expect(
                mockWebService.queryRecentDeliveries((Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessUpcomingDeliveries() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("UPCOMINGDELIVERIES-JF");

        expect(
                mockWebService.queryUpcomingDeliveries((Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessCWCDefaulters() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("CWCDEFAULT-JF");

        expect(
                mockWebService.queryCWCDefaulters((Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessChildPNCDefaulters() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("PNCDEFAULT-JF");

        expect(
                mockWebService.queryChildPNCDefaulters((Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessMotherPNCDefaulters() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("PPCDEFAULT-JF");

        expect(
                mockWebService.queryMotherPNCDefaulters((Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessTTDefaulters() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("TTDEFAULT-JF");

        expect(
                mockWebService.queryTTDefaulters((Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessANCDefaulters() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("ANCDEFAULT-JF");

        expect(
                mockWebService.queryANCDefaulters((Integer) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessMotherOPD() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("MOTHEROPD-JF");

        mockWebService.recordMotherVisit((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (String) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (String) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessChildOPD() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("CHILDOPD-JF");

        mockWebService.recordChildVisit((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (String) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (String) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessReisterCWCChild() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("REGISTERCWCCHILD-JF");

        mockWebService.registerCWCChild((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (String) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (ContactNumberType) anyObject(), (String) anyObject(), (MediaType) anyObject(), (String) anyObject(), (DayOfWeek) anyObject(), (Date) anyObject(), (HowLearned) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessRegisterANCMother() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("REGISTERANCMOTHER-JF");

        mockWebService.registerANCMother((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (String) anyObject(), (Date) anyObject(), (Double) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (ContactNumberType) anyObject(), (String) anyObject(), (MediaType) anyObject(), (String) anyObject(), (DayOfWeek) anyObject(), (Date) anyObject(), (HowLearned) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessRegisterPregnancy() throws ValidationException {

        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("REGISTERPREGNANCY-JF");

        mockWebService.registerPregnancy((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (ContactNumberType) anyObject(), (String) anyObject(), (MediaType) anyObject(), (String) anyObject(), (DayOfWeek) anyObject(), (Date) anyObject(), (HowLearned) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessRegisterPatient() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("REGISTERPATIENT-JF");

        expect(
                mockWebService.registerPatient((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (RegistrationMode) anyObject(), (Integer) anyObject(), (RegistrantType) anyObject(), (String) anyObject(), (String) anyObject(), (String) anyObject(), (String) anyObject(), (Date) anyObject(), (Boolean) anyObject(), (Gender) anyObject(), (Boolean) anyObject(), (String) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (String) anyObject(), (String) anyObject(), (Date) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (ContactNumberType) anyObject(), (MediaType) anyObject(), (String) anyObject(), (DayOfWeek) anyObject(), (Date) anyObject(), (InterestReason) anyObject(), (HowLearned) anyObject(), (Integer) anyObject(), (String) anyObject(), (Boolean) anyObject(), (Date) anyObject(), (String) anyObject(), (String) anyObject(), (Date) anyObject(), (Double) anyObject(), (Integer) anyObject(), (Integer) anyObject(),
                        (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Date) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Date) anyObject(), (Integer) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessChildPNC() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("CHILDPNC-JF");

        mockWebService.recordChildPNCVisit((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (String) anyObject(), (String) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Double) anyObject(), (Double) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (String) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessTT() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("TT-JF");

        mockWebService.recordTTVisit((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Integer) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessDeath() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("DEATH-JF");

        mockWebService.recordDeath((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessMotherPNC() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("MOTHERPNC-JF");

        mockWebService.recordMotherPNCVisit((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (String) anyObject(), (String) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Double) anyObject(), (Double) anyObject(), (String) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessDeliveryNotification() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("DELIVERYNOTIFY-JF");

        mockWebService.recordDeliveryNotification((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessDelivery() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("DELIVERY-JF");

        expect(
                mockWebService.recordPregnancyDelivery((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Integer[]) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (String) anyObject(), (BirthOutcome) anyObject(), (RegistrationMode) anyObject(), (Integer) anyObject(), (Gender) anyObject(), (String) anyObject(), (Double) anyObject(), (BirthOutcome) anyObject(), (RegistrationMode) anyObject(), (Integer) anyObject(), (Gender) anyObject(), (String) anyObject(), (Double) anyObject(), (BirthOutcome) anyObject(), (RegistrationMode) anyObject(), (Integer) anyObject(), (Gender) anyObject(), (String) anyObject(), (Double) anyObject())
        ).andReturn(null);

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessPregnancyTermination() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("ABORTION-JF");

        mockWebService.recordPregnancyTermination((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Integer[]) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (String) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessMotherANCVisit() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("ANC-JF");

        mockWebService.recordMotherANCVisit((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(),(String) anyObject(),(Integer) anyObject(), (Integer) anyObject(), (String) anyObject(), (String) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Double) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Double) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Double) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (HIVResult) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Date) anyObject(), (String) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shouldProcessEditPatient() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("EDITPATIENT-JF");

        mockWebService.editPatient((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (String) anyObject(), (String) anyObject(), (String) anyObject(), (String) anyObject(), (String) anyObject(), (ContactNumberType) anyObject(), (String) anyObject(), (Date) anyObject(), (Date) anyObject(), (Boolean) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void shuouldProcessGeneralVisit() throws ValidationException {
        form.setMessageFormStatus(IncMessageFormStatus.VALID);
        form.getIncomingMsgFormDefinition().setFormCode("GENERALOPD-JF");
        mockWebService.recordGeneralVisit((Integer) anyObject(), (Integer) anyObject(), (Date) anyObject(), (String) anyObject(), (Gender) anyObject(), (Date) anyObject(), (Boolean) anyObject(), (Integer) anyObject(), (Integer) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (Boolean) anyObject(), (String) anyObject());
        expectLastCall();

        replay(mockWebService);
        instance.processForm(form);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
        verify(mockWebService);

        reset(mockWebService);
    }

    @Test
    public void testParseValidationErrors() {
    }
}
