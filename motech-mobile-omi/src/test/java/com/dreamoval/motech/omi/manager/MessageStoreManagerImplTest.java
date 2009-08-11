/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/omi-config.xml"})
public class MessageStoreManagerImplTest {

    @Autowired
    MessageStoreManagerImpl storeManager;

    public MessageStoreManagerImplTest() {
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
     * Test of getMessage method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        String key = "patient";
        MessageStoreManagerImpl instance = storeManager;
        String expResult = "This is a sample patient message for testing purposes";
        String result = instance.getMessage(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageStore method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testGetMessageStore() {
        System.out.println("getMessageStore");
        MessageStoreManagerImpl instance = storeManager;
        Map<String, String> result = instance.getMessageStore();
        assertNotNull(result);
    }

    /**
     * Test of setMessageStore method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testSetMessageStore() {
        System.out.println("setMessageStore");
        Map<String, String> messageStore = storeManager.getMessageStore();
        MessageStoreManagerImpl instance = storeManager;

        instance.setMessageStore(null);
        assertNull(instance.getMessageStore());
        
        instance.setMessageStore(messageStore);
        assertEquals(messageStore, instance.getMessageStore());
    }

}