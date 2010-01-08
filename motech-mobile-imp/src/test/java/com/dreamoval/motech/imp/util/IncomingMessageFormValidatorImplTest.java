/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncMessageFormStatus;
import com.dreamoval.motech.core.model.IncomingMessageForm;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinition;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinitionImpl;
import com.dreamoval.motech.core.model.IncomingMessageFormImpl;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterDefinition;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterDefinitionImpl;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ws.server.RegistrarService;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;
import org.motechproject.ws.server.ValidationException;

/**
 *
 * @author user
 */
public class IncomingMessageFormValidatorImplTest {
    CoreManager mockCore;
    RegistrarService mockRegSvc;
    IncomingMessageFormParameterValidator mockParamValidator;
    Map<String, List<IncomingMessageFormParameterValidator>> mockValidators;

    IncomingMessageFormValidatorImpl instance;

    public IncomingMessageFormValidatorImplTest() {
    }

    @Before
    public void setUp() {
        mockValidators = createMock(Map.class);
        mockCore = createMock(CoreManager.class);
        mockRegSvc = createMock(RegistrarService.class);
        mockParamValidator = createMock(IncomingMessageFormParameterValidator.class);

        //instance.setImParamValidator(mockParamValidator);
        instance = new IncomingMessageFormValidatorImpl();
        instance.setParamValidators(mockValidators);
        instance.setDateFormat("dd.MM.yyyy");
        instance.setCoreManager(mockCore);
        instance.setRegWS(mockRegSvc);
    }

    /**
     * Test of validate method, of class IncomingMessageFormValidatorImpl.
     */
    @Test
    public void testValidate() throws ValidationException {
        System.out.println("validate");

        String reqPhone = "000000000000";

        List<IncomingMessageFormParameterValidator> validators = new ArrayList<IncomingMessageFormParameterValidator>();
        validators.add(mockParamValidator);

        IncomingMessageFormParameterDefinitionImpl pDef1 = new IncomingMessageFormParameterDefinitionImpl();
        pDef1.setRequired(true);
        pDef1.setParamType("ALPHANUM");
        pDef1.setLength(10);
        pDef1.setName("chpsId");

        IncomingMessageFormParameterDefinitionImpl pDef2 = new IncomingMessageFormParameterDefinitionImpl();
        pDef2.setRequired(true);
        pDef2.setParamType("ALPHANUM");
        pDef2.setLength(20);
        pDef2.setName("patientRegNum");

        IncomingMessageFormDefinition formDef = new IncomingMessageFormDefinitionImpl();
        formDef.setFormCode("NONE");
        formDef.setIncomingMsgParamDefinitions(new HashSet<IncomingMessageFormParameterDefinition>());
        formDef.getIncomingMsgParamDefinitions().add(pDef1);
        formDef.getIncomingMsgParamDefinitions().add(pDef2);

        IncomingMessageFormParameterImpl param1 = new IncomingMessageFormParameterImpl();
        param1.setName("chpsId");
        param1.setValue("testchps");

        IncomingMessageFormParameterImpl param2 = new IncomingMessageFormParameterImpl();
        param2.setName("patientRegNum");
        param2.setValue("testpatient");

        IncomingMessageForm form = new IncomingMessageFormImpl();
        form.setIncomingMsgFormParameters(new HashMap<String,IncomingMessageFormParameter>());
        form.setIncomingMsgFormDefinition(formDef);
        form.getIncomingMsgFormParameters().put(param1.getName(),param1);
        
        //Test with required param missing
        boolean expResult = false;

        expect(
                mockCore.createIncomingMessageFormParameter()
                ).andReturn(new IncomingMessageFormParameterImpl());
        expect(
                mockValidators.get(anyObject())
                ).andReturn(validators);
        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true);

        replay(mockCore, mockParamValidator, mockValidators);
        boolean result = instance.validate(form, reqPhone);
        verify(mockCore, mockParamValidator, mockValidators);
        
        assertEquals(expResult, result);
        assertEquals(param1.getIncomingMsgFormParamDefinition(), pDef1);
        assertTrue(form.getIncomingMsgFormParameters().size() == 2);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.INVALID);

        //Test with valid form on mobile
        form.setMessageFormStatus(IncMessageFormStatus.NEW);
        form.getIncomingMsgFormParameters().clear();
        form.getIncomingMsgFormParameters().put(param1.getName(),param1);
        form.getIncomingMsgFormParameters().put(param2.getName(),param2);

        expResult = false;

        reset(mockCore, mockParamValidator, mockValidators);

        expect(
                mockValidators.get(anyObject())
                ).andReturn(validators).times(2);
        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true).times(2);

        replay(mockCore, mockParamValidator, mockValidators);
        result = instance.validate(form, reqPhone);
        verify(mockCore, mockParamValidator, mockValidators);

        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 2);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.VALID);

        //Test with valid form on mobile and server
        formDef.setFormCode("PregnancyStop");
        form.setMessageFormStatus(IncMessageFormStatus.NEW);
        form.getIncomingMsgFormParameters().clear();
        form.getIncomingMsgFormParameters().put(param1.getName(),param1);
        form.getIncomingMsgFormParameters().put(param2.getName(),param2);

        expResult = true;

        reset(mockCore, mockParamValidator, mockValidators);

        expect(
                mockValidators.get(anyObject())
                ).andReturn(validators).times(2);
        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true).times(2);

        mockRegSvc.stopPregnancyProgram((String)anyObject(), (String)anyObject());
        expectLastCall();

        replay(mockCore, mockParamValidator, mockRegSvc, mockValidators);
        result = instance.validate(form, reqPhone);
        verify(mockCore, mockParamValidator, mockRegSvc, mockValidators);

        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 2);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
    }

}