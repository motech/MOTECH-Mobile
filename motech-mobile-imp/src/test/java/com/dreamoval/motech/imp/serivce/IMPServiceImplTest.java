/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.serivce;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.imp.util.CommandAction;
import com.dreamoval.motech.imp.util.IncomingMessageParser;
import com.dreamoval.motech.model.imp.IncomingMessage;
import com.dreamoval.motech.model.imp.IncomingMessageImpl;
import com.dreamoval.motech.model.imp.IncomingMessageResponseImpl;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 *  Date : Dec 5, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class IMPServiceImplTest {
    CoreManager mockCore;
    Map mockCmdAxnMap;
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
        mockCmdAxnMap = createMock(Map.class);

        instance = new IMPServiceImpl();
        instance.setParser(mockParser);
        instance.setCoreManager(mockCore);
        instance.setCmdActionMap(mockCmdAxnMap);
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
                mockParser.getCommand((String) anyObject())
                ).andReturn("*stop*");
        expect(
                mockParser.parseRequest((String) anyObject())
                ).andReturn(new IncomingMessageImpl());
        expect(
                mockCmdAxnMap.get((String) anyObject())
                ).andReturn(mockCmdAxn);
        expect(
                mockCmdAxn.execute((IncomingMessage) anyObject(), (String) anyObject())
                ).andReturn(response);

        replay(mockParser, mockCmdAxnMap, mockCmdAxn);
        String result = instance.processRequest(message, requesterPhone);
        verify(mockParser, mockCmdAxnMap, mockCmdAxn);

        assertEquals(expResult, result);
    }
}