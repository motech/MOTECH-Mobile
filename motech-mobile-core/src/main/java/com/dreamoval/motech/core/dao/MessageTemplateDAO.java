package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;

/**
 * MessageTemplateDao is an interface that defines only methods and attributes that are specific to MessageTemplate entity
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface MessageTemplateDAO<T extends MessageTemplate> extends GenericDAO<T> {

    /**
     * Method to get a specific template based on the language object m notificationtype , and which is an enumeration
     * @param lang specific language object to pass
     * @param notif specific notificationType to pass
     * @param type messagetype to pass
     * @return
     */
    public MessageTemplate getTemplateByLangNotifMType(Language lang, NotificationType notif, MessageType type);

    /**
     * Method to get a specific template based on the language object m notificationtype , and messagetype which is an enumeration, and a default language
     * @param lang specific language object to pass
     * @param notif specific notificationType to pass
     * @param type messagetype to pass
     * @param Language default language object to pass in case the lang param is null
     * @return
     */
    public MessageTemplate getTemplateByLangNotifMType(Language lang, NotificationType notif, MessageType type, Language defaultLang);
}
