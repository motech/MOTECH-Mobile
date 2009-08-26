

package com.dreamoval.motech.core.dao.hibernate;
import junit.framework.Assert;
import org.hibernate.Session;
//
//import org.junit.After;
//import org.junit.AfterClass;
import com.dreamoval.motech.core.dao.TransitionDAO;
import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.Transition;
import com.dreamoval.motech.core.model.TransitionImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.junit.Ignore;

/**
 *  Date : Aug 4, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/core-config.xml"})
public class TransitionDAOImplTest {


      @Autowired
    CoreManager coreManager;
    TransitionDAO tDAO;

      @Autowired
      private Transition t1;
      @Autowired
      private Transition t2;

      @Autowired
      private Transition t3;
      @Autowired
      private Transition t4;

      @Autowired
      private Transition t5;
      @Autowired
      private Transition t6;

    @Before
    public void setUp() {
        tDAO = coreManager.createTransitionDAO(coreManager.createMotechContext());

        t1.setId(1L);
        t1.setTransactionDate(new Date());
        t1.setTransactionDescription("test for transition description 1");
        t1.setTransactionType("update");

        t2.setId(2L);
        t2.setTransactionDate(new Date());
        t2.setTransactionDescription("test for transition description 2");
        t2.setTransactionType("update");

        t3.setId(3L);
        t3.setTransactionDate(new Date());
        t3.setTransactionDescription("test for transition description 3");
        t3.setTransactionType("update");

        t4.setId(4L);
        t4.setTransactionDate(new Date());
        t4.setTransactionDescription("test for transition description 4");
        t4.setTransactionType("insert");

        t5.setId(5L);
        t5.setTransactionDate(new Date());
        t5.setTransactionDescription("test for transition description 5");
        t5.setTransactionType("insert");

        setUpInitialData();
    }

    
    public void setUpInitialData() {
        Transaction tx = ((Session) tDAO.getDBSession().getSession()).beginTransaction();
        tDAO.save(t1);
        tDAO.save(t2);
        tDAO.save(t3);
        tDAO.save(t4);
        tDAO.save(t5);
        tx.commit();
    }
    
//    @Ignore
    @Test
    public void testSave() {
       System.out.println("Save Transition");

       Session session = ((Session) tDAO.getDBSession().getSession());
       session.beginTransaction();
       tDAO.save(t1);
      
       session.getTransaction().commit();
       session.beginTransaction();
       Transition fromdb1 = (TransitionImpl)session.get(TransitionImpl.class, t1.getId());       
       session.getTransaction().commit();
       Assert.assertEquals(t1.getId(), fromdb1.getId());
       Assert.assertEquals(t1.getTransactionDate(), fromdb1.getTransactionDate());
       Assert.assertEquals(t1.getTransactionDescription(), fromdb1.getTransactionDescription());
    
    }


    @Test
    public void testDelete() {
      System.out.println("Delete Transition");
      Session session = ((Session) tDAO.getDBSession().getSession());
      Transaction tx = session.beginTransaction();
       tDAO.delete(t2);
       tx.commit();

       session.beginTransaction();
       Transition fromdb = (Transition) session.get(TransitionImpl.class, t2.getId());
       session.getTransaction().commit();
       Assert.assertNull(fromdb);
    }


    @Test
    public void testUpdate() {
        System.out.println("Update Transition");
        String des = "UPdated description";
        String stat = "update";
        t5.setTransactionDescription(des);
        t5.setTransactionType(stat);
        Session session = (Session)tDAO.getDBSession().getSession();
        Transaction tx = session.beginTransaction();
        tDAO.save(t5);
        tx.commit();
        session.beginTransaction();
        Transition t5fromdb = (Transition) session.get(TransitionImpl.class, t5.getId());
        Assert.assertEquals(t5fromdb.getId(), t5.getId());
        Assert.assertEquals(des, t5fromdb.getTransactionDescription());
        Assert.assertEquals(stat, t5fromdb.getTransactionType());
    }


    @Test
    public void testGetById() {
        System.out.println("Get Transition by id");
          Session session = ((Session) tDAO.getDBSession().getSession());
          session.beginTransaction();
          Transition t3fromdb = (Transition) tDAO.getById(t3.getId());
          session.getTransaction().commit();
          Assert.assertEquals(t3.getId(), t3fromdb.getId());
          Assert.assertEquals(t3.getTransactionDate(), t3fromdb.getTransactionDate());
          Assert.assertEquals(t3.getTransactionDescription(), t3fromdb.getTransactionDescription());
          Assert.assertEquals(t3.getTransactionType(), t3fromdb.getTransactionType());
    }

    @Test
    public void testGetAll() {
        System.out.println("Get All Transition");
        List<Transition> all = new ArrayList<Transition>();
        all.add(t1);
        all.add(t2);
        all.add(t3);
        all.add(t4);
        all.add(t5);

          Session session = ((Session) tDAO.getDBSession().getSession());
          session.beginTransaction();
          List<Transition> allfromdb = tDAO.getAll();
          session.getTransaction().commit();
         for(Transition t : allfromdb)
             System.out.println(t.getId() + " " + t.getTransactionDescription());
          Assert.assertEquals(all.size(), allfromdb.size()); 
          Assert.assertEquals(true, allfromdb.contains(t2));
          Assert.assertEquals(true, allfromdb.contains(t3));
          Assert.assertEquals(true, allfromdb.contains(t4));
          Assert.assertEquals(true, allfromdb.contains(t5));
//          Assert.assertEquals(true, allfromdb.contains(t1));
    }

//    @Ignore
    @Test
    public void testFindByExample() {
        t6.setTransactionType("update");
        List<Transition> expResult = new ArrayList<Transition>();
        expResult.add(t1);
        expResult.add(t2);
        expResult.add(t3);

        List<Transition> result = tDAO.findByExample(t6);
        Assert.assertNotNull(result);
        Assert.assertEquals(expResult.size(), result.size());
        for(Transition t : result)
             System.out.println(t.getId() + " " + t.getTransactionDescription());
//        Assert.assertEquals(true, result.contains(t1));
        Assert.assertEquals(true, result.contains(t2));
        Assert.assertEquals(true, result.contains(t3));
    }

}
