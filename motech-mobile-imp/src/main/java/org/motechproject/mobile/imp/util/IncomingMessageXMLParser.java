/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.util;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.imp.util.exception.MotechParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.jdom.JDOMException;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Feb 14, 2010
 */
public interface IncomingMessageXMLParser {

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager();

    /**
     * @return the delimiter
     */
    public String getDelimiter();

    /**
     * @return the messageParser
     */
    public IncomingMessageParser getMessageParser();

    /**
     * @return the separator
     */
    public String getSeparator();

    /**
     * @return the typeTagName
     */
    public String getFormTypeTagName();

    /**
     * Parses the List of XMLs to Motech incoming SMSes
     *
     * @param xmls the List of XMLs to be parsed
     * @return the list of Motech incoming SMSes representing the parsed XMLs
     * @throws org.jdom.JDOMException thrown if an error occurs while parsing an XML Document
     * @throws java.io.IOException thrown if an error occurs reading the file or stream
     * @throws org.motechproject.mobile.imp.util.exception.MotechParseException
     */
    public ArrayList<String> parseXML(ArrayList<String> xml) throws JDOMException, IOException, MotechParseException;

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager);

    /**
     * @param delimiter the delimiter to set
     */
    public void setDelimiter(String delimiter);

    /**
     * @param messageParser the messageParser to set
     */
    public void setMessageParser(IncomingMessageParser messageParser);

    /**
     * @param separator the separator to set
     */
    public void setSeparator(String separator);

    /**
     * @param typeTagName the typeTagName to set
     */
    public void setFormTypeTagName(String typeTagName);

    /**
     * @return the formTypeLookup
     */
    Map<String, String> getFormTypeLookup();

    /**
     * @return the xmlUtil
     */
    XMLUtil getXmlUtil();

    /**
     * @param formTypeLookup the formTypeLookup to set
     */
    void setFormTypeLookup(Map<String, String> formTypeLookup);

    /**
     * @param xmlUtil the xmlUtil to set
     */
    void setXmlUtil(XMLUtil xmlUtil);

    /**
     * Returns a String similar to SMS forms represnting the xml argument passed
     *
     * @param xml the XML to parse
     * @return a string representing a name/value pair or xml-element/value[CDATA]
     * @throws org.jdom.JDOMException thrown if an error occurs while parsing the XML a Document
     * @throws java.io.IOException thrown if an error occurs `reading the file or stream
     */
    String toSMSMessage(String xml) throws JDOMException, IOException, MotechParseException;

    /**
     * @return the formNameTagName
     */
    String getFormNameTagName();

    /**
     * @param formNameTagName the formNameTagName to set
     */
    void setFormNameTagName(String formNameTagName);

    /**
     * @param oxdDateFormat the oxdDateFormat to set
     */
    void setOxdDateFormat(String oxdDateFormat);

    /**
     * @param oxdDateRegex the oxdDateRegex to set
     */
    void setOxdDateRegex(String oxdDateRegex);

    /**
     * @param impDateFormat the impDateFormat to set
     */
    void setImpDateFormat(String impDateFormat);
}
