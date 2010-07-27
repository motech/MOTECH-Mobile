package org.motechproject.mobile.omp.service;

import org.motechproject.mobile.core.dao.GatewayRequestDAO;
import org.motechproject.mobile.core.dao.GatewayRequestDetailsDAO;
import org.motechproject.mobile.core.dao.GatewayResponseDAO;
import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.GatewayRequest;
import org.motechproject.mobile.core.model.GatewayRequestDetails;
import org.motechproject.mobile.core.model.GatewayResponse;
import org.motechproject.mobile.core.model.MStatus;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * An SMS specific implementation of the CacheService interface
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created: Jul 15, 2009
 */
@TransactionConfiguration
@Transactional
public class SMSCacheServiceImpl implements CacheService {

    private CoreManager coreManager;
    private static Logger logger = Logger.getLogger(SMSCacheServiceImpl.class);

    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(GatewayRequest messageDetails) {
        logger.debug("Initializing DAO");
        GatewayRequestDAO messageDAO = coreManager.createGatewayRequestDAO();
        
        logger.debug("Caching message");
        logger.debug(messageDetails);
  
        messageDAO.save(messageDetails);

    }
    
    /**
     *
     * @see CacheService.saveMessage
     */
    public void saveMessage(GatewayRequestDetails messageDetails) {
        logger.debug("Initializing DAO");
        GatewayRequestDetailsDAO messageDAO = coreManager.createGatewayRequestDetailsDAO();
        
        logger.debug("Caching message");
        logger.debug(messageDetails);
        

        messageDAO.save(messageDetails);

    }
    
    /**
     *
     * @see CacheService.saveResponse
     */
    public void saveResponse(GatewayResponse responseDetails) {
        logger.debug("Initializing DAO");
        GatewayResponseDAO responseDAO = coreManager.createGatewayResponseDAO();
        
        logger.debug("Caching response");
        logger.debug(responseDetails);
    
        responseDAO.save(responseDetails);

    }
    
    /**
     * 
     * see CacheService.getMessages
     */
    public List<GatewayRequest> getMessages(GatewayRequest criteria) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO();
        return messageDao.findByExample(criteria);
    }
    
    /**
     * 
     * see CacheService.getMessagesByStatus
     */
    public List<GatewayRequest> getMessagesByStatus(MStatus criteria) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO();
        return messageDao.getByStatus(criteria);
    }

    /**
     *
     * see CacheService.getMessagesByStatus
     */
    public List<GatewayRequest> getMessagesByStatusAndSchedule(MStatus criteria, Date schedule) {
        GatewayRequestDAO messageDao = coreManager.createGatewayRequestDAO();
        return messageDao.getByStatusAndSchedule(criteria, schedule);
    }
    
    /**
     * 
     * see CacheService.getMessages
     */
    public List<GatewayResponse> getResponses(GatewayResponse criteria) {
        GatewayResponseDAO responseDao = coreManager.createGatewayResponseDAO();
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
