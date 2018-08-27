/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.util.Date;

import com.becoblohm.cr.net.types.CRBigDecimal;

/**
 * 
 * @author Yelitza Farfan (programador11)
 * 
 * @version $Revision: 1.0 $
 */
public class PayMethodCreditsMovement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3428545031583456935L;
	/**
	 * Field id.
	 */
	private int id;
	/**
	 * Field idMoney.
	 */
	private Money idMoney;
	/**
	 * Field idCreditsMovement.
	 */
	private CreditsMovement idCreditsMovement;
	/**
	 * Field idPayMethod.
	 */
	private PaymentMethod idPayMethod;
	/**
	 * Field date.
	 */
	private Date date;
	/**
	 * Field owner.
	 */
	private String owner;
	/**
	 * Field amount.
	 */
	private CRBigDecimal amount;
	/**
	 * Field isActive.
	 */
	private boolean isActive;
	/**
	 * Field conformation.
	 */
	private String conformation;
	/**
	 * Field account.
	 */
	private String account;
	/**
	 * Field document.
	 */
	private String document;
	/**
	 * Field moneyAmount.
	 */
	private CRBigDecimal moneyAmount;

	/**
	 * Constructor for PayMethodCreditsMovement.
	 */
	public PayMethodCreditsMovement() {

	}

	/**
	
	 * @return the id */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	
	 * @return the date */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	
	 * @return the owner */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	
	 * @return the amount */
	public CRBigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(CRBigDecimal amount) {
		this.amount = amount;
	}

	/**
	
	 * @return the isActive */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	
	 * @return the conformation */
	public String getConformation() {
		return conformation;
	}

	/**
	 * @param conformation
	 *            the conformation to set
	 */
	public void setConformation(String conformation) {
		this.conformation = conformation;
	}

	/**
	
	 * @return the account */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	
	 * @return the document */
	public String getDocument() {
		return document;
	}

	/**
	 * @param document
	 *            the document to set
	 */
	public void setDocument(String document) {
		this.document = document;
	}

	/**
	
	 * @return the moneyAmount */
	public CRBigDecimal getMoneyAmount() {
		return moneyAmount;
	}

	/**
	 * @param moneyAmount
	 *            the moneyAmount to set
	 */
	public void setMoneyAmount(CRBigDecimal moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	/**
	 * Method setIdMoney.
	 * @param idMoney Money
	 */
	public void setIdMoney(Money idMoney) {
		this.idMoney = idMoney;
	}

	/**
	 * Method getIdMoney.
	 * @return Money
	 */
	public Money getIdMoney() {
		return idMoney;
	}

	/**
	 * Method setIdCreditsMovement.
	 * @param idCreditsMovement CreditsMovement
	 */
	public void setIdCreditsMovement(CreditsMovement idCreditsMovement) {
		this.idCreditsMovement = idCreditsMovement;
	}

	/**
	 * Method getIdCreditsMovement.
	 * @return CreditsMovement
	 */
	public CreditsMovement getIdCreditsMovement() {
		return idCreditsMovement;
	}

	/**
	 * Method setIdPayMethod.
	 * @param idPayMethod PaymentMethod
	 */
	public void setIdPayMethod(PaymentMethod idPayMethod) {
		this.idPayMethod = idPayMethod;
	}

	/**
	 * Method getIdPayMethod.
	 * @return PaymentMethod
	 */
	public PaymentMethod getIdPayMethod() {
		return idPayMethod;
	}

}
