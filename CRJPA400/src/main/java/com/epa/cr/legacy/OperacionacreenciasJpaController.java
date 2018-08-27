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
import com.becoblohm.cr.models.CreditsOperation;

import crjpa400.Operacionacreencia;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class OperacionacreenciasJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Operacionacreencia";
	/**
	 * Field controller.
	 */
	crjpa400.OperacionacreenciaJpaController controller;

	/**
	 * Constructor for OperacionacreenciasJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public OperacionacreenciasJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.controller = new crjpa400.OperacionacreenciaJpaController(emf);
	}

	/**
	 * Method findOperacionacreencias.
	 * 
	 * @param id
	 *            Integer
	 * @return Operacionacreencia
	 */
	public Operacionacreencia findOperacionacreencias(Integer id) {
		return controller.findOperacionacreencia(id.longValue());
	}

	/**
	 * Method findOperacionacreencia.
	 * 
	 * @param id
	 *            Long
	 * @return Operacionacreencia
	 */
	public Operacionacreencia findOperacionacreencia(Long id) {
		return controller.findOperacionacreencia(id);
	}

	/**
	 * Method fromJPA.
	 * 
	 * @param op
	 *            Operacionacreencia
	 * @return CreditsOperation
	 */
	public CreditsOperation fromJPA(Operacionacreencia op) {

		CreditsOperation obj = new CreditsOperation();
		obj.setId(op.getId());
		obj.setDescription(op.getDescripcion());

		return obj;
	}

}
