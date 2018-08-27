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
import com.becoblohm.cr.models.CategoryLineRetention;

import crjpa400.Categorialinearetencion;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class CategoriaLinearetencionJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Categorialinearetencion";

	/**
	 * Constructor for CategoriaLinearetencionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CategoriaLinearetencionJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}
	
	
	/**
	 * Method fromJPA.
	 * 
	 * @param categorialinearetencion
	 *            Categorialinearetencion
	 * @return CategoryLineRetention
	 */
	public CategoryLineRetention fromJPA(Categorialinearetencion categorialinearetencion) {
		CategoriaJpaController catjpa = new CategoriaJpaController(emf);
		LineaJpaController linejpa = new LineaJpaController(emf);
		CategoryLineRetention catlineret = new CategoryLineRetention();
		catlineret.setFecha(categorialinearetencion.getFecha());
		catlineret.setId(categorialinearetencion.getId().intValue());
		// catlineret.setIdCategoria(catjpa.fromJPA(categorialinearetencion.getIdCategoria()));
		// catlineret.setIdFormadepago(FormadepagoJpaController.fromJPA(idFormadepago));
		catlineret.setIdLinea(linejpa.fromJPA(categorialinearetencion.getIdLinea()));

		return catlineret;
	}

	
	
	
}
