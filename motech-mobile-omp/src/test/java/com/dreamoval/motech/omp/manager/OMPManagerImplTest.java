package com.dreamoval.motech.omp.manager;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import com.dreamoval.motech.omp.service.CacheService;
import com.dreamoval.motech.omp.service.MessagingService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/omp-config.xml"})
public class OMPManagerImplTest {

    @Autowired
    OMPManagerImpl ompManager;

    public OMPManagerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
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
    public void testCreateSMSGatewayManager() {
        System.out.println("createGatewayManager");
        GatewayManager result = ompManager.createGatewayManager();
        assertNotNull(result);
    }

    /**
     * Test of createSMSCacheService method, of class OMPManagerImpl.
     */
    @Test
    public void testCreateSMSCacheService() {
        System.out.println("createCacheService");
        CacheService result = ompManager.createCacheService();
        assertNotNull(result);
    }

    /**
     * Test of createSMSService method, of class OMPManagerImpl.
     */
    @Test
    public void testCreateSMSService() {
        System.out.println("createMessagingService");
        MessagingService result = ompManager.createMessagingService();
        assertNotNull(result);
    }

}