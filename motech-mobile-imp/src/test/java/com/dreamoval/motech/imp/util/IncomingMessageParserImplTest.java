/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.motechproject.ws.NameValuePair;

/**
 *
 * @author user
 */
public class IncomingMessageParserImplTest {

    public IncomingMessageParserImplTest() {
    }

    /**
     * Test of getComand method, of class IncomingMessageParserImpl.
     */
    @Test
    public void testGetComand() {
        System.out.println("getComand");
        String message = "*fc* 1 type=test, message=Test,, Tester";
        IncomingMessageParserImpl instance = new IncomingMessageParserImpl();
        
        String expResult = "*fc*";
        String result = instance.getCommand(message);
        assertEquals(expResult, result);

        message = "*STOP* processing this conversation";
        expResult = "*STOP*";
        result = instance.getCommand(message);
        assertEquals(expResult, result);

        message = "1 *fc*";
        expResult = "";
        result = instance.getCommand(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFormCode method, of class IncomingMessageParserImpl.
     */
    @Test
    public void testGetFormCode() {
        System.out.println("getFormCode");
        String message = "*fc* 1 type=test, message=Test,, Tester";
        IncomingMessageParserImpl instance = new IncomingMessageParserImpl();
        String expResult = "1";
        String result = instance.getFormCode(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of getParams method, of class IncomingMessageParserImpl.
     */
    @Test
    public void testGetParams() {
        System.out.println("getParams");
        String message = "*fc* 1 type=test, message=Test,, Dream Tester, dob = 01/01/01, due-date=right. now, test_format=all";

        Set<NameValuePair> expResult = new HashSet<NameValuePair>();
        expResult.add(new NameValuePair("type","test"));
        expResult.add(new NameValuePair("message","Test, Dream Tester"));
        expResult.add(new NameValuePair("dob","01/01/01"));
        expResult.add(new NameValuePair("due-date","right now"));
        expResult.add(new NameValuePair("test_format","all"));

        IncomingMessageParserImpl instance = new IncomingMessageParserImpl();
        Set<NameValuePair> result = instance.getParams(message);
        assertNotNull(result);
        assertTrue(result.size() == expResult.size());
    }

}