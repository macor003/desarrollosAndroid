/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class SerialPrint extends AbstractModel {

    /**
     * Field id.
     */
    private long id;

    /**
     * Field serial.
     */
    private String serial;

    /**
     * Field isActive.
     */
    private boolean isActive;

    /**
     * Field lastinvoice.
     */
    private int lastinvoice;

    /**
     * Field lastcreditnote.
     */
    private int lastcreditnote;

    // private Long currentAuditRoll;
    /**
     * Method getId.
     * 
     * @return int
     */
    public long getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id int
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method getSerial.
     * 
     * @return String
     */
    public String getSerial() {
        return serial;
    }

    /**
     * Method setSerial.
     * 
     * @param serial String
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * Method isActive.
     * 
     * @return boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Method setActive.
     * 
     * @param isActive boolean
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    /*
     * public void setCurrentAuditRoll(Long currentAuditRoll) {
     * this.currentAuditRoll=currentAuditRoll; } public Long getCurrentAuditRoll() {
     * return currentAuditRoll; }
     */

    /**
     * @param lastcreditnote the lastcreditnote to set
     */
    public void setLastcreditnote(int lastcreditnote) {
        this.lastcreditnote = lastcreditnote;
    }

    /**
     * 
     * @return the lastcreditnote
     */
    public int getLastcreditnote() {
        return lastcreditnote;
    }

    /**
     * @param lastinvoice the lastinvoice to set
     */
    public void setLastinvoice(int lastinvoice) {
        this.lastinvoice = lastinvoice;
    }

    /**
     * 
     * @return the lastinvoice
     */
    public int getLastinvoice() {
        return lastinvoice;
    }

}
