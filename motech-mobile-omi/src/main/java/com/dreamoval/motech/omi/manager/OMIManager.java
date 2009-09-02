/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.omi.service.OMIService;
import com.dreamoval.motech.omi.service.Patient;

/**
 * Handles creation of all objects within the OMI
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Aug 4, 2009
 */
public interface OMIManager {

    /**
     * creates a new OMIService object
     * @return the OMIService object
     */
    OMIService createOMIService();

    /**
     * creates a new MessageStoreManager object
     * @return the MessageStoreManager object
     */
    MessageStoreManager createMessageStoreManager();

    /**
     * creates a new MessagePatient object
     * @return the MessagePatient object
     */
    Patient createPatient();
}
