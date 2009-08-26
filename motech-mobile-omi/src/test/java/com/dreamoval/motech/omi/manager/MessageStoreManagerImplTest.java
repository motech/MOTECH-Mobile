/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

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
@ContextConfiguration(locations = {"classpath:META-INF/omi-config.xml"})
public class MessageStoreManagerImplTest {

    @Autowired
    MessageStoreManagerImpl storeManager;

    public MessageStoreManagerImplTest() {
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

}