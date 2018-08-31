package com.becoblohm.cr.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

public class PaymentMethodDetail extends AbstractModel {

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
	 * Field multiple.
	 */
	private CRBigDecimal multiple;
	
	/**
	 * Field isActive.
	 */
	private String isActive;
	
	/**
	 * Field date.
	 */
	private Date date;
	
	/**
	 * Field store.
	 */
	private String store;
	
	/**
	 * Field commissionPercentage.
	 */
	private CRBigDecimal commissionPercentage;
	
	/**
	 * Field taxPercentage.
	 */
	private CRBigDecimal taxPercentage;
	
	/**
	 * Field taxAccountingAccount.
	 */
	private String taxesAccountingAccount;
	
	/**
	 * Field accountingAcount.
	 */
	private String accountingAcount;

	/**
	 * Field expensesAccountingAcount.
	 */
	private String expensesAccountingAcount;

	/**
	 * Field recordState.
	 */
	private char recordState;
	
	/**
	 * Field auxiliary.
	 */
	private String auxiliary;
	
	/**
	 * Field idMoney.
	 */
	private Money idMoney;
	
	/**
	 * Field idPaymentMethod.
	 */
	private PaymentMethod idPaymentMethod;

	
	/**
	 * @return the id
	 */
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
	 * @return the code
	 */
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
	 * @return the name
	 */
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
	 * @return the multiple
	 */
	public CRBigDecimal getMultiple() {
		return multiple;
	}

	/**
	 * @param multiple the multiple to set
	 */
	public void setMultiple(CRBigDecimal multiple) {
		this.multiple = multiple;
	}

	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the store
	 */
	public String getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(String store) {
		this.store = store;
	}

	/**
	 * @return the commissionPercentage
	 */
	public CRBigDecimal getCommissionPercentage() {
		return commissionPercentage;
	}

	/**
	 * @param commissionPercentage the commissionPercentage to set
	 */
	public void setCommissionPercentage(CRBigDecimal commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	/**
	 * @return the taxPercentage
	 */
	public CRBigDecimal getTaxPercentage() {
		return taxPercentage;
	}

	/**
	 * @param taxPercentage the taxPercentage to set
	 */
	public void setTaxPercentage(CRBigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	
	/**
	 * @return the taxesAccountingAccount
	 */
	public String getTaxesAccountingAccount() {
		return taxesAccountingAccount;
	}

	/**
	 * @param taxesAccountingAccount the taxesAccountingAccount to set
	 */
	public void setTaxesAccountingAccount(String taxesAccountingAccount) {
		this.taxesAccountingAccount = taxesAccountingAccount;
	}

	/**
	 * @return the accountingAcount
	 */
	public String getAccountingAcount() {
		return accountingAcount;
	}

	/**
	 * @param accountingAcount the accountingAcount to set
	 */
	public void setAccountingAcount(String accountingAcount) {
		this.accountingAcount = accountingAcount;
	}

	/**
	 * @return the expensesAccountingAcount
	 */
	public String getExpensesAccountingAcount() {
		return expensesAccountingAcount;
	}

	/**
	 * @param expensesAccountingAcount the expensesAccountingAcount to set
	 */
	public void setExpensesAccountingAcount(String expensesAccountingAcount) {
		this.expensesAccountingAcount = expensesAccountingAcount;
	}

	/**
	 * @return the recordState
	 */
	public char getRecordState() {
		return recordState;
	}

	/**
	 * @param recordState the recordState to set
	 */
	public void setRecordState(char recordState) {
		this.recordState = recordState;
	}

	/**
	 * @return the auxiliary
	 */
	public String getAuxiliary() {
		return auxiliary;
	}

	/**
	 * @param auxiliary the auxiliary to set
	 */
	public void setAuxiliary(String auxiliary) {
		this.auxiliary = auxiliary;
	}

	/**
	 * @return the idMoney
	 */
	public Money getIdMoney() {
		return idMoney;
	}

	/**
	 * @param idMoney the idMoney to set
	 */
	public void setIdMoney(Money idMoney) {
		this.idMoney = idMoney;
	}

	/**
	 * @return the idPaymentMethod
	 */
	public PaymentMethod getIdPaymentMethod() {
		return idPaymentMethod;
	}

	/**
	 * @param idPaymentMethod the idPaymentMethod to set
	 */
	public void setIdPaymentMethod(PaymentMethod idPaymentMethod) {
		this.idPaymentMethod = idPaymentMethod;
	}
	
	
	

}
