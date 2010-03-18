package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.omi.service.OMIService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for the OMIManagerImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/omi-test-config.xml"})
public class OMIManagerImplTest {

    @Autowired
    OMIManagerImpl omiManager;

    public OMIManagerImplTest() {
    }

    /**
     * Test of createOMIService method, of class OMIManagerImpl.
     */
    @Test
    public void testCreateOMIService() {
        System.out.println("createOMIService");
        OMIService result = omiManager.createOMIService();
        assertNotNull(result);
    }

    /**
     * Test of createMessageStoreManager method, of class OMIManagerImpl.
     */
    @Test
    public void testCreateMessageStoreManager() {
        System.out.println("createMessageStoreManager");
        MessageStoreManager result = omiManager.createMessageStoreManager();
        assertNotNull(result);
    }

    /**
     * Test of createMessageFormatter method, of class OMIManagerImpl.
     */
    @Test
    public void testCreateMessageFormatter() {
        System.out.println("createMessageFormatter");
        MessageFormatter result = omiManager.createMessageFormatter();
        assertNotNull(result);
    }

}