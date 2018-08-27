/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

/**
 */
public class DetailsPaymentMethodType {

    /**
     * Field id.
     */
    private long id;

    /**
     * Field idPaymentMethod.
     */
    private long idPaymentMethod;

    /**
     * Field paymentMethodType.
     */
    private long paymentMethodType;

    /**
     * Field description.
     */
    private String description;

    /**
     * Field isActive.
     */
    private boolean isActive;

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
     * Method getIdPaymentMethod.
     * 
     * @return long
     */
    public long getIdPaymentMethod() {
        return idPaymentMethod;
    }

    /**
     * Method setIdPaymentMethod.
     * 
     * @param idPaymentMethod long
     */
    public void setIdPaymentMethod(long idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    /**
     * Method getPaymentMethodType.
     * 
     * @return long
     */
    public long getPaymentMethodType() {
        return paymentMethodType;
    }

    /**
     * Method setPaymentMethodType.
     * 
     * @param paymentMethodType long
     */
    public void setPaymentMethodType(long paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    /**
     * Method getDescription.
     * 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method setDescription.
     * 
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
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

}
