package org.motechproject.mobile.core.dao.hibernate;

import org.motechproject.mobile.core.dao.LanguageDAO;
import org.motechproject.mobile.core.model.Language;
import org.motechproject.mobile.core.model.LanguageImpl;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * LanguageDAOImpl is the implementation class of the LanguageDAO interface
 * This Class implements only LanguageDAO specific persistent operations to the Language model.
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class LanguageDAOImpl extends HibernateGenericDAOImpl<LanguageImpl> implements LanguageDAO<LanguageImpl> {

    private static Logger logger = Logger.getLogger(LanguageDAOImpl.class);

    public LanguageDAOImpl() {
    }

    /**
     * @see {@link org.motechproject.mobile.core.dao.LanguageDAO#getByCode(java.lang.String)  }
     */
    public Language getByCode(String code) {
        logger.debug("variable passed to getByCode: " + code);
        try {
            Session sess = this.getDBSession().getSession();
            Language lang = (Language) sess.createCriteria(LanguageImpl.class).add(Restrictions.eq("code", code)).uniqueResult();
            logger.debug(lang);
            return lang;
        } catch (NonUniqueResultException ne) {
            logger.error("getByCode returned more than one object", ne);
            return null;
        } catch (HibernateException he) {
            logger.error("Persistence or jdbc Exception  in Method getByCode", he);
            return null;
        } catch (Exception e) {
            logger.error("Exception in getByCode", e);
            return null;
        }

    }
}
