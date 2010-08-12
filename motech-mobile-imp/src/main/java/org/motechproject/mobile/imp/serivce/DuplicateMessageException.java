package org.motechproject.mobile.imp.serivce;

/**
 * Represents the condition where a submitted form is not unique.
 * 
 * @author batkinson
 * 
 */
public class DuplicateMessageException extends Exception {

	private static final long serialVersionUID = 3781073598502904645L;

	public DuplicateMessageException() {
	}

	public DuplicateMessageException(String message) {
		super(message);

	}

	public DuplicateMessageException(Throwable cause) {
		super(cause);

	}

	public DuplicateMessageException(String message, Throwable cause) {
		super(message, cause);
	}

}
