package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.MessageTemplateDAO;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.MessageTemplate;
import com.dreamoval.motech.core.model.MessageTemplateImpl;
import com.dreamoval.motech.core.model.MessageType;
import com.dreamoval.motech.core.model.NotificationType;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *  MessageTemplateDAOImpl is the implementation class of the MessageTemplateDAO interface
 * This Class implements only MessageTemplateDAO specific persistent operation to the MessageTemplate model.
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class MessageTemplateDAOImpl extends HibernateGenericDAOImpl<MessageTemplateImpl> implements MessageTemplateDAO<MessageTemplateImpl> {

    /**
     * @see {@link com.dreamoval.motech.core.dao.MessageTemplateDAO#getTemplateByLangNotifMType(com.dreamoval.motech.core.model.Language, com.dreamoval.motech.core.model.NotificationType, com.dreamoval.motech.core.model.MessageType)  }
     */
    public MessageTemplate getTemplateByLangNotifMType(Language lang, NotificationType notif, MessageType type) {

        Session sess = this.getDBSession().getSession();
        MessageTemplate template = (MessageTemplate) sess.createCriteria(MessageTemplateImpl.class).add(Restrictions.eq("language", lang)).add(Restrictions.eq("notificationType", notif)).add(Restrictions.eq("messageType", type)).uniqueResult();
        return template;
    }
}
