

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.ResponseDetailsDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.ResponseDetails;
import junit.framework.Assert;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;







/**
 *  Date : Aug 4, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/core-config.xml"})
public class ResponseDetailsDAOImplTest {

    public ResponseDetailsDAOImplTest() {
    }

      @Autowired
    CoreManager coreManager;
    ResponseDetailsDAO rDDAO;

    @Autowired
    private ResponseDetails rd1;
    @Autowired
    private ResponseDetails rd2;


    @Before
    public void setUp() {
         rDDAO = coreManager.createResponseDetailsDAO(coreManager.createMotechContext());
          rd1.setId(1L);

        rd1.setRecipientNumber("123445");
        rd1.setMessageStatus("Pending");

        rd2.setId(2L);

        rd2.setRecipientNumber("123445");
        rd2.setMessageStatus("Pending");


    }

    @Test
    public void  testSave() {
System.out.println("Save Response");
 Transaction tx = ((Session) rDDAO.getDBSession().getSession()).beginTransaction();

  rDDAO.save(rd1);
  rDDAO.save(rd2);
 tx.commit();

 Assert.assertTrue(true);
    }

}
