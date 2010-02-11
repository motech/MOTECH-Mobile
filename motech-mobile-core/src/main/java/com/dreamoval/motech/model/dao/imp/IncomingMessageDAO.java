package com.dreamoval.motech.model.dao.imp;

import com.dreamoval.motech.core.dao.GenericDAO;
import com.dreamoval.motech.core.model.IncomingMessage;
import java.util.Date;

/**
 * IncomingMessageDAO is an interface that defines Operations on IncomingMessage Pojo
 * Date: Dec 14, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageDAO<T extends IncomingMessage> extends GenericDAO<T> {
    IncomingMessage getByContent(String content);

    IncomingMessage getByContentBefore(String content, Date beforeDate);
}
