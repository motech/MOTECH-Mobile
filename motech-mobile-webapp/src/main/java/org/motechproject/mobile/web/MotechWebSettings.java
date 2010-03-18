/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.web;

import java.io.Serializable;

/**
 *Stores all session information on the Motech Web Module
 *
 * @author Henry Sampaon (henry@dreamoval.com)
 * Date Created: Dec 18, 2009
 */
public interface MotechWebSettings extends Serializable{

    /**
     * @return the incomingMessageFromParam
     */
    public String getIncomingMessageFromParam();

    /**
     * @return the incomingMessageTextParam
     */
    public String getIncomingMessageTextParam();

    /**
     * @param incomingMessageFromParam the incomingMessageFromParam to set
     */
    public void setIncomingMessageFromParam(String incomingMessageFromParam);

    /**
     * @param incomingMessageTextParam the incomingMessageTextParam to set
     */
    public void setIncomingMessageTextParam(String incomingMessageTextParam);

    /**
     * @return the unknownIMRMessage
     */
    String getUnknownIMRMessage();

    /**
     * @param unknownIMRMessage the unknownIMRMessage to set
     */
    void setUnknownIMRMessage(String unknownIMRMessage);

}
