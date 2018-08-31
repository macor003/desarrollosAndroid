/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import com.becoblohm.cr.types.CRBigDecimal;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class Synchronize {

    /**
     * Field serialVersionUID. (value is 8142808592044176177)
     */
    private static final long serialVersionUID = 8142808592044176177L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field nombre.
     */
    private String nombre;

    /**
     * Field escarga.
     */
    private String escarga;

    /**
     * Field esdescarga.
     */
    private String esdescarga;

    /**
     * Field ultimaSincronizacion.
     */
    private CRBigDecimal ultimaSincronizacion;

    /**
     * @param id
     * @param nombre
     * @param escarga
     * @param esdescarga
     * @param ultimaSincronizacion
     */
    public Synchronize(Long id, String nombre, String escarga, String esdescarga,
                       CRBigDecimal ultimaSincronizacion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.escarga = escarga;
        this.esdescarga = esdescarga;
        this.ultimaSincronizacion = ultimaSincronizacion;
    }

    /**
     * Constructor for Synchronize.
     */
    public Synchronize() {
        super();
    }

    /**
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return the escarga
     */
    public String getEscarga() {
        return escarga;
    }

    /**
     * @param escarga the escarga to set
     */
    public void setEscarga(String escarga) {
        this.escarga = escarga;
    }

    /**
     * 
     * @return the esdescarga
     */
    public String getEsdescarga() {
        return esdescarga;
    }

    /**
     * @param esdescarga the esdescarga to set
     */
    public void setEsdescarga(String esdescarga) {
        this.esdescarga = esdescarga;
    }

    /**
     * 
     * @return the ultimaSincronizacion
     */
    public CRBigDecimal getUltimaSincronizacion() {
        return ultimaSincronizacion;
    }

    /**
     * @param ultimaSincronizacion the ultimaSincronizacion to set
     */
    public void setUltimaSincronizacion(CRBigDecimal ultimaSincronizacion) {
        this.ultimaSincronizacion = ultimaSincronizacion;
    }

}
