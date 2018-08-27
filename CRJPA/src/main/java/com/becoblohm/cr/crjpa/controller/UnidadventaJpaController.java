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
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class UnidadventaJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpaController.
     */
    private crjpa.UnidadventaJpaController jpaController;

    /**
     * Field entityName.
     */
    private static String entityName = "Unidadventa";

    /**
     * Constructor for UnidadventaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public UnidadventaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
        jpaController = new crjpa.UnidadventaJpaController(this.emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method findUnidadventa.
     * 
     * @param id Long
     * @return SaleUnit
     */
    public SaleUnit findUnidadventa(Long id) {

        return null;
    }

    /**
     * Method findDefaultSaleUnit.
     * 
     * @param id long
     * @return SaleUnit
     */
    public SaleUnit findDefaultSaleUnit(long id) {
        SaleUnit saleUnit = fromJpa(jpaController.findUnidadventa(id));
        saleUnit.setPrice(CRBigDecimal.ZERO);
        return saleUnit;
    }

    /**
     * Method fromJpa.
     * 
     * @param jpaSaleUnit crjpa.Unidadventa
     * @return SaleUnit
     */
    protected SaleUnit fromJpa(crjpa.Unidadventa jpaSaleUnit) {
        CRBigDecimal defaultQuantity = CRBigDecimal.ONE;
        SaleUnit saleUnit = new SaleUnit();
        saleUnit.setAllowFraction(ActiveValues.get(String.valueOf(jpaSaleUnit.getEsfraccion())).getValue());
        saleUnit.setDefaultQuantity(defaultQuantity);
        saleUnit.setId(jpaSaleUnit.getId());
        return saleUnit;
    }

}
