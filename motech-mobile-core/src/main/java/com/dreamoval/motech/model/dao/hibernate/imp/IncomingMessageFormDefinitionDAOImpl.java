package com.dreamoval.motech.model.dao.hibernate.imp;

import com.dreamoval.motech.core.dao.hibernate.HibernateGenericDAOImpl;
import com.dreamoval.motech.model.dao.imp.IncomingMessageFormDefinitionDAO;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinition;
import com.dreamoval.motech.core.model.IncomingMessageFormDefinitionImpl;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/*
 * IncomingMessageFormDefinitionDAOImpl is the implementation class of the  interface
 * This Class implements only IncomingMessageFormDefinitionDAO specific persistent operation to the IncomingMessageFormDefinition model.
 *
 * Date: Dec 03, 2009
 * @author Joseph Djomeda (joseph@dreamoval.com)
 * @author Kofi Asamoah (yoofi@dreamoval.com)
 */
public class IncomingMessageFormDefinitionDAOImpl extends HibernateGenericDAOImpl<IncomingMessageFormDefinitionImpl>  implements IncomingMessageFormDefinitionDAO<IncomingMessageFormDefinitionImpl>{
    private static Logger logger = Logger.getLogger(IncomingMessageFormDefinitionDAOImpl.class);

    /**
     * Retrieve the most recent GatewayResponse Object based on the request id and the fact its status is not pending nor processing
     * @param requestId the requestId to pass
     * @return GatewayResponse object
     */
    public IncomingMessageFormDefinition getByCode(String formCode) {
        logger.debug("variable passed to getByCode: " + formCode);

        try {

            Session sess = this.getDBSession().getSession();
            Criterion code = Restrictions.eq("formCode", formCode);

            IncomingMessageFormDefinition definition = (IncomingMessageFormDefinition)sess.createCriteria(this.getPersistentClass())
                    .add(code)
                    .setMaxResults(1)
                    .uniqueResult();

            logger.debug(definition);

            return definition;

        } catch (HibernateException he) {

            logger.error("Persistence or JDBC Exception in getByCode", he);
            return null;
        } catch (Exception ex) {

            logger.error("Exception in getByCode", ex);
            return new IncomingMessageFormDefinitionImpl();
        }
    }
}
