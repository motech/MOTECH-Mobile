package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.GatewayRequestDetailsDAO;
import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.service.MotechContext;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

/**
 * An SMS specific implementation of the CacheService interface
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSCacheServiceImpl implements CacheService {

    private CoreManager coreManager;
    private static Logger logger = Logger.getLogger(SMSCacheServiceImpl.class);

    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(GatewayRequest messageDetails, MotechContext context) {
        logger.info("Initializing DAO");
        GatewayRequestDAO messageDAO = coreManager.createGatewayRequestDAO(context);
        
        logger.info("Caching message");
        logger.debug(messageDetails);
        
        Transaction tx = (Transaction) messageDAO.getDBSession().getTransaction();
        tx.begin();
        messageDAO.save(messageDetails);
        tx.commit();
    }
    
    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(GatewayRequestDetails messageDetails, MotechContext context) {
        logger.info("Initializing DAO");
        GatewayRequestDetailsDAO messageDAO = coreManager.createGatewayRequestDetailsDAO(context);
        
        logger.info("Caching message");
        logger.debug(messageDetails);
        
        Transaction tx = (Transaction) messageDAO.getDBSession().getTransaction();
        tx.begin();
        messageDAO.save(messageDetails);
        tx.commit();
    }
    
    /**
     *
     * @see CacheService.saveResponse
     */
    public void saveResponse(GatewayResponse responseDetails, MotechContext context) {
        logger.info("Initializing DAO");
        GatewayResponseDAO responseDAO = coreManager.createGatewayResponseDAO(context);
        
        logger.info("Caching response");
        logger.debug(responseDetails);
        
        Transaction tx = (Transaction) responseDAO.getDBSession().getTransaction();
        tx.begin();
        responseDAO.save(responseDetails);
        tx.commit();
    }
    
    /**
     * 
     * see CacheService.getMessages
     */
    public List<GatewayRequest> getMessages(GatewayRequest criteria, MotechContext context) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO(context);
        return messageDao.findByExample(criteria);
    }
    
    /**
     * 
     * see CacheService.getMessagesByStatus
     */
    public List<GatewayRequest> getMessagesByStatus(MStatus criteria, MotechContext context) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO(context);
        return messageDao.getByStatus(criteria);
    }
    
    /**
     * 
     * see CacheService.getMessages
     */
    public List<GatewayResponse> getResponses(GatewayResponse criteria, MotechContext context) {
        GatewayResponseDAO responseDao = coreManager.createGatewayResponseDAO(context);
        return responseDao.findByExample(criteria);
    }

    /**
     * @return the coreManager
     */
    public CoreManager getCoreManager() {
        return coreManager;
    }

    /**
     * @param coreManager the coreManager to set
     */
    public void setCoreManager(CoreManager coreManager) {
        logger.debug("Setting value of SMSCacheServiceImpl.coreManager");
        logger.debug(coreManager);
        this.coreManager = coreManager;
    }
}
