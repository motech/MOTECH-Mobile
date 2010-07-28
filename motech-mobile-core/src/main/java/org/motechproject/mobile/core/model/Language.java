package org.motechproject.mobile.core.model;

import java.util.Set;

/**
 * Language interface is a POJO to hold Language information for data storage and manipulation
 * Date: Sep 27, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface Language {

     /**
     *
     * @param id the id to set
     */
    public void setId(Long id);

    /**
     *
     * @return id to get
     */
    public Long getId();
    
    public String getCode();

    public String getName();

    public String getDescription();

    public Set<MessageRequest> getMessageRequests();

    public void setMessageRequests(Set<MessageRequest> messageRequests);

    public void setCode(String code);

    public void setName(String name);

    public void setDescription(String description);

    /**
     * Helper method to display string value of all properties of the object
     * @return formated string value of all properties
     */
    @Override
    public String toString();
}
