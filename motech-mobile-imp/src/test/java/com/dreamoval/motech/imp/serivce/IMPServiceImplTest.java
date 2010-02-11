/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.serivce;

import com.dreamoval.motech.core.dao.DBSession;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.imp.manager.IMPManager;
import com.dreamoval.motech.imp.util.CommandAction;
import com.dreamoval.motech.imp.util.IncomingMessageParser;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageImpl;
import com.dreamoval.motech.core.model.IncomingMessageResponseImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import com.dreamoval.motech.model.dao.imp.IncomingMessageDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Transaction;
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
    DBSession mockSession;
    Transaction mockTrans;
    CoreManager mockCore;
    CommandAction mockCmdAxn;
    IncomingMessageDAO mockMsgDao;
    IncomingMessageParser mockParser;

    IMPServiceImpl instance;

    public IMPServiceImplTest() {
    }

    @Before
    public void setUp() {
        mockCore = createMock(CoreManager.class);
        mockCmdAxn = createMock(CommandAction.class);
        mockParser = createMock(IncomingMessageParser.class);
        mockMsgDao = createMock(IncomingMessageDAO.class);
        mockSession = createMock(DBSession.class);
        mockTrans = createMock(Transaction.class);
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
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createIncomingMessageDAO((MotechContext)anyObject())
                ).andReturn(mockMsgDao);
        expect(
                mockMsgDao.getByContentBefore((String)anyObject(), (Date)anyObject())
                ).andReturn(null);
        expect(
                mockParser.parseRequest((String) anyObject())
                ).andReturn(new IncomingMessageImpl());

        expect(
                mockMsgDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);

        mockTrans.begin();
        expectLastCall();

        expect(
                mockMsgDao.save((IncomingMessage) anyObject())
                ).andReturn(null);

        mockTrans.commit();
        expectLastCall();
        expect(
                mockImp.createCommandAction()
                ).andReturn(mockCmdAxn);
        expect(
                mockCmdAxn.execute((IncomingMessage) anyObject(), (String) anyObject(), (MotechContext)anyObject())
                ).andReturn(response);

        replay(mockParser, mockCore, mockMsgDao, mockSession, mockTrans, mockImp, mockCmdAxn);
        String result = instance.processRequest(message, requesterPhone, false);
        verify(mockParser, mockCore, mockMsgDao, mockSession, mockTrans, mockImp, mockCmdAxn);

        assertEquals(expResult, result);

        //Test duplicate message detection
        reset(mockParser, mockCore, mockMsgDao, mockSession, mockTrans, mockImp, mockCmdAxn);

        String duplicateResp = "Error:\nThis form has already been processed!";

        IncomingMessage inMsg = new IncomingMessageImpl();
        inMsg.setContent(message);
        
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createIncomingMessageDAO((MotechContext)anyObject())
                ).andReturn(mockMsgDao);
        expect(
                mockMsgDao.getByContentBefore((String)anyObject(), (Date)anyObject())
                ).andReturn(inMsg);

        replay(mockParser, mockCore, mockMsgDao, mockSession, mockTrans, mockImp, mockCmdAxn);
        result = instance.processRequest(message, requesterPhone, false);
        verify(mockParser, mockCore, mockMsgDao, mockSession, mockTrans, mockImp, mockCmdAxn);

        assertEquals(duplicateResp, result);
    }

    /**
     * Test of processMultiRequests method, of class IMPServiceImpl.
     */
    @Test
    public void testProcessMultiRequests() {
        System.out.println("processMultiRequests");
        List<FormRequest> requests = new ArrayList<FormRequest>();
        requests.add(new FormRequest("Test message 1","first","","001"));
        requests.add(new FormRequest("Test message 2","second","","002"));
        requests.add(new FormRequest("Test message 3","third","","003"));

        String content = "Test response";
        IncomingMessageResponseImpl response = new IncomingMessageResponseImpl();
        response.setContent(content);

        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl()).times(requests.size());
        expect(
                mockCore.createIncomingMessageDAO((MotechContext)anyObject())
                ).andReturn(mockMsgDao).times(requests.size());
        expect(
                mockMsgDao.getByContentBefore((String)anyObject(), (Date)anyObject())
                ).andReturn(null).times(requests.size());
        expect(
                mockParser.parseRequest((String) anyObject())
                ).andReturn(new IncomingMessageImpl()).times(requests.size());

        expect(
                mockMsgDao.getDBSession()
                ).andReturn(mockSession).times(requests.size());
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans).times(requests.size());

        mockTrans.begin();
        expectLastCall().times(requests.size());

        expect(
                mockMsgDao.save((IncomingMessage) anyObject())
                ).andReturn(null).times(requests.size());

        mockTrans.commit();
        expectLastCall().times(requests.size());
        expect(
                mockImp.createCommandAction()
                ).andReturn(mockCmdAxn).times(requests.size());
        expect(
                mockCmdAxn.execute((IncomingMessage) anyObject(), (String) anyObject(), (MotechContext)anyObject())
                ).andReturn(response).times(requests.size());

        replay(mockParser, mockCore, mockMsgDao, mockSession, mockTrans, mockImp, mockCmdAxn);
        List<FormRequest> result = instance.processMultiRequests(requests, false);
        verify(mockParser, mockCore, mockMsgDao, mockSession, mockTrans, mockImp, mockCmdAxn);

        assertNotNull(result);
        assertEquals(result.size(), 3);
        assertEquals(result.get(0).getResponse(), content);
        assertEquals(result.get(1).getResponse(), content);
        assertEquals(result.get(2).getResponse(), content);
    }
}