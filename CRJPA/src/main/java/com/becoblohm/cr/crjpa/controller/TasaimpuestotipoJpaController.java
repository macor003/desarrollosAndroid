/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Tax;
import com.becoblohm.cr.types.ActiveValues;
import com.becoblohm.cr.types.CRBigDecimal;

import crjpa.Tasaimpuestotipo;
import crjpa.Tasaimpuestovalor;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TasaimpuestotipoJpaController extends AbstractJPAController {

    /**
     * Field jpacontroller.
     */
    crjpa.TasaimpuestotipoJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Tasaimpuestotipo";

    /**
     * Constructor for TasaimpuestotipoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TasaimpuestotipoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        jpacontroller = new crjpa.TasaimpuestotipoJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Method fromJPA.
     * 
     * @param jpaTax Tasaimpuestotipo
     * @return Tax
     */
    Tax fromJPA(Tasaimpuestotipo jpaTax) {
        Tax tax = new Tax();
        tax.setActive(ActiveValues.valueOf(jpaTax.getEstavigente()).getValue());
        tax.setName(jpaTax.getNombre());
        tax.setTaxDate(jpaTax.getFecha());
        tax.setCode(jpaTax.getCodigo());
        TasaimpuestovalorJpaController taxValuecontroller = new TasaimpuestovalorJpaController(
                jpacontroller.getEntityManager().getEntityManagerFactory());
        Tasaimpuestovalor jpaTaxValue = taxValuecontroller.findTasaivaValorByidTipo(jpaTax.getId());

        tax.setTaxRate(new CRBigDecimal(jpaTaxValue.getTasa().doubleValue()));
        tax.setId(jpaTax.getId());
        return tax;
    }

    /**
     * Method findTasaivaTipo.
     * 
     * @param long1 Long
     * @return Tax
     */
    public Tax findTasaivaTipo(Long long1) {
        return fromJPA(jpacontroller.findTasaimpuestotipo(long1));

    }

}
