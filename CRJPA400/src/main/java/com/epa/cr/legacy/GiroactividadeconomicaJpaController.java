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
import com.becoblohm.cr.models.EconomicActivity;

import crjpa400.Giroactividadeconomica;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class GiroactividadeconomicaJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Giroactividadeconomica";
	/**
	 * Field jpaController.
	 */
	private crjpa400.GiroactividadeconomicaJpaController jpaController;

	/**
	 * Constructor for GiroactividadeconomicaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public GiroactividadeconomicaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		this.jpaController = new crjpa400.GiroactividadeconomicaJpaController(emf);
	}

	/**
	 * Method findGiroactividadeconomicaByCode.
	 * 
	 * @param id
	 *            long
	 * @return Giroactividadeconomica
	 */
	protected Giroactividadeconomica findGiroactividadeconomicaByCode(long id) {
		return this.jpaController.findGiroactividadeconomica(id);
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param idGiroactividadeconomica
	 *            Giroactividadeconomica
	 * @return EconomicActivity
	 */
	public EconomicActivity fromJPA(Giroactividadeconomica idGiroactividadeconomica) {
		EconomicActivity result = new EconomicActivity(idGiroactividadeconomica.getId());

		result.setName(idGiroactividadeconomica.getDescripcion());
		result.setCode(idGiroactividadeconomica.getCodigo());
		result.setCategory(idGiroactividadeconomica.getCategoria());

		return result;
	}
}
