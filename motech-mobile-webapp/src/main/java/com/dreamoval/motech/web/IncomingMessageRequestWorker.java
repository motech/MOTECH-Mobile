/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.web;

import com.dreamoval.motech.imp.manager.IMPManager;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Henry Sampaon (henry@dreamoval.com)
 * Date Created: Dec 18, 2009
 */
public interface IncomingMessageRequestWorker extends Serializable {

    /**
     * Handles all requests to the IncomingMessageServlet.
     *
     * @param params a map of parameters sent to the Servlet
     * @return the reply to send to the requestor
     */
    public String doRequest(Map<String, String[]> params);

    /**
     * @return the impManager
     */
    public IMPManager getImpManager();

    /**
     * @return the webSettings
     */
    public MotechWebSettings getWebSettings();

    /**
     * @param impManager the impManager to set
     */
    public void setImpManager(IMPManager impManager);

    /**
     * @param webSettings the webSettings to set
     */
    public void setWebSettings(MotechWebSettings webSettings);
}
