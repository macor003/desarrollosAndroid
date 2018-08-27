/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class CreditsType extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = -1450659246125008417L;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field description.
     */
    private String description;

    /**
     * Field passwordRequired.
     */
    private boolean passwordRequired;

    /**
     * Constructor for CreditsType.
     * 
     * @param id long
     * @param description String
     */
    public CreditsType(long id, String description) {
        super();
        this.id = id;
        this.description = description;
    }

    /**
     * Constructor for CreditsType.
     */
    public CreditsType() {
        super();
    }

    /**
     * Constructor for CreditsType.
     * 
     * @param id int
     */
    public CreditsType(int id) {
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
     * Method isPasswordRequired.
     * 
     * @return boolean
     */
    public boolean isPasswordRequired() {
        return passwordRequired;
    }

    /**
     * Method setPasswordRequired.
     * 
     * @param passwordRequired boolean
     */
    public void setPasswordRequired(boolean passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

    /**
     * Method toNetModel.
     * 
     * @return com.becoblohm.cr.net.models.CreditsType
     */
    public com.becoblohm.cr.net.models.CreditsType toNetModel() {
        com.becoblohm.cr.net.models.CreditsType type = new com.becoblohm.cr.net.models.CreditsType();
        type.setDescription(description);
        type.setId(id);
        type.setPasswordRequired(passwordRequired);

        return type;
    }

}
