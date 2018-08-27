/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class MonedadenominacionJpaController extends AbstractJPAController {

	
	/**
	 * Field emf.
	 */
	private final EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Monedadenominacion";

	/**
	 * Constructor for MonedadenominacionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public MonedadenominacionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf=emf;
	}


}
