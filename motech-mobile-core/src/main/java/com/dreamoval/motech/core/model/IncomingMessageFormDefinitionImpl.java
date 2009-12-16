package com.dreamoval.motech.core.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * IncomingMessageFormDefinitionImpl is the implementation of the IncomingMessageFormDefinitionImpl interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageFormDefinitionImpl operations
 *
 * Date: Dec 02, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageFormDefinitionImpl extends MotechEntityImpl implements IncomingMessageFormDefinition {

    private String formCode;
    private Date dateCreated;
    private Date lastModified;
    private Set<IncomingMessageFormParameterDefinition> incomingMsgParamDefinitions = new HashSet<IncomingMessageFormParameterDefinition>();

    /**
     * @return the form_code
     */
    public String getFormCode() {
        return formCode;
    }

    /**
     * @param form_code the form_code to set
     */
    public void setFormCode(String form_code) {
        this.formCode = form_code;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the lastModified
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return the incomingMsgParamDefinition
     */
    public Set<IncomingMessageFormParameterDefinition> getIncomingMsgParamDefinitions() {
        return incomingMsgParamDefinitions;
    }

    /**
     * @param incomingMsgParamDefinition the incomingMsgParamDefinition to set
     */
    public void setIncomingMsgParamDefinitions(Set<IncomingMessageFormParameterDefinition> incomingMsgParamDefinition) {
        this.incomingMsgParamDefinitions = incomingMsgParamDefinition;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");
       if(this != null) {
           sb.append((this.getId()!= null) ? "key=Id value=" + this.getId().toString() : "Id is null ");
           sb.append(newLine);
           sb.append((this.formCode != null) ? "key=formCode value=" + this.formCode : "formCode is null  ");
           sb.append(newLine);
           
           for(Iterator it =this.incomingMsgParamDefinitions.iterator(); it.hasNext();){
               IncomingMessageFormParameterDefinition resp = (IncomingMessageFormParameterDefinition) it.next();
               sb.append((resp != null ) ? "key=IncomingMessageFormParameterDefinition.Id value=" + resp.getId().toString() : "IncomingMessageFormParameterDefinition.Id is null ");
               sb.append(newLine);
           }
        
           sb.append((this.dateCreated != null) ? "key=dateCreated value=" + this.dateCreated.toString() : "dateCreated is null ");
           sb.append(newLine);
          

           return sb.toString();

       } else {
           return "Object is null";
       }


    }

}
