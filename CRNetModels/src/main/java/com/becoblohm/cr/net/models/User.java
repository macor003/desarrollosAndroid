/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.util.Date;

/**
 */
public class User implements Serializable {

	/**
	 * Field id.
	 */
	protected Long id;
	/**
	 * Field idEPA.
	 */
	protected int idEPA;
	/**
	 * Field name.
	 */
	protected String name;
	/**
	 * Field idDocument.
	 */
	protected String idDocument;
	/**
	 * Field finger.
	 */
	protected byte[] finger;
	/**
	 * Field pass.
	 */
	protected String pass;
	/**
	 * Field lastPasswordChange.
	 */
	private Date lastPasswordChange;

	/**
	 * Constructor for User.
	 */
	public User() {

	}

	/**
	 * Constructor for User.
	 * @param id Long
	 * @param idEPA int
	 * @param name String
	 * @param idDocument String
	 */
	public User(Long id, int idEPA, String name, String idDocument) {
		super();
		this.id = id;
		this.idEPA = idEPA;
		this.name = name;
		this.idDocument = idDocument;
	}

	/**
	
	 * @return the id */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	
	 * @return the idEPA */
	public int getIdEPA() {
		return idEPA;
	}

	/**
	 * @param idEPA
	 *            the idEPA to set
	 */
	public void setIdEPA(int idEPA) {
		int tmp = this.idEPA;
		this.idEPA = idEPA;
		//

	}

	/**
	
	 * @return the name */
	public String getName() {
		return name;

	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		String tmp = this.name;
		this.name = name;

	}

	/**
	
	 * @return the idDocument */
	public String getIdDocument() {
		return idDocument;
	}

	/**
	 * @param idDocument
	 *            the idDocument to set
	 */
	public void setIdDocument(String idDocument) {
		this.idDocument = idDocument;
	}

	/**
	 * @param finger
	 *            the finger to set
	 */
	public void setFinger(byte[] finger) {
		this.finger = finger;
	}

	/**
	
	 * @return the finger */
	public byte[] getFinger() {
		return finger;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		String tmp = this.pass;
		this.pass = pass;

	}

	/**
	
	 * @return the pass */
	public String getPass() {
		return pass;
	}

	/**
	 * Method setLastPasswordChange.
	 * @param lastPasswordChange Date
	 */
	public void setLastPasswordChange(Date lastPasswordChange) {
		this.lastPasswordChange = lastPasswordChange;
	}

	/**
	
	 * @return the lastPasswordChange */
	public Date getLastPasswordChange() {
		return lastPasswordChange;
	}

}
