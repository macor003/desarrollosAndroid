package com.becoblohm.cr.crjpa400.controller.exceptions;

/**
 */
public class JpaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7877944568481751602L;

	/**
	 * Constructor for JpaException.
	 */
	public JpaException() {
	}

	/**
	 * Constructor for JpaException.
	 * 
	 * @param message
	 *            String
	 */
	public JpaException(String message) {
		super(message);
	}

	/**
	 * Constructor for JpaException.
	 * 
	 * @param message
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public JpaException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for JpaException.
	 * 
	 * @param cause
	 *            Throwable
	 */
	public JpaException(Throwable cause) {
		super(cause);
	}

}
