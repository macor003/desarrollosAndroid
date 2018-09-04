/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.ArrayList;
import java.util.Collection;

/**
 */
public class UserAuthorization extends User {

    /**
     * Field pass.
     */
    private String pass = "";

    /**
     * Field barcode.
     */
    private String barcode;

    /**
     * Field profiles.
     */
    private Collection<String> profiles;

    /**
     * Field hasProfile.
     */
    private boolean hasProfile;

    // private boolean changeAuthInfo;

    /**
     * Constructor for UserAuthorization.
     */
    public UserAuthorization() {

        this.profiles = new ArrayList<String>();

    }

    /**
     * Constructor for UserAuthorization.
     * 
     * @param id int
     * @param pass String
     */
    public UserAuthorization(int id, String pass) {

        this.idEPA = id;
        this.setPass(pass);
        this.profiles = new ArrayList<String>();
    }

    /**
     * Method getPass.
     * 
     * @return String
     */
    public String getPass() {
        return pass;
    }

    /**
     * Method setPass.
     * 
     * @param pass String
     */
    public void setPass(String pass) {
        String tmp = this.pass;
        this.pass = pass;
        this.fire("pass", tmp, pass);
    }

    // public void setProfiles(){
    //
    // // aquí se busca en la base de datos los perfiles del usuario
    // this.profiles = new ArrayList<String>();
    // profiles.add("01");
    // profiles.add("02");
    //
    // }

    /**
     * Method getProfiles.
     * 
     * @return Collection<String>
     */
    public Collection<String> getProfiles() {

        return this.profiles;
    }

    /**
     * 
     * @param profile String
     */
    public void setProfile(String profile) {
        if (this.profiles != null) {
            profiles.add(profile);
        }
    }

    /**
     * 
     * @return the hasProfile
     */
    public boolean hasProfile() {
        return hasProfile;
    }

    /**
     * Method addProfile.
     * 
     * @param profile String
     */
    public void addProfile(String profile) {
        this.profiles.add(profile);
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
     * Method setBarcode.
     * 
     * @param barcode String
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * Method getBarcode.
     * 
     * @return String
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param changeAuthInfo the changeAuthInfo to set
     */
    /*
     * public void setChangeAuthInfo(boolean changeAuthInfo) { this.changeAuthInfo =
     * changeAuthInfo; } /**
     * @return the changeAuthInfo
     */
    /*
     * public boolean isChangeAuthInfo() { return changeAuthInfo; }
     */

    /**
     * @param lastPasswordChange the lastPasswordChange to set
     */

}
