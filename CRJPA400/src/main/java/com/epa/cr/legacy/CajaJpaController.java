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
import javax.persistence.Query;

import com.becoblohm.cr.interfaces.AbstractJPAController;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class CajaJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Caja";

	/**
	 * Constructor for CajaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CajaJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method findPos.
	 * 
	 * @param posNumber
	 *            long
	 */
	public void findPos(long posNumber) {
		EntityManager em = emf.createEntityManager();
		crjpa400.Caja singleResult = null;
		try {
			Query query = em.createNamedQuery("Caja.findById");
			query.setParameter("id", posNumber);
			query.setMaxResults(1);
			singleResult = (crjpa400.Caja) query.getSingleResult();
		} catch (Exception ex) {
			singleResult = null;
		} finally {
			em.close();
		}
		// return singleResult;
	}

	/**
	 * Method getPosType.
	 * 
	 * @param posNumber
	 *            long
	 * @return Integer
	 */
	public Integer getPosType(long posNumber) {
		Integer posType = 0;
		EntityManager em = emf.createEntityManager();
		crjpa400.Caja singleResult = null;
		try {
			Query query = em.createNamedQuery("Caja.findById");
			query.setParameter("id", posNumber);
			query.setMaxResults(1);
			singleResult = (crjpa400.Caja) query.getSingleResult();
		} catch (Exception ex) {
			singleResult = null;
		} finally {
			em.close();
		}
		if (singleResult != null) {
			posType = singleResult.getTipoCaja();
		} else {
			posType = null;
		}
		return posType;
	}
}
