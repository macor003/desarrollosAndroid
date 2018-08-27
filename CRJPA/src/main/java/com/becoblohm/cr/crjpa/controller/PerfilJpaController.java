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
public class PerfilJpaController extends AbstractJPAController {

    /**
     * Field entityName.
     */
    private static String entityName = "Perfil";

    /**
     * Constructor for PerfilJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public PerfilJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
