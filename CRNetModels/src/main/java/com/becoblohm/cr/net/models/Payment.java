/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;

import com.becoblohm.cr.net.response.CreditsResponse;
import com.becoblohm.cr.net.types.CRBigDecimal;

/*PAGO*/
/**
 */
public class Payment implements Serializable {

	/**
	 * Field PAY_FAIL.
	 * (value is 0)
	 */
	public static final int PAY_FAIL = 0;
	/**
	 * Field PAY_DONE.
	 * (value is 1)
	 */
	public static final int PAY_DONE = 1;
	/**
	 * Field PAY_CANCEL.
	 * (value is 2)
	 */
	public static final int PAY_CANCEL = 2;
	/**
	 * Field REVERSE_DONE.
	 * (value is 3)
	 */
	public static final int REVERSE_DONE = 3;
	/**
	 * Field REVERSE_FAIL.
	 * (value is 4)
	 */
	public static final int REVERSE_FAIL = 4;
	/**
	 * Field id.
	 */
	private String id;
	/**
	 * Field type.
	 */
	private String type;
	/**
	 * Field name.
	 */
	private String name;
	/**
	 * Field item.
	 */
	private short item;
	// CRBigDecimal amount;
	/**
	 * Field donation.
	 */
	private CRBigDecimal donation;
	/**
	 * Field payMethod.
	 */
	private PaymentMethod payMethod;
	/**
	 * Field money.
	 */
	private Money money;
	/**
	 * Field transactionPaymentId.
	 */
	private long transactionPaymentId = -1;
	// private Client client = null;
	/**
	 * Field done.
	 */
	private int done = PAY_FAIL;
	// private AbstractEvent eventDone;

	/*
	 * jpaTransaccionFormadepago.setDocumento(payment.getDocumentNumber());
	 * jpaTransaccionFormadepago.setCuenta(payment.getAccountNumber());
	 * jpaTransaccionFormadepago
	 * .setConformacion(payment.getConformationNumber());
	 * jpaTransaccionFormadepago.setTitular(payment.getCardHolder());
	 */
	/**
	 * Field accountNumber.
	 */
	private String accountNumber;
	/**
	 * Field documentNumber.
	 */
	private String documentNumber;
	/**
	 * Field conformationNumber.
	 */
	private String conformationNumber;
	/**
	 * Field cardHolder.
	 */
	private String cardHolder;
	/**
	 * Field allowChangeClient.
	 */
	private boolean allowChangeClient;
	/**
	 * Field allowAcumulate.
	 */
	private boolean allowAcumulate;
	/**
	 * Field vposActivate.
	 */
	private String vposActivate = null;
	// private CreditsContainer creditsContainer;
	/**
	 * Field creditsResponse.
	 */
	private CreditsResponse creditsResponse;

	/**
	 * Constructor for Payment.
	 */
	public Payment() {
		// super();
		this.accountNumber = "";
		this.documentNumber = "";
		this.conformationNumber = "";
		this.cardHolder = "";
	}

	/**
	 * Constructor for Payment.
	 * @param name String
	 * @param amount CRBigDecimal
	 */
	public Payment(String name, CRBigDecimal amount) {
		this();
		this.name = name;
		// this.amount = amount;
	}

	/**
	 * Constructor for Payment.
	 * @param type String
	 * @param name String
	 * @param amount CRBigDecimal
	 */
	public Payment(String type, String name, CRBigDecimal amount) {
		this(name, amount);
		this.type = type;

		// this.amount = amount;
	}

