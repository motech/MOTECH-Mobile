/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import java.util.Map;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

/**
 *
 * @author administrator
 */
public class MessageStoreManagerImplTest extends AbstractDependencyInjectionSpringContextTests {

    @Override
    protected String[] getConfigLocations(){
        return new String[]{"file:src/main/resources/omi-config.xml"};
    }
    
    public MessageStoreManagerImplTest(String testName) {
        super(testName);
    }

    /**
     * Test of getMessage method, of class MessageStoreManagerImpl.
     */
    public void testGetMessage() {
        System.out.println("getMessage");
        String key = "patient";
        MessageStoreManager instance = (MessageStoreManager)applicationContext.getBean("storeManager");
        String expResult = "This is a sample patient message for testing purposes";
        String result = instance.getMessage(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMessageStore method, of class MessageStoreManagerImpl.
     */
    public void testGetMessageStore() {
        System.out.println("getMessageStore");
        MessageStoreManager instance = (MessageStoreManager)applicationContext.getBean("storeManager");
        Map<String, String> result = instance.getMessageStore();
        assertNotNull(result);
    }

    /**
     * Test of setMessageStore method, of class MessageStoreManagerImpl.
     */
    public void testSetMessageStore() {
        System.out.println("setMessageStore");
        Map<String, String> messageStore = null;
        MessageStoreManager instance = (MessageStoreManager)applicationContext.getBean("storeManager");
        instance.setMessageStore(messageStore);
        assertNull(instance.getMessageStore());
    }

}
