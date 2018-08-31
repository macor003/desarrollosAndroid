/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 * 
 * @author Yelitza Farfan (programador11)
 * 
 * @version $Revision: 1.0 $
 */

public class CreditsBalance extends AbstractModel {

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field creditsTypeId.
     */
    private CreditsType creditsTypeId;

    /**
     * Field creditsNumber.
     */
    private CreditsAccount creditsNumber;

    /**
     * Field amount.
     */
    private CRBigDecimal amount;

    /**
     * Field blocked.
     */
    private CRBigDecimal blocked;

    /**
     * Constructor for CreditsBalance.
     */
    public CreditsBalance() {
    }

    /**
     * Constructor for CreditsBalance.
     * 
     * @param balance com.becoblohm.cr.net.models.CreditsBalance
     */
    public CreditsBalance(com.becoblohm.cr.net.models.CreditsBalance balance) {
        if (balance != null) {
            if (balance.getAmount() != null) {
                this.setAmount(new CRBigDecimal(balance.getAmount().getValue().doubleValue()));
            }

            if (balance.getBlocked() != null) {
                this.setBlocked(new CRBigDecimal(balance.getBlocked().getValue().doubleValue()));
            }

            if (balance.getCreditsNumber() != null) {
                this.setCreditsNumber(new CreditsAccount(balance.getCreditsNumber()));
            }

            this.setCreditsTypeId(new CreditsType(balance.getCreditsTypeId().getId(),
                    balance.getCreditsTypeId().getDescription()));
            this.setId(balance.getId());
        }
    }

    /**
     * Constructor for CreditsBalance.
     * 
     * @param id Long
     */
    public CreditsBalance(Long id) {
        this.id = id;
    }

    /**
     * Method setCreditsNumber.
     * 
     * @param creditsNumber CreditsAccount
     */
    public void setCreditsNumber(CreditsAccount creditsNumber) {
        this.creditsNumber = creditsNumber;
    }

    /**
     * Method getCreditsNumber.
     * 
     * @return CreditsAccount
     */
    public CreditsAccount getCreditsNumber() {
        return creditsNumber;
    }

    /**
     * Method setAmount.
     * 
     * @param amount CRBigDecimal
     */
    public void setAmount(CRBigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Method getAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getAmount() {
        return amount;
    }

    /**
     * Method setBlocked.
     * 
     * @param blocked CRBigDecimal
     */
    public void setBlocked(CRBigDecimal blocked) {
        this.blocked = blocked;
    }

    /**
     * Method getBlocked.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getBlocked() {
        return blocked;
    }

    /**
     * Method setId.
     * 
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method getId.
     * 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Method setCreditsTypeId.
     * 
     * @param creditsTypeId CreditsType
     */
    public void setCreditsTypeId(CreditsType creditsTypeId) {
        this.creditsTypeId = creditsTypeId;
    }

    /**
     * Method getCreditsTypeId.
     * 
     * @return CreditsType
     */
    public CreditsType getCreditsTypeId() {
        return creditsTypeId;
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.CreditsBalance
     */
    public com.becoblohm.cr.net.models.CreditsBalance toNetModel() {
        com.becoblohm.cr.net.models.CreditsBalance balance = new com.becoblohm.cr.net.models.CreditsBalance();
        if (this.getAmount() != null) {
            balance.setAmount(new com.becoblohm.cr.net.types.CRBigDecimal(
                    this.getAmount().getValue().doubleValue()));
        }
        if (this.getBlocked() != null) {
            balance.setBlocked(new com.becoblohm.cr.net.types.CRBigDecimal(
                    this.getBlocked().getValue().doubleValue()));
        }
        if (this.getCreditsNumber() != null) {
            balance.setCreditsNumber(this.getCreditsNumber().toNetModel());
        }

        balance.setCreditsTypeId(this.getCreditsTypeId().toNetModel());
        balance.setId(this.getId());
        return balance;
    }

}
