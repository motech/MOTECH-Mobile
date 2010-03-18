package org.motechproject.mobile.core.service;

import org.motechproject.mobile.core.dao.DBSession;
import java.io.Serializable;

/**
 *
 * This defines a motech specific context which acts more or less as
 * a container for carrying objects that need to exist throughout
 * requests and over conversations/
 *
 * @author Henry Sampson
 * Date Created 31-07-2009
 */
public interface MotechContext<S, TX> extends Serializable {

    /**
     * Sets the DB Session for the context
     *
     * @param session The DB session
     */
    public void setDBSession(DBSession<S, TX> session);

    /**
     *
     * Returns the current context's DB session
     *
     * @return
     */
    public DBSession<S, TX> getDBSession();

    /**
     * Cleans up context for destruction
     */
    public void cleanUp();
}
