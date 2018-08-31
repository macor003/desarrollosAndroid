/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class DeliveryConditionCR400 extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 4728611938604616333L;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field type.
     */
    private String type;

    /**
     * Field description.
     */
    private String description;

    /**
     * Field clientIdRequired.
     */
    private boolean clientIdRequired;

    /**
     * Field phoneNumberRequired.
     */
    private boolean phoneNumberRequired;

    /**
     * Field addressRequired.
     */
    private boolean addressRequired;

    /**
     * Field documentType.
     */
    private DocumentType documentType;

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
     * Method getType.
     * 
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Method setType.
     * 
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
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
     * Method isClientIdRequired.
     * 
     * @return boolean
     */
    public boolean isClientIdRequired() {
        return clientIdRequired;
    }

    /**
     * Method setClientIdRequired.
     * 
     * @param clientIdRequired boolean
     */
    public void setClientIdRequired(boolean clientIdRequired) {
        this.clientIdRequired = clientIdRequired;
    }

    /**
     * Method isPhoneNumberRequired.
     * 
     * @return boolean
     */
    public boolean isPhoneNumberRequired() {
        return phoneNumberRequired;
    }

    /**
     * Method setPhoneNumberRequired.
     * 
     * @param phoneNumberRequired boolean
     */
    public void setPhoneNumberRequired(boolean phoneNumberRequired) {
        this.phoneNumberRequired = phoneNumberRequired;
    }

    /**
     * Method isAddressRequired.
     * 
     * @return boolean
     */
    public boolean isAddressRequired() {
        return addressRequired;
    }

    /**
     * Method setAddressRequired.
     * 
     * @param addressRequired boolean
     */
    public void setAddressRequired(boolean addressRequired) {
        this.addressRequired = addressRequired;
    }

    /**
     * Method getDocumentType.
     * 
     * @return DocumentType
     */
    public DocumentType getDocumentType() {
        return this.documentType;
    }

    /**
     * Method setDocumentType.
     * 
     * @param documentTypeId DocumentType
     */
    public void setDocumentType(DocumentType documentTypeId) {
        this.documentType = documentTypeId;
    }

}
