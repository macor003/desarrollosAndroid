/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.report.models;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class DetailsZReportModel {

    /**
     * Field documentTypeName.
     */
    private String documentTypeName;

    /**
     * Field taxableAmount.
     */
    private CRBigDecimal taxableAmount = CRBigDecimal.ZERO;

    /**
     * Field nonTaxableAmount.
     */
    private CRBigDecimal nonTaxableAmount = CRBigDecimal.ZERO;

    /**
     * Field taxAmount.
     */
    private CRBigDecimal taxAmount = CRBigDecimal.ZERO;

    /**
     * Field collectedTaxAmount.
     */
    private CRBigDecimal collectedTaxAmount = CRBigDecimal.ZERO;

    /**
     * Field totalDayAmount.
     */
    private CRBigDecimal totalDayAmount = CRBigDecimal.ZERO;

    /**
     * Field startNumber.
     */
    private Long startNumber;

    /**
     * Field endNumber.
     */
    private Long endNumber;

    /**
     * Method getDocumentTypeName.
     * 
     * @return String
     */
    public String getDocumentTypeName() {
        return documentTypeName;
    }

    /**
     * Method setDocumentTypeName.
     * 
     * @param documentTypeName String
     */
    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    /**
     * Method getTaxableAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTaxableAmount() {
        return taxableAmount;
    }

    /**
     * Method setTaxableAmount.
     * 
     * @param taxableAmount CRBigDecimal
     */
    public void setTaxableAmount(CRBigDecimal taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    /**
     * Method getNonTaxableAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getNonTaxableAmount() {
        return nonTaxableAmount;
    }

    /**
     * Method setNonTaxableAmount.
     * 
     * @param nonTaxableAmount CRBigDecimal
     */
    public void setNonTaxableAmount(CRBigDecimal nonTaxableAmount) {
        this.nonTaxableAmount = nonTaxableAmount;
    }

    /**
     * Method getTaxAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Method setTaxAmount.
     * 
     * @param taxAmount CRBigDecimal
     */
    public void setTaxAmount(CRBigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * Method getCollectedTaxAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getCollectedTaxAmount() {
        return collectedTaxAmount;
    }

    /**
     * Method setCollectedTaxAmount.
     * 
     * @param collectedTaxAmount CRBigDecimal
     */
    public void setCollectedTaxAmount(CRBigDecimal collectedTaxAmount) {
        this.collectedTaxAmount = collectedTaxAmount;
    }

    /**
     * Method getTotalDayAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getTotalDayAmount() {
        return totalDayAmount;
    }

    /**
     * Method calculateTotalDayAmount.
     */
    public void calculateTotalDayAmount() {
        this.totalDayAmount = this.totalDayAmount.add(this.taxableAmount).add(this.nonTaxableAmount)
                .add(this.taxAmount);
    }

    /**
     * Method getStartNumber.
     * 
     * @return Long
     */
    public Long getStartNumber() {
        return startNumber;
    }

    /**
     * Method setStartNumber.
     * 
     * @param startNumber Long
     */
    public void setStartNumber(Long startNumber) {
        this.startNumber = startNumber;
    }

    /**
     * Method getEndNumber.
     * 
     * @return Long
     */
    public Long getEndNumber() {
        return endNumber;
    }

    /**
     * Method setEndNumber.
     * 
     * @param endNumber Long
     */
    public void setEndNumber(Long endNumber) {
        this.endNumber = endNumber;
    }

}
