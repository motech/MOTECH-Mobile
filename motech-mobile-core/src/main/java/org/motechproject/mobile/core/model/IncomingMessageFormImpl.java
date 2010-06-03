package org.motechproject.mobile.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
 * IncomingMessageFormImpl is the implementation of the IncomingMessageForm interface
 * which is the actually mapped class in the hibernate.It provides properties to handle IncomingMessageForm operations
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public class IncomingMessageFormImpl extends MotechEntityImpl implements IncomingMessageForm {

    private IncomingMessageFormDefinition incomingMsgFormDefinition;
    private Date dateCreated;
    private Date lastModified;
    private IncMessageFormStatus messageFormStatus;
    private Map<String,IncomingMessageFormParameter> incomingMsgFormParameters = new HashMap<String,IncomingMessageFormParameter>();
    private List<String> errors = new ArrayList<String>();
    
    /**
     * @return the incomingMsgFormDefinition
     */
    public IncomingMessageFormDefinition getIncomingMsgFormDefinition() {
        return incomingMsgFormDefinition;
    }

    /**
     * @param incomingMsgFormDefinition the incomingMsgFormDefinition to set
     */
    public void setIncomingMsgFormDefinition(IncomingMessageFormDefinition incomingMsgFormDefinition) {
        this.incomingMsgFormDefinition = incomingMsgFormDefinition;
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
     * @return the messageStatus
     */
    public IncMessageFormStatus getMessageFormStatus() {
        return messageFormStatus;
    }

    /**
     * @param messageFormStatus the messageStatus to set
     */
    public void setMessageFormStatus(IncMessageFormStatus messageFormStatus) {
        this.messageFormStatus = messageFormStatus;
    }

    /**
     * @return the incomingMsgFormParameters
     */
    public Map<String, IncomingMessageFormParameter> getIncomingMsgFormParameters() {
        return incomingMsgFormParameters;
    }

    /**
     * @param incomingMsgFormParameters the incomingMsgFormParameters to set
     */
    public void setIncomingMsgFormParameters(Map<String, IncomingMessageFormParameter> incomingMsgFormParameters) {
        this.incomingMsgFormParameters = incomingMsgFormParameters;
    }

    /**
     * Helper method to add IncomingMessageFormParameter to IncomingMessageForm
     * @param key key of the map
     * @param param the IncomingMessageFormParameter to add
     */
    public void addIncomingMsgFormParam(String key, IncomingMessageFormParameter param){
        this.incomingMsgFormParameters.put(key, param);
        param.setIncomingMsgForm(this);
    }
    /**
     * Helper method to remover IncomingMessageFormParameter to IncomingMessageForm
     * @param key key of the map
     * @param param the IncomingMessageFormParameter to remove
     */
     public void removeIncomingMsgFormParm(String key){
         if(this.incomingMsgFormParameters.containsKey(key)){
             this.incomingMsgFormParameters.remove(key);
         }
     }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");

       if(this != null) {
           sb.append((this.getId()!= null) ? "key=Id value=" + this.getId().toString() : "Id is null ");
           sb.append(newLine);

           sb.append((this.incomingMsgFormDefinition != null) ? "key=IncomingMessageFormDefinition.Id value=" + this.incomingMsgFormDefinition.getId() : "IncomingMessageFormDefinition.Id is null ");
           sb.append(newLine);

           sb.append((this.incomingMsgFormParameters.isEmpty() ) ? "key=IncomingMessageFormParameters length=" + Integer.toString(this.incomingMsgFormParameters.size()) : "IncomingMessageFormParameters is empty ");
           sb.append(newLine);

           for(Iterator it =this.incomingMsgFormParameters.entrySet().iterator(); it.hasNext();){
               Map.Entry  entry = (Map.Entry) it.next();
               String key = (String) entry.getKey();
               IncomingMessageFormParameter  resp = (IncomingMessageFormParameter) entry.getValue();

               sb.append((resp != null ) ? "key="+key+" value=" + resp.getId().toString() : "IncomingMessageFormParameter.Id is null ");
               sb.append(newLine);
           }


           sb.append((this.dateCreated != null) ? "key=dateCreated value=" + this.dateCreated.toString() : "dateCreated is null ");
           sb.append(newLine);
           sb.append((this.lastModified != null) ? "key=lastModified value=" + this.lastModified.toString() : "lastModified is null ");
           sb.append(newLine);
           sb.append((this.messageFormStatus != null) ? "key=messageFormStatus value=" + this.messageFormStatus.toString() : "messageFormStatus is null ");
           sb.append(newLine);

           return sb.toString();

       } else {
           return "Object is null";
       }


    }

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
