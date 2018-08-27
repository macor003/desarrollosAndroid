/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.DiscountType;
import com.becoblohm.cr.types.ActiveValues;

import crjpa.Promociontipodescuento;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class PromociontipodescuentoJpaController extends AbstractJPAController {

    /**
     * 
     */
    private crjpa.PromociontipodescuentoJpaController jpaController;

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field entityName.
     */
    private static String entityName = "Promociontipodescuento";

    /**
    
     */
    EntityManagerFactory emf;

    /**
     * Constructor for PromociontipodescuentoJpaController.
     * 
     * @param p_emf EntityManagerFactory
     */
    public PromociontipodescuentoJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.PromociontipodescuentoJpaController(p_emf);
        this.emf = p_emf;
    }

    /**
     * Method finDiscountTypeByCode.
     * 
     * @param id long
     * @return DiscountType
     */
    public DiscountType finDiscountTypeByCode(long id) {
        return fromJPA(this.jpaController.findPromociontipodescuento(id));
    }

    /**
     * Method fromJPA.
     * 
     * @param findPromociontipodescuento Promociontipodescuento
     * @return DiscountType
     */
    private DiscountType fromJPA(Promociontipodescuento findPromociontipodescuento) {

        DiscountType pmList = new DiscountType();
        pmList.setName(findPromociontipodescuento.getIdTipodescuento().getNombre());
        pmList.setId(findPromociontipodescuento.getIdTipodescuento().getId());

        return pmList;
    }

    /**
     * Method fromJPA.
     * 
     * @param obj List<Promociontipodescuento>
     * @return DiscountType
     */
    public DiscountType fromJPA(List<Promociontipodescuento> obj) {
        DiscountType pmList = new DiscountType();
        for (Promociontipodescuento ptd : obj) {
            pmList.setName(ptd.getIdTipodescuento().getNombre());
            pmList.setDescription(ptd.getIdTipodescuento().getDescripcion());
            pmList.setId(ptd.getIdTipodescuento().getId());
            pmList.setPercent(ActiveValues.get(ptd.getIdTipodescuento().getEsporcentaje()).getValue());
        }
        return pmList;
    }
}
