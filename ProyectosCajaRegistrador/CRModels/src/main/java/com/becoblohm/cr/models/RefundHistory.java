/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.math.BigInteger;
import java.util.Date;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 * @author eve0018536 (Mario Ortega)
 *
 */
public class RefundHistory extends AbstractModel {

    private static final long serialVersionUID = 1L;

    private long id;

    private BigInteger idTransactionRefund;

    private String storeRefund;

    private BigInteger posRefund;

    private BigInteger trRefundNumber;

    private Date refundDate = new Date();

    private CRBigDecimal remainingAmount = CRBigDecimal.ZERO;

    private BigInteger idTransactionSale;

    private String storeSale;

    private BigInteger posSale;

    private BigInteger trSaleNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigInteger getIdTransactionRefund() {
        return idTransactionRefund;
    }

    public void setIdTransactionRefund(BigInteger idTransactionRefund) {
        this.idTransactionRefund = idTransactionRefund;
    }

    public String getStoreRefund() {
        return storeRefund;
    }

    public void setStoreRefund(String storeRefund) {
        this.storeRefund = storeRefund;
    }

    public BigInteger getPosRefund() {
        return posRefund;
    }

    public void setPosRefund(BigInteger posRefund) {
        this.posRefund = posRefund;
    }

    public BigInteger getTrRefundNumber() {
        return trRefundNumber;
    }

    public void setTrRefundNumber(BigInteger trRefundNumber) {
        this.trRefundNumber = trRefundNumber;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public CRBigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(CRBigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public BigInteger getIdTransactionSale() {
        return idTransactionSale;
    }

    public void setIdTransactionSale(BigInteger idTransactionSale) {
        this.idTransactionSale = idTransactionSale;
    }

    public String getStoreSale() {
        return storeSale;
    }

    public void setStoreSale(String storeSale) {
        this.storeSale = storeSale;
    }

    public BigInteger getPosSale() {
        return posSale;
    }

    public void setPosSale(BigInteger posSale) {
        this.posSale = posSale;
    }

    public BigInteger getTrSaleNumber() {
        return trSaleNumber;
    }

    public void setTrSaleNumber(BigInteger trSaleNumber) {
        this.trSaleNumber = trSaleNumber;
    }

}
