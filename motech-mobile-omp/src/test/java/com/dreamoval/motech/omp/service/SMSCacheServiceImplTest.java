/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */

package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import static org.easymock.EasyMock.*;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import com.dreamoval.motech.core.model.MessageDetailsImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;


public class SMSCacheServiceImplTest {

    SMSCacheServiceImpl instance;

    CoreManager mockCore;
    MessageDetailsDAO mockMessageDAO;

    public SMSCacheServiceImplTest() {
    }

    @Before
    public void setUp(){
        mockCore = createMock(CoreManager.class);
        mockMessageDAO = createMock(MessageDetailsDAO.class);
        
        instance = new SMSCacheServiceImpl();
        instance.setCoreManager(mockCore);
    }

    /**
     * Test of saveMessage method, of class SMSCacheServiceImpl.
     */
    @Test
    public void testSaveMessage() {
        System.out.println("saveMessage");

        MessageDetails messageDetails = new MessageDetailsImpl();
        messageDetails.setGlobalStatus("pending");
        messageDetails.setMessageText("a message for testing");
        messageDetails.setNumberOfPages(1);
        messageDetails.setRecipientsNumbers("000000000000");
        messageDetails.setMessageType("TEXT");
        
        expect(
                mockCore.createMessageDetailsDAO((MotechContext) anyObject())
                ).andReturn(mockMessageDAO);
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockMessageDAO.save((MessageDetails) anyObject())
                ).andReturn(messageDetails);
        replay(mockCore, mockMessageDAO);

        instance.saveMessage(messageDetails);
        verify(mockCore, mockMessageDAO);
    }

}