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
public class MotechContextImpl implements MotechContext<Session, Transaction> {
    
    DBSession<Session, Transaction> dBsession;

    /**
     * @see com.dreamoval.motech.core.service.MotechContext#getDBSession() 
     */
    public DBSession<Session, Transaction> getDBSession() {
        return dBsession;
    }

    /**
     * @see com.dreamoval.motech.core.service.MotechContext#cleanUp()
     */
    public void cleanUp() {
        dBsession.getSession().flush();
        dBsession.getSession().close();
    }

     /**
     * @see com.dreamoval.motech.core.service.MotechContext#setDBSession()
     */
    public void setDBSession(DBSession<Session, Transaction> session) {
        this.dBsession = (DBSession<Session, Transaction>)session;
    }

}
