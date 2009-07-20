/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omp.cache;

import com.dreamoval.motech.core.DataAccess.DAO.Impl.MessageDetailsDAOImpl;
import com.dreamoval.motech.core.DataAccess.DAO.MessageDetailsDAO;
import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author Yoofi
 */
public class SMSCacheImplTest extends TestCase {
    MessageDetails messageDetails;

    public SMSCacheImplTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
        this.messageDetails = new MessageDetails("text",1,"text message for dummies","Pending", new Date());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }


    /**
     * Test of getMessageDetailsDao method, of class SMSCacheImpl.
     */
    public void testGetMessageDetailsDao() {
        System.out.println("getMessageDetailsDao");
        SMSCacheImpl instance = new SMSCacheImpl();
        MessageDetailsDAO expResult = null;
        MessageDetailsDAO result = instance.getMessageDetailsDao();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessageDetailsDao method, of class SMSCacheImpl.
     */
    public void testSetMessageDetailsDao() {
        System.out.println("setMessageDetailsDao");
        MessageDetailsDAO messageDetailsDao = null;
        SMSCacheImpl instance = new SMSCacheImpl();
        instance.setMessageDetailsDao(messageDetailsDao);
        assertEquals(instance.getMessageDetailsDao(), null);
    }


    /**
     * Test of saveMessage method, of class SMSCacheImpl.
     */
    public void testSaveMessage() {
        System.out.println("saveMessage");
        SMSCacheImpl instance = new SMSCacheImpl();
        instance.setMessageDetailsDao(new MessageDetailsDAOImpl());
        boolean expResult = true;
        boolean result = instance.saveMessage(messageDetails);
        assertEquals(expResult, result);        
    }

    /**
     * Test of updateMessage method, of class SMSCacheImpl.
     */
//    public void testUpdateMessage() {
//        System.out.println("updateMessage");
//        MessageDetails messageDetails = null;
//        SMSCacheImpl instance = new SMSCacheImpl();
//        boolean expResult = false;
//        boolean result = instance.updateMessage(messageDetails);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
