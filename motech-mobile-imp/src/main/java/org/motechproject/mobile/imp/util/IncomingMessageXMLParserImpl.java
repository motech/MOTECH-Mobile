/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.core.model.IncomingMessageFormParameter;
import org.motechproject.mobile.imp.util.exception.MotechParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * Utility class for converting xForms into SMS Forms
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Feb 14, 2010
 */
public class IncomingMessageXMLParserImpl implements IncomingMessageXMLParser {

    private static Logger log = Logger.getLogger(IncomingMessageXMLParserImpl.class);
    private CoreManager coreManager;
    private IncomingMessageParser messageParser;
    private String separator;
    private String delimiter;
    private String formTypeTagName;
    private Map<String, String> formTypeLookup;
    private XMLUtil xmlUtil;

    /**
     * Parses the List of XMLs to Motech incoming SMSes
     * 
     * @param xmls the List of XMLs to be parsed
     * @return a {@link org.motechproject.mobile.core.model.IncomingMessageForm} representing the parsed XML
     * @throws org.jdom.JDOMException thrown if an error occurs while parsing the XML a Document
     * @throws java.io.IOException thrown if an error occurs reading the file or stream
     * @throws org.motechproject.mobile.imp.util.exception.MotechParseException 
     */
    public ArrayList<String> parseXML(ArrayList<String> xmls) throws JDOMException, IOException, MotechParseException {
        ArrayList<String> result = null;

        if (xmls != null) {
            result = new ArrayList<String>();
            for(String xml : xmls){
                log.debug("parseXML(" + xml + ")");
                String message = toSMSMessage(xml);
                log.debug("SMS Message Createed ===> " + message);
                result.add(message);
            }
        }

        return result;
    }

    /**
     * Returns a String similar to SMS forms represnting the xml argument passed
     * 
     * @param xml the XML to parse
     * @return a string representing a name/value pair or xml-element/value[CDATA]
     * @throws org.jdom.JDOMException thrown if an error occurs while parsing the XML a Document
     * @throws java.io.IOException thrown if an error occurs `reading the file or stream
     */
    public String toSMSMessage(String xml) throws JDOMException, IOException, MotechParseException {
        String result = "";

        InputStream in = new ByteArrayInputStream(xml.getBytes());
        SAXBuilder saxb = new SAXBuilder();
        Document doc = null;

        doc = saxb.build(in);

        Element root = doc.getRootElement();
        Element formTypeElement = getXmlUtil().getElement(doc, formTypeTagName);
        String formType = formTypeElement == null ? null : formTypeElement.getText();

        if (formType == null || formType.trim().equals("")) {
            String error = "Empty or No form type defined in xml with root element: " + root.getName() + " and id: " + root.getAttributeValue("id");
            log.error(error);
            throw new MotechParseException(error);
        }

        String formTypeFieldName = formTypeLookup.get(formType);

        if (formTypeFieldName == null || formTypeFieldName.trim().equals("")) {
            String error = "Could not find a valid (non-null-or-white-space) form type field name associated with form type: " + formType;
            log.error(error);
            throw new MotechParseException(error);
        }

        result += formTypeFieldName + getSeparator() + root.getName();

        List children = root.getChildren();
        for (Object o : children) {
            Element child = (Element) o;
            if (!child.getName().equalsIgnoreCase(formTypeTagName)) {
                result += getDelimiter() + child.getName() + getSeparator() + child.getText();
            }
        }

        return result;
    }

    private Map<String, IncomingMessageFormParameter> toIncomingMessageParameters(Map<String, String> params) {
        Map<String, IncomingMessageFormParameter> result = null;


        return result;
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }

    /**
     * @return the messageParser
     */
    public IncomingMessageParser getMessageParser() {
        return messageParser;
    }

    /**
     * @param messageParser the messageParser to set
     */
    public void setMessageParser(IncomingMessageParser messageParser) {
        this.messageParser = messageParser;
    }

    /**
     * @return the separator
     */
    public String getSeparator() {
        return separator;
    }

    /**
     * @param separator the separator to set
     */
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * @return the delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * @param delimiter the delimiter to set
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * @return the formTypeTagName
     */
    public String getFormTypeTagName() {
        return formTypeTagName;
    }

    /**
     * @param formTypeTagName the formTypeTagName to set
     */
    public void setFormTypeTagName(String formTypeTagName) {
        this.formTypeTagName = formTypeTagName;
    }

    /**
     * @return the formTypeLookup
     */
    public Map<String, String> getFormTypeLookup() {
        return formTypeLookup;
    }

    /**
     * @param formTypeLookup the formTypeLookup to set
     */
    public void setFormTypeLookup(Map<String, String> formTypeLookup) {
        this.formTypeLookup = formTypeLookup;
    }

    /**
     * @return the xmlUtil
     */
    public XMLUtil getXmlUtil() {
        return xmlUtil;
    }

    /**
     * @param xmlUtil the xmlUtil to set
     */
    public void setXmlUtil(XMLUtil xmlUtil) {
        this.xmlUtil = xmlUtil;
    }
}
