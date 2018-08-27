/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class ArticleSaleUnit {

    /**
     * Field id.
     */
    private long id;

    /**
     * Field idArticle.
     */
    private long idArticle;

    /**
     * Field price.
     */
    private CRBigDecimal price;

    /**
     * Field date.
     */
    private Date date;

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
     * Method getIdArticle.
     * 
     * @return long
     */
    public long getIdArticle() {
        return idArticle;
    }

    /**
     * Method setIdArticle.
     * 
     * @param idArticle long
     */
    public void setIdArticle(long idArticle) {
        this.idArticle = idArticle;
    }

    /**
     * Method getPrice.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getPrice() {
        return price;
    }

    /**
     * Method setPrice.
     * 
     * @param price CRBigDecimal
     */
    public void setPrice(CRBigDecimal price) {
        this.price = price;
    }

    /**
     * Method getDate.
     * 
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Method setDate.
     * 
     * @param date Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
