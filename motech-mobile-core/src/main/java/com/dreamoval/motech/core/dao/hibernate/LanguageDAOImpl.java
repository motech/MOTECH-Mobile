package com.dreamoval.motech.core.dao.hibernate;

import com.dreamoval.motech.core.dao.LanguageDAO;
import com.dreamoval.motech.core.model.Language;
import com.dreamoval.motech.core.model.LanguageImpl;
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
     * @see {@link com.dreamoval.motech.core.dao.LanguageDAO#getByCode(java.lang.String)  }
     */
    public Language getByCode(String code) {
    logger.info("getByCode");
    logger.debug(code);
    try{
        Session sess = this.getDBSession().getSession();
        Language lang = (Language) sess.createCriteria(LanguageImpl.class)
                .add(Restrictions.eq("code", code))
                .uniqueResult();
        logger.debug(lang);
        return lang;
    } catch(NonUniqueResultException ne){
        logger.debug("getByCode returned more than one object", ne);
        return null;
    }     catch(HibernateException he){
        logger.debug("Persistence or jdbc Exception  in Method getByCode", he);
        return null;
    }         catch(Exception e){
        logger.debug("Exception in getByCode", e);
        return null;
    }
        
    }
}
