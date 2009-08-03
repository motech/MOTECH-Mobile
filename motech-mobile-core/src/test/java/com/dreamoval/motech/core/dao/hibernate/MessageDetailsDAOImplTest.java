/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageDetailsDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.MessageDetails;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created 03-08-2009
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/motech-core.xml"})
public class MessageDetailsDAOImplTest {

    @Autowired
    CoreManager coreManager;
    
    @Autowired
    private MessageDetails md1;
    @Autowired
    private MessageDetails md2;
    @Autowired
    private MessageDetails md3;

    public MessageDetailsDAOImplTest() {
    }

    /**
     * Test of getByStatus method, of class MessageDetailsDAOImpl.
     */
    @Test
    public void testGetByStatus() {
        System.out.println("getByStatus");
        String status = "TEST";


        MessageDetailsDAO mDDAO = coreManager.createMessageDetailsDAO(coreManager.createMotechContext());
        md1.setId(1L);
        md1.setMessageText("First");
        md1.setRecipientsNumbers("123445");
        md1.setGlobalStatus(status);

        md2.setId(2L);
        md2.setMessageText("Second");
        md2.setRecipientsNumbers("123445");
        md2.setGlobalStatus(status);

        md3.setId(3L);
        md3.setMessageText("Third");
        md3.setRecipientsNumbers("123445");
        md3.setGlobalStatus("TRIAL");

        Transaction tx = ((Session)mDDAO.getDBSession().getSession()).beginTransaction();

        mDDAO.save(md1);
        mDDAO.save(md2);
        mDDAO.save(md3);

        tx.commit();

        List<MessageDetails> expResult = new ArrayList<MessageDetails>();
        expResult.add(md1);
        expResult.add(md2);

        List<MessageDetails> result = mDDAO.getByStatus(status);
        Assert.assertEquals(expResult, result);
    }
}