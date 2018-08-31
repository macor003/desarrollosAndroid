/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;

import com.becoblohm.cr.net.types.CRBigDecimal;

/**
 * 
 * @author Yelitza Farfan (programador11)
 * 
 * @version $Revision: 1.0 $
 */

public class CreditsBalance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3807466189250159470L;
	/**
	 * Field id.
	 */
	private Long id;
	/**
	 * Field creditsTypeId.
	 */
	private CreditsType creditsTypeId;
	/**
	 * Field creditsNumber.
	 */
	private CreditsAccount creditsNumber;
	/**
	 * Field amount.
	 */
	private CRBigDecimal amount;
	/**
	 * Field blocked.
	 */
	private CRBigDecimal blocked;

	/**
	 * Constructor for CreditsBalance.
	 */
	public CreditsBalance() {
	}

	/**
	 * Constructor for CreditsBalance.
	 * @param id Long
	 */
	public CreditsBalance(Long id) {
		this.id = id;
	}

	/**
	 * Method setCreditsNumber.
	 * @param creditsNumber CreditsAccount
	 */
	public void setCreditsNumber(CreditsAccount creditsNumber) {
		this.creditsNumber = creditsNumber;
	}

	/**
	 * Method getCreditsNumber.
	 * @return CreditsAccount
	 */
	public CreditsAccount getCreditsNumber() {
		return creditsNumber;
	}

	/**
	 * Method setAmount.
	 * @param amount CRBigDecimal
	 */
	public void setAmount(CRBigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Method getAmount.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getAmount() {
		return amount;
	}

	/**
	 * Method setBlocked.
	 * @param blocked CRBigDecimal
	 */
	public void setBlocked(CRBigDecimal blocked) {
		this.blocked = blocked;
	}

	/**
	 * Method getBlocked.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getBlocked() {
		return blocked;
	}

	/**
	 * Method setId.
	 * @param id Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method getId.
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Method setCreditsTypeId.
	 * @param creditsTypeId CreditsType
	 */
	public void setCreditsTypeId(CreditsType creditsTypeId) {
		this.creditsTypeId = creditsTypeId;
	}

	/**
	 * Method getCreditsTypeId.
	 * @return CreditsType
	 */
	public CreditsType getCreditsTypeId() {
		return creditsTypeId;
	}

}
