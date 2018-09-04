/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 */
public class CreditsAccount implements Serializable /* extends AbstractModel */{

	/**
	 * 
	 */
	private static final long serialVersionUID = 255277495738607526L;

	/**
	 * Field creditsId.
	 */
	private String creditsId;/**/
	/**
	 * Field client.
	 */
	private Client client;/* Id Cliente */
	/**
	 * Field password.
	 */
	private String password;
	/**
	 * Field changePassword.
	 */
	private boolean changePassword;
	/**
	 * Field idAutorized.
	 */
	private String idAutorized;
	/**
	 * Field creditsBalance.
	 */
	private ArrayList creditsBalance;
	/**
	 * Field creditsMovement.
	 */
	private ArrayList creditsMovement;
	/**
	 * Field status.
	 */
	private String status;

	/**
	 * Constructor for CreditsAccount.
	 * @param client Client
	 * @param password String
	 */
	public CreditsAccount(Client client, String password) {
		super();
		this.client = client;
		this.password = password;
	}

	/**
	 * Constructor for CreditsAccount.
	 */
	public CreditsAccount() {
		super();
		this.client = new Client();
		this.password = "";
	}

	/**
	 * Method getCreditsId.
	 * @return String
	 */
	public String getCreditsId() {
		return creditsId;
	}

	/**
	 * Method setCreditsId.
	 * @param creditsId String
	 */
	public void setCreditsId(String creditsId) {
		this.creditsId = creditsId;
	}

	/**
	 * Method getClient.
	 * @return Client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Method setClient.
	 * @param client Client
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Method getPassword.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method setPassword.
	 * @param password String
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method getSerialversionuid.
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param changePassword
	 *            the changePassword to set
	 */
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	/**
	
	 * @return the changePassword */
	public boolean isChangePassword() {
		return changePassword;
	}

	/**
	 * Method getIdAutorized.
	 * @return String
	 */
	public String getIdAutorized() {
		return idAutorized;
	}

	/**
	 * Method setIdAutorized.
	 * @param idAutorized String
	 */
	public void setIdAutorized(String idAutorized) {
		this.idAutorized = idAutorized;
	}

	/**
	 * Method setCreditsBalance.
	 * @param creditsBalance ArrayList
	 */
	public void setCreditsBalance(ArrayList creditsBalance) {
		this.creditsBalance = creditsBalance;
	}

	/**
	 * Method getCreditsBalance.
	 * @return ArrayList
	 */
	public ArrayList getCreditsBalance() {
		return creditsBalance;
	}

	/**
	 * Method setCreditsMovement.
	 * @param creditsMovement ArrayList
	 */
	public void setCreditsMovement(ArrayList creditsMovement) {
		this.creditsMovement = creditsMovement;
	}

	/**
	 * Method getCreditsMovement.
	 * @return ArrayList
	 */
	public ArrayList getCreditsMovement() {
		return creditsMovement;
	}

	/**
	 * Method setStatus.
	 * @param status String
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Method getStatus.
	 * @return String
	 */
	public String getStatus() {
		return status;
	}

}
