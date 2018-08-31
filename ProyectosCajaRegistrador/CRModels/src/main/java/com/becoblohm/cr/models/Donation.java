/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.math.BigInteger;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class Donation extends AbstractModel {

    public Donation() {
        super();
    }

    /**
     * Constructor for Donation.
     * 
     * @param amount CRBigDecimal
     */
    public Donation(CRBigDecimal amount) {
        super();
        this.amount = amount;
    }

    /**
     * Constructor for Donation.
     * 
     * @param roundAmount BigInteger
     */
    public Donation(BigInteger roundAmount) {
        super();
        this.roundAmount = roundAmount;
    }

    /**
     * Field amount.
     */
    private CRBigDecimal amount = CRBigDecimal.ZERO;

    /**
     * Field round.
     */
    private CRBigDecimal round = CRBigDecimal.ZERO;

    /**
     * Field roundAmount.
     */
    private BigInteger roundAmount = BigInteger.ZERO;
    
    private String description;

    /**
     * @param amount the amount to set
     */
    public void setAmount(CRBigDecimal amount) {
        CRBigDecimal tmp = this.amount;
        this.amount = amount;
        fire("amount", tmp, amount);
    }

    /**
     * 
     * @return the amount
     */
    public CRBigDecimal getAmount() {
        return amount;
    }

    /**
     * @param roundAmount the roundAmount to set
     */
    public void setRoundAmount(BigInteger roundAmount) {
        this.roundAmount = roundAmount;
        updateAmount();
    }

    /**
     * 
     * @return the roundAmount
     */
    public BigInteger getRoundAmount() {
        return roundAmount;
    }

    /**
     * @param round the round to set
     */
    public void setRound(CRBigDecimal round) {
        this.round = round;
        updateAmount();
    }

    /**
     * Method updateAmount.
     */
    private void updateAmount() {
        this.amount = this.round.add(new CRBigDecimal(this.roundAmount));

    }

    /**
     * 
     * @return the round
     */
    public CRBigDecimal getRound() {
        return round;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
