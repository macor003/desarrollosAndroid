/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class Service extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field transactionServiceId.
     */
    private long transactionServiceId = -1;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field phoneNumber.
     */
    private String phoneNumber;

    /**
     * Field email.
     */
    private String email;

    /**
     * Field isActive.
     */
    private boolean isActive;

    /**
     * Field id.
     */
    private long id;

    /**
     * Method getTransactionServiceId.
     * 
     * @return long
     */
    public long getTransactionServiceId() {
        return transactionServiceId;
    }

    /**
     * Method setTransactionServiceId.
     * 
     * @param transactionServiceId long
     */
    public void setTransactionServiceId(long transactionServiceId) {
        this.transactionServiceId = transactionServiceId;
    }

    /**
     * Method getName.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName.
     * 
     * @param name String
     */
    public void setName(String name) {
        String tmp = this.name;
        this.name = name;
        this.fire("name", tmp, name);
    }

    /**
     * Method getPhoneNumber.
     * 
     * @return String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method setPhoneNumber.
     * 
     * @param phoneNumber String
     */
    public void setPhoneNumber(String phoneNumber) {
        String tmp = this.phoneNumber;
        this.phoneNumber = phoneNumber;
        if (phoneNumber != null && email != null) {
            isActive = true;
        }
        this.fire("phoneNumber", tmp, phoneNumber);
    }

    /**
     * Method getEmail.
     * 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method setEmail.
     * 
     * @param email String
     */
    public void setEmail(String email) {
        String tmp = this.email;
        this.email = email;
        if (phoneNumber != null && email != null) {
            isActive = true;
        }
        this.fire("email", tmp, email);
    }

    /**
     * Method getSerialversionuid.
     * 
     * @return long
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
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

}
