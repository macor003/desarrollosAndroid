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
public class MorosidadJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Morosidad";

	/**
	 * Constructor for MorosidadJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MorosidadJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
	}

}
