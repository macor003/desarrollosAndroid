/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.interfaces;

import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Transaction;

/**
 */
public abstract class Calculator {

    /**
     * Method updateArticleCosts.
     * 
     * @param art Article
     */
    public abstract void updateArticleCosts(Article art);

    /**
     * Method updateTransactionCosts.
     * 
     * @param trans Transaction
     */
    public abstract void updateTransactionCosts(Transaction trans);
}