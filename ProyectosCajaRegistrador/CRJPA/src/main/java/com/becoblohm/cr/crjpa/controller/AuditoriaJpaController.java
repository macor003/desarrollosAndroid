/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.becoblohm.cr.crjpa.controller;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

public class AuditoriaJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;

	/**
	 * Field entityName.
	 */
	private static String entityName = "Auditoria";

	/**
	 * Constructor for AuditoriaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AuditoriaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.POSSOURCE, entityName);
		this.emf = emf;
	}

}