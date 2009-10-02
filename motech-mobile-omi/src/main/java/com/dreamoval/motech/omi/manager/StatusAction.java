package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.model.GatewayResponse;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Jul 31, 2009
 */
public interface StatusAction {

   public void doAction(GatewayResponse response);
}
