/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.serivce;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.imp.manager.IMPManager;
import com.dreamoval.motech.imp.util.CommandAction;
import com.dreamoval.motech.imp.util.IncomingMessageParser;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageImpl;
import com.dreamoval.motech.model.imp.IncomingMessageResponseImpl;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * Test for IMPServiceImpl class
 *
 *  Date : Dec 5, 2009
 * @author Kofi A.  Asamoah (yoofi@dreamoval.com)
 */
public class IMPServiceImplTest {
    IMPManager mockImp;
    CoreManager mockCore;
    CommandAction mockCmdAxn;
    IncomingMessageParser mockParser;

    IMPServiceImpl instance;

    public IMPServiceImplTest() {
    }

    @Before
    public void setUp() {
        mockCore = createMock(CoreManager.class);
        mockParser = createMock(IncomingMessageParser.class);
        mockCmdAxn = createMock(CommandAction.class);
        mockImp = createMock(IMPManager.class);

        instance = new IMPServiceImpl();
        instance.setParser(mockParser);
        instance.setCoreManager(mockCore);
        instance.setImpManager(mockImp);
    }

    /**
     * Test of processRequest method, of class IMPServiceImpl.
     */
    @Test
    public void testProcessRequest() {
        System.out.println("processRequest");
        String message = "*STOP*";
        String requesterPhone = "000000000000";
        String expResult = "Form entry cancelled";
        
        IncomingMessageResponseImpl response = new IncomingMessageResponseImpl();
        response.setContent(expResult);

        expect(
                mockParser.parseRequest((String) anyObject())
                ).andReturn(new IncomingMessageImpl());
        expect(
                mockImp.createCommandAction()
                ).andReturn(mockCmdAxn);
        expect(
                mockCmdAxn.execute((IncomingMessage) anyObject(), (String) anyObject())
                ).andReturn(response);

        replay(mockParser, mockImp, mockCmdAxn);
        String result = instance.processRequest(message, requesterPhone);
        verify(mockParser, mockImp, mockCmdAxn);

        assertEquals(expResult, result);
    }
}