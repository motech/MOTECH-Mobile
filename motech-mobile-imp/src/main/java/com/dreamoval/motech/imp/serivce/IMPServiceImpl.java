/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.serivce;

import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.imp.manager.IMPManager;
import com.dreamoval.motech.imp.util.CommandAction;
import com.dreamoval.motech.imp.util.IncomingMessageParser;
import com.dreamoval.motech.core.model.IncomingMessage;
import com.dreamoval.motech.core.model.IncomingMessageResponse;
import java.util.Map;

/**
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public class IMPServiceImpl implements IMPService {
    private IMPManager impManager;
    private CoreManager coreManager;
    private IncomingMessageParser parser;
    private Map<String, CommandAction> cmdActionMap;
    /**
     *
     * @see IMPService.processRequest
     */
    public synchronized String processRequest(String message, String requesterPhone){
        IncomingMessage inMsg = parser.parseRequest(message);

        IncomingMessageResponse response = impManager.createCommandAction().execute(inMsg, requesterPhone);
        return response.getContent();
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    /**
     * @return the parser
     */
    public IncomingMessageParser getParser() {
        return parser;
    }

    /**
     * @param parser the parser to set
     */
    public void setParser(IncomingMessageParser parser) {
        this.parser = parser;
    }

    /**
     * @return the cmdActionMap
     */
    public Map<String, CommandAction> getCmdActionMap() {
        return cmdActionMap;
    }

    /**
     * @param cmdActionMap the cmdActionMap to set
     */
    public void setCmdActionMap(Map<String, CommandAction> cmdActionMap) {
        this.cmdActionMap = cmdActionMap;
    }

    /**
     * @return the impManager
     */
    public IMPManager getImpManager() {
        return impManager;
    }

    /**
     * @param impManager the impManager to set
     */
    public void setImpManager(IMPManager impManager) {
        this.impManager = impManager;
    }
}
