package com.becoblohm.cr.utils.report;

import com.becoblohm.cr.types.CRBigDecimal;

public class ReportsDetailsContainer {
	/**
	 * Field positive.
	 */
	private CRBigDecimal positive = CRBigDecimal.ZERO;
	/**
	 * Field negative.
	 */
	private CRBigDecimal negative = CRBigDecimal.ZERO;
	/**
	 * Field name.
	 */
	private String name = "";
	
	private String monedaSimbolo = "";

	private String formaDePago = "";

	/**
	 * Method getPositive.
	 * 
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getPositive() {
		return positive;
	}

	/**
	 * Method setPositive.
	 * 
	 * @param positive
	 *            CRBigDecimal
	 */
	public void setPositive(CRBigDecimal positive) {
		this.positive = positive;
	}

	/**
	 * Method getNegative. invierte el valor 
	 * 
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getNegative() {
		return negative;
	}

	/**
	 * Method setNegative.
	 * 
	 * @param negative
	 *            CRBigDecimal
	 */
	public void setNegative(CRBigDecimal negative) {
		this.negative = negative;
	}

	/**
	 * Method setName.
	 * 
	 * @param name
	 *            String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method getName.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	public String getMonedaSimbolo() {
		return monedaSimbolo;
	}

	public void setMonedaSimbolo(String monedaSimbolo) {
		this.monedaSimbolo = monedaSimbolo;
	}
	
	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	/**
	 * Suma algebraica de toAdd + this.positive
	 * @param toAdd
	 * @return 
	 */
	public CRBigDecimal addToPositive(CRBigDecimal toAdd){
		this.positive.add(toAdd);
		return this.positive;
	}
	
	/**
	 * Suma algebraica de toAdd + this.negative
	 * @param toAdd
	 * @return 
	 */
	public CRBigDecimal addToNegative(CRBigDecimal toAdd){
		this.negative.add(toAdd);
		return this.negative;
	}
}