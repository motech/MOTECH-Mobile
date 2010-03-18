package org.motechproject.mobile.core.model;

import java.io.Serializable;

/**
 * <p>This represents the ancestor of all domain classes.
 * Conceptually will hold the uniqueness of all domain
 * classes</p>
 *
 * @author Henry Sampson
 * Date Created: 31-07-2009
 */
public interface MotechEntity extends Serializable {

    /**
     * @return the id
     */
    public Long getId();

    /**
     * @param id the id to set
     */
    public void setId(Long id);

    /**
     * @return the version
     */
    int getVersion();

    /**
     * @param version the version to set
     */
    void setVersion(int version);
}
