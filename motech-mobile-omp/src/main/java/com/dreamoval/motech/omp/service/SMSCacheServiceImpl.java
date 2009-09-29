package com.dreamoval.motech.omp.service;

import com.dreamoval.motech.core.dao.GatewayRequestDAO;
import com.dreamoval.motech.core.dao.GatewayResponseDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.omp.manager.OMPManager;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * An SMS specific implementation of the CacheService interface
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
public class SMSCacheServiceImpl implements CacheService {

    private CoreManager coreManager;
    private OMPManager ompManager;
    private static Logger logger = Logger.getLogger(SMSCacheServiceImpl.class);

    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(GatewayRequest messageDetails) {
        logger.info("Initializing DAO");
        GatewayRequestDAO messageDAO = coreManager.createGatewayRequestDAO(coreManager.createMotechContext());
        
        logger.info("Caching message");
        logger.debug(messageDetails);
        messageDAO.save(messageDetails);
    }
    
    /**
     *
     * @see CacheService.saveResponse
     */
    public void saveResponse(GatewayResponse responseDetails) {
        logger.info("Initializing DAO");
        GatewayResponseDAO responseDAO = coreManager.createGatewayResponseDAO(coreManager.createMotechContext());
        
        logger.info("Caching response");
        logger.debug(responseDetails);
        responseDAO.save(responseDetails);
    }
    
    /**
     * 
     * see CacheService.getMessages
     */
    public List<GatewayRequest> getMessages(GatewayRequest criteria) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO(coreManager.createMotechContext());
        return messageDao.findByExample(criteria);
    }
    
    /**
     * 
     * see CacheService.getMessages
     */
    public List<GatewayResponse> getResponses(GatewayResponse criteria) {
        GatewayResponseDAO responseDao = coreManager.createGatewayResponseDAO(coreManager.createMotechContext());
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

    /**
     * @return the ompManager
     */
    public OMPManager getOmpManager() {
        return ompManager;
    }

    /**
     * @param ompManager the ompManager to set
     */
    public void setOmpManager(OMPManager ompManager) {
        logger.debug("Setting value of SMSCacheServiceImpl.ompManager");
        logger.debug(ompManager);
        this.ompManager = ompManager;
    }

}
