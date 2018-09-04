/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import org.apache.commons.lang.StringUtils;

/**
 */
public class MICR {

    /**
     * Field accountNumber.
     */
    String accountNumber;

    /**
     * Field checkNumber.
     */
    String checkNumber;

    /**
     * Constructor for MICR.
     * 
     * @param checkNumber String
     * @param accountNumber String
     */
    private MICR(String checkNumber, String accountNumber) {
        super();
        this.accountNumber = accountNumber;
        this.checkNumber = checkNumber;
    }

    /**
     * 
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 
     * @return the checkNumber
     */
    public String getCheckNumber() {
        return checkNumber;
    }

    /**
     * @param checkNumber the checkNumber to set
     */
    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
     * Method toString.
     * 
     * @return String
     */
    @Override
    public String toString() {

        return this.checkNumber + " - " + this.accountNumber;
    }

    /**
     * Method getInstance.
     * 
     * @param micr String
     * @return MICR
     */
    public static MICR getInstance(String micr) {
        MICR res = new MICR("", "");
        if (micr.length() > 0) {

            // micr = StringUtils.remove(micr, "a");
            // micr = StringUtils.remove(micr, "e");
            // micr = StringUtils.remove(micr, "c");

            micr = removeSpecialChars(micr);
            micr = micr.trim();
            // logger.debug("quedo "+micr);
            if (StringUtils.isNumeric(micr) && micr.length() > 8) {
                res = new MICR(micr.substring(0, 8), micr.substring(8, micr.length() - 2));
            }
        }

        return res;
    }

    /**
     * Method removeSpecialChars.
     * 
     * @param text String
     * @return String
     */
    public static String removeSpecialChars(String text) {

        String digits = "[^0-9]+";
        String alphaOnly = "[^a-zA-Z]+";

        String strippedString = new String();

        strippedString = text.replaceAll(digits, "");

        return strippedString;

    }

}
