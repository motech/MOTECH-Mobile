/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.DataAccess.DAO.Impl;

import com.dreamoval.motech.core.DataAccess.DAO.MessageDetailsDAO;
import com.dreamoval.motech.core.DataAccess.Domain.MessageDetails;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author Jojo
 */
public class MessageDetailsDAOImplTest extends TestCase {
    MessageDetails[] messageDetails;
    MessageDetailsDAO messageDao;
    
    public MessageDetailsDAOImplTest(String testName) {
        super(testName);
        messageDao = new MessageDetailsDAOImpl();
        messageDetails = new  MessageDetails[]
        { 
            new MessageDetails("text",1,"text message for dummies","Pending", new Date()),
          new MessageDetails("text",1,"text message for test","Pending", new Date()), 
          new MessageDetails("text",1,"text message for another test","Pending", new Date())
        };
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of StoreMessage method, of class MessageDetailsDAOImpl.
     */
    public void testStoreMessage() {
        System.out.println("StoreMessage");


        boolean expResult = true;
        boolean result = messageDao.StoreMessage(messageDetails[0]);
        assertEquals(expResult, result);

    }

}
