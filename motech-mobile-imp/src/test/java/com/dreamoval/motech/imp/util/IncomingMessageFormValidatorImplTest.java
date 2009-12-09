/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.model.imp.IncMessageFormStatus;
import com.dreamoval.motech.model.imp.IncomingMessageForm;
import com.dreamoval.motech.model.imp.IncomingMessageFormDefinition;
import com.dreamoval.motech.model.imp.IncomingMessageFormDefinitionImpl;
import com.dreamoval.motech.model.imp.IncomingMessageFormImpl;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameterDefinition;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameterDefinitionImpl;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameterImpl;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 *
 * @author user
 */
public class IncomingMessageFormValidatorImplTest {
    CoreManager mockCore;
    IncomingMessageFormParameterValidator mockParamValidator;

    IncomingMessageFormValidatorImpl instance;

    public IncomingMessageFormValidatorImplTest() {
    }

    @Before
    public void setUp() {
        mockCore = createMock(CoreManager.class);
        mockParamValidator = createMock(IncomingMessageFormParameterValidator.class);

        instance = new IncomingMessageFormValidatorImpl();
        instance.setCoreManager(mockCore);
        instance.setImParamValidator(mockParamValidator);
    }

    /**
     * Test of validate method, of class IncomingMessageFormValidatorImpl.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        IncomingMessageFormParameterDefinitionImpl pDef1 = new IncomingMessageFormParameterDefinitionImpl();
        pDef1.setRequired(true);
        pDef1.setName("name");

        IncomingMessageFormParameterDefinitionImpl pDef2 = new IncomingMessageFormParameterDefinitionImpl();
        pDef2.setRequired(false);
        pDef2.setName("age");

        IncomingMessageFormDefinition formDef = new IncomingMessageFormDefinitionImpl();
        formDef.setIncomingMsgParamDefinitions(new HashSet<IncomingMessageFormParameterDefinition>());
        formDef.getIncomingMsgParamDefinitions().add(pDef1);
        formDef.getIncomingMsgParamDefinitions().add(pDef2);

        IncomingMessageFormParameterImpl param1 = new IncomingMessageFormParameterImpl();
        param1.setName("name");

        IncomingMessageFormParameterImpl param2 = new IncomingMessageFormParameterImpl();
        param2.setName("age");

        IncomingMessageForm form = new IncomingMessageFormImpl();
        form.setIncomingMsgFormParameters(new ArrayList<IncomingMessageFormParameter>());
        form.setIncomingMsgFormDefinition(formDef);
        form.getIncomingMsgFormParameters().add(param2);
        
        //Test with required param missing
        boolean expResult = false;

        expect(
                mockCore.createIncomingMessageFormParameter()
                ).andReturn(new IncomingMessageFormParameterImpl());
        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true).times(2);

        replay(mockCore, mockParamValidator);
        boolean result = instance.validate(form);
        verify(mockCore, mockParamValidator);
        
        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 2);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.INVALID);

        //Test with invalid params
        form.getIncomingMsgFormParameters().clear();
        form.getIncomingMsgFormParameters().add(param1);
        form.getIncomingMsgFormParameters().add(param2);

        reset(mockCore, mockParamValidator);

        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(false).times(2);
        
        replay(mockCore, mockParamValidator);
        result = instance.validate(form);
        verify(mockCore, mockParamValidator);

        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 2);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.INVALID);

        //Test with valid form
        form.getIncomingMsgFormParameters().clear();
        form.getIncomingMsgFormParameters().add(param1);
        form.getIncomingMsgFormParameters().add(param2);

        expResult = true;

        reset(mockCore, mockParamValidator);

        expect(
                mockParamValidator.validate((IncomingMessageFormParameterImpl) anyObject())
                ).andReturn(true).times(2);

        replay(mockCore, mockParamValidator);
        result = instance.validate(form);
        verify(mockCore, mockParamValidator);

        assertEquals(expResult, result);
        assertEquals(param2.getIncomingMsgFormParamDefinition(), pDef2);
        assertTrue(form.getIncomingMsgFormParameters().size() == 2);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.VALID);
    }

}