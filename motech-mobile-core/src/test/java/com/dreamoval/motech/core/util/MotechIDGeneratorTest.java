package com.dreamoval.motech.core.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author administrator
 */
public class MotechIDGeneratorTest {

    public MotechIDGeneratorTest() {
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
     * Test of generateID method, of class MotechIDGenerator.
     */
    @Test
    public void testGenerateID() {
        Long result = MotechIDGenerator.generateID(MotechIDGenerator.DEFUALT_ID_LENGTH);
        System.out.println("ID: " + result);
        assertNotNull(result);
    }
}
