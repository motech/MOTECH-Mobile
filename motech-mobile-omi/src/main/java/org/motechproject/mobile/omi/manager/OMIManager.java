/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.omi.service.OMIService;

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
     * creates a MessageFormatter object
     * @return the created MessageFormatter object
     */
    MessageFormatter createMessageFormatter();
}
