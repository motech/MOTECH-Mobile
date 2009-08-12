/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.omi.service.OMIService;
import com.dreamoval.motech.omi.service.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Aug 10, 2009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/omi-config.xml"})
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
     * Test of createPatient method, of class OMIManagerImpl.
     */
    @Test
    public void testCreatePatient() {
        System.out.println("createPatient");
        Patient result = omiManager.createPatient();
        assertNotNull(result);
    }

}