/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.DiscountType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Tipodescuento;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TipodescuentoJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.TipodescuentoJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Tipodescuento";

    /**
     * Constructor for TipodescuentoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TipodescuentoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.TipodescuentoJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method fromJPA.
     * 
     * @param jpaRebateType Tipodescuento
     * @return DiscountType
     */
    public DiscountType fromJPA(Tipodescuento jpaRebateType) {

        DiscountType discountType = new DiscountType();
        discountType.setName(jpaRebateType.getNombre());
        discountType.setActive(ActiveValues.get(jpaRebateType.getEstaactivo()).getValue());
        discountType.setAutomaticSale(ActiveValues.get(jpaRebateType.getFacturacionAutomatica()).getValue());
        discountType.setCode(jpaRebateType.getCodigo());
        discountType.setDate(jpaRebateType.getFecha());
        discountType.setDescription(jpaRebateType.getDescripcion());
        discountType.setId(jpaRebateType.getId());
        // discountType.setName(jpaRebateType.getNombre());
        discountType.setPercent(ActiveValues.get(jpaRebateType.getEsporcentaje()).getValue());

        return discountType;
    }

    /**
     * Method findTipodescuento.
     * 
     * @param id long
     * @return DiscountType
     */
    public DiscountType findTipodescuento(long id) {

        DiscountType discountType = new DiscountType();
        discountType = fromJPA(this.jpacontroller.findTipodescuento(id));
        return discountType;

    }

    /**
     * Method getDefault.
     * 
     * @param APP_DEFAULT long
     * @return DiscountType
     */
    public DiscountType getDefault(long APP_DEFAULT) {
        DiscountType discountType = null;
        Tipodescuento tmp = jpacontroller.findTipodescuento(APP_DEFAULT);
        discountType = fromJPA(tmp);
        discountType.setPromotion(true);
        return discountType;
    }

    /**
     * Method toJPA.
     * 
     * @param discountType DiscountType
     * @return Tipodescuento
     */
    public Tipodescuento toJPA(DiscountType discountType) {

        Tipodescuento tipoDescuento = new Tipodescuento();
        tipoDescuento.setCodigo(discountType.getCode());
        tipoDescuento.setDescripcion(discountType.getDescription());

        tipoDescuento.setEsporcentaje(ActiveValues.get(discountType.isPercent()).getString());
        tipoDescuento.setEstaactivo(ActiveValues.get(discountType.isActive()).getString());

        // TODO pendient por definir contenido de campo FacturacionAutomatica

        tipoDescuento.setFecha(discountType.getDate());
        tipoDescuento.setId(discountType.getId());
        tipoDescuento.setNombre(discountType.getName());
        tipoDescuento.setFacturacionAutomatica(ActiveValues.get(discountType.isAutomaticSale()).getString());

        return tipoDescuento;
    }

}
