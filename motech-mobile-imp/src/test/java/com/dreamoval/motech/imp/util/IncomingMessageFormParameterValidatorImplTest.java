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
        param.setValue("Test,Tester");
        
        IncomingMessageFormParameter result = imParamValidator.validate(param);
        assertEquals(result.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.getIncomingMsgFormParamDefinition().setParamType("DATE");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setValue("06-12-09");

        result = imParamValidator.validate(param);
        assertEquals(result.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.getIncomingMsgFormParamDefinition().setParamType("DATE");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setValue("2009.12.6");

        result = imParamValidator.validate(param);
        assertEquals(result.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.getIncomingMsgFormParamDefinition().setParamType("DATE");
        param.getIncomingMsgFormParamDefinition().setLength(10);
        param.setValue("6/12/2009");

        result = imParamValidator.validate(param);
        assertEquals(result.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

//        param.getIncomingMsgFormParamDefinition().setParamType("DATE");
//        param.getIncomingMsgFormParamDefinition().setLength(10);
//        param.setValue("06-2009-12");
//
//        result = imParamValidator.validate(param);
//        assertEquals(result.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
//        assertEquals(result.getErrCode(), 0);
//        assertEquals(result.getErrText(), "wrong format");
    }
}