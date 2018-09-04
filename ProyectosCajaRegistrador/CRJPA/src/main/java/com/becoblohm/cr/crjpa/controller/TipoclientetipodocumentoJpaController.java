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
public class TipoclientetipodocumentoJpaController extends AbstractJPAController {

    /**
     * Field entityName.
     */
    private static String entityName = "Tipoclientetipodocumento";

    /**
     * Field jpaController.
     */
    private crjpa.TipoclientetipodocumentoJpaController jpaController;

    /**
     * Constructor for TipoclientetipodocumentoJpaController.
     * 
     * @param emf EntityManagerFactory
     */
    public TipoclientetipodocumentoJpaController(EntityManagerFactory emf) {
        super(AbstractJPAController.POSSOURCE, entityName);
        this.jpaController = new crjpa.TipoclientetipodocumentoJpaController(emf);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
