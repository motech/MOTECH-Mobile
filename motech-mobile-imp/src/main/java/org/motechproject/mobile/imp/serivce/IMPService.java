/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.motechproject.mobile.imp.serivce;

import org.motechproject.mobile.imp.util.exception.MotechParseException;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom.JDOMException;
import org.motechproject.mobile.core.model.IncomingMessageResponse;

/**
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public interface IMPService {

    /**
     * Processes an incoming message request
     * @param message the content of the request
     * @param requesterPhone the phone number through which the request was made
     * @return the response of the request
     */
    IncomingMessageResponse processRequest(String message, String requesterPhone, boolean isDemo);

    /**
     * <p>Processes xForms as Motech Forms by converting them to SMS format. It then goes through normal
     * SMS processing.</p>
     *
     * @param xForms
     * @return a List of responses
     * @throws org.jdom.JDOMException
     * @throws java.io.IOException
     * @throws org.motechproject.mobile.imp.util.exception.MotechParseException
     */
    ArrayList<String> processXForms(ArrayList<String> xForms) throws JDOMException, IOException, MotechParseException;

    /**
     * Validates and processes an xForm.
     *
     * @param xForm the XForm to be validated and processed
     * @return ok if successful otherwise the specifics of the error for reporting
     * @throws org.jdom.JDOMException
     * @throws java.io.IOException
     * @throws org.motechproject.mobile.imp.util.exception.MotechParseException
     */
    String processXForm(String xForm) throws JDOMException, IOException, MotechParseException;

    String formatPhoneNumber(String requesterPhone);

    /**
     * @return the formProcessSuccess
     */
    String getFormProcessSuccess();

    /**
     * @param formProcessSuccess the formProcessSuccess to set
     */
    void setFormProcessSuccess(String formProcessSuccess);

    /**
     * @param maxConcat the maxConcat to set
     */
    void setMaxConcat(int maxConcat);

    /**
     * @param charsPerSMS the charsPerSMS to set
     */
    void setCharsPerSMS(int charsPerSMS);

    /**
     * @param concatAllowance the concatAllowance to set
     */
    void setConcatAllowance(int concatAllowance);

    /**
     * @param maxSMS the maxSMS to set
     */
    void setMaxSMS(int maxSMS);
}
