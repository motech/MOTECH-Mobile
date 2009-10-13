package com.dreamoval.motech.omi.manager;


import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import com.dreamoval.motech.core.dao.MessageTemplateDAO;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestDetailsImpl;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.MessageTemplateImpl;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import com.dreamoval.motech.core.util.MotechException;
import java.util.HashMap;
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
    Language mockLang;
    MessageTemplate template;
    MotechContext mCtx;

    public MessageStoreManagerImplTest() {
    }

    @Before
    public void setUp(){
        mockCore = createMock(CoreManager.class);
        mockLang = createMock(Language.class);
        mockLang.setId(1L);
        mockLang.setCode("testing");
        mockTemplateDao = createMock(MessageTemplateDAO.class);
        instance = new MessageStoreManagerImpl();
        instance.setCoreManager(mockCore);
        
        mCtx = new MotechContextImpl();
        
        template = new MessageTemplateImpl();
        template.setTemplate("testing");
    }

    /**
     * Test of constructMessage method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testConstructMessage() {
        System.out.println("consrtuctMessage");
        
        MessageRequest message = new MessageRequestImpl();

        expect(
                mockCore.createMessageTemplateDAO((MotechContext) anyObject())
                ).andReturn(mockTemplateDao);
        expect(
                mockTemplateDao.getTemplateByLangNotifMType((Language)anyObject(), (NotificationType) anyObject(), (MessageType) anyObject())
                ).andReturn(template);
        expect(
                mockCore.createGatewayRequest((MotechContext) anyObject())
                ).andReturn(new GatewayRequestImpl());
        expect(
                mockCore.createGatewayRequestDetails((MotechContext) anyObject())
                ).andReturn(new GatewayRequestDetailsImpl());
        
        replay(mockCore, mockTemplateDao);

        GatewayRequestDetails result = instance.constructMessage(message, mCtx);
        assertNotNull(result);
        verify(mockCore, mockTemplateDao);
    }

    /**
     * Test of parseTemplate method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testParseTemplate() {
        System.out.println("parseTemplate");
        
        String tpl = "Testing the <method> method of the <class> class";
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("class", "MessageStoreManagerImpl");
        params.put("method", "parseTemplate");
        
        String expResult = "Testing the parseTemplate method of the MessageStoreManagerImpl class";
        
        String result = instance.parseTemplate(tpl, params);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseTemplate method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testParseTemplate_WrongParams() {
        System.out.println("parseTemplate");
        
        String tpl = "Testing the <name> method of the <object> class";
        String errorMsg = "Parameters did not match template.";
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("class", "MessageStoreManagerImpl");
        params.put("method", "parseTemplate");
        
        try{
            String result = instance.parseTemplate(tpl, params);
            fail("Should have thrown MotechException - Parameters did not match template.");
        }
        catch(MotechException expected){
            assertEquals(expected.getMessage(), errorMsg);            
        }
    }
    
    /**
     * Test of constructMessage method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testFetchTemplate() {
        System.out.println("fetchTemplate");
        
        mockTemplateDao = createMock(MessageTemplateDAO.class);
        
        MessageRequest message = new MessageRequestImpl();
        
        
        expect(
                mockCore.createMessageTemplateDAO((MotechContext) anyObject())
                ).andReturn(mockTemplateDao);
        expect(
                mockTemplateDao.getTemplateByLangNotifMType((Language)anyObject(), (NotificationType) anyObject(), (MessageType) anyObject())
                ).andReturn(template);
        
        replay(mockCore, mockTemplateDao);

        String result = instance.fetchTemplate(message, mCtx);
        assertEquals(result, "testing");
        verify(mockCore, mockTemplateDao);
    }
}