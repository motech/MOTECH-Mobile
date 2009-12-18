package com.dreamoval.motech.omp.manager;

import com.dreamoval.motech.omp.service.CacheService;
import com.dreamoval.motech.omp.service.MessagingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Unit test for the OMPManagerImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/test-omp-config.xml"})
public class OMPManagerImplTest {

    @Autowired
    OMPManagerImpl ompManager;

    public OMPManagerImplTest() {
    }

    /**
     * Test of createGatewayMessageHandler method, of class OMPManagerImpl.
     */
    @Test
    public void testCreateGatewayMessageHandler() {
        System.out.println("createGatewayMessageHandler");
        GatewayMessageHandler result = ompManager.createGatewayMessageHandler();
        assertNotNull(result);
    }

    /**
     * Test of createSMSGatewayManager method, of class OMPManagerImpl.
     */
    @Test
    public void testCreateGatewayManager() {
        System.out.println("createGatewayManager");
        GatewayManager result = ompManager.createGatewayManager();
        assertNotNull(result);
    }

    /**
     * Test of createSMSCacheService method, of class OMPManagerImpl.
     */
    @Test
    public void testCreateCacheService() {
        System.out.println("createCacheService");
        CacheService result = ompManager.createCacheService();
        assertNotNull(result);
    }

    /**
     * Test of createSMSService method, of class OMPManagerImpl.
     */
    @Test
    public void testCreateMessagingService() {
        System.out.println("createMessagingService");
        MessagingService result = ompManager.createMessagingService();
        assertNotNull(result);
    }

}