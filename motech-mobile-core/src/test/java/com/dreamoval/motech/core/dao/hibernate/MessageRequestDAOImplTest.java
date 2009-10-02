/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.LanguageDAO;
import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.dao.NotificationTypeDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;
import com.dreamoval.motech.core.service.MotechContext;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  Date : Sep 25, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/core-config.xml"})
public class MessageRequestDAOImplTest {

    MessageType t = MessageType.TEXT;

    public MessageRequestDAOImplTest() {
    }
    @Autowired
    private CoreManager coreManager;
    @Autowired
    private MessageRequest mr1;
    @Autowired
    private Language lg1;
    @Autowired
    private NotificationType nt1;
    MessageRequestDAO mrDAO;
    LanguageDAO lDAO;
    NotificationTypeDAO ntDao;

    @Before
    public void setUp() {

        MotechContext mc = coreManager.createMotechContext();
        lDAO = coreManager.createLanguageDAO(mc);
        lg1.setId(23L);
        lg1.setCode("en");

        ntDao = coreManager.createNotificationTypeDAO(mc);
        nt1.setId(9878L);
        nt1.setName("pregnancy");

        Session session = (Session) lDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        lDAO.save(lg1);
        ntDao.save(nt1);
        tx.commit();


        mrDAO = coreManager.createMessageRequestDAO(mc);
        mr1.setId(1L);
        mr1.setDateCreated(new Date());
        mr1.setLanguage(lg1);
        mr1.setRecipientName("jlkj");
        mr1.setMessageType(t);



    }

    @Test
    public void testSave() {
        System.out.println("test save MessageRequest Object");
        Session session = (Session) mrDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mrDAO.save(mr1);
        tx.commit();

        MessageRequest fromdb = (MessageRequestImpl) session.get(MessageRequestImpl.class, mr1.getId());

        Assert.assertNotNull(fromdb);
        Assert.assertEquals(t, fromdb.getMessageType());
        Assert.assertEquals(lg1.getCode(), fromdb.getLanguage().getCode());
    }
}
