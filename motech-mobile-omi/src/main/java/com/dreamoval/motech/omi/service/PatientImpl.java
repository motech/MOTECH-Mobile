/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.service;

/**
 * An implementation of the Patient interface
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Jul 31, 2009
 *
 */
public class PatientImpl implements Patient {
    private String name;
    private String serialNumber;

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
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
