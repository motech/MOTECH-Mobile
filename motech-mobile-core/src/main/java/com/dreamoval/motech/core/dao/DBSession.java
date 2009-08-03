/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao;

import java.io.Serializable;

/**
 *
 * This is a Wrapper class for sessions. Tjis allows Motech not to be tied
 * down to only hibernate sessions. The general idea is to have a generic
 * session which can be used across multiple ORMS
 *
 * @author Henry Sampson
 */
public interface DBSession<S, TX> extends Serializable {

    /**
     * <p>Releases the DBSession session back to the creating Session Container</p>
     */
    public void releaseSession();

    /**
     * Retrives a transaction from the DB Session
     *
     * @return Transaction object from the DB session
     */
    public TX getTransaction();

    /**
     * Returns the wrapped session
     *
     * @return The wrapped session
     */
    public S getSession();

    /**
     * Sets the session parameter
     *
     * @param session The session to set
     */
    void setSession(S session);
}
