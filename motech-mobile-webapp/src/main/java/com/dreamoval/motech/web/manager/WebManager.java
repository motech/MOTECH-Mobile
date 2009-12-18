/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.web.manager;

import com.dreamoval.motech.web.IncomingMessageRequestWorker;
import java.io.Serializable;

/**
 *
 * @author Henry Sampaon (henry@dreamoval.com)
 * Date Created: Dec 18, 2009
 */
public interface WebManager extends Serializable {

    /**
     * Instantiates a new instance of IncomingMessageRequestWorker if none exist in
     * the work space otherwise returns an instance within the work space
     * @return a new or existing instance of IncomingMessageRequestWorker in the work space
     */
    public IncomingMessageRequestWorker createIMRWorker();
}
