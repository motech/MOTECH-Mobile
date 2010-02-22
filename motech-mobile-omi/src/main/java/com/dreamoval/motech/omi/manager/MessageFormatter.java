/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import org.motechproject.ws.Care;
import org.motechproject.ws.Patient;

/**
 *
 * @author user
 */
public interface MessageFormatter {

    /**
     * Constructs a formatted patient care defaulter message
     * @param care object containing patient information
     * @return the formatted message
     */
    String formatDefaulterMessage(Care care);

    /**
     * Constructs a formatted patient delivery schedule message
     * @param type of schedule
     * @param patients list of patients within schedule
     * @return the formatted message
     */
    String formatDeliveriesMessage(String type, Patient[] patients);

    /**
     * Constructs a patient query response message
     * @param patients list of patients matching query
     * @return the formatted message
     */
    String formatMatchingPatientsMessage(Patient[] patients);

    /**
     * Constructs a formatted patient details message
     * @param patient object containing patient details
     * @return the formatted message
     */
    String formatPatientDetailsMessage(Patient patient);

    /**
     * Constructs a formatted patient upcoming care message
     * @param patient object containing list of upcoming care
     * @return the formatted message
     */
    String formatUpcomingCaresMessage(Patient patient);

    /**
     * @param dateFormat the dateFormat to set
     */
    void setDateFormat(String dateFormat);

    /**
     * @param omiManager the omiManager to set
     */
    void setOmiManager(OMIManager omiManager);

}
