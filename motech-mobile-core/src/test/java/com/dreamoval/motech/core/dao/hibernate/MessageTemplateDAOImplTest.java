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
    MessageTemplateDAO mtDao;
    @Autowired
    NotificationType nt1;
    @Autowired
    Language l1;
    NotificationTypeDAO ntDao;
    LanguageDAO lDao;

    public MessageTemplateDAOImplTest() {
    }

    @Before
    public void setUp() {
        MotechContext mc = coreManager.createMotechContext();
        nt1.setId(1L);
        nt1.setName("some name");
        l1.setId(98L);
        l1.setCode("en");

        lDao = coreManager.createLanguageDAO(mc);
        ntDao = coreManager.createNotificationTypeDAO(mc);
        Session session = (Session) ntDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        lDao.save(l1);
        ntDao.save(nt1);
        tx.commit();

        mtDao = coreManager.createMessageTemplateDAO(mc);
        mt1.setId(343L);
        mt1.setNotificationType(nt1);
        mt1.setLanguage(l1);
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
}
