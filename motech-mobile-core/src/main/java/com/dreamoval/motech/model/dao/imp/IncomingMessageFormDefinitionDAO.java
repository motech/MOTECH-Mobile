package com.dreamoval.motech.model.dao.imp;

import com.dreamoval.motech.core.dao.GenericDAO;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinition;

/*
 * IncomingMessageFormDefinitionDAO is an interface that defines Operations on IncomingMessageFormDefinition Pojo
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageFormDefinitionDAO<T extends IncomingMessageFormDefinition> extends GenericDAO<T> {

    IncomingMessageFormDefinition getByCode(String formCode);
}
