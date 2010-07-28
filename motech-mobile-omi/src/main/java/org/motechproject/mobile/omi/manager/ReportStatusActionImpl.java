package org.motechproject.mobile.omi.manager;

import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.apache.log4j.Logger;
import org.motechproject.ws.server.RegistrarService;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public class ReportStatusActionImpl implements StatusAction {

    private RegistrarService regWs;
    private static Logger logger = Logger.getLogger(ReportStatusActionImpl.class);

    /**
     *
     * @see StatusAction.doAction
     */
    public void doAction(GatewayResponse response) {

        if (response.getRequestId() == null || response.getRequestId().isEmpty()) {
            return;
        }
        if (response.getRecipientNumber() == null || response.getRecipientNumber().isEmpty()) {
            return;
        }

        try {
            if (response.getMessageStatus() == MStatus.DELIVERED) {
                getRegWs().setMessageStatus(response.getRequestId(), true);
            } else if (response.getMessageStatus() == MStatus.FAILED) {
                getRegWs().setMessageStatus(response.getRequestId(), false);
            }

        } catch (Exception e) {
            logger.error("Error communicating with event engine", e);
        }
    }

    public RegistrarService getRegWs() {
        return regWs;
    }

    public void setRegWs(RegistrarService regWs) {
        this.regWs = regWs;
    }
}
