/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import com.epa.mvc.core.AbstractModel;

/**
 */
public class Family extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field line.
     */
    private ArticleLine line;

    /**
     * Field description.
     */
    private String description;

    /**
     * Method getId.
     * 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id Long
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method getLine.
     * 
     * @return ArticleLine
     */
    public ArticleLine getLine() {
        return line;
    }

    /**
     * Method setLine.
     * 
     * @param line ArticleLine
     */
    public void setLine(ArticleLine line) {
        this.line = line;
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
}
