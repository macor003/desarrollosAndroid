/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 */
public class Process implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4845141999495368590L;

    /**
     * Field id.
     */
    private String id;

    /**
     * Field requiresAuthorization.
     */
    private boolean requiresAuthorization;

    /**
     * Field autoAuthorization.
     */
    private boolean autoAuthorization;

    /**
     * Field profiles.
     */
    private Collection<String> profiles;

    /**
     * Field description.
     */
    private String description;

    /**
     * Constructor for Process.
     */
    public Process() {

        this.id = "1";
        this.requiresAuthorization = true;
        profiles = new ArrayList<String>();
    }

    /**
     * 
     * @return the requiresAuthorization
     */
    public boolean requiresAuthorization() {
        return requiresAuthorization;
    }

    /**
     * @param requiresAuthorization the requiresAuthorization to set
     */
    public void setRequiresAuthorization(boolean requiresAuthorization) {
        this.requiresAuthorization = requiresAuthorization;
    }

    /**
     * Method addProfile.
     * 
     * @param profile String
     */
    public void addProfile(String profile) {
        profiles.add(profile);
    }

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
     * @return the profiles
     */
    public Collection<String> getProfiles() {
        return profiles;
    }

    /**
     * @param profiles the profiles to set
     */
    public void setProfiles(Collection<String> profiles) {
        this.profiles = profiles;
    }

    /**
     * Method isAProfile.
     * 
     * @param id String
     * @return boolean
     */
    public boolean isAProfile(String id) {

        if (this.profiles != null) {
            if (this.profiles.contains(id)) {
                return true;
            }

        }
        return false;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param autoAuthorization the autoAuthorization to set
     */
    public void setAutoAuthorization(boolean autoAuthorization) {
        this.autoAuthorization = autoAuthorization;
    }

    /**
     * 
     * @return the autoAuthorization
     */
    public boolean isAutoAuthorization() {
        return autoAuthorization;
    }

}
