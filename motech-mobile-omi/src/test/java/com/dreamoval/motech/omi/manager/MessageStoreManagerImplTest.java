package com.dreamoval.motech.omi.manager;


import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import com.dreamoval.motech.core.dao.MessageTemplateDAO;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestDetailsImpl;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.LanguageImpl;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.MessageTemplateImpl;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import java.util.HashSet;
import java.util.Set;
import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.ws.NameValuePair;
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
        Language defaultLang = new LanguageImpl();
        message.setPersInfos(new HashSet<NameValuePair>());

        expect(
                mockCore.createMessageTemplateDAO((MotechContext) anyObject())
                ).andReturn(mockTemplateDao);
        expect(
                mockTemplateDao.getTemplateByLangNotifMType((Language)anyObject(), (NotificationType) anyObject(), (MessageType) anyObject(), (Language) anyObject())
                ).andReturn(template);
        expect(
                mockCore.createGatewayRequest((MotechContext) anyObject())
                ).andReturn(new GatewayRequestImpl());
        expect(
                mockCore.createGatewayRequestDetails((MotechContext) anyObject())
                ).andReturn(new GatewayRequestDetailsImpl());
        
        replay(mockCore, mockTemplateDao);

        GatewayRequestDetails result = instance.constructMessage(message, mCtx, defaultLang);
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
        
        Set<NameValuePair> params = new HashSet<NameValuePair>();
        params.add(new NameValuePair("method", "parseTemplate"));
        params.add(new NameValuePair("class", "MessageStoreManagerImpl"));
        
        String expResult = "Testing the parseTemplate method of the MessageStoreManagerImpl class";
        
        String result = instance.parseTemplate(tpl, params);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseTemplate method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testParseTemplate_MissingParam() {
        System.out.println("parseTemplate");
        
        String tpl = "Testing the <method> method of the <class> class";
        
        Set<NameValuePair> params = new HashSet<NameValuePair>();
        params.add(new NameValuePair("method", "parseTemplate"));
        
        String expResult = "Testing the parseTemplate method of the <class> class";
        
        String result = instance.parseTemplate(tpl, params);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseTemplate method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testParseTemplate_NULLParam() {
        System.out.println("parseTemplate");
        
        String tpl = "Testing the <method> method of the <class> class";
        
        String result = instance.parseTemplate(tpl, null);
        assertEquals(tpl, result);
    }
    
    /**
     * Test of constructMessage method, of class MessageStoreManagerImpl.
     */
    @Test
    public void testFetchTemplate() {
        System.out.println("fetchTemplate");
        
        mockTemplateDao = createMock(MessageTemplateDAO.class);
        
        MessageRequest message = new MessageRequestImpl();
        Language defaultLang = new LanguageImpl();
        
        expect(
                mockCore.createMessageTemplateDAO((MotechContext) anyObject())
                ).andReturn(mockTemplateDao);
        expect(
                mockTemplateDao.getTemplateByLangNotifMType((Language)anyObject(), (NotificationType) anyObject(), (MessageType) anyObject(), (Language) anyObject())
                ).andReturn(template);
        
        replay(mockCore, mockTemplateDao);

        String result = instance.fetchTemplate(message, mCtx, defaultLang);
        assertEquals(result, "testing");
        verify(mockCore, mockTemplateDao);
    }
}