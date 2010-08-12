package org.motechproject.mobile.imp.serivce;

import org.motechproject.mobile.core.model.IncomingMessage;

/**
 * An interface defining a registry abstraction, useful for ensuring that
 * duplicate messages are not processed.
 * 
 * @author batkinson
 * 
 */
public interface MessageRegistry {

	IncomingMessage registerMessage(String message)
			throws DuplicateMessageException;

}
