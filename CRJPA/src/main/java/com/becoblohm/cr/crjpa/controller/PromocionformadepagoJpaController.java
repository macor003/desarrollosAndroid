/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.PaymentMethod;

import crjpa.Promocionformadepago;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class PromocionformadepagoJpaController extends AbstractJPAController {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
    
     */
    private crjpa.PromocionformadepagoJpaController jpaController;

    /**
     * Field emf.
     */
    EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Promocionformadepago";

    /**
     * Constructor for PromocionformadepagoJpaController.
     * 
     * @param p_emf EntityManagerFactory
     */
    public PromocionformadepagoJpaController(EntityManagerFactory p_emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.PromocionformadepagoJpaController(p_emf);
        this.emf = p_emf;
    }

    /**
     * Method fromJPA.
     * 
     * @param obj List<Promocionformadepago>
     * @return List<PaymentMethod>
     */
    public List<PaymentMethod> fromJPA(List<Promocionformadepago> obj) {
        List<PaymentMethod> pmList = new ArrayList<PaymentMethod>();

        FormadepagoJpaController fm = new FormadepagoJpaController(emf);

        for (Promocionformadepago pmf : obj) {
            // TODO Como carajo pasar las FDP
            // pmList.add(fm.fromJPA(pmf.getIdFormaDePago(),null));
        }
        return pmList;

    }

}
