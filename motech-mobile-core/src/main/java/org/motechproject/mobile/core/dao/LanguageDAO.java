package org.motechproject.mobile.core.dao;

import org.motechproject.mobile.core.model.Language;

/**
 * LanguageDao is an interface that defines only methods and attributes that are specific to Language entity
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface LanguageDAO<T extends Language> extends GenericDAO<T> {

    /**
     * Method to take a language code and return the language object representing that row in the database
     * @param code the code of the registered language
     * @return the language object
     */
    public Language getByCode(String code);
}
