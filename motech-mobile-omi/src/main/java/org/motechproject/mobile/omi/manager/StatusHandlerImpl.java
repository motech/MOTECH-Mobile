package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import java.util.List;
import java.util.Map;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public class StatusHandlerImpl implements StatusHandler {

    private Map<MStatus, List<StatusAction>> actionRegister;

    /**
     *
     * @see StatusHandler.handleStatus
     */
    public void handleStatus(GatewayResponse response) {
        List<StatusAction> actions = actionRegister.get(response.getMessageStatus());
        if (actions != null) {
            for (StatusAction action : actions) {
                action.doAction(response);
            }
        }
    }

    /**
     *
     * @see StatusHandler.registerStatusAction
     */
    public boolean registerStatusAction(MStatus status, StatusAction action) {
        return actionRegister.get(status).add(action);
    }

    public Map<MStatus, List<StatusAction>> getActionRegister() {
        return actionRegister;
    }

    public void setActionRegister(Map<MStatus, List<StatusAction>> registry) {
        this.actionRegister = registry;
    }
}