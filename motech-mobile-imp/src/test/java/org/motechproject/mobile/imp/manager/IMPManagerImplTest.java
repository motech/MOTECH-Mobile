/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.manager;

import org.motechproject.mobile.imp.serivce.IMPService;
import org.motechproject.mobile.imp.util.CommandAction;
import org.motechproject.mobile.imp.util.IncomingMessageFormValidator;
import org.motechproject.mobile.imp.util.IncomingMessageParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Test for IMPManagerImpl class
 *
 *  Date : Dec 5, 2009
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/imp-test-config.xml"})
public class IMPManagerImplTest {
    @Autowired
    IMPManager impManager;

    public IMPManagerImplTest() {
    }

    /**
     * Test of createIMPService method, of class IMPManagerImpl.
     */
    @Test
    public void testCreateIMPService() {
        System.out.println("createIMPService");

        IMPService result = impManager.createIMPService();
        assertNotNull(result);
    }

    /**
     * Test of createIncomingMessageParser method, of class IMPManagerImpl.
     */
    @Test
    public void testCreateIncomingMessageParser() {
        System.out.println("createIncomingMessageParser");

        IncomingMessageParser result = impManager.createIncomingMessageParser();
        assertNotNull(result);
    }

    /**
     * Test of createIncomingMessageFormValidator method, of class IMPManagerImpl.
     */
    @Test
    public void testCreateIncomingMessageFormValidator() {
        System.out.println("createIncomingMessageFormValidator");

        IncomingMessageFormValidator result = impManager.createIncomingMessageFormValidator();
        assertNotNull(result);
    }

    /**
     * Test of createIncomingMessageFormParameterValidator method, of class IMPManagerImpl.
     */
//    @Test
//    public void testCreateIncomingMessageFormParameterValidator() {
//        System.out.println("createIncomingMessageFormParameterValidator");
//
//        IncomingMessageFormParameterValidator result = impManager.createIncomingMessageFormParameterValidator();
//        assertNotNull(result);
//    }

    /**
     * Test of createIncomingMessageFormParameterValidator method, of class IMPManagerImpl.
     */
    @Test
    public void testCreateCommandAction() {
        System.out.println("createCommandAction");

        CommandAction result = impManager.createCommandAction();
        assertNotNull(result);
    }

}