/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;


/**
 */
public class EconomicActivity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 216347457992062855L;
	/**
	 * Field id.
	 */
	private long id;
	/**
	 * Field code.
	 */
	private int code;
	/**
	 * Field name.
	 */
	private String name;
	/**
	 * Field category.
	 */
	private int category;

	
	/**
	 * Constructor for EconomicActivity.
	 */
	public EconomicActivity() {
//		super();
	}

	/**
	 * Constructor for EconomicActivity.
	 * @param economicActivity com.becoblohm.cr.net.models.EconomicActivity
	 */
	public EconomicActivity(com.becoblohm.cr.net.models.EconomicActivity economicActivity) {
		this.id = economicActivity.getId();
		this.code = economicActivity.getCode();
		this.name = economicActivity.getName();
		this.category = economicActivity.getCategory();
	}
	
	/**
	 * Constructor for EconomicActivity.
	 * @param economicActivity long
	 */
	public EconomicActivity(long economicActivity) {
		this.id = economicActivity;
	}
	/**
	
	 * @return the id */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	
	 * @return the code */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	
	 * @return the name */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	
	 * @return the category */
	public int getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}
}
