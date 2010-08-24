package org.motechproject.mobile.imp.serivce;

import java.util.Calendar;
import java.util.Date;

import org.motechproject.mobile.core.manager.CoreManager;
import org.motechproject.mobile.core.model.Duplicatable;
import org.motechproject.mobile.core.model.IncMessageFormStatus;
import org.motechproject.mobile.core.model.IncMessageStatus;
import org.motechproject.mobile.core.model.IncomingMessage;
import org.motechproject.mobile.core.model.IncomingMessageForm;
import org.motechproject.mobile.imp.util.IncomingMessageParser;
import org.motechproject.mobile.model.dao.imp.IncomingMessageDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of a simplistic message registry. The intended purpose is to
 * register messages as they come in and to prevent duplicate submissions.
 * 
 * @author batkinson
 * 
 */
public class MessageRegistryImpl implements MessageRegistry {

	private int duplicatePeriod;
	private CoreManager coreManager;
	private IncomingMessageParser parser;

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = DuplicateMessageException.class)
	public IncomingMessage registerMessage(String message)
			throws DuplicateMessageException {

		IncomingMessageDAO<IncomingMessage> msgDao = coreManager
				.createIncomingMessageDAO();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 0 - duplicatePeriod);
		Date beforeDate = cal.getTime();
		IncomingMessage existingMsg = msgDao
				.getByContentNonDuplicatable(message);

		if (existingMsg != null) {

			if (existingMsg.getMessageStatus() == IncMessageStatus.PROCESSING)
				throw new DuplicateProcessingException(
						"message is already processing");

			IncomingMessageForm existingMsgForm = existingMsg
					.getIncomingMessageForm();
			if (existingMsgForm != null
					&& (existingMsgForm.getMessageFormStatus() == IncMessageFormStatus.SERVER_VALID)
					&& (existingMsgForm.getIncomingMsgFormDefinition()
							.getDuplicatable() == Duplicatable.DISALLOWED || (existingMsgForm
							.getIncomingMsgFormDefinition().getDuplicatable() == Duplicatable.TIME_BOUND && existingMsg
							.getDateCreated().after(beforeDate)))) {
				throw new DuplicateMessageException("message exists");
			}
		}

		IncomingMessage newMsg = parser.parseRequest(message);

		return msgDao.save(newMsg);
	}

	/**
	 * @param duplicatePeriod
	 *            the duplicatePeriod to set
	 */
	public void setDuplicatePeriod(int duplicatePeriod) {
		this.duplicatePeriod = duplicatePeriod;
	}

	/**
	 * @param coreManager
	 *            the coreManager to set
	 */
	public void setCoreManager(CoreManager coreManager) {
		this.coreManager = coreManager;
	}

	/**
	 * @param parser
	 */
	public void setParser(IncomingMessageParser parser) {
		this.parser = parser;
	}
}
