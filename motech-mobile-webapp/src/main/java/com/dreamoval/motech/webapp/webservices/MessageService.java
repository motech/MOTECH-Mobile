/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.webapp.webservices;

import com.dreamoval.motech.core.model.MessageDetails;
import java.io.Serializable;
import javax.jws.WebService;
import javax.jws.WebParam;

/**
 *
 * @author Yoofi
 */
@WebService()
public interface MessageService extends Serializable{

    MessageDetails getMessageDetails(@WebParam(name="msgId") String msgId);
}
