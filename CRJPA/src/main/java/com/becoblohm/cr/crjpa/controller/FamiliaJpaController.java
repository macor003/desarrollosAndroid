/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;
import com.becoblohm.cr.models.Family;

import crjpa.Familia;

/**
 */
public class FamiliaJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field entityName.
     */
    private static String entityName = "Familia";

    /**
     * Constructor for FamiliaJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public FamiliaJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.emf = emf;
    }

    /**
     * Method fromJpa.
     * 
     * @param jpaFamily Familia
     * @return Family
     */
    public Family fromJpa(Familia jpaFamily) {
        LineaJpaController linecontroller = new LineaJpaController(this.emf);
        Family family = new Family();
        family.setDescription(jpaFamily.getDescripcion());
        family.setId(jpaFamily.getId());
        family.setLine(linecontroller.fromJPA(jpaFamily.getIdLinea()));
        return family;
    }
}
