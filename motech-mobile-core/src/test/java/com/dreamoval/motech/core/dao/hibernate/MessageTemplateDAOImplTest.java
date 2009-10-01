/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageTemplateDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.MessageTemplateImpl;
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
    public MessageTemplateDAOImplTest() {
    }

    @Before
    public void setUp() {
        mtDao = coreManager.createMessageTemplateDAO(coreManager.createMotechContext());
        mt1.setId(Long.MIN_VALUE);
        mt1.setLanguage("en");
        mt1.setNotificationType(1L);
    }

    @Test
    public void testSave() {
        System.out.print("test save MessageTemplate Object");
        Session session = (Session) mtDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        mtDao.save(mt1);
        tx.commit();

        session.beginTransaction();
        MessageTemplate fromdb = (MessageTemplate) session.get(MessageTemplateImpl.class, mt1.getId());
        session.getTransaction().commit();
        Assert.assertNotNull(fromdb);
    }
}
