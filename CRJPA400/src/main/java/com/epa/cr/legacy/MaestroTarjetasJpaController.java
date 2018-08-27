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
public class MaestroTarjetasJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Cuentacredito";

	/**
	 * Constructor for MaestroTarjetasJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MaestroTarjetasJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}
}
