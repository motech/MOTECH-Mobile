/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.model.imp.IncMessageFormParameterStatus;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameter;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameterDefinitionImpl;
import com.dreamoval.motech.model.imp.IncomingMessageFormParameterImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Test for IncomingMessageFormParameterValidatorImpl class
 *
 *  Date : Dec 6, 2009
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/imp-config.xml"})
public class IncomingMessageFormParameterValidatorImplTest {
    @Autowired
    IncomingMessageFormParameterValidator imParamValidator;

    public IncomingMessageFormParameterValidatorImplTest() {
    }

    /**
     * Test of validate method, of class IncomingMessageFormParameterValidatorImpl.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        IncomingMessageFormParameter param = new IncomingMessageFormParameterImpl();
        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.setIncomingMsgFormParamDefinition(new IncomingMessageFormParameterDefinitionImpl());
        param.getIncomingMsgFormParamDefinition().setParamType("ALPHA");
        param.getIncomingMsgFormParamDefinition().setLength(30);
        param.setName("name");
        param.setValue("O'Test,Dream-Tester Test");
        
        boolean expResult = true;
        boolean result = imParamValidator.validate(param);

        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("ALPHA");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setName("name");
        param.setValue("tester1234");

        result = imParamValidator.validate(param);
        assertFalse(result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 1);
        assertEquals(param.getErrText(), "name=wrong format");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("DATE");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setName("date");
        param.setValue("06-12-09");

        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("DATE");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setValue("2009.12.6");

        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("DATE");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setValue("6/12/2009");

        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("DATE");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setValue("06-2009-12");

        expResult = false;
        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 1);
        assertEquals(param.getErrText(), "date=wrong format");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("NUMERIC");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setName("age");
        param.setValue("06-2009-12");

        expResult = false;
        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 1);
        assertEquals(param.getErrText(), "age=wrong format");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("NUMERIC");
        param.getIncomingMsgFormParamDefinition().setLength(3);
        param.setName("age");
        param.setValue("2009");

        expResult = false;
        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 2);
        assertEquals(param.getErrText(), "age=too long");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("NUMERIC");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setName("age");
        param.setValue("30");

        expResult = true;
        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("BOOLEAN");
        param.getIncomingMsgFormParamDefinition().setLength(5);
        param.setName("registered");
        param.setValue("yup");

        expResult = false;
        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 1);
        assertEquals(param.getErrText(), "registered=wrong format");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.getIncomingMsgFormParamDefinition().setParamType("BOOLEAN");
        param.getIncomingMsgFormParamDefinition().setLength(5);
        param.setName("registered");
        param.setValue("1");

        expResult = true;
        result = imParamValidator.validate(param);
        assertEquals(result, expResult);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);
    }
}