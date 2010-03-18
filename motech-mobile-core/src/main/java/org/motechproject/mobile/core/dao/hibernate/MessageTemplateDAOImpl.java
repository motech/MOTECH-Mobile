package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.MessageTemplateDAO;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.MessageTemplate;
import org.motechproject.mobile.core.model.MessageTemplateImpl;
import org.motechproject.mobile.core.model.MessageType;
import org.motechproject.mobile.core.model.NotificationType;
import javax.persistence.NonUniqueResultException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *  MessageTemplateDAOImpl is the implementation class of the MessageTemplateDAO interface
 * This Class implements only MessageTemplateDAO specific persistent operation to the MessageTemplate model.
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class MessageTemplateDAOImpl extends HibernateGenericDAOImpl<MessageTemplateImpl> implements MessageTemplateDAO<MessageTemplateImpl> {

    private static Logger logger = Logger.getLogger(MessageTemplateDAOImpl.class);

    /**
     * @see {@link org.motechproject.mobile.core.dao.MessageTemplateDAO#getTemplateByLangNotifMType(org.motechproject.mobile.core.model.Language, org.motechproject.mobile.core.model.NotificationType, org.motechproject.mobile.core.model.MessageType)  }
     */
    public MessageTemplate getTemplateByLangNotifMType(Language lang, NotificationType notif, MessageType type) {

        logger.debug("variables passed to getTemplateByLangNotifMType. language: " + lang + "And NotificationType: " + notif + "And MessageType: " + type);
        try {
            Session sess = this.getDBSession().getSession();
            MessageTemplate template = (MessageTemplate) sess.createCriteria(MessageTemplateImpl.class).add(Restrictions.eq("language", lang)).add(Restrictions.eq("notificationType", notif)).add(Restrictions.eq("messageType", type)).uniqueResult();

            logger.debug(template);

            return template;
        } catch (NonUniqueResultException ne) {
            logger.error("Method getTemplateByLangNotifMType returned more than one MessageTemplate object", ne);
            return null;
        } catch (HibernateException he) {
            logger.error(" Persistence or JDBC Exception in Method getTemplateByLangNotifMType", he);
            return null;
        } catch (Exception ex) {
            logger.error("Exception in Method getTemplateByLangNotifMType", ex);
            return null;
        }

    }

    /**
     * @see {@link org.motechproject.mobile.core.dao.MessageTemplateDAO#getTemplateByLangNotifMType(org.motechproject.mobile.core.model.Language, org.motechproject.mobile.core.model.NotificationType, org.motechproject.mobile.core.model.MessageType, org.motechproject.mobile.core.model.Language)   }
     */
    public MessageTemplate getTemplateByLangNotifMType(Language lang, NotificationType notif, MessageType type, Language defaultLang) {
        if (lang != null) {
            return getTemplateByLangNotifMType(lang, notif, type);

        } else {
            return getTemplateByLangNotifMType(defaultLang, notif, type);
        }
    }
}
