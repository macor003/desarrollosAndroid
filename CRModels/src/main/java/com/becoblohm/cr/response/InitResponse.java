/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.response;

import javax.print.attribute.standard.Severity;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class InitResponse {

    /**
     * Field msg.
     */
    private String msg;

    /**
     * Field ok.
     */
    private boolean ok;

    /**
     * Field responseSeverity.
     */
    private Severity responseSeverity;

    /**
     * Constructor for InitResponse.
     */
    public InitResponse() {

    }

    /**
     * @param p_msg
     * 
     * @param p_code
     */
    public InitResponse(String p_msg, boolean p_code) {
        super();
        msg = p_msg;
        ok = p_code;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param p_tmp the code to set
     */
    public void setOk(boolean p_tmp) {
        this.ok = p_tmp;
    }

    /**
     * 
     * @return the code
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * Method getResponseSeverity.
     * 
     * @return Severity
     */
    public Severity getResponseSeverity() {
        return responseSeverity;
    }

    /**
     * Method setResponseSeverity.
     * 
     * @param responseSeverity Severity
     */
    public void setResponseSeverity(Severity responseSeverity) {
        this.responseSeverity = responseSeverity;
    }
}
