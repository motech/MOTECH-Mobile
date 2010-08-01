/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.web;

import org.motechproject.mobile.imp.manager.IMPManager;
import org.motechproject.mobile.imp.serivce.IMPService;
import java.util.Map;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncomingMessageResponse;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Dec 18, 2009
 */
public class IncomingMessageRequestWorkerImpl implements IncomingMessageRequestWorker{
    private IMPManager impManager;
    private CoreManager coreManager;
    private MotechWebSettings webSettings;

    public String doRequest(Map<String, String[]> params) {
        IncomingMessageResponse response = getCoreManager().createIncomingMessageResponse();
        response.setContent("No response");

        if(params != null){
            if(params.containsKey(webSettings.getIncomingMessageFromParam())
                    &&
                    params.containsKey(webSettings.getIncomingMessageTextParam())){

                IMPService impService = getImpManager().createIMPService();

                String[] number = params.get(webSettings.getIncomingMessageFromParam());
                String[] text = params.get(webSettings.getIncomingMessageTextParam());
                
                //TODO Check the array length so it doesn't break disgracefully
                response = impService.processRequest(text[0], number[0], false);
            }else{
                response.setContent(webSettings.getUnknownIMRMessage());
            }
        }

        return response.getContent();
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

    /**
     * @return the webSettings
     */
    public MotechWebSettings getWebSettings() {
        return webSettings;
    }

    /**
     * @param webSettings the webSettings to set
     */
    public void setWebSettings(MotechWebSettings webSettings) {
        this.webSettings = webSettings;
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

}
