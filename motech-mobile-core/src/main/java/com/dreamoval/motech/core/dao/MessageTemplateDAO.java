/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageTemplateDAO<T extends MessageTemplate> extends GenericDAO<T> {
    public MessageTemplate getTemplateByLangNotifMType(Language lang, NotificationType notif, MessageType type);
   

}
