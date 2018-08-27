/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;

import com.becoblohm.cr.net.types.CRBigDecimal;

/**
 */
public class Money implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2983836976867298096L;
	/**
	 * Field currencyId.
	 */
	private long currencyId;
	/**
	 * Field localAmmount.
	 */
	private CRBigDecimal localAmmount;
	/**
	 * Field currencyAmmount.
	 */
	private CRBigDecimal currencyAmmount;
	/**
	 * Field name.
	 */
	private String name;
	/**
	 * Field exchangeRate.
	 */
	private CRBigDecimal exchangeRate;

	/**
	 * Constructor for Money.
	 * @param defaultMoney Money
	 */
	public Money(Money defaultMoney) {
		super();
		this.currencyId = defaultMoney.currencyId;
		this.localAmmount = defaultMoney.localAmmount;
		this.currencyAmmount = defaultMoney.currencyAmmount;
		this.name = defaultMoney.name;
		this.exchangeRate = defaultMoney.exchangeRate;
	}

	/**
	 * Constructor for Money.
	 */
	public Money() {

	}

	/**
	 * Method getCurrencyId.
	 * @return long
	 */
	public long getCurrencyId() {
		return currencyId;
	}

	/**
	 * Method setCurrencyId.
	 * @param currencyId long
	 */
	public void setCurrencyId(long currencyId) {
		this.currencyId = currencyId;
	}

	/**
	 * Method getLocalAmmount.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getLocalAmmount() {
		return localAmmount;
	}

	/**
	 * Method setLocalAmmount.
	 * @param localAmmount CRBigDecimal
	 */
	public void setLocalAmmount(CRBigDecimal localAmmount) {
		this.localAmmount = localAmmount;
		if (this.exchangeRate != null) {
			CRBigDecimal tmp = this.localAmmount.divide(new CRBigDecimal(exchangeRate.doubleValue(), 3));
			this.currencyAmmount = tmp;
		}
	}

	/**
	 * Method getCurrencyAmmount.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getCurrencyAmmount() {
		return currencyAmmount;
	}

	/**
	 * Method setCurrencyAmmount.
	 * @param currencyAmmount CRBigDecimal
	 */
	public void setCurrencyAmmount(CRBigDecimal currencyAmmount) {
		this.currencyAmmount = currencyAmmount;
		if (this.exchangeRate != null) {
			CRBigDecimal tmp = this.currencyAmmount.multiply(new CRBigDecimal(exchangeRate.doubleValue(), 3));
			this.localAmmount = tmp;
		}
	}

	/**
	 * Method getName.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method setName.
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method getExchangeRate.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getExchangeRate() {
		return exchangeRate;
	}

	/**
	 * Method setExchangeRate.
	 * @param exchangeRate CRBigDecimal
	 */
	public void setExchangeRate(CRBigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

}
