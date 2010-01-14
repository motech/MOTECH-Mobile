/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.model.IncMessageFormParameterStatus;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterImpl;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for ParamValueValidator class
 *
 *  Date : Jan 14, 2010
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
public class ParamValueValidatorTest {

    public ParamValueValidatorTest() {
    }

    /**
     * Test of validate method, of class ParamValueValidator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        IncomingMessageFormParameter param = new IncomingMessageFormParameterImpl();
        param.setValue("y");

        ParamValueValidator instance = new ParamValueValidator();
        instance.setCaseSensitive(true);
        instance.setValues("Y,N");

        boolean expResult = false;
        boolean result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.INVALID);
        assertEquals(param.getErrCode(), 3);
        assertEquals(param.getErrText(), "out of range");

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);
        instance.setCaseSensitive(false);

        expResult = true;
        result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);

        param.setMessageFormParamStatus(IncMessageFormParameterStatus.NEW);

        HashMap<String,String> conversions = new HashMap<String,String>();
        conversions.put("Y", "true");
        instance.setConversions(new HashMap<String,String>());
        instance.setConversions(conversions);

        expResult = true;
        result = instance.validate(param);
        assertEquals(expResult, result);
        assertEquals(param.getValue(), "true");
        assertEquals(param.getMessageFormParamStatus(), IncMessageFormParameterStatus.VALID);
    }

}