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

import crjpa400.Reportez;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ReportezJpaController extends AbstractJPAController {

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;
	/**
	 * Field entityName.
	 */
	private static String entityName = "Reportez";

	/**
	 * Constructor for ReporteZJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ReportezJpaController(EntityManagerFactory emf) {
		super(AbstractJPAController.SERVERSOURCE, entityName);
		this.emf = emf;
	}

	/**
	 * Method findLastZReport.
	 * 
	 * @param caja
	 *            long
	 * @return long
	 */
	public long findLastZReport(long caja) {
		long idZReport;
		try {
			EntityManager em = emf.createEntityManager();
			String queryString = "SELECT MAX(t.id) FROM " + entityName + " t WHERE t.idSesion.idCaja.id = :id";
			Query query = em.createQuery(queryString);
			query.setParameter("id", caja);
			query.setMaxResults(1);
			idZReport = (Long) query.getSingleResult();
		} catch (NoResultException ex) {
			idZReport = 0;
		} catch (Exception ex) {
			idZReport = 0;
		}
		return idZReport;
	}

	public int findLastClosureId(String serialNumber) {
		int number = -1;
		Reportez lastReport;
		try {
			Query query = emf
					.createEntityManager()
					.createQuery(
							"select a from Reportez a where a.numeroReporte = (select max(b.numeroReporte) from Reportez b where b.serialImpresora = '"
									+ serialNumber + "')");
			lastReport = (Reportez) query.getSingleResult();
			number = lastReport.getNumeroReporte();
		} catch (javax.persistence.NoResultException e) {
			lastReport = null;
		}

		return number;
	}
}
