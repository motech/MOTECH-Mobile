package com.dreamoval.motech.omi.service;

import java.io.Serializable;

/**
 * Wrapper for passing patient information across the webservice
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 31, 2009
 */
public interface Patient extends Serializable {

    /**
     * @return the name
     */
    String getName();

    /**
     * @return the serialNumber
     */
    String getSerialNumber();

    /**
     * @param name the name to set
     */
    void setName(String name);

    /**
     * @param serialNumber the serialNumber to set
     */
    void setSerialNumber(String serialNumber);

}
