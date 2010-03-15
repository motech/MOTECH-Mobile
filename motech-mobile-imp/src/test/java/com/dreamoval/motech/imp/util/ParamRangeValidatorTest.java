/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.model.IncMessageFormParameterStatus;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterImpl;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for ParamRangeValidator class
 *
 *  Date : Jan 14, 2010
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
public class ParamRangeValidatorTest {

    public ParamRangeValidatorTest() {
    }

    /**
     * Test of validate method, of class ParamRangeValidator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        IncomingMessageFormParameter param = new IncomingMessageFormParameterImpl();
        param.setValue("10");

        ParamRangeValidator instance = new ParamRangeValidator();
        instance.setMaxValue(5f);
        instance.setMinValue(0f);

        boolean expResult = false;
        boolean result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 3);
        assertEquals(param.getErrText(), "too large");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        instance.setMaxValue(20f);

        expResult = true;
        result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);
    }

}