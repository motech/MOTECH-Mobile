/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.web;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Dec 18, 2009
 */
public class MotechWebSettingsImpl implements MotechWebSettings {
    private String incomingMessageFromParam;
    private String incomingMessageTextParam;
    private String unknownIMRMessage;

    /**
     * @return the incomingMessageFromParam
     */
    public String getIncomingMessageFromParam() {
        return incomingMessageFromParam;
    }

    /**
     * @param incomingMessageFromParam the incomingMessageFromParam to set
     */
    public void setIncomingMessageFromParam(String incomingMessageFromParam) {
        this.incomingMessageFromParam = incomingMessageFromParam;
    }

    /**
     * @return the incomingMessageTextParam
     */
    public String getIncomingMessageTextParam() {
        return incomingMessageTextParam;
    }

    /**
     * @param incomingMessageTextParam the incomingMessageTextParam to set
     */
    public void setIncomingMessageTextParam(String incomingMessageTextParam) {
        this.incomingMessageTextParam = incomingMessageTextParam;
    }

    /**
     * @return the unknownIMRMessage
     */
    public String getUnknownIMRMessage() {
        return unknownIMRMessage;
    }

    /**
     * @param unknownIMRMessage the unknownIMRMessage to set
     */
    public void setUnknownIMRMessage(String unknownIMRMessage) {
        this.unknownIMRMessage = unknownIMRMessage;
    }

}
