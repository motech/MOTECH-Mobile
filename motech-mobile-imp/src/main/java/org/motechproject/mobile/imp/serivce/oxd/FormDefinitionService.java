/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.serivce.oxd;

import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com) and Brent Atkinson
 * Date Created: Mar 3, 2010
 */
public interface FormDefinitionService {

    /**
     * Retrieves the OXD definitions in the inputstream
     *
     * @return
     * @throws java.lang.Exception
     */
    public Map<Integer, String> getXForms() throws Exception;

    /**
     * @return the exportStream
     */
    public InputStream getExportStream();

    /**
     * @return the xFormsResourcename
     */
    public String getOxdFormDefResourceName();

    /**
     * @param xFormsResourcename the xFormsResourcename to set
     */
    public void setOxdFormDefResourceName(String oxdFormDefResourceName);

}
