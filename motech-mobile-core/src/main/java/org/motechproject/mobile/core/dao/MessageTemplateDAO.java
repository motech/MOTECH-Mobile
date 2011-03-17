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

package org.motechproject.mobile.core.dao;

import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MessageTemplate;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationType;

/**
 * Provides Generic CRUD functionalities inherited from {@link org.motechproject.mobile.core.dao.GenericDAO}
 * with additional Helper methods to manipulate {@link org.motechproject.mobile.core.model.MessageTemplate } objects
 * or Collection of objects
 * 
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageTemplateDAO<T extends MessageTemplate> extends GenericDAO<T> {

    /**
     * Selects a MessageTemplate object based on the {@link org.motechproject.mobile.core.model.Language }, 
     * {@link org.motechproject.mobile.core.model.NotificationType } and {@link org.motechproject.mobile.core.model.MessageType }
     * @param lang specific language object to pass
     * @param notif specific notificationType to pass
     * @param type messagetype to pass
     * @return MessageTemplate object
     */
    public MessageTemplate getTemplateByLangNotifMType(Language lang, NotificationType notif, MessageType type);

    /**
     * Select MessageTemplate based on the language object {@link org.motechproject.mobile.core.model.Language }, 
     * {@link org.motechproject.mobile.core.model.NotificationType }, {@link org.motechproject.mobile.core.model.MessageType }
     * and defaut language object
     * @param lang specific language object to pass
     * @param notif specific notificationType to pass
     * @param type messagetype to pass
     * @param Language default language object to pass in case the lang param is null
     * @return MessageTemplate object
     */
    public MessageTemplate getTemplateByLangNotifMType(Language lang, NotificationType notif, MessageType type, Language defaultLang);
}
