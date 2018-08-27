/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 */
public class ZDigitalReportDetails extends AbstractModel {

    /**
     * Constructor for ZDigitalReportDetails.
     * 
     * @param taxableAmmount CRBigDecimal
     * @param tax CRBigDecimal
     * @param percent CRBigDecimal
     */
    public ZDigitalReportDetails(CRBigDecimal taxableAmmount, CRBigDecimal tax, CRBigDecimal percent) {
        super();
        this.taxableAmmount = taxableAmmount;
        this.tax = tax;
        this.percent = percent;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field taxableAmmount.
     */
    private CRBigDecimal taxableAmmount;

    /**
     * Field tax.
     */
    private CRBigDecimal tax;

    /**
     * Field percent.
     */
    private CRBigDecimal percent;

    /**
     * Method getTaxableAmmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTaxableAmmount() {
        return taxableAmmount;
    }

    /**
     * Method setTaxableAmmount.
     * 
     * @param taxableAmmount CRBigDecimal
     */
    public void setTaxableAmmount(CRBigDecimal taxableAmmount) {
        this.taxableAmmount = taxableAmmount;
    }

    /**
     * Method getTax.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTax() {
        return tax;
    }

    /**
     * Method setTax.
     * 
     * @param tax CRBigDecimal
     */
    public void setTax(CRBigDecimal tax) {
        this.tax = tax;
    }

    /**
     * Method getPercent.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getPercent() {
        return percent;
    }

    /**
     * Method setPercent.
     * 
     * @param percent CRBigDecimal
     */
    public void setPercent(CRBigDecimal percent) {
        this.percent = percent;
    }
}
