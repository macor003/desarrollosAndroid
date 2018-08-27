/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class Store extends AbstractModel {

    /**
     * Constructor for Store.
     * 
     * @param id String
     * @param name String
     * @param address String
     * @param phone String
     * @param fiscalId String
     */
    public Store(String id, String name, String address, String phone, String fiscalId) {
        super();
        this.id = id;
        this.setName(name);
        this.address = address;
        this.officePhone = phone;
        this.fiscalId = fiscalId;
    }

    /**
     * Constructor for Store.
     * 
     * @param id String
     * @param name String
     * @param address String
     * @param phone String
     * @param fiscalId String
     * @param office String
     * @param officeAddress String
     */
    public Store(String id, String name, String address, String phone, String fiscalId, String office,
                 String officeAddress) {
        super();
        this.id = id;
        this.setName(name);
        this.address = address;
        this.officePhone = phone;
        this.fiscalId = fiscalId;
        this.office = office;
        this.officeAddress = officeAddress;
    }

    /**
     * Constructor for Store.
     */
    public Store() {

    }

    /**
     * Constructor for Store.
     * 
     * @param store String
     * @param storeName String
     * @param storeAddress String
     * @param storePhone String
     * @param rif String
     * @param storeOffice String
     * @param storeOfficeAddress String
     * @param storeOfficePhone String
     */
    public Store(String store, String storeName, String storeAddress, String storePhone, String rif,
                 String storeOffice, String storeOfficeAddress, String storeOfficePhone) {
        super();
        this.id = store;
        this.setName(storeName);
        this.address = storeAddress;
        this.setStorePhone(storePhone);
        this.fiscalId = rif;
        this.office = storeOffice;
        this.officeAddress = storeOfficeAddress;
        this.officePhone = storeOfficePhone;

    }

    /**
     * Field id.
     */
    private String id;

    /**
     * Field address.
     */
    private String address;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field storePhone.
     */
    private String storePhone;

    /**
     * Field fiscalId.
     */
    private String fiscalId;

    /**
     * Field office.
     */
    private String office;

    /**
     * Field officeAddress.
     */
    private String officeAddress;

    /**
     * Field officePhone.
     */
    private String officePhone;

    /**
     * 
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * 
     * @return the phone
     */
    public String getOfficePhone() {
        return this.officePhone;
    }

    /**
     * 
     * @param officePhone String
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**
     * 
     * @return the fiscalId
     */
    public String getFiscalId() {
        return fiscalId;
    }

    /**
     * @param fiscalId the fiscalId to set
     */
    public void setFiscalId(String fiscalId) {
        this.fiscalId = fiscalId;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Method setOfficeAddress.
     * 
     * @param officeAddress String
     */
    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    /**
     * Method getOfficeAddress.
     * 
     * @return String
     */
    public String getOfficeAddress() {
        return officeAddress;
    }

    /**
     * Method setOffice.
     * 
     * @param office String
     */
    public void setOffice(String office) {
        this.office = office;
    }

    /**
     * Method getOffice.
     * 
     * @return String
     */
    public String getOffice() {
        return office;
    }

    /**
     * Method setStorePhone.
     * 
     * @param storePhone String
     */
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    /**
     * Method getStorePhone.
     * 
     * @return String
     */
    public String getStorePhone() {
        return storePhone;
    }

}
