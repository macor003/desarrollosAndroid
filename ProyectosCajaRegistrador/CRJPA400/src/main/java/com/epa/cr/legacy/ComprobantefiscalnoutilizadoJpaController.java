/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epa.cr.legacy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ComprobantefiscalnoutilizadoJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Comprobantefiscalnoutilizado";

	/**
	 * Constructor for ComprobantefiscalnoutilizadoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ComprobantefiscalnoutilizadoJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method findLastComp.
	 * 
	 * @param caja
	 *            long
	 * @return long
	 */
	public long findLastComp(long caja) {
		long idComp;
		try {
			EntityManager em = emf.createEntityManager();
			String queryString = "SELECT MAX(t.id) FROM " + entityName + " t WHERE t.idSesion.idCaja.id = :id";
			Query query = em.createQuery(queryString);
			query.setParameter("id", caja);
			query.setMaxResults(1);
			idComp = (Long) query.getSingleResult();
		} catch (NoResultException ex) {
			idComp = 0;
		} catch (Exception ex) {
			idComp = 0;
		}
		return idComp;
	}
}
