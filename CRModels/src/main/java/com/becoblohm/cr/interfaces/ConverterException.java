/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.interfaces;

/**
 */
public class ConverterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Field entity.
	 */
	private String entity;

	/**
	 * Field errorMessage.
	 */
	private String errorMessage;

	/**
	 * Field entityValue.
	 */
	private String entityValue;

	private Exception exception;

	/**
	 * Constructor for ConverterException.
	 * 
	 * @param entityTmp
	 *            String
	 * @param messageTmp
	 *            String
	 * @param entityValueTmp
	 *            String
	 */
	public ConverterException(String entityTmp, String messageTmp, String entityValueTmp) {
		super();
		this.entity = entityTmp;
		this.errorMessage = messageTmp;
		this.entityValue = entityValueTmp;
		this.exception = null;
	}

	public ConverterException(String entityTmp, Exception e, String entityValueTmp) {
		super();
		this.entity = entityTmp;
		this.exception = e;
		this.entityValue = entityValueTmp;
	}

	/**
	 * Method getEntity.
	 * 
	 * @return String
	 */
	public String getEntity() {
		return entity;
	}

	// public void setEntity(String entity) {
	// this.entity = entity;
	// }
	/**
	 * Method getErrorMessage.
	 * 
	 * @return String
	 */
	public String getErrorMessage() {
		String complexMessage = entity + "->" + entityValue + ": " + errorMessage;
		return complexMessage;
	}

	// public void setErrorMessage(String errorMessage) {
	// this.errorMessage = errorMessage;
	// }
	/**
	 * Method getEntityValue.
	 * 
	 * @return String
	 */
	public String getEntityValue() {
		return entityValue;
	}
	// public void setEntityValue(String entityValue) {
	// this.entityValue = entityValue;
	// }

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
