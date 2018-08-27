/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.SaleUnit;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class ArticulounidadventaJpaController extends AbstractJPAController {

    /**
     * Field jpaController.
     */
    crjpa.ArticulounidadventaJpaController jpaController;

    /**
     * Field saleUnitControler.
     */
    UnidadventaJpaController saleUnitControler;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Articulounidadventa";

    /**
     * Constructor for ArticulounidadventaJpaController.
     * 
     * @param p_emf EntityManagerFactory
     */
    public ArticulounidadventaJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = p_emf;
        jpaController = new crjpa.ArticulounidadventaJpaController(p_emf);
        saleUnitControler = new UnidadventaJpaController(this.emf);
    }

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method fromJPA.
     * 
     * @param jpaSaleUnit crjpa.Articulounidadventa
     * @return SaleUnit
     */
    protected SaleUnit fromJPA(crjpa.Articulounidadventa jpaSaleUnit) {
        SaleUnit tmp = new SaleUnit();
        tmp = saleUnitControler.fromJpa(jpaSaleUnit.getIdUnidadventa());
        tmp.setPrice(new CRBigDecimal(jpaSaleUnit.getPrecio().doubleValue()));
        return tmp;
    }

    /**
     * Method findArticuloUnidadventa.
     * 
     * @param id Long
     * @return SaleUnit
     */
    public SaleUnit findArticuloUnidadventa(Long id) {
        SaleUnit saleUnit = new SaleUnit();
        saleUnit = fromJPA(jpaController.findArticulounidadventa(id));
        return saleUnit;
    }

}
