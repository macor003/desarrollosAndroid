/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */

public class CategoriaLineaincluyeJpaController extends AbstractJPAController {

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpacontroller.
     */
    private crjpa.CategorialineaincluyeJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Categorialineaincluye";

    /**
     * Constructor for CategoriaLineaincluyeJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public CategoriaLineaincluyeJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.CategorialineaincluyeJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
