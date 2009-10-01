/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.GatewayRequestDetailsDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestDetailsImpl;
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
 *  Date : Oct 1, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/core-config.xml"})
public class GatewayRequestDetailsDAOImplTest {

    @Autowired
    CoreManager coreManager;

    GatewayRequestDetailsDAO grDao;
    
    @Autowired
    GatewayRequestDetails grd1;

    @Autowired
    GatewayRequest gr1;

    @Autowired
    GatewayRequest gr2;
    
    @Autowired
    GatewayRequest gr3;

    @Before
    public void setUp() {
        grDao = coreManager.createGatewayRequestDetailsDAO(coreManager.createMotechContext());
        grd1.setId(Long.MIN_VALUE);
        grd1.setMessage("message to send");
        grd1.setNumberOfPages(2);

        gr1.setId(1L);
        gr1.setRecipientsNumber("234556");

        gr2.setId(2L);
        gr2.setRecipientsNumber("12345");
 
    }

    @Test
    public void testSave(){
        System.out.println("test save GatewayRequestDetails object");
        Session session = (Session )grDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        grDao.save(grd1);
        tx.commit();

        session.beginTransaction();
        GatewayRequestDetails fromdb = (GatewayRequestDetailsImpl)session.get(GatewayRequestDetailsImpl.class, grd1.getId());
        session.getTransaction().commit();

        Assert.assertNotNull(fromdb);
        Assert.assertEquals( grd1,fromdb);
        Assert.assertEquals(grd1.getId(),fromdb.getId());
    }


    public void testSaveWithChild(){
        System.out.println("test save GatewayRequestDetails object");
        Session session = (Session )grDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        grDao.save(grd1);
        tx.commit();

        session.beginTransaction();
        GatewayRequestDetails fromdb = (GatewayRequestDetailsImpl)session.get(GatewayRequestDetailsImpl.class, grd1.getId());
        session.getTransaction().commit();

//        Assert.assertNotNull(fromdb);
//        Assert.assertEquals( grd1,fromdb);
//        Assert.assertEquals(grd1.getId(),fromdb.getId());
    }
}
