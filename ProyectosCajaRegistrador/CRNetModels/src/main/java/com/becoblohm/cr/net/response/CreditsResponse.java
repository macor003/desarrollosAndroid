/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.response;

/**
 */
public class CreditsResponse extends RMIServerResponse {

	/**
	 * Field OK.
	 * (value is 0)
	 */
	public static final int OK = 0;
	/**
	 * Field CHANGE_PASSWORD.
	 * (value is 1)
	 */
	public static final int CHANGE_PASSWORD = 1;
	/**
	 * Field INSUFFICIENT_BALANCE.
	 * (value is 2)
	 */
	public static final int INSUFFICIENT_BALANCE = 2;
	/**
	 * Field INVALID_ACCOUNT.
	 * (value is 3)
	 */
	public static final int INVALID_ACCOUNT = 3;
	/**
	 * Field INVALID_PASSWORD.
	 * (value is 4)
	 */
	public static final int INVALID_PASSWORD = 4;
	/**
	 * Field INVALID_PROCESS.
	 * (value is 5)
	 */
	public static final int INVALID_PROCESS = 5;
	/**
	 * Field LAST_PROCESS_NOT_FOUND.
	 * (value is 6)
	 */
	public static final int LAST_PROCESS_NOT_FOUND = 6;
	/**
	 * Field SERVER_OFF_LINE.
	 * (value is 7)
	 */
	public static final int SERVER_OFF_LINE = 7;
	/**
	 * Field LAST_PROCESS_HAVE_PAYMENT_DISABLE.
	 * (value is 8)
	 */
	public static final int LAST_PROCESS_HAVE_PAYMENT_DISABLE = 8;
	/**
	 * Field ACCOUNT_BLOCKED.
	 * (value is 9)
	 */
	public static final int ACCOUNT_BLOCKED = 9;
	/**
	 * Field PROCESS_CANCELLATED.
	 * (value is 10)
	 */
	public static final int PROCESS_CANCELLATED = 10;
	/**
	 * Field LAST_PROCESS_HAVE_CREDITS_PAYMENT.
	 * (value is 11)
	 */
	public static final int LAST_PROCESS_HAVE_CREDITS_PAYMENT = 11;
	/**
	 * Field PARTIAL.
	 * (value is 98)
	 */
	public static final int PARTIAL = 98;
	/**
	 * Field PARTIAL.
	 * (value is 97)
	 */
	public static final int ROLLBACK = 97;

	/**
	 * Constructor for CreditsResponse.
	 * @param msg String
	 * @param data Object
	 */
	public CreditsResponse(String msg, Object data) {
		super(msg, data);
	}

	/**
	 * Constructor for CreditsResponse.
	 */
	public CreditsResponse() {
		super();
	}

}
