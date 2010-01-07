/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util;

import com.dreamoval.motech.core.dao.DBSession;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageFormParameter;
import com.dreamoval.motech.core.model.IncomingMessageFormParameterImpl;
import com.dreamoval.motech.core.model.IncomingMessageImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormParameterDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

/**
 * Test for IncomingMessageParserImpl class
 *
 *  Date : Dec 5, 2009
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/imp-test-config.xml"})
public class IncomingMessageParserImplTest {
    CoreManager mockCore;

    @Autowired
    IncomingMessageParserImpl imParser;
    
    public IncomingMessageParserImplTest() {
    }

    @Before
    public void setup(){
        mockCore = createMock(CoreManager.class);
        imParser.setCoreManager(mockCore);
    }

    /**
     * Test of parseRequest method, of class IncomingMessageParserImpl.
     */
    @Test
    public void testParseRequest() {
        System.out.println("parseRequest");
        String message = "Type=GENERAL\naction=test\nmessage=Test,, Tester";

        expect(
                mockCore.createIncomingMessage()
                ).andReturn(new IncomingMessageImpl());

        replay(mockCore);
        IncomingMessage result = imParser.parseRequest(message);
        verify(mockCore);

        assertNotNull(result);
        assertEquals(message, result.getContent());
    }

    /**
     * Test of getComand method, of class IncomingMessageParserImpl.
     */
    @Test
    public void testGetComand() {
        System.out.println("getComand");
        String message = "Type=GENERAL\naction=test\nmessage=Test,, Tester";
        
        String expResult = "type";
        String result = imParser.getCommand(message);
        assertEquals(expResult, result);

        message = "someting=test Type";
        expResult = "";
        result = imParser.getCommand(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFormCode method, of class IncomingMessageParserImpl.
     */
    @Test
    public void testGetFormCode() {
        System.out.println("getFormCode");
        String message = "Type=GENERAL\naction=test\nmessage=Test,, Tester";
        String expResult = "GENERAL";
        String result = imParser.getFormCode(message);
        assertEquals(expResult, result);
    }

    /**
     * Test of getParams method, of class IncomingMessageParserImpl.
     */
    @Test
    public void testGetParams() {
        System.out.println("getParams");
        String message = "Type=GENERAL\naction=test\nmessage=Test, Dream Tester\ndob = 01/01/01\ndue-date=right. now\ntest_format=all";

        List<IncomingMessageFormParameter> expResult = new ArrayList<IncomingMessageFormParameter>();

        Transaction mockTrans = createMock(Transaction.class);
        DBSession mockSess = createMock(DBSession.class);
        IncomingMessageFormParameterDAO mockImParamDao = createMock(IncomingMessageFormParameterDAO.class);

        IncomingMessageFormParameter param1 = new IncomingMessageFormParameterImpl();
        param1.setName("type");
        param1.setValue("test");
        expResult.add(param1);

        IncomingMessageFormParameter param2 = new IncomingMessageFormParameterImpl();
        param1.setName("message");
        param1.setValue("Test, Dream Tester");
        expResult.add(param2);

        IncomingMessageFormParameter param3 = new IncomingMessageFormParameterImpl();
        param1.setName("dob");
        param1.setValue("01.01.01");
        expResult.add(param3);

        IncomingMessageFormParameter param4 = new IncomingMessageFormParameterImpl();
        param1.setName("due-date");
        param1.setValue("right now");
        expResult.add(param4);

        IncomingMessageFormParameter param5 = new IncomingMessageFormParameterImpl();
        param1.setName("test_format");
        param1.setValue("all");
        expResult.add(param5);

        expect(
                mockCore.createIncomingMessageFormParameter()
                ).andReturn(new IncomingMessageFormParameterImpl()).times(expResult.size());
        expect(
                mockCore.createIncomingMessageFormParameterDAO((MotechContext)anyObject())
                ).andReturn(mockImParamDao);
        expect(
                mockImParamDao.getDBSession()
                ).andReturn(mockSess);
        expect(
                mockSess.getTransaction()
                ).andReturn(mockTrans);

        mockTrans.begin();
        expectLastCall();

        expect(
                mockImParamDao.save((IncomingMessageFormParameter)anyObject())
                ).andReturn(param1).times(expResult.size());

        mockTrans.commit();
        expectLastCall();

        replay(mockCore,mockImParamDao,mockSess,mockTrans);
        Map<String,IncomingMessageFormParameter> result = imParser.getParams(message, new MotechContextImpl());
        verify(mockCore,mockImParamDao,mockSess,mockTrans);
        
        assertNotNull(result);
        assertEquals(result.size(), expResult.size());
    }

}