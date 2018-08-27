/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/**
 * 
 */
package com.becoblohm.cr.models;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class CommitmentPaymentDetailsLines extends AbstractModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field descripcion.
     */
    private String descripcion;

    /**
     * Field monto.
     */
    private CRBigDecimal monto = CRBigDecimal.ZERO;

    /**
     * 
     */
    public CommitmentPaymentDetailsLines() {
        super();
    }

    /**
     * @param id
     * @param descripcion
     * @param monto
     */
    public CommitmentPaymentDetailsLines(Long id, String descripcion, CRBigDecimal monto) {
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return the monto
     */
    public CRBigDecimal getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(CRBigDecimal monto) {
        this.monto = monto;
    }

}
