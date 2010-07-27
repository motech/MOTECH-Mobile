package org.motechproject.mobile.core.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * LanguageImpl class is an implementation of the Language interface which is actuaally
 * mapped in hibernate.It provides properties to handle Language operations
 * Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class LanguageImpl implements Language {

    private String id;
    private String code;
    private String name;
    private String description;
    private Set<MessageRequest> messageRequests = new HashSet<MessageRequest>();

    public LanguageImpl() {
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the messageRequests
     */
    public Set<MessageRequest> getMessageRequests() {
        return messageRequests;
    }

    /**
     * @param messageRequests the messageRequests to set
     */
    public void setMessageRequests(Set<MessageRequest> messageRequests) {
        this.messageRequests = messageRequests;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = System.getProperty("line.separator");

        if (this != null) {
            sb.append((this.getId() != null) ? "key=Id--------------------value=" + this.getId().toString() : "Id is null ");
            sb.append(newLine);
            sb.append((this.code != null) ? "key=code------------------value=" + this.code : "code is null  ");
            sb.append(newLine);
            sb.append((this.name != null) ? "key=recipientsNumber value=" + this.name : "name is null ");
            sb.append(newLine);
            sb.append((this.description != null) ? "key=description value=" + this.description : "description is null ");
            sb.append(newLine);

            sb.append((this.messageRequests.isEmpty()) ? "key=messageRequests length=" + Integer.toString(this.messageRequests.size()) : "responseDetails is empty ");
            sb.append(newLine);

            for (Iterator it = this.messageRequests.iterator(); it.hasNext();) {
                MessageRequest resp = (MessageRequest) it.next();
                sb.append((resp != null) ? "key=MessageRequest.Id value=" + resp.getId().toString() : "MessageRequest.Id is null ");
                sb.append(newLine);
            }


            return sb.toString();

        } else {
            return "Object is null";
        }


    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
