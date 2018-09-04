/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.report.models;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class DetailsXReportModel {

    /**
     * Field pos.
     */
    private CRBigDecimal pos;

    /**
     * Field neg.
     */
    private CRBigDecimal neg;

    /**
     * 
     */
    public DetailsXReportModel() {
        super();
    }

    /**
     * @param p_pos
     * @param p_neg
     */
    public DetailsXReportModel(CRBigDecimal p_pos, CRBigDecimal p_neg) {
        super();
        setPos(p_pos);
        setNeg(p_neg);
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(CRBigDecimal pos) {
        this.pos = pos;
    }

    /**
     * 
     * @return the pos
     */
    public CRBigDecimal getPos() {
        return pos;
    }

    /**
     * @param neg the neg to set
     */
    public void setNeg(CRBigDecimal neg) {
        this.neg = neg;
    }

    /**
     * 
     * @return the neg
     */
    public CRBigDecimal getNeg() {
        return neg;
    }

}
