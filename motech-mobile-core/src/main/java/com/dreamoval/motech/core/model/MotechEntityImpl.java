

package com.dreamoval.motech.core.model;

/**
 *
 * @author Henry Sampson
 * Date Created: 30-07-2009
 */
public abstract class MotechEntityImpl implements MotechEntity {
    private Long id;
    private int version = 0;
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

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }
}
