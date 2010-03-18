package org.motechproject.mobile.model.dao.imp;

import org.motechproject.mobile.core.dao.GenericDAO;
import org.motechproject.mobile.core.model.IncomingMessage;
import java.util.Date;

/**
 * IncomingMessageDAO is an interface that defines Operations on IncomingMessage Pojo
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageDAO<T extends IncomingMessage> extends GenericDAO<T> {
    IncomingMessage getByContent(String content);

    IncomingMessage getByContentNonDuplicatable(String content);

    IncomingMessage getByContentBefore(String content, Date beforeDate);
}
