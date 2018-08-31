/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ExoneradoArticuloJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Exoneradoarticulo";

	/**
	 * Constructor for ExoneradoArticuloJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ExoneradoArticuloJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}
}
