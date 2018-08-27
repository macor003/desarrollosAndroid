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
public class RolloAuditoriaJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Rolloauditoria";

	/**
	 * Constructor for RolloAuditoriaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public RolloAuditoriaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method findLastAudit.
	 * 
	 * @param idCaja
	 *            long
	 * @return long
	 */
	public long findLastAudit(long idCaja) {
		long idAuditRoll;
		try {
			EntityManager em = emf.createEntityManager();
			String queryString = "SELECT MAX(t.id) FROM " + entityName + " t WHERE t.caja = :id";
			Query query = em.createQuery(queryString);
			query.setParameter("id", idCaja);
			query.setMaxResults(1);
			idAuditRoll = (Long) query.getSingleResult();
		} catch (NoResultException ex) {
			idAuditRoll = 0;
		} catch (Exception ex) {
			idAuditRoll = 0;
		}
		return idAuditRoll;
	}
}
