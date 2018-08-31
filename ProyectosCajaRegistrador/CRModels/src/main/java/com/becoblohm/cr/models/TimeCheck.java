/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class TimeCheck extends AbstractModel {

    /**
     * Field outOfTime.
     */
    private boolean outOfTime;

    /**
     * Field manualOverride.
     */
    private boolean manualOverride;

    /**
     * Field rebootNeeded.
     */
    private boolean rebootNeeded;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method isOutOfTime.
     * 
     * @return boolean
     */
    public boolean isOutOfTime() {
        return outOfTime;
    }

    /**
     * @param outOfTime the outOfTime to set
     */
    public void setOutOfTime(boolean outOfTime) {
        this.outOfTime = outOfTime;
    }

    /**
     * @param manualOverride the manualOverride to set
     */
    public void setManualOverride(boolean manualOverride) {
        this.manualOverride = manualOverride;
    }

    /**
     * 
     * @return the manualOverride
     */
    public boolean isManualOverride() {
        return manualOverride;
    }

    /**
     * Method isRebootNeeded.
     * 
     * @return boolean
     */
    public boolean isRebootNeeded() {
        return false;
    }

    /**
     * @param rebootNeeded the rebootNeeded to set
     */
    public void setRebootNeeded(boolean rebootNeeded) {
        this.rebootNeeded = rebootNeeded;
    }

}
