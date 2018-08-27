/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.util.Date;

import com.becoblohm.cr.net.types.CRBigDecimal;

/**
 * @author programa13
 * 
 * @version $Revision: 1.0 $
 */
public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7215573815640862824L;

	/**
	 * Constructor for Session.
	 */
	public Session() {
		super();
		salesCount = 0;
		anulCount = 0;
		devCount = 0;
		setCashMoney(CRBigDecimal.ZERO);
		setDeliveredExceeded(false);
	}

	/**
	 * Field deliveredExceeded.
	 */
	private boolean deliveredExceeded;
	/**
	 * Field id.
	 */
	private long id;
	/**
	 * Field salesCount.
	 */
	private int salesCount;
	/**
	 * Field anulCount.
	 */
	private int anulCount;
	/**
	 * Field devCount.
	 */
	private int devCount;
	/**
	 * Field end.
	 */
	/**
	 * Field begin.
	 */
	private Date begin, end;
	/**
	 * Field cashier.
	 */
	private User cashier;
	/**
	 * Field storeId.
	 */
	protected String storeId;
	/**
	 * Field posId.
	 */
	protected String posId;
	/**
	 * Field cashMoney.
	 */
	private CRBigDecimal cashMoney = CRBigDecimal.ZERO;
	/**
	 * Field closureDate.
	 */
	private Date closureDate;
	/**
	 * Field vposClosureDate.
	 */
	private Date vposClosureDate;
	/**
	 * Field zReport_printDate.
	 */
	private Date zReport_printDate;
	/**
	 * Field idTransactionExceeded.
	 */
	private String idTransactionExceeded;
	/**
	 * Field serverSessionId.
	 */
	private long serverSessionId;

	/**
	
	 * @return the id */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	
	 * @return the salesCount */
	public int getSalesCount() {
		return salesCount;
	}

	/**
	 * @param salesCount
	 *            the salesCount to set
	 */
	public void setSalesCount(int salesCount) {
		this.salesCount = salesCount;
	}

	/**
	
	 * @return the anulCount */
	public int getAnulCount() {
		return anulCount;
	}

	/**
	 * @param anulCount
	 *            the anulCount to set
	 */
	public void setAnulCount(int anulCount) {
		this.anulCount = anulCount;
	}

	/**
	
	 * @return the devCount */
	public int getDevCount() {
		return devCount;
	}

	/**
	 * @param devCount
	 *            the devCount to set
	 */
	public void setDevCount(int devCount) {
		this.devCount = devCount;
	}

	/**
	
	 * @return the begin */
	public Date getBegin() {
		return begin;
	}

	/**
	 * @param begin
	 *            the begin to set
	 */
	public void setBegin(Date begin) {
		this.begin = begin;
	}

	/**
	
	 * @return the end */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	
	 * @return the cashier */
	public User getCashier() {
		return cashier;
	}

	/**
	 * @param cashier
	 *            the cashier to set
	 */
	public void setCashier(User cashier) {
		this.cashier = cashier;
	}

	/**
	 * Method getStoreId.
	 * @return String
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * Method setStoreId.
	 * @param storeId String
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * Method getPosId.
	 * @return String
	 */
	public String getPosId() {
		return posId;
	}

	/**
	 * Method setPosId.
	 * @param posId String
	 */
	public void setPosId(String posId) {
		String tmp = this.posId;
		this.posId = posId;

	}

	/**
	 * @param cashMoney
	 *            the cashMoney to set
	 */
	public void setCashMoney(CRBigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	/**
	
	 * @return the cashMoney */
	public CRBigDecimal getCashMoney() {
		return cashMoney;
	}

	/**
	 * @param deliveredExceeded
	 *            the deliveredExceeded to set
	 */
	public void setDeliveredExceeded(boolean deliveredExceeded) {
		this.deliveredExceeded = deliveredExceeded;
	}

	/**
	
	 * @return the deliveredExceeded */
	public boolean isDeliveredExceeded() {
		return deliveredExceeded;
	}

	/**
	 * @param vposClosureDate
	 *            the vposClosureDate to set
	 */
	public void setVposClosureDate(Date vposClosureDate) {
		this.vposClosureDate = vposClosureDate;
	}

	/**
	
	 * @return the vposClosureDate */
	public Date getVposClosureDate() {
		return vposClosureDate;
	}

	/* Fecha y hora de la Sesion */
	/**
	 * Method getDateTime.
	 * @return Date
	 */
	public Date getDateTime() {
		return new Date();
	}

	/**
	 * @param idTransactionExceeded
	 *            the idTransactionExceeded to set
	 */
	public void setIdTransactionExceeded(String idTransactionExceeded) {
		this.idTransactionExceeded = idTransactionExceeded;
	}

	/**
	
	 * @return the idTransactionExceeded */
	public String getIdTransactionExceeded() {
		return idTransactionExceeded;
	}

	/**
	 * Method getzReport_printDate.
	 * @return Date
	 */
	public Date getzReport_printDate() {
		return zReport_printDate;
	}

	/**
	 * Method setzReport_printDate.
	 * @param zReport_printDate Date
	 */
	public void setzReport_printDate(Date zReport_printDate) {
		this.zReport_printDate = zReport_printDate;
	}

	// public void setClosureDate(Date closureDate) {
	// this.closureDate = closureDate;
	// }
	// public Date getClosureDate() {
	// return closureDate;
	// }

	/**
	 * Method clear.
	 */
	public void clear() {

		id = 0;
		salesCount = 0;
		anulCount = 0;
		devCount = 0;
		begin = end = closureDate = vposClosureDate = zReport_printDate = null;
		cashier = null;

		idTransactionExceeded = "";
		setCashMoney(CRBigDecimal.ZERO);
		setDeliveredExceeded(false);

	}

}
