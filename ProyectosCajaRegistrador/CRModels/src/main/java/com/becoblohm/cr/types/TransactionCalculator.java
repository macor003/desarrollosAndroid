package com.becoblohm.cr.types;

import com.becoblohm.cr.interfaces.Calculator;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Transaction;
import com.epa.mvc.core.AbstractIOModel;

public class TransactionCalculator {

    /**
     * Field trcalculator.
     */
    private static TransactionCalculator trcalculator;

    /**
     * Method getInstance.
     * 
     * @return TransactionCalculator
     */
    public static TransactionCalculator getInstance() {
        if (trcalculator == null) {
            trcalculator = new TransactionCalculator();
        }

        return trcalculator;
    }

    /**
     * Constructor for TransactionCalculator.
     */
    private TransactionCalculator() {
        super();
    }

    /**
     * Field calculator.
     */
    private Calculator calculator = new TransactionCalculatorFiscal();

    /**
     * Method setCalculator.
     * 
     * @param transactionCalculatorClass String
     */
    public void setCalculator(String transactionCalculatorClass) {
        try {
            this.calculator = ((Calculator) Class.forName(transactionCalculatorClass).newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    /**
     * @param calculator the calculator to set
     */
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * 
     * @return the calculator
     */
    public Calculator getCalculator() {
        return calculator;
    }
}
