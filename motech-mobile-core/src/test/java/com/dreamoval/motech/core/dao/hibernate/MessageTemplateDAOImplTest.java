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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class MessageTemplateDAOImplTest {

    @Autowired
    CoreManager coreManager;
    @Autowired
    MessageTemplate mt1;
    @Autowired
    MessageTemplate mt2;
    @Autowired
    MessageTemplate mt3;
    @Autowired
    MessageTemplate mt4;
    @Autowired
    MessageTemplate mt5;
    @Autowired
    MessageTemplate mt6;
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
    String template = "some template";
    MessageType type = MessageType.TEXT;

    public MessageTemplateDAOImplTest() {
    }

    @Before
    public void setUp() {
        MotechContext mc = coreManager.createMotechContext();
        nt1.setId(401L);
        nt1.setName("some name");

        nt2.setId(402L);
        nt2.setName("second name");
        nt2.setDescription("for second test");

        nt3.setId(407L);
        nt3.setName("third name");
        nt3.setDescription("for third test");

        l1.setId(908L);
        l1.setCode("sk");

        l2.setId(909L);
        l2.setCode("ch");

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

        mt3.setId(543L);
        mt3.setNotificationType(nt3);
        mt3.setLanguage(l1);
        mt3.setMessageType(type);
        mt3.setTemplate(template);

        mt4.setId(544L);
        mt4.setNotificationType(nt1);
        mt4.setLanguage(l1);
        mt4.setMessageType(type);
        mt4.setTemplate("template for message 4");

        mt5.setId(545L);
        mt5.setNotificationType(nt3);
        mt5.setLanguage(l1);
        mt5.setMessageType(type);
        mt5.setTemplate("template for message 5");

        Session session = (Session) ntDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        lDao.save(l1);
        lDao.save(l2);
        ntDao.save(nt1);
        ntDao.save(nt2);
        ntDao.save(nt3);
        mtDao.save(mt3);
        mtDao.save(mt4);
        mtDao.save(mt5);
        tx.commit();
    }

    /**
     * Test of save method, of class MessagTemplateDAOImpl.
     */
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
     * Test of save method, of class MessagTemplateDAOImpl.
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

    /**
     * Test of getById method, of class MessagTemplateDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.println("test MessageTemplate getById");
        MessageTemplate mt = (MessageTemplate) mtDao.getById(mt3.getId());
        Assert.assertNotNull(mt);
        Assert.assertEquals(mt3, mt);
        Assert.assertEquals(mt3.getId(), mt.getId());
        Assert.assertEquals(mt3.getLanguage(), mt.getLanguage());
        Assert.assertEquals(mt3.getTemplate(), mt.getTemplate());

    }

    /**
     * Test of delete method, of class MessagTemplateDAOImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("test MessageTemplate delete");
        Session session = (Session) mtDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mtDao.delete(mt4);
        tx.commit();

        MessageTemplate fromdb = (MessageTemplate) session.get(MessageTemplateImpl.class, mt4.getId());
        Assert.assertNull(fromdb);
    }

    /**
     * Test of save method with update purpose, of class MessagTemplateDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.println("test MessageTemplateDAO update");
        String alttemplate = "altered template";
        Date altdate = new Date();
        mt5.setDateCreated(altdate);
        mt5.setMessageType(type);
        mt5.setNotificationType(nt2);
        mt5.setTemplate(alttemplate);
        Session session = (Session) mtDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mtDao.save(mt5);
        tx.commit();

        MessageTemplate fromdb = (MessageTemplateImpl) session.get(MessageTemplateImpl.class, mt5.getId());
        Assert.assertNotNull(fromdb);
        Assert.assertEquals(mt5, fromdb);
        Assert.assertEquals(nt2, fromdb.getNotificationType());
        Assert.assertEquals(alttemplate, fromdb.getTemplate());
        Assert.assertEquals(altdate, fromdb.getDateCreated());
    }

    /**
     * Test of findByExample method, of class MessagTemplateDAOImpl.
     */
    @Test
    public void testFindByExample() {
        System.out.print("test MessageTemplate findByExample");
        List expResult = new ArrayList();
        expResult.add(mt4);
        mt6.setTemplate(mt4.getTemplate());
        List result = mtDao.findByExample(mt6);
        Assert.assertNotNull(result);
        Assert.assertEquals(expResult, result);
        Assert.assertEquals(expResult.size(), result.size());

    }
}
