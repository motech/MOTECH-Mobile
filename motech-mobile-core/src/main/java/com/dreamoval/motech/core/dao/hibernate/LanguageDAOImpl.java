package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.LanguageDAO;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.LanguageImpl;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * LanguageDAOImpl is the implementation class of the LanguageDAO interface
 * This Class implements only LanguageDAO specific persistent operations to the Language model.
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class LanguageDAOImpl extends HibernateGenericDAOImpl<LanguageImpl> implements LanguageDAO<LanguageImpl> {

    public LanguageDAOImpl() {
    }

    /**
     * @see {@link com.dreamoval.motech.core.dao.LanguageDAO#getByCode(java.lang.String)  }
     */
    public Language getByCode(String code) {

        Session sess = this.getDBSession().getSession();
        Language lang = (Language) sess.createCriteria(LanguageImpl.class).add(Restrictions.eq("code", code)).uniqueResult();
        return lang;
    }
}
