/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.serivce.oxd;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

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
     * <p>Sets the resource names for the Exported OXD Form definitions</p>
     *
     * @param oxdFormDefResource the oxdFormDefResourceName to set
     */
    void addOxdFormDefResources(String oxdFormDefResource);

    /**
     * @return the oxdFormDefResources
     */
    Set<String> getOxdFormDefResources();

    /**
     * <p>Sets the resource names for the Exported OXD Form definitions</p>
     *
     * @param oxdFormDefResources the oxdFormDefResourceNames to set
     */
    void setOxdFormDefResources(Set<String> oxdFormDefResources);
}
