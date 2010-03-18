package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.core.model.GatewayResponse;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Jul 31, 2009
 */
public interface StatusAction {

   /**
    * Perform the corresponding action on the GatewayResponse object
    * @param response object on which action should be performed
    */
   public void doAction(GatewayResponse response);
}
