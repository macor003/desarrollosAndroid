/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.response;

import javax.print.attribute.standard.Severity;

import com.becoblohm.cr.sync.models.IdContainer;

/**
 */
public class SyncResponse {

    /**
     * Field errorLevel.
     */
    private Severity errorLevel;

    /**
     * Field message.
     */
    private String message;

    /**
     * Field sinchronized.
     */
    private boolean sinchronized = true;

    /**
     * Field lastId.
     */
    private IdContainer lastId;

    /**
     * Method getMessage.
     * 
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method setMessage.
     * 
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Constructor for SyncResponse.
     */
    public SyncResponse() {
        super();
    }

    /**
     * Method getErrorLevel.
     * 
     * @return Severity
     */
    public Severity getErrorLevel() {
        return this.errorLevel;
    }

    /**
     * Method setErrorLevel.
     * 
     * @param code Severity
     */
    public void setErrorLevel(Severity code) {
        this.errorLevel = code;
    }

    /**
     * Method isSinchronized.
     * 
     * @return boolean
     */
    public boolean isSinchronized() {
        return sinchronized;
    }

    /**
     * Method setSinchronized.
     * 
     * @param sinchronized boolean
     */
    public void setSinchronized(boolean sinchronized) {
        this.sinchronized = sinchronized;
    }

    /**
     * Method getLastId.
     * 
     * @return IdContainer
     */
    public IdContainer getLastId() {
        return lastId;
    }

    /**
     * Method setLastId.
     * 
     * @param lastId IdContainer
     */
    public void setLastId(IdContainer lastId) {
        this.lastId = lastId;
    }
}
