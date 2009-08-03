/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.model;

/**
 *
 * @author Henry Sampson
 * Date Created: 30-07-2009
 */
public abstract class MotechEntityImpl implements MotechEntity {
    private Long id;

    /**
     * @see {@link com.dreamoval..motech.core.model.MotechEntity#getId()}
     */
    public Long getId() {
        return id;
    }

    /**
     * @see {@link com.dreamoval..motech.core.model.MotechEntity#setId()}
     */
    public void setId(Long id) {
        this.id = id;
    }
}
