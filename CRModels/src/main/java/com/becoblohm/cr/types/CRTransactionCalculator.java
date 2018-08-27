/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.types;

import com.becoblohm.cr.interfaces.Calculator;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Transaction;

public class CRTransactionCalculator extends Calculator {

    private static final NonFiscalTransactionCalculator calculator = new NonFiscalTransactionCalculator();

    @Override
    public void updateArticleCosts(Article article) {
        calculator.updateArticleCosts(article);
    }

    @Override
    public void updateTransactionCosts(Transaction transaction) {
        calculator.updateTransactionCosts(transaction);
    }
}
