/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.ArrayList;
import java.util.Collection;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class CommitmentPayment extends AbstractModel {

    /**
     * Field name.
     */
    private String name;

    /**
     * Field paymentType.
     */
    private int paymentType;

    /**
     * Field code.
     */
    private int code;

    /**
     * Field detailsPaymentDelivery.
     */
    private Collection<CommitmentPaymentDetails> detailsPaymentDelivery = new ArrayList<CommitmentPaymentDetails>();

    /**
     * Field cashierDeliveryParent.
     */
    private CashierCommitment cashierDeliveryParent;

    /**
     * Field currencies.
     */
    private Collection<CommitmentCurrency> currencies = new ArrayList<CommitmentCurrency>();

    /**
     * Field totalCurrency.
     */
    private CRBigDecimal totalCurrency = CRBigDecimal.ZERO;

    // protected Map<Integer, CommitmentCurrencyDetails> currencyDetails;// =
    // new ArrayList<CurrencyDetails>();

    /**
     * Method getTotalCurrency.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTotalCurrency() {
        return totalCurrency;
    }

    /**
     * Field DifferenceQuantity.
     */
    private String differenceQuantity;

    /**
     * Field DifferenceAmount.
     */
    private String differenceAmount;

    /**
     * Method setTotalCurrency.
     * 
     * @param totalCurrency CRBigDecimal
     */
    public void setTotalCurrency(CRBigDecimal totalCurrency) {
        this.totalCurrency = totalCurrency;
    }

    // public Map<Integer, CommitmentCurrencyDetails> getCurrencyDetails() {
    // return currencyDetails;
    // }
    // public void setCurrencyDetails(Map<Integer, CommitmentCurrencyDetails>
    // currencyDetails) {
    // this.currencyDetails = currencyDetails;
    // }
    /**
     * Method getPaymentParent.
     * 
     * @return PaymentMethod
     */
    public PaymentMethod getPaymentParent() {
        return paymentParent;
    }

    /**
     * Method setPaymentParent.
     * 
     * @param paymentParent PaymentMethod
     */
    public void setPaymentParent(PaymentMethod paymentParent) {
        this.paymentParent = paymentParent;
    }

    /**
     * Field paymentParent.
     */
    private PaymentMethod paymentParent;

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param currencies the currencies to set
     */
    public void setCurrencies(Collection<CommitmentCurrency> currencies) {
        this.currencies = currencies;
    }

    /**
     * 
     * @return the currencies
     */
    public Collection<CommitmentCurrency> getCurrencies() {
        return currencies;
    }

    /**
     * @param cashierDeliveryParent the cashierDeliveryParent to set
     */
    public void setCashierDeliveryParent(CashierCommitment cashierDeliveryParent) {
        this.cashierDeliveryParent = cashierDeliveryParent;
    }

    /**
     * 
     * @return the cashierDeliveryParent
     */
    public CashierCommitment getCashierDeliveryParent() {
        return cashierDeliveryParent;
    }

    /**
     * @param detailsPaymentDelivery the detailsPaymentDelivery to set
     */
    public void setDetailsPaymentDelivery(Collection<CommitmentPaymentDetails> detailsPaymentDelivery) {
        this.detailsPaymentDelivery = detailsPaymentDelivery;
    }

    /**
     * 
     * @return the detailsPaymentDelivery
     */
    public Collection<CommitmentPaymentDetails> getDetailsPaymentDelivery() {
        return detailsPaymentDelivery;
    }

    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 
     * @return the paymentType
     */
    public int getPaymentType() {
        return paymentType;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 
     * @return the code
     */
    public int getCode() {
        return code;
    }

    public String getDifferenceQuantity() {
        return differenceQuantity;
    }

    public void setDifferenceQuantity(String differenceQuantity) {
        this.differenceQuantity = differenceQuantity;
    }

    public String getDifferenceAmount() {
        return differenceAmount;
    }

    public void setDifferenceAmount(String differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

}
