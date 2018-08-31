/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class AnulDev extends Transaction {

    /**
     * 
     */
    private static final long serialVersionUID = -8845592371226160571L;

    /**
     * Field originalSale.
     */
    private Transaction originalSale = new Transaction();

    /**
     * Field nextSale.
     */
    private Transaction nextSale = null;

    /**
     * Field motiveId.
     */
    private int motiveId = -1;

    /**
     * Field motiveDescription.
     */
    private String motiveDescription = null;

    /**
     * Field fromSpecialOrder.
     */
    private boolean fromSpecialOrder = false;

    /**
     * Field specialOrderId.
     */
    private Order specialOrder;

    /**
     * Field amountToRefund.
     */
    private CRBigDecimal amountToRefund = CRBigDecimal.ZERO;

    /**
     * Constructor for AnulDev.
     * 
     * @param id String
     * @param fiscalId int
     * @param date Date
     * @param taxes Map
     * @param totalCost CRBigDecimal
     * @param totalPay CRBigDecimal
     * @param client Client
     * @param user User
     * @param articles Collection<Article>
     * @param payments Collection<Payment>
     * @param rebates Collection<Rebate>
     * @param articlesCount CRBigDecimal
     * @param saleSource Source
     * @param transactionType SourceTransactionType
     * @param store String
     * @param posId String
     * @param motive String
     */
    public AnulDev(String id, int fiscalId, Date date, Map taxes, CRBigDecimal totalCost, CRBigDecimal totalPay,
                   Client client, User user, Collection<Article> articles, Collection<Payment> payments,
                   Collection<Rebate> rebates, CRBigDecimal articlesCount, Source saleSource,
                   SourceTransactionType transactionType, String store, String posId, String motive) {
        super(id, fiscalId, date, taxes, totalCost, totalPay, client, user, articles, payments, rebates,
                articlesCount, saleSource, transactionType);
        this.setStore(store);
        this.setPosId(posId);
    }

    /**
     * Constructor for AnulDev.
     */
    public AnulDev() {
        super();
    }

    /**
     * Method getOriginalSale.
     * 
     * @return Transaction
     */
    public Transaction getOriginalSale() {
        return originalSale;
    }

    /**
     * Method setOriginalSale.
     * 
     * @param originalSale Transaction
     */
    public void setOriginalSale(Transaction originalSale) {
        this.originalSale = originalSale;
    }

    /**
     * Method getNextSale.
     * 
     * @return Transaction
     */
    public Transaction getNextSale() {
        return nextSale;
    }

    /**
     * Method setNextSale.
     * 
     * @param nextSale Transaction
     */
    public void setNextSale(Transaction nextSale) {
        this.nextSale = nextSale;
    }

    /**
     * Method getMotiveId.
     * 
     * @return int
     */
    public int getMotiveId() {
        return motiveId;
    }

    /**
     * Method setMotiveId.
     * 
     * @param motive int
     */
    public void setMotiveId(int motive) {
        this.motiveId = motive;
    }

    /**
     * Method getMotiveDescription.
     * 
     * @return String
     */
    public String getMotiveDescription() {
        return motiveDescription;
    }

    /**
     * Method setMotiveDescription.
     * 
     * @param motiveDescription String
     */
    public void setMotiveDescription(String motiveDescription) {
        this.motiveDescription = motiveDescription;
    }

    /**
     * Method isFromSpecialOrder.
     * 
     * @return boolean
     */
    public boolean isFromSpecialOrder() {
        return fromSpecialOrder;
    }

    /**
     * Method setFromSpecialOrder.
     * 
     * @param fromSpecialOrder boolean
     */
    public void setFromSpecialOrder(boolean fromSpecialOrder) {
        this.fromSpecialOrder = fromSpecialOrder;
    }

    /**
     * Method getSpecialOrderId.
     * 
     * @return Long
     */
    public Order getSpecialOrder() {
        return specialOrder;
    }

    /**
     * Method setSpecialOrderId.
     * 
     * @param specialOrderId Long
     */
    public void setSpecialOrder(Order specialOrderId) {
        this.specialOrder = specialOrderId;
    }

    /**
     * Method getAmountToRefund.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getAmountToRefund() {
        if (amountToRefund == null) {
            amountToRefund = CRBigDecimal.ZERO;
        }
        return amountToRefund;
    }

    /**
     * Method setAmountToRefund.
     * 
     * @param amountToRefund CRBigDecimal
     */
    public void setAmountToRefund(CRBigDecimal amountToRefund) {
        this.amountToRefund = amountToRefund;
    }

}
