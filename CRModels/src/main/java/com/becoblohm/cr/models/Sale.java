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
public class Sale extends Transaction {

    /**
     * Constructor for Sale.
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
     */
    public Sale(String id, int fiscalId, Date date, Map taxes, CRBigDecimal totalCost, CRBigDecimal totalPay,
                Client client, User user, Collection<Article> articles, Collection<Payment> payments,
                Collection<Rebate> rebates, CRBigDecimal articlesCount, Source saleSource,
                SourceTransactionType transactionType) {
        super(id, fiscalId, date, taxes, totalCost, totalPay, client, user, articles, payments, rebates,
                articlesCount, saleSource, transactionType);
    }

    /**
     * Constructor for Sale.
     */
    public Sale() {
        super();
    }

    /**
     * Method initSale.
     */
    public void initSale() {
        super.initTransaction();
    }

    /**
     * Method initSale.
     * 
     * @param sale Sale
     */
    public void initSale(Sale sale) {
        super.initTransaction(sale);
    }

}
