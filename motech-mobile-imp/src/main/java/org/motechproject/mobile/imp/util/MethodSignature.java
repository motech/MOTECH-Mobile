/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import java.util.Map;

/**
 *
 * @author user
 */
public class MethodSignature {
    private String methodName;
    private Map<String, Class> methodParams;
    private Class returnType = null;

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * @return the returnType
     */
    public Class getReturnType() {
        return returnType;
    }

    /**
     * @param returnType the returnType to set
     */
    public void setReturnType(Class returnType) {
        this.returnType = returnType;
    }

    /**
     * @return the methodParams
     */
    public Map<String, Class> getMethodParams() {
        return methodParams;
    }

    /**
     * @param methodParams the methodParams to set
     */
    public void setMethodParams(Map<String, Class> methodParams) {
        this.methodParams = methodParams;
    }
}
