/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.ArrayList;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class ExonerationType extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    private long id;

    /**
     * Field exonerationName.
     */
    private String exonerationName;

    /**
     * Field calculateTax.
     */
    private boolean calculateTax;

    /**
     * Field partial.
     */
    private boolean partial;

    /**
     * Field exoneratedArticles.
     */
    private transient ArrayList<Article> exoneratedArticles;

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
     * Method getExonerationName.
     * 
     * @return String
     */
    public String getExonerationName() {
        return exonerationName;
    }

    /**
     * Method setExonerationName.
     * 
     * @param exonerationName String
     */
    public void setExonerationName(String exonerationName) {
        this.exonerationName = exonerationName;
    }

    /**
     * Method isCalculateTax.
     * 
     * @return boolean
     */
    public boolean isCalculateTax() {
        return calculateTax;
    }

    /**
     * Method setCalculateTax.
     * 
     * @param calculateTax boolean
     */
    public void setCalculateTax(boolean calculateTax) {
        this.calculateTax = calculateTax;
    }

    /**
     * Method isPartial.
     * 
     * @return boolean
     */
    public boolean isPartial() {
        return partial;
    }

    /**
     * Method setPartial.
     * 
     * @param partial boolean
     */
    public void setPartial(boolean partial) {
        this.partial = partial;
    }

    /**
     * Method getExoneratedArticles.
     * 
     * @return ArrayList<Article>
     */
    public ArrayList<Article> getExoneratedArticles() {
        return exoneratedArticles;
    }

    /**
     * Method setExoneratedArticles.
     * 
     * @param exoneratedArticles ArrayList<Article>
     */
    public void setExoneratedArticles(ArrayList<Article> exoneratedArticles) {
        this.exoneratedArticles = exoneratedArticles;
    }
}
