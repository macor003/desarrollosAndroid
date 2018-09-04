package com.becoblohm.cr.net.models;

import java.io.Serializable;

/**
 * Class CreditsTypes
 * 
 * Entity CR.TIPOACREENCIAS
 * 
 * @author eve0017909
 *
 */
public class CreditsTypes implements Serializable {

    private static final long serialVersionUID = 4867383340523928475L;

    private String name;

    private long type;

    private char isValid;

    private char refundAllowed;

    private Object field1 = null;

    private Object field2 = null;

    private int reportCopyNumber = 2;

    private char jComboX;

    private char creditsAllowed;

    private char printBalance;

    public CreditsTypes() {
    }

    public CreditsTypes(String name, long type, char isValid, char refundAllowed, char jComboX,
                        char creditsAllowed, char printBalance) {
        super();
        this.name = name;
        this.type = type;
        this.isValid = isValid;
        this.refundAllowed = refundAllowed;
        this.jComboX = jComboX;
        this.creditsAllowed = creditsAllowed;
        this.printBalance = printBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public char getIsValid() {
        return isValid;
    }

    public void setIsValid(char isValid) {
        this.isValid = isValid;
    }

    public char getRefundAllowed() {
        return refundAllowed;
    }

    public void setRefundAllowed(char refundAllowed) {
        this.refundAllowed = refundAllowed;
    }

    public Object getField1() {
        return field1;
    }

    public void setField1(Object field1) {
        this.field1 = field1;
    }

    public Object getField2() {
        return field2;
    }

    public void setField2(Object field2) {
        this.field2 = field2;
    }

    public int getReportCopyNumber() {
        return reportCopyNumber;
    }

    public void setReportCopyNumber(int reportCopyNumber) {
        this.reportCopyNumber = reportCopyNumber;
    }

    public char getjComboX() {
        return jComboX;
    }

    public void setjComboX(char jComboX) {
        this.jComboX = jComboX;
    }

    public char getCreditsAllowed() {
        return creditsAllowed;
    }

    public void setCreditsAllowed(char creditsAllowed) {
        this.creditsAllowed = creditsAllowed;
    }

    public char getPrintBalance() {
        return printBalance;
    }

    public void setPrintBalance(char printBalance) {
        this.printBalance = printBalance;
    }
}
