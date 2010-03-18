package org.motechproject.mobile.core.dao;

import org.hibernate.Session;

/**
 * SessionContainer is an interface that provides contract between the Any instance that can return a session an the clent
 * Date :Jul 30, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 * 
 */
public interface SessionContainer {

    /**
     *  Provides Session to any client that request it.
     * @return Session
     */
    public Session requestSession();
}
