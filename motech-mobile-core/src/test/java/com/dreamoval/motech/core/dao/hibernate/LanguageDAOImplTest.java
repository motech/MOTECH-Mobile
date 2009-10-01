/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.LanguageDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.LanguageImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/core-config.xml"})
public class LanguageDAOImplTest {

    public LanguageDAOImplTest() {
    }

    LanguageDAO lDao;
    @Autowired
    Language l1;
    @Autowired
    Language l2;
    @Autowired
    CoreManager coreManager;

    @Before
    public void setUp() {
        lDao = coreManager.createLanguageDAO(coreManager.createMotechContext());
        l1.setId(Long.MIN_VALUE);
        l1.setCode("aul887");
        l1.setName("day notifier");
        l1.setDescription("some description");
    }

    @Test
    public void testSave() {
        System.out.println("Test save Language object");
        Session session =(Session) lDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        lDao.save(l1);
        tx.commit();

        session.beginTransaction();
        Language fromdb = (LanguageImpl) session.get(LanguageImpl.class, l1.getId());
        Assert.assertNotNull(fromdb);
        Assert.assertSame(l1, fromdb);
        Assert.assertEquals(l1.getId(),fromdb.getId());
        Assert.assertEquals(l1.getCode(),fromdb.getCode());
        Assert.assertEquals(l1.getName(),fromdb.getName());
        Assert.assertEquals(l1.getDescription(),fromdb.getDescription());

        
    }
}