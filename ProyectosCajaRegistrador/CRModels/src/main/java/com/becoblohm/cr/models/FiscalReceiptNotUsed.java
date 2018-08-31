/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.math.BigInteger;
import java.util.Date;

/**
 */
public class FiscalReceiptNotUsed {

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field numeroComprobante.
     */
    private BigInteger numeroComprobante;

    /**
     * Field fecha.
     */
    private Date fecha;

    /**
     * Field estasincronizado.
     */
    private String estasincronizado;

    /**
     * Field idComprobantefiscalpreimpreso.
     */
    private PrePrintedFiscalReceipt idComprobantefiscalpreimpreso;

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
     * @return the numeroComprobante
     */
    public BigInteger getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * @param numeroComprobante the numeroComprobante to set
     */
    public void setNumeroComprobante(BigInteger numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    /**
     * 
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @return the estasincronizado
     */
    public String getEstasincronizado() {
        return estasincronizado;
    }

    /**
     * @param estasincronizado the estasincronizado to set
     */
    public void setEstasincronizado(String estasincronizado) {
        this.estasincronizado = estasincronizado;
    }

    /**
     * 
     * @return the idComprobantefiscalpreimpreso
     */
    public PrePrintedFiscalReceipt getIdComprobantefiscalpreimpreso() {
        return idComprobantefiscalpreimpreso;
    }

    /**
     * @param idComprobantefiscalpreimpreso the idComprobantefiscalpreimpreso to set
     */
    public void setIdComprobantefiscalpreimpreso(PrePrintedFiscalReceipt idComprobantefiscalpreimpreso) {
        this.idComprobantefiscalpreimpreso = idComprobantefiscalpreimpreso;
    }
}
