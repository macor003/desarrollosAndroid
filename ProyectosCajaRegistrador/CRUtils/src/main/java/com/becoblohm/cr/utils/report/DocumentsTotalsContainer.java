/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.utils.report;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class DocumentsTotalsContainer {

	/**
	 * Field totalGravado.
	 */
	private CRBigDecimal totalGravado;
	/**
	 * Field totalExento.
	 */
	private CRBigDecimal totalExento;
	/**
	 * Field totalImpuesto.
	 */
	private CRBigDecimal totalImpuesto;
	/**
	 * Field totalIVAPercibido.
	 */
	private CRBigDecimal totalIVAPercibido;
	/**
	 * Field totalDiario.
	 */
	private CRBigDecimal totalDiario;
	/**
	 * Field showsTax.
	 */
	private boolean showsTax;
	/**
	 * Field saleSupport.
	 */
	private boolean saleSupport;
	/**
	 * Field counterpart.
	 */
	private boolean counterpart;

	/**
	 * Constructor for DocumentsTotalsContainer.
	 */
	public DocumentsTotalsContainer() {
		this.totalGravado = CRBigDecimal.ZERO;
		this.totalExento = CRBigDecimal.ZERO;
		this.totalImpuesto = CRBigDecimal.ZERO;
		this.totalIVAPercibido = CRBigDecimal.ZERO;
		this.totalDiario = CRBigDecimal.ZERO;
	}


	/**
	 * Method getTotalGravado.
	 * 
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getTotalGravado() {
		return totalGravado;
	}

	/**
	 * Method setTotalGravado.
	 * 
	 * @param totalGravado
	 *            CRBigDecimal
	 */
	public void setTotalGravado(CRBigDecimal totalGravado) {
		this.totalGravado = totalGravado;
	}

	/**
	 * Method getTotalExento.
	 * 
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getTotalExento() {
		return totalExento;
	}

	/**
	 * Method setTotalExento.
	 * 
	 * @param totalExento
	 *            CRBigDecimal
	 */
	public void setTotalExento(CRBigDecimal totalExento) {
		this.totalExento = totalExento;
	}

	/**
	 * Method getTotalImpuesto.
	 * 
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getTotalImpuesto() {
		return totalImpuesto;
	}

	/**
	 * Method setTotalImpuesto.
	 * 
	 * @param totalImpuesto
	 *            CRBigDecimal
	 */
	public void setTotalImpuesto(CRBigDecimal totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	/**
	 * Method getTotalIVAPercibido.
	 * 
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getTotalIVAPercibido() {
		return totalIVAPercibido;
	}

	/**
	 * Method setTotalIVAPercibido.
	 * 
	 * @param totalIVAPercibido
	 *            CRBigDecimal
	 */
	public void setTotalIVAPercibido(CRBigDecimal totalIVAPercibido) {
		this.totalIVAPercibido = totalIVAPercibido;
	}

	/**
	 * Method getTotalDiario.
	 * 
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getTotalDiario() {
		return totalDiario;
	}

	/**
	 * Method setTotalDiario.
	 * 
	 * @param totalDiario
	 *            CRBigDecimal
	 */
	public void setTotalDiario(CRBigDecimal totalDiario) {
		this.totalDiario = totalDiario;
	}

	/**
	 * Method isShowsTax.
	 * 
	 * @return boolean
	 */
	public boolean isShowsTax() {
		return showsTax;
	}

	/**
	 * Method setShowsTax.
	 * 
	 * @param showsTax
	 *            boolean
	 */
	public void setShowsTax(boolean showsTax) {
		this.showsTax = showsTax;
	}

	/**
	 * Method isSaleSupport.
	 * 
	 * @return boolean
	 */
	public boolean isSaleSupport() {
		return saleSupport;
	}

	/**
	 * Method setSaleSupport.
	 * 
	 * @param saleSupport
	 *            boolean
	 */
	public void setSaleSupport(boolean saleSupport) {
		this.saleSupport = saleSupport;
	}

	/**
	 * Method isCounterpart.
	 * 
	 * @return boolean
	 */
	public boolean isCounterpart() {
		return counterpart;
	}

	/**
	 * Method setCounterpart.
	 * 
	 * @param counterpart
	 *            boolean
	 */
	public void setCounterpart(boolean counterpart) {
		this.counterpart = counterpart;
	}
	
	public CRBigDecimal getTotalByType(){
		return this.totalGravado;
	}
}