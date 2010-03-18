package org.motechproject.mobile.model.dao.imp;

import org.motechproject.mobile.core.dao.GenericDAO;
import org.motechproject.mobile.core.model.IncomingMessageSession;
import java.util.List;

/*
 * IncomingMessageSessionDAO is an interface that defines Operations on IncomingMessageSession Pojo
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 */
public interface IncomingMessageSessionDAO<T extends IncomingMessageSession> extends GenericDAO<T> {

    /**
     * Method to provide a list of IncomingMessageSession object based on requestedphone
     * @param requesterPhone the requestedphone to pass
     * @return List of IncomingMessageSession
     */
    public List<IncomingMessageSession> getIncomingMsgSessionByRequestedPhone(String requesterPhone);
}
