/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.io.Serializable;
import java.util.Comparator;

import com.becoblohm.cr.net.response.CreditsResponse;
import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class Payment extends AbstractModel implements Serializable, Cloneable {

    /**
     * Field PAY_FAIL. (value is 0)
     */
    public static final int PAY_FAIL = 0;

    /**
     * Field PAY_DONE. (value is 1)
     */
    public static final int PAY_DONE = 1;

    /**
     * Field PAY_CANCEL. (value is 2)
     */
    public static final int PAY_CANCEL = 2;

    /**
     * Field REVERSE_DONE. (value is 3)
     */
    public static final int REVERSE_DONE = 3;

    /**
     * Field REVERSE_FAIL. (value is 4)
     */
    public static final int REVERSE_FAIL = 4;

    /**
     * Field REFUND_DONE. (value is 5)
     */
    public static final int REFUND_DONE = 5;

    /**
     * Field REFUND_FAIL. (value is 6)
     */
    public static final int REFUND_FAIL = 6;

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

    /**
     * Field retentionPercentage.
     */
    private CRBigDecimal retentionPercentage;

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
     * jpaTransaccionFormadepago .setConformacion(payment.getConformationNumber());
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
     * Field active.
     */
    private boolean active;

    /**
     * Field active.
     */
    private Retention retencion;

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
     * 
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
     * 
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
     * 
     * @param defaultPayment Payment
     */
    public Payment(Payment defaultPayment) {
        super();
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
     * Constructor for Payment.
     * 
     * @param p com.becoblohm.cr.net.models.Payment
     */
    public Payment(com.becoblohm.cr.net.models.Payment p) {

        this.id = p.getId();
        this.type = p.getType();
        this.name = p.getName();
        this.item = p.getItem();
        if (p.getDonation() != null) {
            this.donation = new CRBigDecimal(p.getDonation().doubleValue());
        }

        this.payMethod = new PaymentMethod(p.getPayMethod());

        this.money = new Money(p.getMoney());
        this.transactionPaymentId = p.getTransactionPaymentId();
        this.done = p.getDone();
        this.accountNumber = p.getAccountNumber();
        this.documentNumber = p.getDocumentNumber();
        this.conformationNumber = p.getConformationNumber();
        this.cardHolder = p.getCardHolder();
        this.allowChangeClient = p.isAllowChangeClient();
        this.allowAcumulate = p.isAllowAcumulate();
        this.vposActivate = p.getVposActivate();
        this.creditsResponse = p.getCreditsResponse();
    }

    /**
     * Method getName.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName.
     * 
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
     * 
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method getType.
     * 
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Method setType.
     * 
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method getDonation.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getDonation() {
        return donation;
    }

    /**
     * Method setDonation.
     * 
     * @param donation CRBigDecimal
     */
    public void setDonation(CRBigDecimal donation) {
        this.donation = donation;
    }

    /**
     * Method getPayMethod.
     * 
     * @return PaymentMethod
     */
    public PaymentMethod getPayMethod() {
        return payMethod;
    }

    /**
     * Method setPayMethod.
     * 
     * @param payMethod PaymentMethod
     */
    public void setPayMethod(PaymentMethod payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * Method getMoney.
     * 
     * @return Money
     */
    public Money getMoney() {
        return money;
    }

    /**
     * Method setMoney.
     * 
     * @param money Money
     */
    public void setMoney(Money money) {
        this.money = money;
    }

    /**
     * Method getTransactionPaymentId.
     * 
     * @return long
     */
    public long getTransactionPaymentId() {
        return transactionPaymentId;
    }

    /**
     * Method setTransactionPaymentId.
     * 
     * @param transactionPaymentId long
     */
    public void setTransactionPaymentId(long transactionPaymentId) {
        this.transactionPaymentId = transactionPaymentId;
    }

    /**
     * 
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
     * @param item the item to set
     */
    public void setItem(short item) {
        this.item = item;
    }

    /**
     * 
     * @return the item
     */
    public short getItem() {
        return item;
    }

    /**
     * Method getAccountNumber.
     * 
     * @return String
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Method setAccountNumber.
     * 
     * @param accountNumber String
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Method getDocumentNumber.
     * 
     * @return String
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Method setDocumentNumber.
     * 
     * @param documentNumber String
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * Method getConformationNumber.
     * 
     * @return String
     */
    public String getConformationNumber() {
        return conformationNumber;
    }

    /**
     * Method setConformationNumber.
     * 
     * @param conformationNumber String
     */
    public void setConformationNumber(String conformationNumber) {
        this.conformationNumber = conformationNumber;
    }

    /**
     * Method getCardHolder.
     * 
     * @return String
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * Method setCardHolder.
     * 
     * @param cardHolder String
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    /**
     * Method isAllowChangeClient.
     * 
     * @return boolean
     */
    public boolean isAllowChangeClient() {
        return allowChangeClient;
    }

    /**
     * @param allowChangeClient the allowChangeClient to set
     */
    public void setAllowChangeClient(boolean allowChangeClient) {
        this.allowChangeClient = allowChangeClient;
    }

    /**
     * Method setAllowAcumulate.
     * 
     * @param allowAcumulate boolean
     */
    public void setAllowAcumulate(boolean allowAcumulate) {
        this.allowAcumulate = allowAcumulate;
    }

    /**
     * Method isAllowAcumulate.
     * 
     * @return boolean
     */
    public boolean isAllowAcumulate() {
        return allowAcumulate;
    }

    /**
     * Method setDone.
     * 
     * @param done int
     */
    public void setDone(int done) {
        this.done = done;
    }

    /**
     * Method getDone.
     * 
     * @return int
     */
    public int getDone() {
        return done;
    }

    /**
     * Method setVposActivate.
     * 
     * @param vposActivate String
     */
    public void setVposActivate(String vposActivate) {
        this.vposActivate = vposActivate;
    }

    /**
     * Method getVposActivate.
     * 
     * @return String
     */
    public String getVposActivate() {
        return vposActivate;
    }

    /**
     * Method clone.
     * 
     * @return Payment
     * @throws CloneNotSupportedException
     */
    @Override
    public Payment clone() throws CloneNotSupportedException {

        Payment defaultPayment = new Payment();
        defaultPayment.setDonation(CRBigDecimal.ZERO);
        defaultPayment.setMoney(this.getMoney().clone());
        defaultPayment.setName(String.valueOf(""));
        defaultPayment.setType(String.valueOf(""));
        defaultPayment.setPayMethod(this.payMethod.clone());
        defaultPayment.setActive(this.isActive());
        defaultPayment.setDocumentNumber(this.getDocumentNumber());
        defaultPayment.setAccountNumber(this.getAccountNumber());
        defaultPayment.setConformationNumber(this.getConformationNumber());
        defaultPayment.setCardHolder(this.getCardHolder());
        defaultPayment.setRetencion(this.getRetencion());
        defaultPayment.setRetentionPercentage(this.getRetentionPercentage());

        return defaultPayment;
    }

    /**
     * @param creditsResponse the creditsResponse to set
     */
    public void setCreditsResponse(CreditsResponse creditsResponse) {
        this.creditsResponse = creditsResponse;
    }

    /**
     * 
     * @return the creditsResponse
     */
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

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.Payment
     */
    public com.becoblohm.cr.net.models.Payment toNetModel() {
        com.becoblohm.cr.net.models.Payment tmp = new com.becoblohm.cr.net.models.Payment();
        tmp.setId(this.getId());
        tmp.setType(this.getType());
        tmp.setName(this.getName());
        tmp.setItem(this.getItem());
        if (this.getDonation() != null) {
            tmp.setDonation(new com.becoblohm.cr.net.types.CRBigDecimal(this.getDonation().doubleValue()));
        }
        tmp.setPayMethod(this.getPayMethod().toNetModel());
        tmp.setMoney(this.getMoney().toNetModel());
        tmp.setTransactionPaymentId(this.getTransactionPaymentId());
        tmp.setDone(this.getDone());
        tmp.setAccountNumber(this.getAccountNumber());
        tmp.setDocumentNumber(this.getDocumentNumber());
        tmp.setConformationNumber(this.getConformationNumber());
        tmp.setCardHolder(this.getCardHolder());
        tmp.setAllowChangeClient(this.isAllowChangeClient());
        tmp.setAllowAcumulate(this.isAllowAcumulate());
        tmp.setVposActivate(this.getVposActivate());
        tmp.setCreditsResponse(this.getCreditsResponse());
        return tmp;
    }

    /**
     * @param retentionPercentage the retentionPercentage to set
     */
    public void setRetentionPercentage(CRBigDecimal retentionPercentage) {
        this.retentionPercentage = retentionPercentage;
    }

    /**
     * 
     * @return the retentionPercentage
     */
    public CRBigDecimal getRetentionPercentage() {
        return retentionPercentage;
    }

    /**
     * Method isActive.
     * 
     * @return boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Method setActive.
     * 
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public Retention getRetencion() {
        return retencion;
    }

    public void setRetencion(Retention retencion) {
        this.retencion = retencion;
    }

    public static class OrderByDonationPriority implements Comparator<Payment> {

        @Override
        public int compare(Payment p0, Payment p1) {
            int donationPriority0 = p0.getPayMethod().getDonationPriority();
            int donationPriority1 = p1.getPayMethod().getDonationPriority();
            if (donationPriority0 < 1) {
                donationPriority0 = 99;
            }
            if (donationPriority1 < 1) {
                donationPriority1 = 99;
            }
            return donationPriority0 - donationPriority1;
        }
    }

}
