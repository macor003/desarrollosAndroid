/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.Date;

/**
 */
public class CategoryLineRetention {

    /**
     * Field id.
     */
    private int id;

    /**
     * Field fecha.
     */
    private Date fecha;
    // private ArticleCategory idCategoria;

    /**
     * Field idLinea.
     */
    private ArticleLine idLinea;

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
     * Method getFecha.
     * 
     * @return Date
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Method setFecha.
     * 
     * @param fecha Date
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // public void setIdCategoria(ArticleCategory idCategoria) {
    // this.idCategoria = idCategoria;
    // }
    // public ArticleCategory getIdCategoria() {
    // return idCategoria;
    // }
    /**
     * Method setIdLinea.
     * 
     * @param idLinea ArticleLine
     */
    public void setIdLinea(ArticleLine idLinea) {
        this.idLinea = idLinea;
    }

    /**
     * Method getIdLinea.
     * 
     * @return ArticleLine
     */
    public ArticleLine getIdLinea() {
        return idLinea;
    }

}
