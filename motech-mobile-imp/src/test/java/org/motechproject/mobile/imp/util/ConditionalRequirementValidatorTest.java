/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.model.IncMessageFormStatus;
import java.util.ArrayList;
import java.util.Date;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormImpl;
import org.motechproject.mobile.core.model.IncomingMessageFormParameterImpl;
import static org.easymock.EasyMock.*;

/**
 *
 * @author Yoofi
 */
public class ConditionalRequirementValidatorTest {
    CoreManager mockCore;
    ConditionalRequirementValidator instance;

    public ConditionalRequirementValidatorTest() {
    }

    @Before
    public void setUp() {
        mockCore = createMock(CoreManager.class);
        instance = new ConditionalRequirementValidator();
    }

    /**
     * Test of validate method, of class ConditionalRequirementValidator.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");

        IncomingMessageFormParameter param1 = new IncomingMessageFormParameterImpl();
        param1.setDateCreated(new Date());
        param1.setName("parent");
        param1.setValue("value");

        IncomingMessageForm form = new IncomingMessageFormImpl();
        form.setIncomingMsgFormParameters(new HashMap<String, IncomingMessageFormParameter>());
        form.getIncomingMsgFormParameters().put(param1.getName(), param1);

        SubField sf = new SubField();
        sf.setFieldName("subfield");
        sf.setParentField("parent");
        sf.setReplaceOn("require");

        List<SubField> subFields = new ArrayList();
        subFields.add(sf);

        boolean expResult = true;
        boolean result = instance.validate(form, subFields, mockCore);
        assertEquals(expResult, result);

        param1.setValue("require");
        expResult = false;

        expect(
                mockCore.createIncomingMessageFormParameter()
                ).andReturn(new IncomingMessageFormParameterImpl());

        replay(mockCore);
        result = instance.validate(form, subFields, mockCore);
        verify(mockCore);

        assertEquals(result, expResult);
        assertEquals(form.getMessageFormStatus(), IncMessageFormStatus.INVALID);
    }

}