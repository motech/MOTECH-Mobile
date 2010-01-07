/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.web;

import com.dreamoval.motech.imp.manager.IMPManager;
import com.dreamoval.motech.imp.serivce.IMPService;
import java.util.Map;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Dec 18, 2009
 */
public class IncomingMessageRequestWorkerImpl implements IncomingMessageRequestWorker{
    private IMPManager impManager;
    private MotechWebSettings webSettings;

    public synchronized String doRequest(Map<String, String[]> params) {
        String result = "No Response";

        if(params != null){
            if(params.containsKey(webSettings.getIncomingMessageFromParam())
                    &&
                    params.containsKey(webSettings.getIncomingMessageTextParam())){

                IMPService impService = getImpManager().createIMPService();

                String[] number = params.get(webSettings.getIncomingMessageFromParam());
                String[] text = params.get(webSettings.getIncomingMessageTextParam());
                
                //TODO Check the array length so it doesn't break disgracefully
                result = impService.processRequest(text[0], number[0]);
            }else{
                result = webSettings.getUnknownIMRMessage();
            }
        }

        return result;
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

}
