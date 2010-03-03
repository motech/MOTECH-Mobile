/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.imp.util.exception;

/**
 *
 * @author Henry Sampson (henry@dreamoval.com)
 * Date Created: Feb 25, 2010
 */
public class MotechParseException extends Exception {

    public MotechParseException(String message){
        super(message);
    }

    public MotechParseException(Throwable t){
        super(t);
    }

    public MotechParseException(String message, Throwable t){
        super(message, t);
    }
}
