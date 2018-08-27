/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.report.models;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ArticleXReportModel {

    /**
     * Field exemptTotal.
     */
    private CRBigDecimal exemptTotal;

    /**
     * Field nonExemptTotal.
     */
    private CRBigDecimal nonExemptTotal;

    /**
     * Field taxTotal.
     */
    private CRBigDecimal taxTotal;

    /**
     * Field first.
     */
    private long first;

    /**
     * Field last.
     */
    private long last;

    /**
     * Constructor for ArticleXReportModel.
     */
    public ArticleXReportModel() {
        setExemptTotal(CRBigDecimal.ZERO);
        setNonExemptTotal(CRBigDecimal.ZERO);
        setTaxTotal(CRBigDecimal.ZERO);
    }

    /**
     * @param p_exemptTotal
     * @param p_nonExemptTotal
     * @param p_taxTotal
     */
    public ArticleXReportModel(CRBigDecimal p_exemptTotal, CRBigDecimal p_nonExemptTotal,
                               CRBigDecimal p_taxTotal) {
        super();
        exemptTotal = p_exemptTotal;
        nonExemptTotal = p_nonExemptTotal;
        taxTotal = p_taxTotal;
    }

    /**
     * @param taxTotal the taxTotal to set
     */
    public void setTaxTotal(CRBigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    /**
     * 
     * @return the taxTotal
     */
    public CRBigDecimal getTaxTotal() {
        return taxTotal;
    }

    /**
     * @param nonExemptTotal the nonExemptTotal to set
     */
    public void setNonExemptTotal(CRBigDecimal nonExemptTotal) {
        this.nonExemptTotal = nonExemptTotal;
    }

    /**
     * 
     * @return the nonExemptTotal
     */
    public CRBigDecimal getNonExemptTotal() {
        return nonExemptTotal;
    }

    /**
     * @param exemptTotal the exemptTotal to set
     */
    public void setExemptTotal(CRBigDecimal exemptTotal) {
        this.exemptTotal = exemptTotal;
    }

    /**
     * 
     * @return the exemptTotal
     */
    public CRBigDecimal getExemptTotal() {
        return exemptTotal;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(long first) {
        this.first = first;
    }

    /**
     * 
     * @return the first
     */
    public long getFirst() {
        return first;
    }

    /**
     * @param last the last to set
     */
    public void setLast(long last) {
        this.last = last;
    }

    /**
     * 
     * @return the last
     */
    public long getLast() {
        return last;
    }

}
