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
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class TransaccionServicioJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "";

	/**
	 * Constructor for TransaccionServicioJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionServicioJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

}
