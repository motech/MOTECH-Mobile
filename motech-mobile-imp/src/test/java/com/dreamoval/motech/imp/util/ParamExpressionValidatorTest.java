/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.model.IncMessageFormParameterStatus;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterDefinitionImpl;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterImpl;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for ParamExpressionValidator class
 *
 *  Date : Jan 14, 2010
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
public class ParamExpressionValidatorTest {

    public ParamExpressionValidatorTest() {
    }

    /**
     * Test of validate method, of class ParamExpressionValidator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        IncomingMessageFormParameter param = new IncomingMessageFormParameterImpl();
        param.setValue("2010.12.10");
        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.setIncomingMsgFormParamDefinition(new IncomingMessageFormParameterDefinitionImpl());
        param.getIncomingMsgFormParamDefinition().setParamType("DATE");

        ParamExpressionValidator instance = new ParamExpressionValidator();
        instance.setExpression("(0[1-9]|[12][0-9]|3[01])[\\-/. ]?(0[1-9]|1[012])[\\-/. ]?(19|20)?\\d\\d");

        boolean expResult = false;
        boolean result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 1);
        assertEquals(param.getErrText(), "wrong format");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.setValue("10|02|1998");

        expResult = false;
        result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 1);
        assertEquals(param.getErrText(), "wrong format");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.setValue("10.12.2010");

        expResult = false;
        result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.setValue("10-01-10");

        expResult = true;
        result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        param.setValue("10012010");

        expResult = true;
        result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);
    }

}