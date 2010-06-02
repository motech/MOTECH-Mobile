/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

/**
 *
 * @author user
 */
public class SubField {
    private String parentField, fieldName, replaceOn;

    /**
     * @return the parentField
     */
    public String getParentField() {
        return parentField;
    }

    /**
     * @param parentField the parentField to set
     */
    public void setParentField(String parentField) {
        this.parentField = parentField;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * @param fieldName the fieldName to set
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * @return the replaceOn
     */
    public String getReplaceOn() {
        return replaceOn;
    }

    /**
     * @param replaceOn the replaceOn to set
     */
    public void setReplaceOn(String replaceOn) {
        this.replaceOn = replaceOn;
    }
}
