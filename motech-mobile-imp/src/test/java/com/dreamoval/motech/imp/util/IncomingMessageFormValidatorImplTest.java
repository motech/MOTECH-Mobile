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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.ws.server.RegistrarService;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 *
 * @author user
 */
public class IncomingMessageFormValidatorImplTest {
    CoreManager mockCore;
    RegistrarService mockRegSvc;
    IncomingMessageFormParameterValidator mockParamValidator;

    IncomingMessageFormValidatorImpl instance;

    public IncomingMessageFormValidatorImplTest() {
    }

    @Before
    public void setUp() {
        mockCore = createMock(CoreManager.class);
        mockRegSvc = createMock(RegistrarService.class);
        mockParamValidator = createMock(IncomingMessageFormParameterValidator.class);

        instance = new IncomingMessageFormValidatorImpl();
        instance.setDateFormat("dd.MM.yyyy");
        instance.setCoreManager(mockCore);
        instance.setImParamValidator(mockParamValidator);
        instance.setRegWS(mockRegSvc);
    }

    /**
     * Test of validate method, of class IncomingMessageFormValidatorImpl.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        String reqPhone = "000000000000";

        IncomingMessageFormParameterDefinitionImpl pDef1 = new IncomingMessageFormParameterDefinitionImpl();
        pDef1.setRequired(true);
        pDef1.setParamType("DATE");
        pDef1.setLength(10);
        pDef1.setName("date");

        IncomingMessageFormParameterDefinitionImpl pDef2 = new IncomingMessageFormParameterDefinitionImpl();
        pDef2.setRequired(true);
        pDef2.setParamType("ALPHANUM");
        pDef2.setLength(20);
        pDef2.setName("serial_id");

        IncomingMessageFormParameterDefinitionImpl pDef3 = new IncomingMessageFormParameterDefinitionImpl();
        pDef3.setRequired(true);
        pDef3.setParamType("DATE");
        pDef3.setLength(10);
        pDef3.setName("due_date");

        IncomingMessageFormParameterDefinitionImpl pDef4 = new IncomingMessageFormParameterDefinitionImpl();
        pDef4.setRequired(true);
        pDef4.setParamType("NUMERIC");
        pDef4.setLength(1);
        pDef4.setName("parity");

        IncomingMessageFormParameterDefinitionImpl pDef5 = new IncomingMessageFormParameterDefinitionImpl();
        pDef5.setRequired(true);
        pDef5.setParamType("NUMERIC");
        pDef5.setLength(10);
        pDef5.setName("haemoglobin");

        IncomingMessageFormDefinition formDef = new IncomingMessageFormDefinitionImpl();
        formDef.setFormCode("NONE");
        formDef.setIncomingMsgParamDefinitions(new HashSet<IncomingMessageFormParameterDefinition>());
        formDef.getIncomingMsgParamDefinitions().add(pDef1);
        formDef.getIncomingMsgParamDefinitions().add(pDef2);
        formDef.getIncomingMsgParamDefinitions().add(pDef3);
        formDef.getIncomingMsgParamDefinitions().add(pDef4);
        formDef.getIncomingMsgParamDefinitions().add(pDef5);

        IncomingMessageFormParameterImpl param1 = new IncomingMessageFormParameterImpl();
        param1.setName("date");
        param1.setValue("13.12.2009");

        IncomingMessageFormParameterImpl param2 = new IncomingMessageFormParameterImpl();
        param2.setName("serial_id");
        param2.setValue("tester007");

        IncomingMessageFormParameterImpl param3 = new IncomingMessageFormParameterImpl();
        param3.setName("due_date");
        param3.setValue("12.07.2010");

        IncomingMessageFormParameterImpl param4 = new IncomingMessageFormParameterImpl();
        param4.setName("parity");
        param4.setValue("1");

        IncomingMessageFormParameterImpl param5 = new IncomingMessageFormParameterImpl();
        param5.setName("haemoglobin");
        param5.setValue("abc");

        IncomingMessageForm form = new IncomingMessageFormImpl();
        form.setIncomingMsgFormParameters(new HashMap<String,IncomingMessageFormParameter>());
        form.setIncomingMsgFormDefinition(formDef);
        form.getIncomingMsgFormParameters().put(param1.getName(),param1);
        form.getIncomingMsgFormParameters().put(param2.getName(),param2);
        form.getIncomingMsgFormParameters().put(param3.getName(),param3);
        form.getIncomingMsgFormParameters().put(param4.getName(),param4);
        
        //Test with required param missing
        boolean expResult = false;

        expect(
                mockCore.createIncomingMessageFormParameter()
                ).andReturn(new IncomingMessageFormParameterImpl());
        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true).times(4);

        replay(mockCore, mockParamValidator);
        boolean result = instance.validate(form, reqPhone);
        verify(mockCore, mockParamValidator);
        
        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 5);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.INVALID);

        //Test with invalid params
        form.setMessageFormStatus(IncMessageFormStatus.NEW);
        form.getIncomingMsgFormParameters().clear();
        form.getIncomingMsgFormParameters().put(param1.getName(),param1);
        form.getIncomingMsgFormParameters().put(param2.getName(),param2);
        form.getIncomingMsgFormParameters().put(param3.getName(),param3);
        form.getIncomingMsgFormParameters().put(param4.getName(),param4);
        form.getIncomingMsgFormParameters().put(param5.getName(),param5);

        reset(mockCore, mockParamValidator);

        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(false).times(5);
        
        replay(mockCore, mockParamValidator);
        result = instance.validate(form, reqPhone);
        verify(mockCore, mockParamValidator);

        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 5);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.INVALID);

        //Test with valid form on mobile
        param5.setValue("10");
        form.setMessageFormStatus(IncMessageFormStatus.NEW);
        form.getIncomingMsgFormParameters().clear();
        form.getIncomingMsgFormParameters().put(param1.getName(),param1);
        form.getIncomingMsgFormParameters().put(param2.getName(),param2);
        form.getIncomingMsgFormParameters().put(param3.getName(),param3);
        form.getIncomingMsgFormParameters().put(param4.getName(),param4);
        form.getIncomingMsgFormParameters().put(param5.getName(),param5);

        expResult = false;

        reset(mockCore, mockParamValidator);

        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true).times(5);

        replay(mockCore, mockParamValidator);
        result = instance.validate(form, reqPhone);
        verify(mockCore, mockParamValidator);

        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 5);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.VALID);

        //Test with valid form on mobile and server
        formDef.setFormCode("PREGNANCY");
        form.setMessageFormStatus(IncMessageFormStatus.NEW);
        form.getIncomingMsgFormParameters().clear();
        form.getIncomingMsgFormParameters().put(param1.getName(),param1);
        form.getIncomingMsgFormParameters().put(param2.getName(),param2);
        form.getIncomingMsgFormParameters().put(param3.getName(),param3);
        form.getIncomingMsgFormParameters().put(param4.getName(),param4);
        form.getIncomingMsgFormParameters().put(param5.getName(),param5);

        expResult = true;

        reset(mockCore, mockParamValidator);

        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true).times(5);

        mockRegSvc.registerPregnancy((String)anyObject(), (Date)anyObject(), (String)anyObject(), (Date)anyObject(), anyInt(), anyDouble());
        expectLastCall();

        replay(mockCore, mockParamValidator, mockRegSvc);
        result = instance.validate(form, reqPhone);
        verify(mockCore, mockParamValidator, mockRegSvc);

        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 5);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.SERVER_VALID);
    }

}