/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa400.Articulocodigoexterno;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ArticulocodigoexternoJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field jpaController.
	 */
	private crjpa400.ArticulocodigoexternoJpaController jpaController;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Articulocodigoexterno";

	/**
	 * Constructor for ArticulocodigoexternoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ArticulocodigoexternoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
		this.jpaController = new crjpa400.ArticulocodigoexternoJpaController(emf);
	}

	/**
	 * Method create.
	 * 
	 * @param obj
	 *            Articulocodigoexterno
	 * @throws JpaException
	 */
	public void create(Articulocodigoexterno obj) throws JpaException {
		try {
			jpaController.create(obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JpaException();
		}
	}

	/**
	 * Method findArticuloEntitiesByCodigoext.
	 * 
	 * @param codExt
	 *            Long
	 * @return crjpa400.Articulo
	 */
	protected crjpa400.Articulo findArticuloEntitiesByCodigoext(Long codExt) {
		try {
			Query query = jpaController.getEntityManager()
					.createNamedQuery("Articulocodigoexterno.findByCodigoExterno");
			query.setParameter("codigoExterno", codExt);
			return ((Articulocodigoexterno) query.getSingleResult()).getIdArticulo();
		} catch (Exception ex) {
			System.out.println("Articulo no encontrado por codigo externo #" + codExt);
			return null;
		} finally {
			jpaController.getEntityManager().close();
		}
	}
}
