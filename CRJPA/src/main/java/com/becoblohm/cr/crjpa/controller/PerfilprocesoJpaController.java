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
public class PerfilprocesoJpaController extends AbstractJPAController {// extends
                                                                       // crjpa.PerfilProcesoJpaController
                                                                       // {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field emf.
     */
    private EntityManagerFactory emf;

    /**
     * Field jpacontroller.
     */
    private crjpa.PerfilprocesoJpaController jpacontroller;

    /**
     * Field entityName.
     */
    private static String entityName = "Perfilproceso";

    /**
     * Constructor for PerfilprocesoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public PerfilprocesoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpacontroller = new crjpa.PerfilprocesoJpaController(emf);
    }

}
