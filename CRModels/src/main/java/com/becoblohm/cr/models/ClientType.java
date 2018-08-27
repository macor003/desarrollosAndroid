/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.ArrayList;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class ClientType extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field symbol.
     */
    private String symbol;

    /**
     * Field name.
     */
    private String name;

    /**
     * Field mask.
     */
    private String mask;

    /**
     * Field allowedDocumentTypes.
     */
    private ArrayList<DocumentType> allowedDocumentTypes;

    /**
     * Field id.
     */
    private int id;

    /**
     * Field taxPayer.
     */
    private boolean taxPayer;

    /**
     * Field isActive.
     */
    private boolean isActive;

    /**
     * Field validateID.
     */
    private int validateID;

    /**
     * Field validationMasks.
     */
    private ArrayList<String> validationMasks;

    /**
     * Constructor for ClientType.
     * 
     * @param symbol String
     * @param id int
     * @param mask String
     * @param taxPayer boolean
     * @param validateID int
     */
    public ClientType(String symbol, int id, String mask, boolean taxPayer, int validateID) {
        this.symbol = symbol;
        this.id = id;
        this.mask = mask;
        this.taxPayer = taxPayer;
        this.validateID = validateID;
    }

    /**
     * Constructor for ClientType.
     */
    public ClientType() {
    }

    /**
     * Constructor for ClientType.
     * 
     * @param clientType com.becoblohm.cr.net.models.ClientType
     */
    public ClientType(com.becoblohm.cr.net.models.ClientType clientType) {
        this.setId(clientType.getId());
    }

    /**
     * Method getSymbol.
     * 
     * @return String
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Method setSymbol.
     * 
     * @param symbol String
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
        this.name = name;
    }

    /**
     * Method getMask.
     * 
     * @return String
     */
    public String getMask() {
        return mask;
    }

    /**
     * Method setMask.
     * 
     * @param mask String
     */
    public void setMask(String mask) {
        this.mask = mask;
    }

    /**
     * Method getAllowedDocumentTypes.
     * 
     * @return ArrayList<DocumentType>
     */
    public ArrayList<DocumentType> getAllowedDocumentTypes() {
        return allowedDocumentTypes;
    }

    /**
     * Method setAllowedDocumentTypes.
     * 
     * @param allowedDocumentTypes ArrayList<DocumentType>
     */
    public void setAllowedDocumentTypes(ArrayList<DocumentType> allowedDocumentTypes) {
        this.allowedDocumentTypes = allowedDocumentTypes;
    }

    /**
     * Method getId.
     * 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method isTaxPayer.
     * 
     * @return boolean
     */
    public boolean isTaxPayer() {
        return taxPayer;
    }

    /**
     * Method setTaxPayer.
     * 
     * @param taxPayer boolean
     */
    public void setTaxPayer(boolean taxPayer) {
        this.taxPayer = taxPayer;
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

    /**
     * Method getValidateID.
     * 
     * @return int
     */
    public int getValidateID() {
        return validateID;
    }

    /**
     * Method setValidateID.
     * 
     * @param validateID int
     */
    public void setValidateID(int validateID) {
        this.validateID = validateID;
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.ClientType
     */
    public com.becoblohm.cr.net.models.ClientType toNetModel() {
        com.becoblohm.cr.net.models.ClientType clientType = new com.becoblohm.cr.net.models.ClientType();
        clientType.setId(this.getId());
        return clientType;
    }

    /**
     * 
     * @return the validationMasks
     */
    public ArrayList<String> getValidationMasks() {
        return validationMasks;
    }

    /**
     * @param validationMasks the validationMasks to set
     */
    public void setValidationMasks(ArrayList<String> validationMasks) {
        this.validationMasks = validationMasks;
    }

}
