/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa400.Opcion;

/**
 * 
 * @author Magroberth Alvarado (programador18)
 * 
 * @version $Revision: 1.0 $
 */
public class OpcionJpaController extends AbstractJPAController {

	/**
	 * Field entityName.
	 */
	private static String entityName = "Opcion";
	/**
	 * Field emf.
	 */
	private static EntityManagerFactory emf;
	/**
	 * Field jpaController.
	 */
	private static crjpa400.OpcionJpaController jpaController;

	/**
	 * Constructor for OpcionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public OpcionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		jpaController = new crjpa400.OpcionJpaController(emf);
	}

	/**
	 * Method findById.
	 * 
	 * @param id
	 *            Long
	 * @return String
	 */
	public String findById(Long id) {

		Opcion op = jpaController.findOpcion(id);

		return op.getValor();
	}

	/**
	 * Method loadOptions.
	 * 
	 * @return Properties
	 */
	public Properties loadOptions() {
		List<Opcion> opciones = jpaController.findOpcionEntities();
		Iterator<Opcion> it = opciones.iterator();
		Properties props = new Properties();
		while (it.hasNext()) {
			Opcion op = it.next();
			props.put(op.getId().toString().trim(), op.getValor().trim());
		}
		return props;
	}

}
