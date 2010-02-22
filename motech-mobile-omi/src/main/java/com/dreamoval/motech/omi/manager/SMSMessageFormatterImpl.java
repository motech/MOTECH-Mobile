/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.motechproject.ws.Care;
import org.motechproject.ws.NameValuePair;
import org.motechproject.ws.Patient;

/**
 * Formats objects into structured messages for sending
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Feb 19, 2010
 *
 */
public class SMSMessageFormatterImpl implements MessageFormatter {
    private static Logger logger = Logger.getLogger(MessageStoreManagerImpl.class);
    private OMIManager omiManager;
    private String dateFormat;

    /**
     *
     * @param see MessageFormatter.formatDefaulterMessage
     */
    public String formatDefaulterMessage(Care care){
        int num = 0;
        String message = "";
        String template = care.getName() + " Defaulters:";

        Set<NameValuePair> data = new HashSet<NameValuePair>();
        
        for(Patient p : care.getPatients()){
            data.add(new NameValuePair("PreferredName"+num, p.getPreferredName()));
            data.add(new NameValuePair("LastName"+num, p.getLastName()));
            data.add(new NameValuePair("MoTeCHID"+num, p.getMotechId()));
            data.add(new NameValuePair("Community"+num, p.getCommunity()));

            template += "\n<PreferredName"
                    + num
                    + "> <LastName"
                    + num
                    + ">-<MoTeCHID"
                    + num
                    + "> (<Community"
                    + num
                    + ">)";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    /**
     *
     * @param see MessageFormatter.formatDeliveriesMessage
     */
    public String formatDeliveriesMessage(String type, Patient[] patients){
        int num = 0;
        String message = "";
        String template = type + " Deliveries:";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for(Patient p : patients){
            data.add(new NameValuePair("EDD"+num, dFormat.format(p.getEstimateDueDate())));
            data.add(new NameValuePair("PreferredName"+num, p.getPreferredName()));
            data.add(new NameValuePair("LastName"+num, p.getLastName()));
            data.add(new NameValuePair("MoTeCHID"+num, p.getMotechId()));
            data.add(new NameValuePair("Community"+num, p.getCommunity()));

            template += "\n<EDD"
                    + num
                    + ">:<PreferredName"
                    + num
                    + "> <LastName"
                    + num
                    + ">-<MoTeCHID"
                    + num
                    + "> (<Community"
                    + num
                    + ">)";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    /**
     *
     * @param see MessageFormatter.formatUpcomingCaresMessage
     */
    public String formatUpcomingCaresMessage(Patient patient){
        int num = 0;
        String message = "";
        String template = "Upcoming care for "+patient.getPreferredName()+patient.getLastName()+":";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for(Care c : patient.getCares()){
            data.add(new NameValuePair("Care"+num, c.getName()));
            data.add(new NameValuePair("Date"+num, dFormat.format(c.getDate())));

            template += "\n<Care"
                    + num
                    + ">:<Date"
                    + num
                    + ">";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    /**
     *
     * @param see MessageFormatter.formatMatchingPatientsMessage
     */
    public String formatMatchingPatientsMessage(Patient[] patients){
        int num = 0;
        String message = "";
        String template = "Matching Patients:";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for(Patient p : patients){
            data.add(new NameValuePair("PreferredName"+num, p.getPreferredName()));
            data.add(new NameValuePair("LastName"+num, p.getLastName()));
            data.add(new NameValuePair("DoB"+num, dFormat.format(p.getEstimateDueDate())));
            data.add(new NameValuePair("Sex"+num, p.getSex().toString()));
            data.add(new NameValuePair("Community"+num, p.getCommunity()));
            data.add(new NameValuePair("MoTeCHID"+num, p.getMotechId()));

            template += "\n<PreferredName"
                    + num
                    + "> <LastName"
                    + num
                    + "> <DoB"
                    + num
                    + "> <Sex"
                    + num
                    + "> <Community"
                    + num
                    + "> MoTeCH ID:<MoTeCHID"
                    + num
                    + ">";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    /**
     *
     * @param see MessageFormatter.formatPatientDetailsMessage
     */
    public String formatPatientDetailsMessage(Patient patient){
        String message = "";
        String template = "";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        data.add(new NameValuePair("MoTeCHID", patient.getMotechId()));
        data.add(new NameValuePair("FirstName", patient.getFirstName()));
        data.add(new NameValuePair("LastName", patient.getLastName()));
        data.add(new NameValuePair("PreferredName", patient.getPreferredName()));
        data.add(new NameValuePair("Sex", patient.getSex().toString()));
        data.add(new NameValuePair("DoB", dFormat.format(patient.getEstimateDueDate())));
        data.add(new NameValuePair("Age", patient.getAge().toString()));
        data.add(new NameValuePair("Community", patient.getCommunity()));
        data.add(new NameValuePair("PhoneNumber", patient.getPhoneNumber()));
        data.add(new NameValuePair("EDD", dFormat.format(patient.getEstimateDueDate())));

        template += "MoTeCH ID<MoTeCHID>\nFirstName=<FirstName>\nLastName=<LastName>\nPreferredName=<PreferredName>\nSex=<Sex>\nDoB=<DoB>\nAge=<Age>\nCommunity=<Community>\nPhoneNumber=<PhoneNumber>\nEDD=<EDD>";

        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    /**
     * @param omiManager the omiManager to set
     */
    public void setOmiManager(OMIManager omiManager) {
        this.omiManager = omiManager;
    }

    /**
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