	/**
	 * Constructor for Payment.
	 * @param defaultPayment Payment
	 */
	public Payment(Payment defaultPayment) {
		// super();
		this.id = defaultPayment.id;
		this.type = defaultPayment.type;
		this.name = defaultPayment.name;
		this.item = defaultPayment.item;
		this.donation = defaultPayment.donation;
		this.payMethod = new PaymentMethod();
		this.payMethod.initialize(defaultPayment.payMethod);
		this.money = defaultPayment.money;
		this.transactionPaymentId = defaultPayment.transactionPaymentId;
		this.done = defaultPayment.done;
		this.accountNumber = defaultPayment.accountNumber;
		this.documentNumber = defaultPayment.documentNumber;
		this.conformationNumber = defaultPayment.conformationNumber;
		this.cardHolder = defaultPayment.cardHolder;
		this.allowChangeClient = defaultPayment.allowChangeClient;
		this.allowAcumulate = defaultPayment.allowAcumulate;
		this.vposActivate = defaultPayment.vposActivate;
		this.creditsResponse = defaultPayment.creditsResponse;
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

	// public CRBigDecimal getAmount() {
	// return amount;
	// }
	// public void setAmount(CRBigDecimal amount) {
	// this.amount = amount;
	// }

	/**
	 * Method getId.
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method setId.
	 * @param id String
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Method getType.
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Method setType.
	 * @param type String
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Method getDonation.
	 * @return CRBigDecimal
	 */
	public CRBigDecimal getDonation() {
		return donation;
	}

	/**
	 * Method setDonation.
	 * @param donation CRBigDecimal
	 */
	public void setDonation(CRBigDecimal donation) {
		this.donation = donation;
	}

	/**
	 * Method getPayMethod.
	 * @return PaymentMethod
	 */
	public PaymentMethod getPayMethod() {
		return payMethod;
	}

	/**
	 * Method setPayMethod.
	 * @param payMethod PaymentMethod
	 */
	public void setPayMethod(PaymentMethod payMethod) {
		this.payMethod = payMethod;
	}

	/**
	 * Method getMoney.
	 * @return Money
	 */
	public Money getMoney() {
		return money;
	}

	/**
	 * Method setMoney.
	 * @param money Money
	 */
	public void setMoney(Money money) {
		this.money = money;
	}

	/**
	 * Method getTransactionPaymentId.
	 * @return long
	 */
	public long getTransactionPaymentId() {
		return transactionPaymentId;
	}

	/**
	 * Method setTransactionPaymentId.
	 * @param transactionPaymentId long
	 */
	public void setTransactionPaymentId(long transactionPaymentId) {
		this.transactionPaymentId = transactionPaymentId;
	}

	/**
	
	 * @param item short
	 */
	/*
	 * public void setClient(Client client) { this.client = client; }
	 */

	/**
	 * @return the idClient
	 */
	/*
	 * public Client getClient() { return client; }
	 */

	// /**
	// * @param p_actionEventPayDone the done to set
	// */
	// public void setDone(AbstractEvent p_actionEventPayDone) {
	// this.eventDone = p_actionEventPayDone;
	// }
	//
	// /**
	// * @return the done
	// */
	// public AbstractEvent getDone() {
	// return eventDone;
	// }

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(short item) {
		this.item = item;
	}

	/**
	
	 * @return the item */
	public short getItem() {
		return item;
	}

	/**
	 * Method getAccountNumber.
	 * @return String
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Method setAccountNumber.
	 * @param accountNumber String
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Method getDocumentNumber.
	 * @return String
	 */
	public String getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * Method setDocumentNumber.
	 * @param documentNumber String
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	/**
	 * Method getConformationNumber.
	 * @return String
	 */
	public String getConformationNumber() {
		return conformationNumber;
	}

	/**
	 * Method setConformationNumber.
	 * @param conformationNumber String
	 */
	public void setConformationNumber(String conformationNumber) {
		this.conformationNumber = conformationNumber;
	}

	/**
	 * Method getCardHolder.
	 * @return String
	 */
	public String getCardHolder() {
		return cardHolder;
	}

	/**
	 * Method setCardHolder.
	 * @param cardHolder String
	 */
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	/**
	 * Method isAllowChangeClient.
	 * @return boolean
	 */
	public boolean isAllowChangeClient() {
		return allowChangeClient;
	}

	/**
	 * @param allowChangeClient
	 *            the allowChangeClient to set
	 */
	public void setAllowChangeClient(boolean allowChangeClient) {
		this.allowChangeClient = allowChangeClient;
	}

	/**
	 * Method setAllowAcumulate.
	 * @param allowAcumulate boolean
	 */
	public void setAllowAcumulate(boolean allowAcumulate) {
		this.allowAcumulate = allowAcumulate;
	}

	/**
	 * Method isAllowAcumulate.
	 * @return boolean
	 */
	public boolean isAllowAcumulate() {
		return allowAcumulate;
	}

	/**
	 * Method setDone.
	 * @param done int
	 */
	public void setDone(int done) {
		this.done = done;
	}

	/**
	 * Method getDone.
	 * @return int
	 */
	public int getDone() {
		return done;
	}

	/**
	 * Method setVposActivate.
	 * @param vposActivate String
	 */
	public void setVposActivate(String vposActivate) {
		this.vposActivate = vposActivate;
	}

	/**
	 * Method getVposActivate.
	 * @return String
	 */
	public String getVposActivate() {
		return vposActivate;
	}

	/**
	 * @param creditsResponse
	 *            the creditsResponse to set
	 */
	public void setCreditsResponse(CreditsResponse creditsResponse) {
		this.creditsResponse = creditsResponse;
	}

	/**
	
	 * @return the creditsResponse */
	public CreditsResponse getCreditsResponse() {
		return creditsResponse;
	}

	// /**
	// * @param creditsContainer the creditsContainer to set
	// */
	// public void setCreditsContainer(CreditsContainer creditsContainer) {
	// this.creditsContainer = creditsContainer;
	// }
	//
	// /**
	// * @return the creditsContainer
	// */
	// public CreditsContainer getCreditsContainer() {
	// return creditsContainer;
	// }

}
