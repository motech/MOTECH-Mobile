package org.motechproject.mobile.core.model;

/**
 * MotechEntityImpl class is an implementation of MotechEntity which is actually mapped in
 * hibernate.It's the like the super class from which every pojo inherits ids and version properties
 * @author Henry Sampson
 * Date Created: 30-07-2009
 */
public abstract class MotechEntityImpl implements MotechEntity {

    private Long id;
    private int version = -1;

    /**
     * @see {@link org.motechproject.mobile.core.model.MotechEntity#getId()}
     */
    public Long getId() {
        return id;
    }

    /**
     * @see {@link org.motechproject.mobile.core.model.MotechEntity#setId()}
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
