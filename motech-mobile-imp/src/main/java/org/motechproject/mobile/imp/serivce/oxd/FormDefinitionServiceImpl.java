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

import org.springframework.core.io.ClassPathResource;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        for (String resource : getOxdFormDefResources()) {
            int index = resource.lastIndexOf("/");
            String studyName = resource.substring(index + 1);
            List<File> fileList = getFileList(resource);
            if (fileList == null || fileList.isEmpty())
                throw new FileNotFoundException(" No files in the directory " + resource);
            List<Integer> formIdList = new ArrayList<Integer>();
            for (File fileName : fileList) {
                String formDefinition = getFileContent(fileName.getAbsolutePath());
                Integer extractedFormId = extractFormId(formDefinition);
                formMap.put(extractedFormId, formDefinition);
                formIdList.add(extractedFormId);
            }

            studyForms.put(studyName, formIdList);
            studies.add(studyName);
        }

    }

    private Integer extractFormId(String formDefinition) {
        Matcher matcher = Pattern.compile("<xf:xforms.*?id=\"(.*?)\"", Pattern.CASE_INSENSITIVE).matcher(formDefinition);
        Integer formId = null;
        if (matcher.find()) {
            String formIdString = matcher.group(1);
            formId = Integer.valueOf(formIdString);
        }
        return formId;
    }

    private String getFileContent(String fileName) {
        StringBuilder builder = new StringBuilder();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
            scanner.useDelimiter("\n");
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public List<File> getFileList(String directorySource) throws IOException {

        File directory = new ClassPathResource(directorySource).getFile();
        if (directory == null)
            throw new RuntimeException(new FileNotFoundException(" resource not found" + directorySource));

        if (!directory.exists())
            return Collections.emptyList();

        List<File> fileList = Arrays.asList(directory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (name.endsWith(".xml"))
                    return true;
                return false;
            }
        }));
        Collections.sort(fileList, new Comparator<File>() {
            public int compare(File o1, File o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return fileList;
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
        private String studyName;
        private String currentXFormDefinition;
        private static final String xFormTag = "xform";
        private static final String versionTextTag = "versionText";
        private static final String studyTag = "study";
        private boolean isXform;
        private boolean isVersionText;

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
            if (xFormTag.equals(xmlPullParser.getName())) {
                endXFormTag();
            } else if (versionTextTag.equals(xmlPullParser.getName())) {
                endVersionTextTag();
            }
        }

        private void handleStartTag() {
            if (studyTag.equals(xmlPullParser.getName())) {
                captureStudyDetails(xmlPullParser);
            } else if (xFormTag.equals(xmlPullParser.getName())) {
                startXFormTag();
            } else if (versionTextTag.equals(xmlPullParser.getName())) {
                startVersionTextTag();
            }
        }

        public void startXFormTag() {
            isXform = true;
        }

        public void endXFormTag() {
            isXform = false;
        }

        public void startVersionTextTag() {
            isVersionText = true;
        }

        public void endVersionTextTag() {
            isVersionText = false;
        }

        private void addFormToStudy(Integer formId, String studyName) {
            if (studyForms.get(studyName) != null) {
                studyForms.get(studyName).add(formId);
            }
        }

        private void captureStudyDetails(XmlPullParser xmlPullParser) {
            studyName = getAttributeValue(xmlPullParser, "name");
            studies.add(studyName);
            studyForms.put(studyName, new ArrayList<Integer>());
        }

        private Integer extractFormIdFromVersionText(XmlPullParserFactory xmlPullParserFactory, String versionTextXform) throws XmlPullParserException, IOException {
            Integer formId = null;
            XmlPullParser xFormXmlPullParser = xmlPullParserFactory.newPullParser();
            xFormXmlPullParser.setInput(new StringReader(versionTextXform));
            for (int formEvent = xFormXmlPullParser.getEventType(); formEvent != XmlPullParser.END_DOCUMENT; formEvent = xFormXmlPullParser.next()) {
                if (formEvent == XmlPullParser.START_TAG && xFormTag.equals(xFormXmlPullParser.getName())) {
                    String formIdString = getAttributeValue(xFormXmlPullParser, "id");
                    formId = Integer.valueOf(formIdString);
                }
            }
            return formId;
        }

        private String getAttributeValue(XmlPullParser xmlPullParser, String attributeName) {
            return xmlPullParser.getAttributeValue(null, attributeName);
        }
    }
}
