/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class Money extends AbstractModel implements Cloneable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field currencyId.
     */
    private long currencyId;

    /**
     * Field localAmmount.
     */
    private CRBigDecimal localAmmount;

    /**
     * Field currencyAmmount.
     */
    private CRBigDecimal currencyAmmount;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field exchangeRate.
     */
    private CRBigDecimal exchangeRate;

    /**
     * Constructor for Money.
     * 
     * @param defaultMoney Money
     */
    public Money(Money defaultMoney) {
        super();
        this.currencyId = defaultMoney.currencyId;
        this.localAmmount = defaultMoney.localAmmount;
        this.currencyAmmount = defaultMoney.currencyAmmount;
        this.name = defaultMoney.name;
        this.exchangeRate = defaultMoney.exchangeRate;
    }

    /**
     * Constructor for Money.
     */
    public Money() {

    }

    /**
     * Method clone.
     * 
     * @return Money
     * @throws CloneNotSupportedException
     */
    @Override
    public Money clone() throws CloneNotSupportedException {
        Money tmpMoney = new Money();
        tmpMoney.currencyId = Long.valueOf(this.currencyId);
        tmpMoney.localAmmount = new CRBigDecimal(this.localAmmount.doubleValue());
        tmpMoney.currencyAmmount = new CRBigDecimal(this.currencyAmmount.doubleValue());
        tmpMoney.name = String.valueOf(this.name);
        tmpMoney.exchangeRate = new CRBigDecimal(this.exchangeRate.doubleValue());
        return tmpMoney;
    }

    /**
     * Constructor for Money.
     * 
     * @param m com.becoblohm.cr.net.models.Money
     */
    public Money(com.becoblohm.cr.net.models.Money m) {
        if (m != null) {
            this.currencyId = m.getCurrencyId();
            this.localAmmount = new CRBigDecimal(m.getLocalAmmount().doubleValue());
            this.currencyAmmount = new CRBigDecimal(m.getCurrencyAmmount().doubleValue());
            this.name = m.getName();
            if (m.getExchangeRate() != null) {
                this.exchangeRate = new CRBigDecimal(m.getExchangeRate().doubleValue());
            }
        }

    }

    /**
     * Method getCurrencyId.
     * 
     * @return long
     */
    public long getCurrencyId() {
        return currencyId;
    }

    /**
     * Method setCurrencyId.
     * 
     * @param currencyId long
     */
    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * Method getLocalAmmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getLocalAmmount() {
        return localAmmount;
    }

    /**
     * Method setLocalAmmount.
     * 
     * @param localAmmount CRBigDecimal
     */
    public void setLocalAmmount(CRBigDecimal localAmmount) {
        this.localAmmount = localAmmount;
        if (this.exchangeRate != null) {
            CRBigDecimal tmp = this.localAmmount.divide(new CRBigDecimal(exchangeRate.doubleValue(), 3));
            this.currencyAmmount = tmp;
        }
    }

    /**
     * Method getCurrencyAmmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getCurrencyAmmount() {
        return currencyAmmount;
    }

    /**
     * Method setCurrencyAmmount.
     * 
     * @param currencyAmmount CRBigDecimal
     */
    public void setCurrencyAmmount(CRBigDecimal currencyAmmount) {
        this.currencyAmmount = currencyAmmount;
        if (this.exchangeRate != null) {
            CRBigDecimal tmp = this.currencyAmmount.multiply(new CRBigDecimal(exchangeRate.doubleValue(), 3));
            this.localAmmount = tmp;
        }
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

    /**
     * Method getExchangeRate.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getExchangeRate() {
        return exchangeRate;
    }

    /**
     * Method setExchangeRate.
     * 
     * @param exchangeRate CRBigDecimal
     */
    public void setExchangeRate(CRBigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.Money
     */
    public com.becoblohm.cr.net.models.Money toNetModel() {
        com.becoblohm.cr.net.models.Money tmp = new com.becoblohm.cr.net.models.Money();
        tmp.setCurrencyAmmount(new com.becoblohm.cr.net.types.CRBigDecimal(currencyAmmount.doubleValue()));
        tmp.setCurrencyId(currencyId);
        if (exchangeRate != null) {
            tmp.setExchangeRate(new com.becoblohm.cr.net.types.CRBigDecimal(exchangeRate.doubleValue()));
        }
        tmp.setLocalAmmount(new com.becoblohm.cr.net.types.CRBigDecimal(localAmmount.doubleValue()));
        if (name != null) {
            tmp.setName(name);
        }

        return tmp;
    }

}
