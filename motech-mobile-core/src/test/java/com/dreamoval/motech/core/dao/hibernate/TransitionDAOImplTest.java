

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
import java.util.Date;

/**
 *  Date : Aug 4, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/core-config.xml"})
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

    @Before
    public void setUp() {
        tDAO = coreManager.createTransitionDAO(coreManager.createMotechContext());

        t1.setId(1L);
        t1.setTransactionDate(new Date());
        t1.setTransactionDescription("test for transition description");
        t1.setTransactionType("update");

        t2.setId(2L);
        t2.setTransactionDate(new Date());
        t2.setTransactionDescription("test for transition description");
        t2.setTransactionType("update");

        t3.setId(3L);
        t3.setTransactionDate(new Date());
        t3.setTransactionDescription("test for transition description");
        t3.setTransactionType("update");

    }

    @Test
    public void testSave() {
       System.out.println("Save Transition");
//       Transaction tx = ((Session) tDAO.getDBSession().getSession()).beginTransaction();
       Session session = ((Session) tDAO.getDBSession().getSession());
       session.beginTransaction();
       tDAO.save(t1);
       tDAO.save(t2);
       tDAO.save(t3);
       session.getTransaction().commit();
       session.beginTransaction();
       Transition fromdb1 = (TransitionImpl)session.get(TransitionImpl.class, t1.getId());
       Transition fromdb2 = (TransitionImpl)session.get(TransitionImpl.class, t2.getId());
       Transition fromdb3 = (TransitionImpl)session.get(TransitionImpl.class, t3.getId());
       session.getTransaction().commit();
       
       Assert.assertEquals(t1.getId(), fromdb1.getId());
      Assert.assertEquals(t2.getId(), fromdb2.getId());
      Assert.assertEquals(t3.getId(), fromdb3.getId());
    }

}
