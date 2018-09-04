/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.becoblohm.cr.models.Transaction.SourceTransactionType;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class DefaultValues extends AbstractModel implements Cloneable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field defaultTransaction.
     */
    private Transaction defaultTransaction;

    /**
     * Field defaultArticle.
     */
    private Article defaultArticle;

    /**
     * Field defaultMoney.
     */
    private Money defaultMoney;

    /**
     * Field defaultPayment.
     */
    private Payment defaultPayment;

    /**
     * Field taxfree.
     */
    private Tax taxfree;

    /**
     * Method getDefaultTransaction.
     * 
     * @return Transaction
     */
    public Transaction getDefaultTransaction() {
        return defaultTransaction;
    }

    /**
     * Method setDefaultTransaction.
     * 
     * @param defaultTransaction Transaction
     */
    public void setDefaultTransaction(Transaction defaultTransaction) {
        this.defaultTransaction = defaultTransaction;
    }

    /**
     * Method getDefaultArticle.
     * 
     * @return Article
     */
    public Article getDefaultArticle() {
        return defaultArticle;
    }

    /**
     * Method setDefaultArticle.
     * 
     * @param defaultArticle Article
     */
    public void setDefaultArticle(Article defaultArticle) {
        this.defaultArticle = defaultArticle;
    }

    /**
     * Method getDefaultMoney.
     * 
     * @return Money
     */
    public Money getDefaultMoney() {
        return defaultMoney;
    }

    /**
     * Method setDefaultMoney.
     * 
     * @param defaultMoney Money
     */
    public void setDefaultMoney(Money defaultMoney) {
        this.defaultMoney = defaultMoney;
    }

    /**
     * Method getDefaultPayment.
     * 
     * @return Payment
     */
    public Payment getDefaultPayment() {
        return defaultPayment;
    }

    /**
     * Method setDefaultPayment.
     * 
     * @param defaultPayment Payment
     */
    public void setDefaultPayment(Payment defaultPayment) {
        this.defaultPayment = defaultPayment;
    }

    // public DefaultValues clone(){
    //
    // DefaultValues tmp = new DefaultValues();
    //
    // Article defaultArticletmp = new Article(defaultArticle);
    // tmp.setDefaultArticle(defaultArticletmp);
    // Money defaultMoneytmp = new Money(defaultMoney);
    //
    // tmp.setDefaultMoney(defaultMoneytmp);
    // Payment defaultPaymenttmp = new Payment(defaultPayment);
    // tmp.setDefaultPayment(defaultPaymenttmp);
    // Transaction defaultTransactiontmp = new Transaction();
    // defaultTransactiontmp.initTransaction(defaultTransaction);
    // tmp.setDefaultTransaction(defaultTransactiontmp);
    // Tax taxfreetmp = new Tax(taxfree);
    // tmp.setTaxfree(taxfreetmp);
    //
    // return tmp;
    //
    // }

    /**
     * Method clone.
     * 
     * @return DefaultValues
     */
    public DefaultValues clone() {

        DefaultValues tmp = new DefaultValues();

        Transaction sale = new Transaction();
        sale.setDocument(defaultTransaction.getDocument());
        sale.setSaleOrigin(defaultTransaction.getSaleOrigin());
        sale.setTransactionType(SourceTransactionType.Sale);
        tmp.setDefaultTransaction(sale);

        // Article defaultArticletmp = new Article(defaultArticle);
        // tmp.setDefaultArticle(defaultArticletmp);
        tmp.setDefaultArticle(defaultArticle.clone());

        try {
            tmp.setDefaultMoney(defaultMoney.clone());
            tmp.setDefaultPayment(defaultPayment.clone());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Tax taxfreetmp = taxfree.clone();
        tmp.setTaxfree(taxfree.clone());

        return tmp;

    }

    // public DefaultValues clone()
    // {
    // DefaultValues clone = null;
    // try{
    // clone = (DefaultValues)super.clone();
    // }
    // catch(CloneNotSupportedException e){
    // // No deberia ocurrir
    // }
    // return clone;
    // }

    /**
     * @param taxfree the taxfree to set
     */
    public void setTaxfree(Tax taxfree) {
        this.taxfree = taxfree;
    }

    /**
     * 
     * @return the taxfree
     */
    public Tax getTaxfree() {
        return taxfree;
    }

}
