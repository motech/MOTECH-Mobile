package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.NotificationTypeDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.NotificationType;
import org.motechproject.mobile.core.model.NotificationTypeImpl;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
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
@ContextConfiguration(locations = {"classpath:META-INF/test-core-config.xml"})
public class NotificationTypeDAOImplTest {

    public NotificationTypeDAOImplTest() {
    }
    NotificationTypeDAO nDao;
    @Autowired
    CoreManager coreManager;
    @Autowired
    NotificationType nt1;
    @Autowired
    NotificationType nt2;
    @Autowired
    NotificationType nt3;
    @Autowired
    NotificationType nt4;
    @Autowired
    NotificationType nt5;
    @Autowired
    NotificationType nt6;

    @Before
    public void setUp() {
        nDao = coreManager.createNotificationTypeDAO(coreManager.createMotechContext());
        nt1.setId(701L);
        nt1.setName("the name");
        nt1.setDescription("the description");

        nt2.setId(702L);
        nt2.setName("the name of notif 2");
        nt2.setDescription("the description for notif 2");

        nt3.setId(703L);
        nt3.setName("the name of notif 3");
        nt3.setDescription("the description for notif 3");

        nt4.setId(704L);
        nt4.setName("the name of notif 4");
        nt4.setDescription("the description for notif 4");

        nt5.setId(705L);
        nt5.setName("the name of notif 5");
        nt5.setDescription("the description fo notif 5");

        Session session = (Session) nDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        nDao.save(nt2);
        nDao.save(nt3);
        nDao.save(nt4);
        tx.commit();
    }

    @After
    public void tearDown() {
        Session session = (Session) nDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        nDao.delete(nt1);
        nDao.delete(nt2);
        nDao.delete(nt3);
        nDao.delete(nt4);
        tx.commit();
    }

    /**
     * Test of save method, of NotificationTypeDAOImpl.
     */
    @Test
    public void testSave() {
        System.out.println("test save NotificationType");
        Session session = (Session) nDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        nDao.save(nt1);
        tx.commit();

        session.beginTransaction();
        NotificationType fromdb = (NotificationType) session.get(NotificationTypeImpl.class, nt1.getId());
        session.getTransaction().commit();
        Assert.assertNotNull(fromdb);
    }

    /**
     * Test of save method with update purpose, of NotificationTypeDAOImpl.
     */
    @Test
    public void testUpdate() {
        System.out.print("test NotificationType update");
        String altname = "altered name 2";
        String description = "description";
        nt2.setName(altname);
        nt2.setDescription(description);

        Session session = (Session) nDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        nDao.save(nt2);
        tx.commit();
        NotificationType fromdb = (NotificationTypeImpl) session.get(NotificationTypeImpl.class, nt2.getId());
        Assert.assertNotNull(fromdb);
        Assert.assertEquals(altname, fromdb.getName());
        Assert.assertEquals(description, fromdb.getDescription());
    }

    /**
     * Test of delete method, of NotificationTypeDAOImpl.
     */
    @Test
    public void testDelete() {
        System.out.print("test NotificationType delete");
        Session session = (Session) nDao.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        nDao.delete(nt3);
        tx.commit();

        NotificationType fromdb = (NotificationTypeImpl) session.get(NotificationTypeImpl.class, nt3.getId());
        Assert.assertNull(fromdb);
    }

    /**
     * Test of getAll method, of NotificationTypeDAOImpl.
     */
    @Test
    public void testGetAll() {
        System.out.print("test NotificationType delete");
        List all = new ArrayList();
        all.add(nt2);
        all.add(nt3);
        all.add(nt4);

        List allfromdb = nDao.getAll();
        Assert.assertNotNull(allfromdb);
        Assert.assertTrue(allfromdb.contains(nt2));
        Assert.assertTrue(allfromdb.contains(nt3));
        Assert.assertTrue(allfromdb.contains(nt4));

    }

    /**
     * Test of getById method, of NotificationTypeDAOImpl.
     */
    @Test
    public void testGetById() {
        System.out.print("test NotificationType getById");
        NotificationType fromdb = (NotificationTypeImpl) nDao.getById(nt3.getId());
        Assert.assertNotNull(fromdb);
        Assert.assertEquals(fromdb, nt3);
        Assert.assertEquals(fromdb.getId(), nt3.getId());
    }

    /**
     * Test of findByExample  method, of NotificationTypeDAOImpl.
     */
    @Test
    public void testFindByExample() {
        System.out.println("test NotificationType findByExample");
        List expResult = new ArrayList();
        expResult.add(nt3);
        nt6.setName(nt3.getName());
        nt6.setDescription(nt3.getDescription());
        List result = nDao.findByExample(nt6);
        Assert.assertNotNull(result);
        Assert.assertEquals(expResult, result);

    }
}
