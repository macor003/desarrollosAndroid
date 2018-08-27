/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CommitmentCurrencyDetails extends AbstractModel {

    /**
     * Field idCurrencyDetail.
     */
    protected long idCurrencyDetail;

    /**
     * Field codeCurrency.
     */
    protected long codeCurrency;

    /**
     * Field name.
     */
    protected CRBigDecimal name;

    /**
     * Field bills.
     */
    protected long bills;

    /**
     * Field amount.
     */
    protected CRBigDecimal amount = CRBigDecimal.ZERO;

    /**
     * Field currencyAmount.
     */
    protected CRBigDecimal currencyAmount = CRBigDecimal.ZERO;

    /**
     * Field parent.
     */
    private CommitmentCurrency parent;

    /**
     * Field money.
     */
    private Money money;

    /**
     * Constructor for CommitmentCurrencyDetails.
     */
    public CommitmentCurrencyDetails() {
        super();
    }

    /**
     * Constructor for CommitmentCurrencyDetails.
     * 
     * @param idCurrencyDetail long
     * @param codeCurrency long
     * @param amount CRBigDecimal
     */
    public CommitmentCurrencyDetails(long idCurrencyDetail, long codeCurrency, CRBigDecimal amount) {
        super();
        this.idCurrencyDetail = idCurrencyDetail;
        this.codeCurrency = codeCurrency;
        this.amount = amount;
    }

    /**
     * 
     * @return the idCurrencyDetail
     */
    public long getIdCurrencyDetail() {
        return idCurrencyDetail;
    }

    /**
     * @param idCurrencyDetail the idCurrencyDetail to set
     */
    public void setIdCurrencyDetail(long idCurrencyDetail) {
        this.idCurrencyDetail = idCurrencyDetail;
    }

    /**
     * 
     * @return the codeCurrency
     */
    public long getCodeCurrency() {
        return codeCurrency;
    }

    /**
     * @param codeCurrency the codeCurrency to set
     */
    public void setCodeCurrency(long codeCurrency) {
        this.codeCurrency = codeCurrency;
    }

    /**
     * Method getName.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getName() {
        return name;
    }

    /**
     * Method setName.
     * 
     * @param name CRBigDecimal
     */
    public void setName(CRBigDecimal name) {
        this.name = name;
    }

    /**
     * Method getBills.
     * 
     * @return long
     */
    public long getBills() {
        return bills;
    }

    /**
     * Method setBills.
     * 
     * @param bills long
     */
    public void setBills(long bills) {
        long tmp = this.bills;

        // CRBigDecimal
        // currency=CRBigDecimal.valueOf(Long.valueOf(this.getName()));
        CRBigDecimal currency = this.getName();
        CRBigDecimal amount = currency.multiply(CRBigDecimal.valueOf(bills));

        // if(this.money!=null){
        // // amount = amount.multiply(this.money.getExchangeRate());
        // logger.debug(this.money.getExchangeRate());
        // this.parent.getParentModel().setMonto_recaudado(this.parent.getParentModel().getMonto_recaudado().subtract(amount.multiply(this.money.getExchangeRate())));
        // this.parent.getParentModel().setMonto_recaudado(this.parent.getParentModel().getMonto_recaudado().add(amount.multiply(this.money.getExchangeRate())));
        // }
        this.setCurrencyAmount(currency.multiply(CRBigDecimal.valueOf(bills)));
        this.setAmount(amount);

        this.bills = bills;

        this.fire("bills", tmp, bills);
    }

    /**
     * 
     * @return the amount
     */
    public CRBigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(CRBigDecimal amount) {
        CRBigDecimal tmp = this.amount;
        if (this.money != null) {
            amount = amount.multiply(this.money.getExchangeRate());
        }

        this.parent.setTotalCurrency(this.parent.getTotalCurrency().subtract(tmp));
        this.amount = amount;
        this.parent.setTotalCurrency(this.parent.getTotalCurrency().add(amount));

        this.fire("amount", tmp, amount);
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(CommitmentCurrency parent) {
        this.parent = parent;
    }

    /**
     * 
     * @return the parent
     */
    public CommitmentCurrency getParent() {
        return this.parent;

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
     * 
     * @return the money
     */
    public Money getMoney() {
        return money;
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

        this.parent.setCurrencyAmount(this.parent.getCurrencyAmount().subtract(tmp));
        this.currencyAmount = currencyAmount;
        this.parent.setCurrencyAmount(this.parent.getCurrencyAmount().add(currencyAmount));

        this.fire("currencyAmount", tmp, currencyAmount);

    }

}
