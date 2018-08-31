/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import java.math.BigDecimal;
import java.util.Map;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CommitmentCurrency extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field idCurrency.
     */
    protected long idCurrency;

    /**
     * Field nameCurrency.
     */
    protected String nameCurrency;

    /**
     * Field symbolCurrency.
     */
    protected String symbolCurrency;

    /**
     * Field totalCurrency.
     */
    private CRBigDecimal totalCurrency = CRBigDecimal.ZERO;

    /**
     * Field currencyAmount.
     */
    private CRBigDecimal currencyAmount = CRBigDecimal.ZERO;

    /**
     * Field currencyDetails.
     */
    protected Map<BigDecimal, CommitmentCurrencyDetails> currencyDetails;// =
                                                                         // new
                                                                         // ArrayList<CurrencyDetails>();

    /**
     * Field parentModel.
     */
    private CashierCommitment parentModel;

    /**
     * Field paymentParent.
     */
    private PaymentMethod paymentParent;

    /**
     * Constructor for CommitmentCurrency.
     */
    public CommitmentCurrency() {
        super();
    }

    /**
     * @param idCurrency
     * @param nameCurrency
     * @param symbolCurrency
     */
    public CommitmentCurrency(long idCurrency, String nameCurrency, String symbolCurrency) {
        super();
        this.idCurrency = idCurrency;
        this.nameCurrency = nameCurrency;
        this.symbolCurrency = symbolCurrency;
    }

    /**
     * 
     * @return the idCurrency
     */
    public long getIdCurrency() {
        return idCurrency;
    }

    /**
     * @param idCurrency the idCurrency to set
     */
    public void setIdCurrency(long idCurrency) {
        this.idCurrency = idCurrency;
    }

    /**
     * 
     * @return the nameCurrency
     */
    public String getNameCurrency() {
        return nameCurrency;
    }

    /**
     * @param nameCurrency the nameCurrency to set
     */
    public void setNameCurrency(String nameCurrency) {
        this.nameCurrency = nameCurrency;
    }

    /**
     * 
     * @return the symbolCurrency
     */
    public String getSymbolCurrency() {
        return symbolCurrency;
    }

    /**
     * @param symbolCurrency the symbolCurrency to set
     */
    public void setSymbolCurrency(String symbolCurrency) {
        this.symbolCurrency = symbolCurrency;
    }

    /**
     * 
     * @return the currencyDetails
     */
    public Map<BigDecimal, CommitmentCurrencyDetails> getCurrencyDetails() {
        return currencyDetails;
    }

    /**
     * @param currencyDetails the currencyDetails to set
     */
    public void setCurrencyDetails(Map<BigDecimal, CommitmentCurrencyDetails> currencyDetails) {
        this.currencyDetails = currencyDetails;
    }

    /**
     * @param totalCurrency the totalCurrency to set
     */
    public void setTotalCurrency(CRBigDecimal totalCurrency) {
        CRBigDecimal tmp = this.totalCurrency;

        this.parentModel.setMonto_recaudado(this.parentModel.getMonto_recaudado().subtract(tmp));
        this.totalCurrency = totalCurrency;
        this.parentModel.setMonto_recaudado(this.parentModel.getMonto_recaudado().add(totalCurrency));

        this.fire("totalCurrency", tmp, totalCurrency);
    }

    /**
     * 
     * @return the totalCurrency
     */
    public CRBigDecimal getTotalCurrency() {
        return totalCurrency;
    }

    /**
     * @param parentModel the parentModel to set
     */
    public void setParentModel(CashierCommitment parentModel) {
        this.parentModel = parentModel;
    }

    /**
     * 
     * @return the parentModel
     */
    public CashierCommitment getParentModel() {
        return parentModel;
    }

    /**
     * @param paymentParent the paymentParent to set
     */
    public void setPaymentParent(PaymentMethod paymentParent) {
        this.paymentParent = paymentParent;
    }

    /**
     * 
     * @return the paymentParent
     */
    public PaymentMethod getPaymentParent() {
        return paymentParent;
    }

    /**
     * Method getCurrencyAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    /**
     * Method setCurrencyAmount.
     * 
     * @param currencyAmount CRBigDecimal
     */
    public void setCurrencyAmount(CRBigDecimal currencyAmount) {
        CRBigDecimal tmp = this.currencyAmount;

        this.currencyAmount = currencyAmount;

        this.fire("currencyAmount", tmp, currencyAmount);
    }

}
