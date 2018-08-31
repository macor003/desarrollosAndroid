/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class CreditsOperation extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 255277495738607526L;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field description.
     */
    private String description;

    /**
     * Constructor for CreditsOperation.
     * 
     * @param id long
     * @param description String
     */
    public CreditsOperation(long id, String description) {
        super();
        this.id = id;
        this.description = description;
    }

    /**
     * Constructor for CreditsOperation.
     */
    public CreditsOperation() {
        super();
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
     * Method initialize.
     * 
     * @param creditsOperation CreditsOperation
     */
    public void initialize(CreditsOperation creditsOperation) {
        this.id = creditsOperation.getId();
        this.description = creditsOperation.getDescription();
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.CreditsOperation
     */
    public com.becoblohm.cr.net.models.CreditsOperation toNetModel() {
        com.becoblohm.cr.net.models.CreditsOperation operation = new com.becoblohm.cr.net.models.CreditsOperation();
        operation.setId(this.getId());
        return operation;
    }

}
