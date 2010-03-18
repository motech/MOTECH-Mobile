/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.web.manager;

import org.motechproject.mobile.web.IncomingMessageRequestWorker;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Managers the creation of all entities in the web module
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Dec 18, 2009
 */
public class WebManagerImpl implements WebManager, ApplicationContextAware{
    /**
     * Spring Application Cotext
     */
    ApplicationContext appContext;

    public IncomingMessageRequestWorker createIMRWorker() {
        return (IncomingMessageRequestWorker)appContext.getBean("iMRWorkerBean");
    }

    /**
     * Needed to set the spring application context
     *
     * @param aCxt the application context passed from spring
     * @throws org.springframework.beans.BeansException
     */
    public void setApplicationContext(ApplicationContext aCxt) throws BeansException {
        appContext = aCxt;
    }
}
