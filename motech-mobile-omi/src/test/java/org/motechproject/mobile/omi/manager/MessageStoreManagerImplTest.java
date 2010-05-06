package org.motechproject.mobile.omi.manager;


import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequestImpl;
import org.motechproject.mobile.core.model.MessageRequest;
import org.motechproject.mobile.core.model.MessageRequestImpl;
import org.motechproject.mobile.core.dao.MessageTemplateDAO;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetailsImpl;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.LanguageImpl;
import org.motechproject.mobile.core.model.MessageTemplate;
import org.motechproject.mobile.core.model.MessageTemplateImpl;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationType;
import org.motechproject.mobile.core.model.NotificationTypeImpl;
import org.motechproject.mobile.core.service.MotechContext;
import org.motechproject.mobile.core.service.MotechContextImpl;
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
        instance.setCharsPerSMS(160);
        instance.setConcatAllowance(7);
        instance.setMaxConcat(3);
        
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
        message.setMessageType(MessageType.TEXT);
        Language defaultLang = new LanguageImpl();

        message.setPersInfos(new HashSet<NameValuePair>());
        message.setNotificationType(new NotificationTypeImpl());

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

        GatewayRequest result = instance.constructMessage(message, mCtx, defaultLang);
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

        message.setNotificationType(new NotificationTypeImpl());
        
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