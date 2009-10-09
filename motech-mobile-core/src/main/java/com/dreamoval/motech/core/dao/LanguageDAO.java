/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao;

import com.dreamoval.motech.core.model.Language;

/**
 *  Date : Sep 27, 2009
 * @author joseph Djomeda(joseph@dreamoval.com)
 */
public interface LanguageDAO<T extends Language> extends GenericDAO<T>{
    public Long getIdByCode(String code);
    
    public Language getByCode(String code);
   

}
