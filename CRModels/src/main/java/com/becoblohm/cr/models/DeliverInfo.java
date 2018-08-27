/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class DeliverInfo extends AbstractModel {

    /**
     * Field name.
     */
    private String name;

    /**
     * Field iddoc.
     */
    private String iddoc;

    /**
     * Field phone.
     */
    private String phone;

    /**
     * Field address.
     */
    private String address;

    /**
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return the iddoc
     */
    public String getIddoc() {
        return iddoc;
    }

    /**
     * @param iddoc the iddoc to set
     */
    public void setIddoc(String iddoc) {
        this.iddoc = iddoc;
    }

    /**
     * 
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
