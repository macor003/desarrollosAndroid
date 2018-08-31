/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.models;

import java.io.Serializable;

/**
 */
public class CreditsOperationType implements Serializable {

    private static final long serialVersionUID = 6889806283771378503L;

    private long id;

    private CreditsType creditsType;

    private CreditsOperation creditsOperation;

    private boolean isVisible;

    private boolean requirePassword;

    private CreditsTypes creditsTypesCR; // CR.TIPOACREENCIAS

    private char condition; // VENTAS.DETVACR.CONDCDACR

    /**
     * Constructor for CreditsOperationType.
     * 
     * @param id int
     * @param creditsType CreditsType
     * @param creditsOperation CreditsOperation
     * @param isVisible boolean
     */
    public CreditsOperationType(int id, CreditsType creditsType, CreditsOperation creditsOperation,
                                boolean isVisible, CreditsTypes creditsTypesCR, char condition) {
        super();
        this.id = id;
        this.creditsType = creditsType;
        this.creditsOperation = creditsOperation;
        this.isVisible = isVisible;
        this.creditsTypesCR = creditsTypesCR;
        this.condition = condition;
    }

    public CreditsOperationType(int id, CreditsType creditsType, CreditsOperation creditsOperation,
                                boolean isVisible) {
        super();
        this.id = id;
        this.creditsType = creditsType;
        this.creditsOperation = creditsOperation;
        this.isVisible = isVisible;
    }

    /**
     * Constructor for CreditsOperationType.
     */
    public CreditsOperationType() {
        super();
    }

    /**
     * Constructor for CreditsOperationType.
     * 
     * @param id int
     */
    public CreditsOperationType(int id) {
        super();
        this.id = id;
    }

    /**
     * Method getId.
     * 
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method getCreditsType.
     * 
     * @return CreditsType
     */
    public CreditsType getCreditsType() {// POTE
        return creditsType;
    }

    /**
     * Method setCreditsType.
     * 
     * @param creditsType CreditsType
     */
    public void setCreditsType(CreditsType creditsType) {
        this.creditsType = creditsType;
    }

    /**
     * Method getCreditsOperation.
     * 
     * @return CreditsOperation
     */
    public CreditsOperation getCreditsOperation() {
        return creditsOperation;
    }

    /**
     * Method setCreditsOperation.
     * 
     * @param creditsOperation CreditsOperation
     */
    public void setCreditsOperation(CreditsOperation creditsOperation) {
        this.creditsOperation = creditsOperation;
    }

    /**
     * Method isVisible.
     * 
     * @return boolean
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Method setVisible.
     * 
     * @param isAmount boolean
     */
    public void setVisible(boolean isAmount) {
        this.isVisible = isAmount;
    }

    /**
     * Method setRequirePassword.
     * 
     * @param requirePassword boolean
     */
    public void setRequirePassword(boolean requirePassword) {
        this.requirePassword = requirePassword;
    }

    /**
     * Method isRequirePassword.
     * 
     * @return boolean
     */
    public boolean isRequirePassword() {
        return requirePassword;
    }

    public CreditsTypes getCreditsTypesCR() {
        return creditsTypesCR;
    }

    public void setCreditsTypesCR(CreditsTypes creditsTypesCR) {
        this.creditsTypesCR = creditsTypesCR;
    }

    public char getCondition() {
        return condition;
    }

    public void setCondition(char condition) {
        this.condition = condition;
    }
}
