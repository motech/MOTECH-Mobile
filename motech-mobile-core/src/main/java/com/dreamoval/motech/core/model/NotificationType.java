/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface NotificationType extends MotechEntity {

    /**
     * @return the description
     */
    String getDescription();

    /**
     * @return the name
     */
    String getName();

    /**
     * @param description the description to set
     */
    void setDescription(String description);

    /**
     * @param name the name to set
     */
    void setName(String name);

}
