package crjpa.exceptions;

/**
 */
public class PreexistingEntityException extends Exception {
	/**
	 * Constructor for PreexistingEntityException.
	 * 
	 * @param message
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public PreexistingEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for PreexistingEntityException.
	 * 
	 * @param message
	 *            String
	 */
	public PreexistingEntityException(String message) {
		super(message);
	}
}
