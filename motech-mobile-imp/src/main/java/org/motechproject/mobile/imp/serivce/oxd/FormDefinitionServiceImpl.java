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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.serivce.oxd;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

/**
 * @author Henry Sampson (henry@dreamoval.com) and Brent Atkinson
 *         Date Created: Mar 3, 2010
 */
public class FormDefinitionServiceImpl implements FormDefinitionService,
        StudyDefinitionService, UserDefinitionService {

    private List<Object[]> users;
    private PasswordEncoder encoder;
    private List<String> studies;
    private Map<String, List<Integer>> studyForms;
    private Map<Integer, String> formMap;
    private Set<String> oxdFormDefResources;

    public FormDefinitionServiceImpl() {
        studies = new ArrayList<String>();
        studyForms = new LinkedHashMap<String, List<Integer>>();
        formMap = new HashMap<Integer, String>();
    }

    public void init() throws Exception {
        XmlPullParserFactory xmlPullParserFactory = getXMLPullParserFactory();
        XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
        for (String resource : getOxdFormDefResources()) {
            new StudyFormsParser(xmlPullParserFactory, xmlPullParser, resource).invoke();
        }
    }

    private XmlPullParserFactory getXMLPullParserFactory() throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory;
    }

    /**
     * @return a map of xml xforms definitions, keyed on their definition ids
     */
    public Map<Integer, String> getXForms() throws Exception {


        return formMap;
    }

    /**
     * @return the oxdFormDefResources
     */
    public Set<String> getOxdFormDefResources() {
        return oxdFormDefResources;
    }

    /**
     * <p>Sets the resource names for the Exported OXD Form definitions</p>
     *
     * @param oxdFormDefResources the oxdFormDefResourceNames to set
     */
    public void setOxdFormDefResources(Set<String> oxdFormDefResources) {
        this.oxdFormDefResources = oxdFormDefResources;
    }

    /**
     * <p>Sets the resource names for the Exported OXD Form definitions</p>
     *
     * @param oxdFormDefResource the oxdFormDefResourceName to set
     */
    public void addOxdFormDefResources(String oxdFormDefResource) {
        if (oxdFormDefResources == null) {
            oxdFormDefResources = new HashSet<String>();
        }
        oxdFormDefResources.add(oxdFormDefResource);
    }

    /**
     * Returns the list of studies. The studies are stored in the same order
     * that they are read in. This means you should be able to use the list to
     * get the proper study based on the numerical index of the study.
     */
    public List<Object[]> getStudies() {

        List<Object[]> result = new ArrayList<Object[]>();

        if (studies == null) {
            return Collections.emptyList();
        }

        for (int studyidx = 0; studyidx < studies.size(); studyidx++) {
            result.add(new Object[]{studyidx, studies.get(studyidx)});
        }

        return result;
    }

    /**
     * Used to set the password encoder implementation.
     *
     * @param encoder
     */
    public void setPasswordEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * Returns the list of openxdata users.
     */
    public List<Object[]> getUsers() {
        return users;
    }

    /**
     * Sets the list of openxdata users.
     *
     * @return
     */
    public void setUsers(List<String[]> users) {

        this.users = new ArrayList<Object[]>();

        int i = 0;
        for (Object[] user : users) {
            Integer userId = ++i;
            String name = (String) user[0];
            String password = (String) user[1];
            String salt = (String) user[2];
            String encodedPassword = encoder.encodePassword(password, salt);
            Object[] completeUser = {userId, name, encodedPassword, salt};
            this.users.add(completeUser);
        }
    }

    /**
     * Returns the name of the study for the given id
     */
    public String getStudyName(int id) {
        return studies.get(id);
    }

    /**
     * Returns the list of forms for the specified study.
     */
    public List<String> getStudyForms(int studyId) {
        String studyName = studies.get(studyId);
        List<Integer> forms = studyForms.get(studyName);
        List<String> result = new ArrayList<String>();

        for (Integer formId : forms) {
            result.add(formMap.get(formId));
        }

        return result;
    }

    private class StudyFormsParser {
        private XmlPullParserFactory xmlPullParserFactory;
        private XmlPullParser xmlPullParser;
        private String resource;
        private boolean isXform = false;
        private boolean isVersionText = false;
        private String studyName;
        private String currentXFormDefinition;

        public StudyFormsParser(XmlPullParserFactory xmlPullParserFactory, XmlPullParser xmlPullParser, String resource) {
            this.xmlPullParserFactory = xmlPullParserFactory;
            this.xmlPullParser = xmlPullParser;
            this.resource = resource;
        }

        public void invoke() throws XmlPullParserException, IOException {
            xmlPullParser.setInput(new InputStreamReader(getClass().getResourceAsStream(resource)));
            for (int eventType = xmlPullParser.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = xmlPullParser.next()) {

                if (eventType == XmlPullParser.START_TAG) {
                    handleStartTag();
                } else if (eventType == XmlPullParser.END_TAG) {
                    handleEndTag();
                } else if (eventType == XmlPullParser.TEXT) {
                    if (isXform) {
                        processXForm();
                    }
                }
            }
        }

        private void processXForm() throws XmlPullParserException, IOException {
            if (isVersionText) {
                Integer formId = extractFormIdFromVersionText(xmlPullParserFactory, xmlPullParser.getText());
                saveFormDetails(formId);
            } else {
                extractXFormDefinition();
            }
        }

        private void extractXFormDefinition() {
            currentXFormDefinition = xmlPullParser.getText();
        }

        private void saveFormDetails(Integer formId) throws XmlPullParserException, IOException {

            if (formId != null && formMap.get(formId) == null) {
                formMap.put(formId, currentXFormDefinition);
                addFormToStudy(formId, studyName);
            }
        }

        private void handleEndTag() {
            if ("xform".equals(xmlPullParser.getName())) {
                xFormTagEnd();
            } else if ("versionText".equals(xmlPullParser.getName())) {
                versionTextTagEnd();
            }
        }

        private void handleStartTag() {
            if ("study".equals(xmlPullParser.getName())) {
                captureStudyDetails(xmlPullParser);
            } else if ("xform".equals(xmlPullParser.getName())) {
                xFormTagStart();
            } else if ("versionText".equals(xmlPullParser.getName())) {
                versionTextTagStart();
            }
        }

        public void xFormTagStart() {
            isXform = true;
        }

        public void xFormTagEnd() {
            isXform = false;
        }

        public void versionTextTagStart() {
            isVersionText = true;
        }

        public void versionTextTagEnd() {
            isVersionText = false;
        }


        private void addFormToStudy(Integer formId, String studyName) {
            if (studyForms.get(studyName) != null) {
                studyForms.get(studyName).add(formId);
            }
        }

        private void captureStudyDetails(XmlPullParser xmlPullParser) {
            studyName = xmlPullParser.getAttributeValue(null, "name");
            studies.add(studyName);
            studyForms.put(studyName, new ArrayList<Integer>());
        }

        private Integer extractFormIdFromVersionText(XmlPullParserFactory xmlPullParserFactory, String versionTextXform) throws XmlPullParserException, IOException {
            Integer formId = null;
            XmlPullParser formXpp = xmlPullParserFactory.newPullParser();
            formXpp.setInput(new StringReader(versionTextXform));
            for (int formEvent = formXpp.getEventType(); formEvent != XmlPullParser.END_DOCUMENT; formEvent = formXpp.next()) {
                if (formEvent == XmlPullParser.START_TAG && "xform".equals(formXpp.getName())) {
                    String formIdStr = formXpp.getAttributeValue(null, "id");
                    formId = Integer.valueOf(formIdStr);
                }
            }
            return formId;
        }
    }
}
