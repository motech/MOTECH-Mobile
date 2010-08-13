package org.motechproject.mobile.imp.serivce;

/**
 * Represents a condition where there is a duplicate message being processed,
 * and we can't determine whether to ignore it until it completes.
 * 
 * @author batkinson
 * 
 */
public class DuplicateProcessingException extends DuplicateMessageException {

	private static final long serialVersionUID = 7105826763316667044L;

	public DuplicateProcessingException() {
	}

	public DuplicateProcessingException(String message) {
		super(message);
	}

	public DuplicateProcessingException(Throwable cause) {
		super(cause);
	}

	public DuplicateProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

}
