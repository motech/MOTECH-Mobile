/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface Language extends MotechEntity {

    String getCode();
    String getName();
    String getDescription();

    void setCode(String code);
    void setName(String name);
    void setDescription(String description);
}
