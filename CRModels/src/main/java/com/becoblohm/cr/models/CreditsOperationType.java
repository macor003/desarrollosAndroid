/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.becoblohm.cr.legacy.models.CreditsTypes;
import com.epa.mvc.core.AbstractModel;

public class CreditsOperationType extends AbstractModel {

    private static final long serialVersionUID = -5919522158194834449L;

    private long id;

    private CreditsType creditsType;

    private CreditsOperation creditsOperation;

    private boolean isVisible;

    private boolean requirePassword;

    private CreditsOperationType reverse;

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
     * @param id long
     */
    public CreditsOperationType(long id) {
        super();
        this.id = id;
    }

    public CreditsOperationType(long id, CreditsTypes creditsTypesCR, char condition) {
        super();
        this.id = id;
        this.creditsTypesCR = creditsTypesCR;
        this.condition = condition;
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

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.CreditsOperationType
     */
    public com.becoblohm.cr.net.models.CreditsOperationType toNetModel() {
        com.becoblohm.cr.net.models.CreditsOperationType op = new com.becoblohm.cr.net.models.CreditsOperationType();

        if (creditsOperation != null) {
            op.setCreditsOperation(creditsOperation.toNetModel());
        }
        if (creditsType != null) {
            op.setCreditsType(creditsType.toNetModel());
        }
        if (creditsTypesCR != null) {
            op.setCreditsTypesCR(creditsTypesCR.toNetModel());
        }
        op.setId(id);
        op.setRequirePassword(requirePassword);
        op.setVisible(isVisible);
        op.setCondition(condition);

        return op;
    }

    public CreditsOperationType getReverse() {
        return reverse;
    }

    public void setReverse(CreditsOperationType reverse) {
        this.reverse = reverse;
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
