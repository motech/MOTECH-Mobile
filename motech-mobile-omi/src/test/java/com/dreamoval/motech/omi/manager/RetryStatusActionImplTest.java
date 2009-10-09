package com.dreamoval.motech.omi.manager;

import com.dreamoval.motech.core.dao.DBSession;
import com.dreamoval.motech.core.dao.MessageRequestDAO;
import com.dreamoval.motech.core.manager.CoreManager;
import com.dreamoval.motech.core.model.GatewayRequest;
import com.dreamoval.motech.core.model.GatewayRequestDetails;
import com.dreamoval.motech.core.model.GatewayRequestImpl;
import com.dreamoval.motech.core.model.GatewayResponse;
import com.dreamoval.motech.core.model.GatewayResponseImpl;
import com.dreamoval.motech.core.model.MStatus;
import com.dreamoval.motech.core.model.MessageRequest;
import com.dreamoval.motech.core.model.MessageRequestImpl;
import com.dreamoval.motech.core.service.MotechContext;
import com.dreamoval.motech.core.service.MotechContextImpl;
import java.util.Date;
import org.hibernate.Transaction;
import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the ReportStatusActionImpl class
 *
 * @author Kofi A. Asamoah (yoofi@dreamoval.com)
 * Date Created Oct 02, 2009
 */
public class RetryStatusActionImplTest {

    CoreManager mockCore;
    MessageRequestDAO mockDao;
    RetryStatusActionImpl instance;
    DBSession mockSession;
    Transaction mockTrans;
    GatewayRequestDetails mockGatewayRequestDetails;

    public RetryStatusActionImplTest() {
    }

    @Before
    public void setUp() {
        mockCore = createMock(CoreManager.class);
        mockDao = createMock(MessageRequestDAO.class);
        mockSession = createMock(DBSession.class);
        mockTrans = createMock(Transaction.class);
        mockGatewayRequestDetails = createMock(GatewayRequestDetails.class);
        mockGatewayRequestDetails.setId(2L);

        instance = new RetryStatusActionImpl();
        instance.setCoreManager(mockCore);
    }

    @Test
    public void testDoAction() {
        System.out.println("doAction");

        GatewayRequest messageDetails = new GatewayRequestImpl();
        messageDetails.setDateFrom(new Date());
        messageDetails.setMessage("a message for testing");
        messageDetails.setDateTo(new Date());
        messageDetails.setRecipientsNumber("000000000000");
        messageDetails.setGatewayRequestDetails(mockGatewayRequestDetails);

        GatewayResponse response = new GatewayResponseImpl();
        response.setGatewayRequest(messageDetails);
        response.setGatewayMessageId("werfet54y56g645v4e");
        response.setMessageStatus(MStatus.DELIVERED);
        response.setRecipientNumber("000000000000");
        response.setResponseText("Some gateway response message");
        response.setId(5L);

        expect(
                mockCore.createMessageRequestDAO((MotechContext) anyObject())).andReturn(mockDao);
        expect(
                mockCore.createMotechContext()).andReturn(new MotechContextImpl());
        expect(
                mockDao.getById((Long) anyObject())).andReturn(new MessageRequestImpl());
        expect(
                mockDao.getDBSession()
                ).andReturn(mockSession);
        expect(
                mockSession.getTransaction()
                ).andReturn(mockTrans);
        
        mockTrans.begin();
        expectLastCall();
        
        expect(
                mockDao.save((MessageRequest) anyObject())
                ).andReturn(response);
        
        mockTrans.commit();
        expectLastCall();

        replay(mockCore, mockDao, mockSession, mockTrans);
        instance.doAction(response);
        verify(mockCore, mockDao, mockSession, mockTrans);
    }
}
