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
public class CorrelativoJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Correlativo";

	/**
	 * Constructor for CorrelativoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CorrelativoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}
}