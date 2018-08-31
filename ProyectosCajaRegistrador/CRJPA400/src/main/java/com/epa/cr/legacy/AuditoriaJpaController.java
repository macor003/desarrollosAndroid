/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.epa.cr.legacy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.becoblohm.cr.crjpa400.controller.exceptions.JpaException;
import com.becoblohm.cr.interfaces.AbstractJPAController;

import crjpa400.Auditoria;

/**
 */
public class AuditoriaJpaController extends AbstractJPAController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5083879167519351126L;
	// private final crjpa400.AuditoriaJpaController jpaController;
	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Auditoria";

	/**
	 * Constructor for AuditoriaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AuditoriaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		// this.jpaController = new crjpa400.AuditoriaJpaController(emf);
		this.emf = emf;
	}

	/**
	 * Method findTransactionAudit.
	 * 
	 * @param transactionId
	 *            long
	 * @return List<Auditoria>
	 * @throws JpaException
	 */
	public List<Auditoria> findTransactionAudit(long transactionId) throws JpaException {
		EntityManager em = this.emf.createEntityManager();
		List<Auditoria> auditList = new ArrayList<Auditoria>();
		try {
			Query query = em.createQuery("SELECT a FROM Auditoria a WHERE a.arg1 = :idTr", Auditoria.class);
			query.setParameter("idTr", String.valueOf(transactionId));
			auditList = query.getResultList();
		} catch (Exception ex) {
			throw new JpaException(ex.getMessage(), ex);
		} finally {
			em.close();
		}

		return auditList;
	}

}
