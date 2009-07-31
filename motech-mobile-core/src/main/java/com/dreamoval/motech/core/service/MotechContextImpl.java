/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.motech.core.service;

import com.dreamoval.motech.core.dao.DBSession;
import javax.transaction.Transaction;
import org.hibernate.Session;

/**
 *
 * @see  com.dreamoval.motech.core.service.MotechContext
 *
 * @author Henry Sampson
 * Date Created @date
 */
public class MotechContextImpl implements MotechContext {
    
    /**
     * @see com.dreamoval.motech.core.service.MotechContext#getDBSession() 
     */
    public DBSession<Session, Transaction> getDBSession() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @see com.dreamoval.motech.core.service.MotechContext#cleanup()
     */
    public void cleanup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

     /**
     * @see com.dreamoval.motech.core.service.MotechContext#setDBSession()
     */
    public void setDBSession(DBSession<?, ?> session) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
