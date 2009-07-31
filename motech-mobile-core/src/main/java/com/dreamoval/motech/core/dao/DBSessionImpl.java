/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.dao;

import javax.transaction.Transaction;
import org.hibernate.Session;

/**
 *
 * see com.dreamoval.motech.core.dao.DBSession
 *
 * @author Henry Sampson
 * Date Created @date
 */
public class DBSessionImpl<S extends Session, T extends Transaction> implements DBSession {

    Session session;
    
    public DBSessionImpl(Session session){
        this.session = session;
    }
    
    /**
     * see com.dreamoval.motech.core.dao.DBSession#releaseSession()
     */
    public void releaseSession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * see com.dreamoval.motech.core.dao.DBSession#getTransaction()
     */
    public T getTransaction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * see com.dreamoval.motech.core.dao.DBSession#getSession()
     */
    public S getSession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
