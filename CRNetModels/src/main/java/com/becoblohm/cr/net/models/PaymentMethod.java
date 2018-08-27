/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;
import java.sql.Timestamp;

import com.becoblohm.cr.net.types.CRBigDecimal;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
/* FORMA DE PAGO */
public class PaymentMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -355674420671845113L;
	/**
	 * Field id.
	 */
	protected long id;
	/**
	 * Field code.
	 */
	protected long code;
	/**
	 * Field name.
	 */
	protected String name;
	/**
	 * Field description.
	 */
	protected String description;
	/**
	 * Field priority.
	 */
	protected int priority;
	/**
	 * Field donationPriority.
	 */
	protected int donationPriority;
	/**
	 * Field allowsChange.
	 */
	protected boolean allowsChange;
	/**
	 * Field allowsRePrint.
	 */
	private boolean allowsRePrint;
	/**
	 * Field active.
	 */
	protected boolean active;
	/**
	 * Field allowsClientChange.
	 */
	private boolean allowsClientChange;
	/**
	 * Field minAmount.
	 */
	protected CRBigDecimal minAmount;
	/**
	 * Field maxAmount.
	 */
	protected CRBigDecimal maxAmount;
	/**
	 * Field date.
	 */
	protected Timestamp date;
	/**
	 * Field maxTaxWithholdingPercentage.
	 */
	protected CRBigDecimal maxTaxWithholdingPercentage;
	/**
	 * Field minTaxWithholdingPercentage.
	 */
	protected CRBigDecimal minTaxWithholdingPercentage;
	/**
	 * Field cardType.
	 */
	protected String cardType;
	/**
	 * Field requiresAuthorization.
	 */
	protected boolean requiresAuthorization;
	/**
	 * Field dueLevel.
	 */
	protected Object dueLevel;
	/**
	 * Field paymentType.
	 */
	protected int paymentType;
	// protected StateMachineInterface stateMachine;
	/**
	 * Field paymentIcon.
	 */
	protected String paymentIcon;
	/**
	 * Field moneyIcon.
	 */
	protected String moneyIcon;
	/**
	 * Field moneyId.
	 */
	protected long moneyId;
	/**
	 * Field exchangeRate.
	 */
	private CRBigDecimal exchangeRate;

	/**
	 * Field enabled.
	 */
	private boolean enabled = true;

	// protected Collection<Currency> currencies = new ArrayList<Currency>();
	// private Collection<DetailsPaymentDelivery> detailsPaymentDelivery = new
	// ArrayList<DetailsPaymentDelivery>();
	// protected CashierDelivery cashierDeliveryParent;
	/**
	 * Field moneySymbol.
	 */
	private String moneySymbol;
	/**
	 * Field reverseAccept.
	 */
	private boolean reverseAccept;
	/**
	 * Field counterPart.
	 */
	private PaymentMethod counterPart;

	/*
	 * Constructores de la clase
	 */
	/**
	 * Constructor for PaymentMethod.
	 */
	public PaymentMethod() {
		// super();
	}

	/**
	 * Constructor for PaymentMethod.
	 * @param code int
	 * @param name String
	 * @param description String
	 * @param paymentType int
	 */
	public PaymentMethod(int code, String name, String description, int paymentType) {
		// super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.paymentType = paymentType;
	}

	/**
	 * Method initialize.
	 * @param paymentMethod PaymentMethod
	 */
	public void initialize(PaymentMethod paymentMethod) {

		this.id = paymentMethod.getId();
		this.code = paymentMethod.getCode();
		this.name = paymentMethod.getName();
		this.description = paymentMethod.getDescription();
		this.priority = paymentMethod.getPriority();
		this.donationPriority = paymentMethod.getDonationPriority();
		this.allowsChange = paymentMethod.allowsChange();
		this.active = paymentMethod.isActive();
		this.minAmount = paymentMethod.getMinAmount();
		this.maxAmount = paymentMethod.getMaxAmount();
		this.date = paymentMethod.getDate();
		this.maxTaxWithholdingPercentage = paymentMethod.getMaxTaxWithholdingPercentage();
		this.minTaxWithholdingPercentage = paymentMethod.getMinTaxWithholdingPercentage();
		this.cardType = paymentMethod.getCardType();
		this.requiresAuthorization = paymentMethod.requiresAuthorization();
		this.dueLevel = paymentMethod.getDueLevel();
		this.paymentType = paymentMethod.getPaymentType();
		this.paymentIcon = paymentMethod.getPaymentIcon();
		this.moneyIcon = paymentMethod.getMoneyIcon();
		this.moneyId = paymentMethod.getMoneyId();
		this.reverseAccept = paymentMethod.isReverseAccept();
		this.enabled = paymentMethod.isEnabled();
		this.allowsClientChange = paymentMethod.isAllowsClientChange();
		this.exchangeRate = paymentMethod.getExchangeRate();

		this.setAllowsRePrint(false);
		this.moneySymbol = paymentMethod.getMoneySymbol();
		// this.pm=paymentMethod.getPm();
	}

	/**
	
	 * @return the paymentType */
	public int getPaymentType() {
		return paymentType;
	}

	/**
	 * @param paymentType
	 *            the paymentType to set
	 */
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	/**
	 * Method getId.
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * Method setId.
	 * @param id long
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Method getCode.
	 * @return long
	 */
	public long getCode() {
		return code;
	}

	/**
	 * Method setCode.
	 * @param code long
	 */
	public void setCode(long code) {
		this.code = code;
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
	 * Method getDescription.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method setDescription.
	 * @param description String
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method setPriority.
	 * @param priority int
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Method getPriority.
	 * @return int
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Method getDonationPriority.
	 * @return int
	 */
	public int getDonationPriority() {
		return donationPriority;
	}

	/**
	 * Method setDonationpriority.
	 * @param donationpriority int
	 */
	public void setDonationpriority(int donationpriority) {
		this.donationPriority = donationpriority;
	}

	/**
	 * Method allowsChange.
	 * @return boolean
	 */
	public boolean allowsChange() {
		return allowsChange;
	}

	/**
	 * Method setAllowsChange.
	 * @param allowsChange boolean
	 */
	public void setAllowsChange(boolean allowsChange) {
		this.allowsChange = allowsChange;
	}

	/**
	 * Method isActive.
	 * @return boolean
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Method setActive.
	 * @param active boolean
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Method getMinAmount.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getMinAmount() {
		return minAmount;
	}

	/**
	 * Method setMinAmount.
	 * @param minAmount CRBigDecimal
	 */
	public void setMinAmount(CRBigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * Method getMaxAmount.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getMaxAmount() {
		return maxAmount;
	}

	/**
	 * Method setMaxAmount.
	 * @param maxAmount CRBigDecimal
	 */
	public void setMaxAmount(CRBigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * Method getDate.
	 * @return Timestamp
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * Method setDate.
	 * @param date Timestamp
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * Method getMaxTaxWithholdingPercentage.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getMaxTaxWithholdingPercentage() {
		return maxTaxWithholdingPercentage;
	}

	/**
	 * Method setMaxTaxWithholdingPercentage.
	 * @param maxTaxWithholdingPercentage CRBigDecimal
	 */
	public void setMaxTaxWithholdingPercentage(CRBigDecimal maxTaxWithholdingPercentage) {
		this.maxTaxWithholdingPercentage = maxTaxWithholdingPercentage;
	}

	/**
	 * Method getMinTaxWithholdingPercentage.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getMinTaxWithholdingPercentage() {
		return minTaxWithholdingPercentage;
	}

	/**
	 * Method setMinTaxWithholdingPercentage.
	 * @param minTaxWithholdingPercentage CRBigDecimal
	 */
	public void setMinTaxWithholdingPercentage(CRBigDecimal minTaxWithholdingPercentage) {
		this.minTaxWithholdingPercentage = minTaxWithholdingPercentage;
	}

	/**
	 * Method requiresAuthorization.
	 * @return boolean
	 */
	public boolean requiresAuthorization() {
		return requiresAuthorization;
	}

	/**
	 * Method setRequiresAuthorization.
	 * @param requiresAuthorization boolean
	 */
	public void setRequiresAuthorization(boolean requiresAuthorization) {
		this.requiresAuthorization = requiresAuthorization;
	}

	/**
	 * Method getDueLevel.
	 * @return Object
	 */
	public Object getDueLevel() {
		return dueLevel;
	}

	/**
	 * Method setDueLevel.
	 * @param dueLevel Object
	 */
	public void setDueLevel(Object dueLevel) {
		this.dueLevel = dueLevel;
	}
	
	

	/**
	 * Method doReverse.
	 * @param payment Payment
	 * @return Payment
	 */
	public Payment doReverse(Payment payment) {
		payment.setDone(Payment.REVERSE_DONE);
		return payment;
	}

	/**
	 * Method doPayment.
	 * @param payment Payment
	 * @return Payment
	 */
	public Payment doPayment(Payment payment) {
		payment.setDone(Payment.PAY_DONE);
		return payment;
	}

	/**
	 * Method doReprint.
	 * @param payment Payment
	 * @return int
	 */
	public int doReprint(Payment payment) {
		payment.setDone(Payment.PAY_DONE);
		return payment.getDone();
	}

	/**
	
	 * @return the tipoTarjeta */
	public String getCardType() {
		return cardType;
	}

	/**
	
	 * @param p_string String
	 */
	public void setCardType(String p_string) {
		this.cardType = p_string;
	}

	/*
	 * public StateMachineInterface getStateMachine() { return stateMachine; }
	 * public void setStateMachine(StateMachineInterface stateMachine) {
	 * this.stateMachine = stateMachine; }
	 */
	/**
	 * Method getPaymentIcon.
	 * @return String
	 */
	public String getPaymentIcon() {
		return paymentIcon;
	}

	/**
	 * Method setPaymentIcon.
	 * @param paymentIcon String
	 */
	public void setPaymentIcon(String paymentIcon) {
		this.paymentIcon = paymentIcon;
	}

	/**
	 * Method getMoneyIcon.
	 * @return String
	 */
	public String getMoneyIcon() {
		return moneyIcon;
	}

	/**
	 * Method setMoneyIcon.
	 * @param moneyIcon String
	 */
	public void setMoneyIcon(String moneyIcon) {
		this.moneyIcon = moneyIcon;
	}

	/**
	 * Method getMoneyId.
	 * @return long
	 */
	public long getMoneyId() {
		return moneyId;
	}

	/**
	 * Method setMoneyId.
	 * @param moneyId long
	 */
	public void setMoneyId(long moneyId) {
		this.moneyId = moneyId;
	}

	/**
	
	 * @param moneySymbol String
	 */
	/*
	 * public Collection<Currency> getCurrencies() { return currencies; } /**
	 * 
	 * @param currencies the currencies to set
	 */
	/*
	 * public void setCurrencies(Collection<Currency> currencies) {
	 * this.currencies.addAll(currencies); } /**
	 * 
	 * @param cashierDeliveryParent the cashierDeliveryParent to set
	 */
	/*
	 * public void setCashierDeliveryParent(CashierDelivery
	 * cashierDeliveryParent) { this.cashierDeliveryParent =
	 * cashierDeliveryParent; } /**
	 * 
	 * @return the cashierDeliveryParent
	 */
	/*
	 * public CashierDelivery getCashierDeliveryParent() { return
	 * cashierDeliveryParent; } /**
	 * 
	 * @param moneySymbol the moneySymbol to set
	 */
	public void setMoneySymbol(String moneySymbol) {
		this.moneySymbol = moneySymbol;
	}

	/**
	
	 * @return the moneySymbol */
	public String getMoneySymbol() {
		return moneySymbol;
	}

	/**
	
	 * @param reverseAccept boolean
	 */
	/*
	 * public void setDetailsPaymentDelivery(Collection<DetailsPaymentDelivery>
	 * detailsPaymentDelivery) { this.detailsPaymentDelivery =
	 * detailsPaymentDelivery; } /**
	 * 
	 * @return the detailsPaymentDelivery
	 */
	/*
	 * public Collection<DetailsPaymentDelivery> getDetailsPaymentDelivery() {
	 * return detailsPaymentDelivery; } /**
	 * 
	 * @param reverseAccept the reverseAccept to set
	 */
	public void setReverseAccept(boolean reverseAccept) {
		this.reverseAccept = reverseAccept;
	}

	/**
	
	 * @return the reverseAccept */
	public boolean isReverseAccept() {
		return reverseAccept;
	}

	/*
	 * public List<PaymentMethod> getPm() { return pm; } public void
	 * setPm(List<PaymentMethod> pm) { this.pm = pm; }
	 */

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

	/**
	 * Method setEnabled.
	 * @param enabled boolean
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Method isEnabled.
	 * @return boolean
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Method setAllowsClientChange.
	 * @param allowsClientChange boolean
	 */
	public void setAllowsClientChange(boolean allowsClientChange) {
		this.allowsClientChange = allowsClientChange;
	}

	/**
	 * Method isAllowsClientChange.
	 * @return boolean
	 */
	public boolean isAllowsClientChange() {
		return allowsClientChange;
	}

	/**
	 * Method setAllowsRePrint.
	 * @param allowsRePrint boolean
	 */
	public void setAllowsRePrint(boolean allowsRePrint) {
		this.allowsRePrint = allowsRePrint;
	}

	/**
	 * Method isAllowsRePrint.
	 * @return boolean
	 */
	public boolean isAllowsRePrint() {
		return allowsRePrint;
	}
	
	/**
	 * Method setCounterPart.
	 * @param fromJPA DocumentType
	 */
	public void setCounterPart(PaymentMethod fromJPA) {
		this.counterPart = fromJPA;

	}
	/**
	
	 * @return the counterPart */
	public PaymentMethod getCounterPart() {
		return counterPart;
	}
}
