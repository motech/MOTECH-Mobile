/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.omi.manager;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.motechproject.ws.Care;
import org.motechproject.ws.Gender;
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
    public String formatDefaulterMessage(Care care) {
        int num = 0;
        String message = "";

        if (care == null) {
            return "No defaulters found for this clinic";
        } else if (care.getPatients().length < 0) {
            return "No " + care.getName() + " defaulters found for this clinic";
        }

        String template = care.getName() + " Defaulters:";

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for (Patient p : care.getPatients()) {
            data.add(new NameValuePair("PreferredName" + num, p.getPreferredName()));
            data.add(new NameValuePair("LastName" + num, p.getLastName()));
            data.add(new NameValuePair("MoTeCHID" + num, p.getMotechId()));
            data.add(new NameValuePair("Community" + num, p.getCommunity()));

            template += "\n<PreferredName" + num + "> <LastName" + num + ">-<MoTeCHID" + num + "> (<Community" + num + ">)";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    public String formatDefaulterMessage(Care[] cares) {
        String result = "";

        for (Care c : cares) {
            result += formatDefaulterMessage(c) + "\n\n";
        }
        return result.trim();
    }

    /**
     *
     * @param see MessageFormatter.formatDeliveriesMessage
     */
    public String formatDeliveriesMessage(String type, Patient[] patients) {
        int num = 0;
        String edd;
        String message = "";

        if (patients == null || patients.length < 1) {
            return "No " + type + " deliveries for this clinic";
        }

        String template = type + " Deliveries:";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for (Patient p : patients) {
            if (type.toLowerCase().trim().equals("recent")) {
                edd = (p.getDeliveryDate() == null) ? "" : dFormat.format(p.getDeliveryDate());
            } else {
                edd = (p.getEstimateDueDate() == null) ? "" : dFormat.format(p.getEstimateDueDate());
            }

            data.add(new NameValuePair("EDD" + num, edd));
            data.add(new NameValuePair("PreferredName" + num, p.getPreferredName()));
            data.add(new NameValuePair("LastName" + num, p.getLastName()));
            data.add(new NameValuePair("MoTeCHID" + num, p.getMotechId()));
            data.add(new NameValuePair("Community" + num, p.getCommunity()));

            template += "\n<<EDD" + num + ">>: <PreferredName" + num + "> <LastName" + num + "> - <MoTeCHID" + num + "> (<Community" + num + ">)";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    public String formatUpcomingDeliveriesMessage(Patient[] patients) {
        return formatDeliveriesMessage("Upcoming", patients);
    }

    public String formatRecentDeliveriesMessage(Patient[] patients) {
        return formatDeliveriesMessage("Recent", patients);
    }

    public String formatOverdueDeliveriesMessage(Patient[] patients) {
        return formatDeliveriesMessage("Overdue", patients);
    }

    /**
     *
     * @param see MessageFormatter.formatUpcomingCaresMessage
     */
    public String formatUpcomingCaresMessage(Patient patient) {
        int num = 0;
        String careDate;
        String message = "";

        if (patient == null) {
            return "No upcoming care required for this patient";
        } else if (patient.getCares() == null || patient.getCares().length < 1) {
            return "No upcoming care required for " + patient.getPreferredName() + " " + patient.getLastName();
        }

        String template = "Upcoming care for " + patient.getPreferredName() + patient.getLastName() + ":";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for (Care c : patient.getCares()) {
            careDate = c.getDate() == null ? "" : dFormat.format(c.getDate());
            data.add(new NameValuePair("Care" + num, c.getName()));
            data.add(new NameValuePair("Date" + num, careDate));

            template += "\n<Care" + num + ">:<Date" + num + ">";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    /**
     *
     * @param see MessageFormatter.formatMatchingPatientsMessage
     */
    public String formatMatchingPatientsMessage(Patient[] patients) {
        int num = 0;

        if (patients == null || patients.length < 1) {
            return "No matching patients found";
        }

        String message = "";
        String sex = "";
        String birthDate = "";
        String template = "Matching Patients:";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for (Patient p : patients) {
            birthDate = p.getBirthDate() == null ? "" : dFormat.format(p.getBirthDate());
            sex = p.getSex() == null ? "" : p.getSex().toString();
            data.add(new NameValuePair("PreferredName" + num, p.getPreferredName()));
            data.add(new NameValuePair("LastName" + num, p.getLastName()));
            data.add(new NameValuePair("DoB" + num, birthDate));
            data.add(new NameValuePair("Sex" + num, sex));
            data.add(new NameValuePair("Community" + num, p.getCommunity()));
            data.add(new NameValuePair("MoTeCHID" + num, p.getMotechId()));

            template += "\n<PreferredName" + num + "> <LastName" + num + "> <DoB" + num + "> <Sex" + num + "> <Community" + num + "> MoTeCH ID:<MoTeCHID" + num + ">";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    /**
     *
     * @param see MessageFormatter.formatPatientDetailsMessage
     */
    public String formatPatientDetailsMessage(Patient patient) {
        if (patient == null) {
            return "No matching patients found";
        }

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        String message = "";
        String template = "";
        String edd = patient.getEstimateDueDate() == null ? "" : dFormat.format(patient.getEstimateDueDate());
        String dob = patient.getBirthDate() == null ? "" : dFormat.format(patient.getBirthDate());
        String age = patient.getAge() == null ? "" : patient.getAge().toString();
        String sex = patient.getSex() == null ? "" : patient.getSex().toString();
        String phone = patient.getPhoneNumber() == null ? "" : patient.getPhoneNumber();


        Set<NameValuePair> data = new HashSet<NameValuePair>();

        data.add(new NameValuePair("MoTeCHID", patient.getMotechId()));
        data.add(new NameValuePair("FirstName", patient.getFirstName()));
        data.add(new NameValuePair("LastName", patient.getLastName()));
        data.add(new NameValuePair("PreferredName", patient.getPreferredName()));
        data.add(new NameValuePair("Sex", sex));
        data.add(new NameValuePair("DoB", dob));
        data.add(new NameValuePair("Age", age));
        data.add(new NameValuePair("Community", patient.getCommunity()));
        data.add(new NameValuePair("PhoneNumber", phone));
        data.add(new NameValuePair("EDD", edd));

        template += "MoTeCH ID <MoTeCHID>\nFirstName=<FirstName>\nLastName=<LastName>";
        template += patient.getPreferredName().equals(patient.getFirstName()) ? "" : "\nPreferredName=<PreferredName>";
        template += sex.isEmpty() ? sex : "\nSex=<Sex>";
        template += dob.isEmpty() ? dob : "\nDoB=<DoB>";
        template += age.isEmpty() ? age : "\nAge=<Age>";
        template += "\nCommunity=<Community>\nPhoneNumber=";
        template += phone.isEmpty() ? "None" : "<PhoneNumber>";
        template += edd.isEmpty() ? edd : "\nEDD=<EDD>";

        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    public String formatBabyRegistrationMessage(Patient[] patients) {
        if(patients == null || patients.length < 1)
            return "Your request for a new MoTeCH ID could not be completed";

        String template = "Your request for a new MoTeCH ID was successful";
        Set<NameValuePair> data = new HashSet<NameValuePair>();

        int count = 0;

        for(Patient p : patients){
            String pName = "Baby";
            if(p.getPreferredName() == null || p.getPreferredName().isEmpty()){
                if(p.getSex() != null){
                    String sex = (p.getSex() == Gender.FEMALE) ? "Girl" : "Boy";
                    pName += " " + sex;
                }
            }
            else
                pName = p.getPreferredName();

            data.add(new NameValuePair("MoTeCHID"+count, p.getMotechId()));
            data.add(new NameValuePair("PreferredName"+count, pName));
            data.add(new NameValuePair("LastName"+count, p.getLastName()));

            template += "\n";
            template += "Name: <PreferredName"+count+"> <LastName"+count+">\n";
            template += "MoTeCH ID: <MoTeCHID"+count+">";

            count++;
        }
        String message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    public String formatPatientRegistrationMessage(Patient patient) {
        if (patient == null) {
            return "Your request for a new MoTeCH ID could not be completed";
        }

        String message = "";
        String template = "";


        Set<NameValuePair> data = new HashSet<NameValuePair>();

        data.add(new NameValuePair("MoTeCHID", patient.getMotechId()));
        data.add(new NameValuePair("PreferredName", patient.getPreferredName()));
        data.add(new NameValuePair("LastName", patient.getLastName()));

        template += "Your request for a new MoTeCH ID was successful\n";
        template += "Name: <PreferredName> <LastName>\n";
        template += "MoTeCH ID: <MoTeCHID>";

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
