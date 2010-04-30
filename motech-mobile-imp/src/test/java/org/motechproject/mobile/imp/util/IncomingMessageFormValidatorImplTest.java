/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormDefinition;
import org.motechproject.mobile.core.model.IncomingMessageFormDefinitionImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import org.motechproject.mobile.core.model.IncomingMessageFormParameterDefinition;
import org.motechproject.mobile.core.model.IncomingMessageFormParameterDefinitionImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormParameterImpl;
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
 * Test for IncomingMessageFormValidatorImpl class
 *
 *  Date : Dec 6, 2009
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
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

        MethodSignature mSig = new MethodSignature();
        mSig.setMethodName("stopPregnancyProgram");
        mSig.setMethodParams(new HashMap<String, Class>(2));
        mSig.getMethodParams().put("chpsId", String.class);
        mSig.getMethodParams().put("motechId", String.class);

        Map<String, MethodSignature> signatures = new HashMap<String, MethodSignature>(1);
        signatures.put("PREGNANCYSTOP", mSig);

        //instance.setImParamValidator(mockParamValidator);
        instance = new IncomingMessageFormValidatorImpl();
        instance.setParamValidators(mockValidators);
        instance.setCoreManager(mockCore);
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
        form.getIncomingMsgFormParameters().put(param1.getName().toLowerCase(),param1);
        
        //Test with required param missing
        IncMessageFormStatus expResult = IncMessageFormStatus.INVALID;

        expect(
                mockCore.createIncomingMessageFormParameter()
                ).andReturn(new IncomingMessageFormParameterImpl());
        expect(
                mockValidators.get((String)anyObject())
                ).andReturn(validators);
        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true);

        replay(mockCore, mockParamValidator, mockValidators);
        IncMessageFormStatus result = instance.validate(form, reqPhone);
        verify(mockCore, mockParamValidator, mockValidators);
        
        assertEquals(expResult, result);
        assertEquals(param1.getIncomingMsgFormParamDefinition(), pDef1);
        assertTrue(form.getIncomingMsgFormParameters().size() == 2);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.INVALID);

        //Test with valid form on mobile
        form.setMessageFormStatus(IncMessageFormStatus.NEW);
        form.getIncomingMsgFormParameters().clear();
        form.getIncomingMsgFormParameters().put(param1.getName().toLowerCase(),param1);
        form.getIncomingMsgFormParameters().put(param2.getName().toLowerCase(),param2);

        expResult = IncMessageFormStatus.VALID;

        reset(mockCore, mockParamValidator, mockValidators);

        expect(
                mockValidators.get((String)anyObject())
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
    }
}