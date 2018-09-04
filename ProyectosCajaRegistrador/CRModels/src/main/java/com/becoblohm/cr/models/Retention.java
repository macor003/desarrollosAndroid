/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.util.List;

import com.becoblohm.cr.types.CRBigDecimal;
import com.epa.mvc.core.AbstractModel;

/**
 * @version $Revision: 1.0 $
 */
public class Retention extends AbstractModel {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field id.
     */
    private Long id;

    /**
     * Field porcenminret.
     */
    private CRBigDecimal porcenminret;

    /**
     * Field porcemaxret.
     */
    private CRBigDecimal porcemaxret;

    /**
     * Field tipoartretencion.
     */
    private String tipoartretencion;

    /**
     * Field formadepago.
     */
    private PaymentMethod formadepago;

    /**
     * Field categoryLineRetencion.
     */
    private transient List<CategoryLineRetention> categoryLineRetencion;

    /**
     * Field categoryLineRetencion.
     */
    private int group;

    /**
     * 
     */
    public Retention() {
    }

    /**
     * @param id
     * @param porcenminret
     * @param porcemaxret
     * @param tipoartretencion
     * 
     * @param fdp PaymentMethod670.03
     */
    public Retention(Long id, CRBigDecimal porcenminret, CRBigDecimal porcemaxret, String tipoartretencion,
                     PaymentMethod fdp) {
        super();
        this.id = id;
        this.porcenminret = porcenminret;
        this.porcemaxret = porcemaxret;
        this.tipoartretencion = tipoartretencion;
        this.formadepago = fdp;
    }

    /**
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
     * @return the porcenminret
     */
    public CRBigDecimal getPorcenminret() {
        return porcenminret;
    }

    /**
     * @param porcenminret the porcenminret to set
     */
    public void setPorcenminret(CRBigDecimal porcenminret) {
        this.porcenminret = porcenminret;
    }

    /**
     * @return the porcemaxret
     */
    public CRBigDecimal getPorcemaxret() {
        return porcemaxret;
    }

    /**
     * @param porcemaxret the porcemaxret to set
     */
    public void setPorcemaxret(CRBigDecimal porcemaxret) {
        this.porcemaxret = porcemaxret;
    }

    /**
     * @return the tipoartretencion
     */
    public String getTipoartretencion() {
        return tipoartretencion;
    }

    /**
     * @param tipoartretencion the tipoartretencion to set
     */
    public void setTipoartretencion(String tipoartretencion) {
        this.tipoartretencion = tipoartretencion;
    }

    /**
     * @return the idFormadepago
     */
    public PaymentMethod getFormadepago() {
        return formadepago;
    }

    /**
     * @param fdp PaymentMethod
     */
    public void setFormadepago(PaymentMethod fdp) {
        this.formadepago = fdp;
    }

    public List<CategoryLineRetention> getCategoryLineRetencion() {
        return categoryLineRetencion;
    }

    public void setCategoryLineRetencion(List<CategoryLineRetention> categoryLineRetencion) {
        this.categoryLineRetencion = categoryLineRetencion;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

}
