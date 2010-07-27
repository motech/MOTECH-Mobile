package org.motechproject.mobile.core.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import org.motechproject.mobile.core.dao.DBSession;
import javax.transaction.Transaction;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @see  org.motechproject.mobile.core.service.MotechContext
 *
 * @author Henry Sampson
 * Date Created @date
 */
public class MotechContextImpl implements MotechContext<Session, Transaction> {

    private static Logger logger = Logger.getLogger(MotechContextImpl.class);
    DBSession<Session, Transaction> dBsession;

    /**
     * @see org.motechproject.mobile.core.service.MotechContext#getDBSession() 
     */
    public DBSession<Session, Transaction> getDBSession() {
        logger.info("Calling GetDBSesion");
        return dBsession;
    }

    /**
     * @see org.motechproject.mobile.core.service.MotechContext#cleanUp()
     */
    public void cleanUp() {
        logger.info("Cleaning up the session");
        if (dBsession != null) {
            dBsession.getSession().flush();
           Connection con= dBsession.getSession().close();
           if(con != null)
           {
                try {
                    con.close();
                    con = null;
                } catch (SQLException ex) {
                    logger.fatal(" Severe error during connection closing", ex);
                }
           }
        }
    }

    /**
     * @see org.motechproject.mobile.core.service.MotechContext#setDBSession()
     */
    public void setDBSession(DBSession<Session, Transaction> session) {
        logger.info("Setting DBSession");
        this.dBsession = (DBSession<Session, Transaction>) session;
    }
}
