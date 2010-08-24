/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.omp.manager;

import java.util.ArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import org.motechproject.mobile.core.util.MotechIDGenerator;
import java.util.Set;

/**
 * <p>A dummy gateway manager for testing purposes</p>
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 */
public class DummyGatewayManagerImpl implements GatewayManager {

    private static Log log = LogFactory.getLog(DummyGatewayManagerImpl.class);
    private long sleepTime;
    private boolean throwRandomExceptions;
    private GatewayMessageHandler messageHandler;
    private ArrayList<Integer> exceptionPoints;
    private int exceptionPointRange;

    /**
     *
     * @see GatewayManager.send
     */
    public Set<GatewayResponse> sendMessage(GatewayRequest messageDetails) {
        if (log.isInfoEnabled()) {
            log.info(messageDetails.getId() + "|"
                    + messageDetails.getRecipientsNumber() + "|"
                    + messageDetails.getMessage());
        }
        String msgResponse = (messageDetails.getRecipientsNumber().length() < 8) ? "failed" : "ID: " + MotechIDGenerator.generateID(10);

        if (sleepTime > 0) {
            try {
                log.debug("going to sleep to simulate latency");
                Thread.sleep(sleepTime);
                log.debug("finished simulated latency");
            } catch (InterruptedException ie) {
                log.debug("interrupted while sleeping");
            }
        }

        if(isThrowRandomExceptions() && getExceptionPoints().contains(new Integer((int)(Math.random() * exceptionPointRange)))){
            log.error("Throwing Exception to mimic possible fault behaviour");
            throw new RuntimeException("Arbitrary exception thrown to mimic fault behaviour");
        }

        return messageHandler.parseMessageResponse(messageDetails, msgResponse);
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    /**
     *
     * @see GatewayManager.getMessageStatus
     */
    public String getMessageStatus(GatewayResponse response) {
        return "004";
    }

    public MStatus mapMessageStatus(GatewayResponse response) {
        return messageHandler.parseMessageStatus(response.getResponseText());
    }

    /**
     * @return the messageHandler
     */
    public GatewayMessageHandler getMessageHandler() {
        return messageHandler;
    }

    /**
     * @param messageHandler the messageHandler to set
     */
    public void setMessageHandler(GatewayMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    /**
     * @return the throwRandomExceptions
     */
    public boolean isThrowRandomExceptions() {
        return throwRandomExceptions;
    }

    /**
     * @param throwRandomExceptions the throwRandomExceptions to set
     */
    public void setThrowRandomExceptions(boolean throwRandomExceptions) {
        this.throwRandomExceptions = throwRandomExceptions;
    }

    /**
     * @return the exceptionPoints
     */
    public ArrayList<Integer> getExceptionPoints() {
        return exceptionPoints;
    }

    /**
     * @param exceptionPoints the exceptionPoints to set
     */
    public void setExceptionPoints(ArrayList<Integer> exceptionPoints) {
        this.exceptionPoints = exceptionPoints;
    }

    /**
     * @return the exceptionPointRange
     */
    public int getExceptionPointRange() {
        return exceptionPointRange;
    }

    /**
     * @param exceptionPointRange the exceptionPointRange to set
     */
    public void setExceptionPointRange(int exceptionPointRange) {
        this.exceptionPointRange = exceptionPointRange;
    }
}
