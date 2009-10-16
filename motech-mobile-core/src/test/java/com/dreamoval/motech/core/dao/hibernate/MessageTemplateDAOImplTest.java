/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.LanguageDAO;
import com.dreamoval.motech.core.dao.MessageTemplateDAO;
import com.dreamoval.motech.core.dao.NotificationTypeDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.MessageTemplateImpl;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;
import com.dreamoval.motech.core.service.MotechContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/core-config.xml"})
public class MessageTemplateDAOImplTest {

     @Autowired
    CoreManager coreManager;
    @Autowired
    MessageTemplate mt1;

    @Autowired
    MessageTemplate mt2;

    @Autowired
    MessageTemplate mt3;
    MessageTemplateDAO mtDao;
    @Autowired
    NotificationType nt1;

    @Autowired
    NotificationType nt2;

    @Autowired
    NotificationType nt3;

    @Autowired
    Language l1;

    @Autowired
    Language l2;
    
    NotificationTypeDAO ntDao;
    LanguageDAO lDao;
    String template ="some template";
    MessageType type = MessageType.TEXT;
    
    public MessageTemplateDAOImplTest() {
    }

    @Before
    public void setUp() {
        MotechContext mc = coreManager.createMotechContext();
        nt1.setId(1L);
        nt1.setName("some name");

        nt2.setId(2L);
        nt2.setName("second name");
        nt2.setDescription("for second test");

        nt3.setId(7L);
        nt3.setName("third name");
        nt3.setDescription("for third test");
        


        
        l1.setId(98L);
        l1.setCode("fr");
        
        mtDao = coreManager.createMessageTemplateDAO(mc);
        lDao = coreManager.createLanguageDAO(mc);
        ntDao = coreManager.createNotificationTypeDAO(mc);
        
        
        mt1.setId(343L);
        mt1.setNotificationType(nt1);
        mt1.setLanguage(l1);
        mt1.setMessageType(type);
        mt1.setTemplate("test template for test 1");

        mt2.setId(443L);
        mt2.setNotificationType(nt2);
        mt2.setLanguage(l1);
        mt2.setMessageType(type);
        mt2.setTemplate(template);

        mt3.setId(243L);
        mt3.setNotificationType(nt3);
        mt3.setLanguage(l1);
        mt3.setMessageType(type);
        mt3.setTemplate(template);
        
        
        
        Session session = (Session) ntDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        lDao.save(l1);
        ntDao.save(nt1);
        ntDao.save(nt2);
        mtDao.save(mt3);
        
        tx.commit();


        
    }

    @Test
    public void testSave() {
        System.out.print("test save MessageTemplate Object");
        Session session = (Session) mtDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mtDao.save(mt1);
        tx.commit();

        MessageTemplate fromdb = (MessageTemplate) session.get(MessageTemplateImpl.class, mt1.getId());
        Assert.assertNotNull(fromdb);

    }

    /**
     * Test of getTemplateByLangNotifMType method, of class MessageTemplateDAOImpl.
     */
    @Test
    public void testGetTemplateByLangNotifMType() {
        System.out.println("getTemplateByLangNotifMType");
        
        Session session = (Session) mtDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        session.save(mt2);
        tx.commit();
        MessageTemplate result = mtDao.getTemplateByLangNotifMType(l1, nt2, type);
        Assert.assertNotNull(result);
        Assert.assertEquals(mt2, result);

    }
    
    @Test
    public void testGetById(){
        System.out.println("test MessageTemplate getById");
        MessageTemplate mt = (MessageTemplate) mtDao.getById(mt3.getId());
        Assert.assertNotNull(mt);
        Assert.assertEquals(mt3, mt);
        Assert.assertEquals(mt3.getId(), mt.getId());
        Assert.assertEquals(mt3.getLanguage(), mt.getLanguage());
        Assert.assertEquals(mt3.getTemplate(), mt.getTemplate());
        
    }
}