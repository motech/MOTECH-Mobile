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

package org.motechproject.mobile.imp.manager;

import org.motechproject.mobile.imp.serivce.IMPService;
import org.motechproject.mobile.imp.util.CommandAction;
import org.motechproject.mobile.imp.util.IncomingMessageFormValidator;
import org.motechproject.mobile.imp.util.IncomingMessageParser;
import org.motechproject.mobile.imp.util.IncomingMessageXMLParser;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Handles creation of IMP objects
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 *  Date : Dec 5, 2009
 */
public class IMPManagerImpl implements ApplicationContextAware, IMPManager{
    ApplicationContext context;

    public IMPService createIMPService(){
        return (IMPService)context.getBean("impService");
    }

    public IncomingMessageParser createIncomingMessageParser(){
        return (IncomingMessageParser)context.getBean("imParser");
    }

    /**
     * Returns the spring managed instance of IncomingMessageXMLParser
     *
     * @return an instance of IncomingMessageXMLParser
     */
    public IncomingMessageXMLParser createIncomingMessageXMLParser(){
        return (IncomingMessageXMLParser)context.getBean("imXMLParser");
    }

    /**
     * Creates a wired IncomingMessageFormValidator instance
     * @return the created IncomingMessageFormValidator
     */
    public IncomingMessageFormValidator createIncomingMessageFormValidator(){
        return (IncomingMessageFormValidator)context.getBean("imFormValidator");
    }

    public CommandAction createCommandAction() {
        return (CommandAction)context.getBean("formCmdAxn");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
