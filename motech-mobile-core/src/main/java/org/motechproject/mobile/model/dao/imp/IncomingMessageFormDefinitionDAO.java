package org.motechproject.mobile.model.dao.imp;

import org.motechproject.mobile.core.dao.GenericDAO;
import org.motechproject.mobile.core.model.IncomingMessageFormDefinition;

/*
 * IncomingMessageFormDefinitionDAO is an interface that defines Operations on IncomingMessageFormDefinition Pojo
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageFormDefinitionDAO<T extends IncomingMessageFormDefinition> extends GenericDAO<T> {

    IncomingMessageFormDefinition getByCode(String formCode);
}
