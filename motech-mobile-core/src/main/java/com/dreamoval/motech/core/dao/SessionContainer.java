/*
 * SessionContainer is an interface that provides contract between the Any instance that can return a session an the clent
 */

package com.dreamoval.motech.core.dao;

import org.hibernate.Session;



/**
 * Date :
 * @author Joseph Djomeda
 * Email: joseph@dreamoval.com
 */
public interface SessionContainer {

    /**
     *  Provides Session to any client that request it.
     * @return Session
     */
   public Session requestSession();

}
