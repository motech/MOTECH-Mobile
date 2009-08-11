/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class PatientImplTest {

    public PatientImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class PatientImpl.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        PatientImpl instance = new PatientImpl();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class PatientImpl.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Tester";
        PatientImpl instance = new PatientImpl();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getSerialNumber method, of class PatientImpl.
     */
    @Test
    public void testGetSerialNumber() {
        System.out.println("getSerialNumber");
        PatientImpl instance = new PatientImpl();
        String expResult = null;
        String result = instance.getSerialNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSerialNumber method, of class PatientImpl.
     */
    @Test
    public void testSetSerialNumber() {
        System.out.println("setSerialNumber");
        String serialNumber = "#000000";
        PatientImpl instance = new PatientImpl();
        instance.setSerialNumber(serialNumber);
        assertEquals(serialNumber, instance.getSerialNumber());
    }

}