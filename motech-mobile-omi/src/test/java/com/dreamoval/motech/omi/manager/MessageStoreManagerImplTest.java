package com.dreamoval.motech.omi.manager;


import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.MessageTemplateImpl;
import com.dreamoval.motech.core.dao.MessageTemplateDAO;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the MessageStoreManagerImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
public class MessageStoreManagerImplTest {

    CoreManager mockCore;
    MessageTemplateDAO mockTemplateDao;
    MessageStoreManager instance;

    public MessageStoreManagerImplTest() {
    }

    @Before
    public void setUp(){
        mockCore = createMock(CoreManager.class);
        mockTemplateDao = createMock(MessageTemplateDAO.class);
        instance = new MessageStoreManagerImpl();
        instance.setCoreManager(mockCore);
    }

    /**
     * Test of constructMessage method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testConstructMessage() {
        System.out.println("consrtuctMessage");
        
        MessageRequest message = new MessageRequestImpl();

        MessageTemplate tpl = new MessageTemplateImpl();
        tpl.setLanguage("testing");
        
        List<MessageTemplate> templates = new ArrayList<MessageTemplate>();
        templates.add(tpl);
        
        expect(
                mockCore.createGatewayRequest((MotechContext) anyObject())
                ).andReturn(new GatewayRequestImpl());
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createMessageTemplate((MotechContext) anyObject())
                ).andReturn(new MessageTemplateImpl());
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createMessageTemplateDAO((MotechContext) anyObject())
                ).andReturn(mockTemplateDao);
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockTemplateDao.findByExample(anyObject())
                ).andReturn(templates);
        
        replay(mockCore, mockTemplateDao);

        GatewayRequest result = instance.constructMessage(message);
        assertNotNull(result);
        verify(mockCore, mockTemplateDao);
    }

    /**
     * Test of parseTemplate method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testParseTemplate() {
        System.out.println("parseTemplate");
        
        String template = "Testing the <method> method of the <class> class";
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("class", "MessageStoreManagerImpl");
        params.put("method", "parseTemplate");
        
        String expResult = "Testing the parseTemplate method of the MessageStoreManagerImpl class";
        
        String result = instance.parseTemplate(template, params);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of constructMessage method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testFetchTemplate() {
        System.out.println("fetchTemplate");
        
        mockTemplateDao = createMock(MessageTemplateDAO.class);
        
        MessageRequest message = new MessageRequestImpl();
        
        MessageTemplate tpl = new MessageTemplateImpl();
        tpl.setLanguage("testing");
        
        List<MessageTemplate> templates = new ArrayList<MessageTemplate>();
        templates.add(tpl);
        
        expect(
                mockCore.createMessageTemplate((MotechContext) anyObject())
                ).andReturn(new MessageTemplateImpl());
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockCore.createMessageTemplateDAO((MotechContext) anyObject())
                ).andReturn(mockTemplateDao);
        expect(
                mockCore.createMotechContext()
                ).andReturn(new MotechContextImpl());
        expect(
                mockTemplateDao.findByExample(anyObject())
                ).andReturn(templates);
        
        replay(mockCore, mockTemplateDao);

        String result = instance.fetchTemplate(message);
        assertEquals(result, "testing");
        verify(mockCore, mockTemplateDao);
    }
}