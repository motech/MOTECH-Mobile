/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2010-11 The Trustees of Columbia University in the City of
 * New York and Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, Columbia University, or
 * their respective contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA, COLUMBIA UNIVERSITY
 * AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION
 * USA, COLUMBIA UNIVERSITY OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.motechproject.mobile.omi.manager;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.motechproject.ws.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Formats objects into structured messages for sending
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Feb 19, 2010
 *
 */
public class SMSMessageFormatter {

    private static Logger logger = Logger.getLogger(MessageStoreManagerImpl.class);
    private OMIManager omiManager;
    private String dateFormat;

    private String formatCommunityDefaulterMessage(TreeMap<String, TreeMap<String, List<String>>> defaulters) {

        if (defaulters == null || defaulters.isEmpty()) {
            return "\nNo defaulters found for this clinic";
        }

        String message = "";

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        TreeSet<String> communities = new TreeSet<String>(defaulters.keySet());
        for (String c : communities) {
            TreeMap<String, List<String>> patientDefaulters = defaulters.get(c);
            message += "\n" + c;

            message += formatDefaulterMessage(patientDefaulters);
        }

        return message;
    }

    private String formatDefaulterMessage(TreeMap<String, List<String>> defaulters) {
        String message = "";

        if (defaulters == null || defaulters.isEmpty()) {
            return "\nNo defaulters found for this clinic";
        }

        TreeSet<String> keys = new TreeSet<String>(defaulters.keySet());
        for (String p : keys) {
            List<String> defaultedCareList = defaulters.get(p);

            if (defaultedCareList == null || defaultedCareList.isEmpty()) {
                continue;
            }

            String defaultedCare = StringUtils.join(defaultedCareList.toArray(), ",");

            message += String.format("\n%s (%s)", p, defaultedCare);
        }

        return message;
    }

    public String formatDefaulterMessage(Care[] cares, CareMessageGroupingStrategy groupingStrategy) {
        String result = "";

        // Map of patient to the care the defaulted for
        TreeMap<String, List<String>> patientDefaulters = new TreeMap<String, List<String>>();

        // Map of community to the defaulted patients in that community
        TreeMap<String, TreeMap<String, List<String>>> communityDefaulterMap = new TreeMap<String, TreeMap<String, List<String>>>();

        if (cares != null) {
            for (Care c : cares) {
                for (Patient p : c.getPatients()) {
                    String lastName = (p.getLastName() == null) ? "" : p.getLastName();
                    String preferredName = checkPreferredName(p);

                    String patient = String.format("%s %s, %s", preferredName, lastName, p.getMotechId());

                    if (!patientDefaulters.containsKey(patient)) {
                        patientDefaulters.put(patient, new ArrayList<String>());
                    }

                    List<String> defaultedFor = patientDefaulters.get(patient);
                    defaultedFor.add(c.getName());

                    if (p.getCommunity() != null) {
                        if (!communityDefaulterMap.containsKey(p.getCommunity())) {
                            communityDefaulterMap.put(p.getCommunity(), new TreeMap<String, List<String>>());
                        }

                        TreeMap<String, List<String>> communityDefaulters = communityDefaulterMap.get(p.getCommunity());
                        communityDefaulters.put(patient, defaultedFor);
                    }
                }
            }
        }

        if (groupingStrategy == CareMessageGroupingStrategy.NONE) {
            result = formatDefaulterMessage(patientDefaulters);
        } else {
            result = formatCommunityDefaulterMessage(communityDefaulterMap);
        }

        return ("Defaulter Alerts" + result).trim();
    }

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
            String preferredName = checkPreferredName(p);

            if (type.toLowerCase().trim().equals("recent")) {
                edd = (p.getDeliveryDate() == null) ? "" : dFormat.format(p.getDeliveryDate());
            } else {
                edd = (p.getEstimateDueDate() == null) ? "" : dFormat.format(p.getEstimateDueDate());
            }

            data.add(new NameValuePair("EDD" + num, edd));
            data.add(new NameValuePair("PreferredName" + num, preferredName));
            data.add(new NameValuePair("LastName" + num, p.getLastName()));
            data.add(new NameValuePair("MoTeCHID" + num, p.getMotechId()));
            data.add(new NameValuePair("Community" + num, p.getCommunity()));

            template += "\n<<EDD" + num + ">>: <PreferredName" + num + "> <LastName" + num + "> - <MoTeCHID" + num + "> (<Community" + num + ">)";

