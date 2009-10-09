/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.LanguageDAO;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.LanguageImpl;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda (joseph@dreamoval.com)
 */
public class LanguageDAOImpl extends HibernateGenericDAOImpl<LanguageImpl> implements LanguageDAO<LanguageImpl>{

    public LanguageDAOImpl() {
    }
    
    public Long getIdByCode(String code) {

        Session sess = this.getDBSession().getSession();
        Language lang = (Language) sess.createCriteria(LanguageImpl.class).add(Restrictions.eq("code", code)).uniqueResult();
        return lang.getId();
    }
    
    public Language getByCode(String code) {

        Session sess = this.getDBSession().getSession();
        Language lang = (Language) sess.createCriteria(LanguageImpl.class).add(Restrictions.eq("code", code)).uniqueResult();
        return lang;
    }

}
