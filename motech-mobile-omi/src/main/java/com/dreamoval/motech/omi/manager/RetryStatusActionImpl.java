package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequest;
import org.hibernate.Transaction;

/**
 * Provides external access to OMI methods
 *
 * @author Kofi A. Asamoah (yoofi@dremoval.com)
 * Date Created: Sep 30, 2009
 */
public class RetryStatusActionImpl implements StatusAction{
    private CoreManager coreManager;
    
   public void doAction(GatewayResponse response){   
        MessageRequestDAO msgReqDao = coreManager.createMessageRequestDAO(coreManager.createMotechContext());
        MessageRequest msgReq = (MessageRequest) msgReqDao.getById(response.getGatewayRequest().getRequestId());
        msgReq.setStatus(MStatus.RETRY);
        
        Transaction tx = (Transaction)msgReqDao.getDBSession().getTransaction();
        tx.begin();
        msgReqDao.save(msgReq);
        tx.commit(); 
   }

    public CoreManager getCoreManager() {
        return coreManager;
    }

    public void setCoreManager(CoreManager coreManager) {
        this.coreManager = coreManager;
    }
}