            num++;
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    public String formatUpcomingCaresMessage(Patient patient) {
        int num = 0;
        String careDate;
        String message = "";
        String preferredName = "";

        if (patient == null) {
            return "No upcoming care required for this patient";
        }

        preferredName = checkPreferredName(patient);

        if (patient.getCares() == null || patient.getCares().length < 1) {
            preferredName = checkPreferredName(patient);
            return "No upcoming care required for " + preferredName + " " + patient.getLastName();
        }

        String template = "Upcoming care for " + preferredName + " " + patient.getLastName() + ":";

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

    public String formatBulkCaresMessage(Care[] cares, CareMessageGroupingStrategy groupingStrategy) {
        if (cares == null) {
            return "No upcoming care.";
        }

        int careNum = 0;
        String message = "";
        String template = "Upcoming care:";

        SimpleDateFormat dFormat = new SimpleDateFormat(dateFormat);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        for (Care c : cares) {
            if (c.getPatients() != null) {
                String careDate = c.getDate() == null ? "" : dFormat.format(c.getDate());
                data.add(new NameValuePair("Care" + careNum, c.getName()));
                data.add(new NameValuePair("Date" + careNum, careDate));

                template += "\n<Care" + careNum + "> - <Date" + careNum + ">:";

                int patientNum = 0;
                for (Patient p : c.getPatients()) {
                    String lastName = (p.getLastName() == null) ? "" : p.getLastName();
                    String preferredName = checkPreferredName(p);

                    data.add(new NameValuePair("PreferredName" + careNum + patientNum, preferredName));
                    data.add(new NameValuePair("LastName" + careNum + patientNum, lastName));
                    data.add(new NameValuePair("MoTeCHID" + careNum + patientNum, p.getMotechId()));
                    data.add(new NameValuePair("Community" + careNum + patientNum, p.getCommunity()));

                    template += "\n<PreferredName" + careNum + patientNum + "> <LastName" + careNum + patientNum + ">-<MoTeCHID" + careNum + patientNum + "> (<Community" + careNum + patientNum + ">)";

                    patientNum++;
                }
                template += "\n";
                careNum++;
            }
        }
        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

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
            String preferredName = checkPreferredName(p);

            birthDate = p.getBirthDate() == null ? "" : dFormat.format(p.getBirthDate());
            sex = p.getSex() == null ? "" : p.getSex().toString();
            data.add(new NameValuePair("PreferredName" + num, preferredName));
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

        String preferredName = checkPreferredName(patient);

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        data.add(new NameValuePair("MoTeCHID", patient.getMotechId()));
        data.add(new NameValuePair("FirstName", patient.getFirstName()));
        data.add(new NameValuePair("LastName", patient.getLastName()));
        data.add(new NameValuePair("PreferredName", preferredName));
        data.add(new NameValuePair("Sex", sex));
        data.add(new NameValuePair("DoB", dob));
        data.add(new NameValuePair("Age", age));
        data.add(new NameValuePair("Community", patient.getCommunity()));
        data.add(new NameValuePair("PhoneNumber", phone));
        data.add(new NameValuePair("EDD", edd));

        template += "MoTeCH ID <MoTeCHID>\nFirstName=<FirstName>\nLastName=<LastName>";
        template += "\nPreferredName=<PreferredName>";
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
        if (patients == null || patients.length < 1) {
            return "Your request for a new MoTeCH ID could not be completed";
        }

        String template = "Your request for a new MoTeCH ID was successful";
        Set<NameValuePair> data = new HashSet<NameValuePair>();

        int count = 0;

        for (Patient p : patients) {
            String pName = "Baby";
            if (p.getPreferredName() == null || p.getPreferredName().isEmpty()) {
                if (p.getSex() != null) {
                    String sex = (p.getSex() == Gender.FEMALE) ? "Girl" : "Boy";
                    pName += " " + sex;
                }
            } else {
                pName = p.getPreferredName();
            }

            data.add(new NameValuePair("MoTeCHID" + count, p.getMotechId()));
            data.add(new NameValuePair("PreferredName" + count, pName));
            data.add(new NameValuePair("LastName" + count, p.getLastName()));

            template += "\n";
            template += "Name: <PreferredName" + count + "> <LastName" + count + ">\n";
            template += "MoTeCH ID: <MoTeCHID" + count + ">";

            count++;
        }
        String message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    public String formatPatientRegistrationMessage(Patient patient) {
        if (patient == null) {
            return "Your request for a new MoTeCH ID could not be completed";
        }

        String preferredName = checkPreferredName(patient);

        String message = "";
        String template = "";

        Set<NameValuePair> data = new HashSet<NameValuePair>();

        data.add(new NameValuePair("MoTeCHID", patient.getMotechId()));
        data.add(new NameValuePair("PreferredName", preferredName));
        data.add(new NameValuePair("LastName", patient.getLastName()));

        template += "Your request for a new MoTeCH ID was successful\n";
        template += "Name: <PreferredName> <LastName>\n";
        template += "MoTeCH ID: <MoTeCHID>";

        message = omiManager.createMessageStoreManager().parseTemplate(template, data);
        return message;
    }

    public String checkPreferredName(Patient patient) {
        String preferredName = patient.getPreferredName();
        if (preferredName == null || preferredName.isEmpty()) {
            preferredName = "Patient";

            if (patient.getBirthDate() != null) {
                long timeDiff = patient.getBirthDate().getTime() - new Date().getTime();
                long dayDiff = timeDiff / (1000 * 60 * 60 * 24);

                if (dayDiff < 1095) {
                    preferredName = "Baby ";
                } else {
                    preferredName = "";
                }

                if (dayDiff < 6570) {
                    preferredName += (patient.getSex() == Gender.FEMALE) ? "Girl" : "Boy";
                } else {
                    preferredName = (patient.getSex() == Gender.FEMALE) ? "Woman" : "Man";
                }
            }
        }
        return preferredName;
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
