/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.omi.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        String edd;
        String message = "";
        String template = type + " Deliveries:";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for(Patient p : patients){
            edd = (p.getEstimateDueDate() == null) ? "" : dFormat.format(p.getEstimateDueDate());
            data.add(new NameValuePair("EDD"+num, edd));
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
        String careDate;
        String message = "";
        String template = "Upcoming care for "+patient.getPreferredName()+patient.getLastName()+":";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for(Care c : patient.getCares()){
            careDate = c.getDate() == null ? "" : dFormat.format(c.getDate());
            data.add(new NameValuePair("Care"+num, c.getName()));
            data.add(new NameValuePair("Date"+num, careDate));

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
        String sex = "";
        String birthDate = "";
        String template = "Matching Patients:";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for(Patient p : patients){
            birthDate = p.getBirthDate() == null ? "" : dFormat.format(p.getBirthDate());
            sex = p.getSex() == null ? "" : p.getSex().toString();
            data.add(new NameValuePair("PreferredName"+num, p.getPreferredName()));
            data.add(new NameValuePair("LastName"+num, p.getLastName()));
            data.add(new NameValuePair("DoB"+num, birthDate));
            data.add(new NameValuePair("Sex"+num, sex));
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
        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);
        
        String message = "";
        String template = "";
        String edd = patient.getEstimateDueDate() == null ? "" : dFormat.format(patient.getEstimateDueDate());
        String dob = patient.getBirthDate() == null ? "" : dFormat.format(patient.getBirthDate());
        String age = patient.getAge() == null ? "" : patient.getAge().toString();
        String sex = patient.getSex() == null ? "" : patient.getSex().toString();


        Set<NameValuePair> data = new HashSet<NameValuePair>();

        data.add(new NameValuePair("MoTeCHID", patient.getMotechId()));
        data.add(new NameValuePair("FirstName", patient.getFirstName()));
        data.add(new NameValuePair("LastName", patient.getLastName()));
        data.add(new NameValuePair("PreferredName", patient.getPreferredName()));
        data.add(new NameValuePair("Sex", sex));
        data.add(new NameValuePair("DoB", dob));
        data.add(new NameValuePair("Age", age));
        data.add(new NameValuePair("Community", patient.getCommunity()));
        data.add(new NameValuePair("PhoneNumber", patient.getPhoneNumber()));
        data.add(new NameValuePair("EDD", edd));

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
