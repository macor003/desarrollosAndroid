/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;

/**
 */
public class CreditsOperation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 255277495738607526L;

	/**
	 * Field id.
	 */
	private long id;
	/**
	 * Field description.
	 */
	private String description;

	/**
	 * Constructor for CreditsOperation.
	 * @param id long
	 * @param description String
	 */
	public CreditsOperation(long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	/**
	 * Constructor for CreditsOperation.
	 */
	public CreditsOperation() {
		super();
	}

	/**
	 * Method getId.
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * Method setId.
	 * @param id long
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Method getDescription.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method setDescription.
	 * @param description String
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method initialize.
	 * @param creditsOperation CreditsOperation
	 */
	public void initialize(CreditsOperation creditsOperation) {
		this.id = creditsOperation.getId();
		this.description = creditsOperation.getDescription();
	}

}
