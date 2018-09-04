/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ReportezJpaController implements Serializable {

	/**
	 * Constructor for ReportezJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ReportezJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * Field emf.
	 */
	private EntityManagerFactory emf = null;

	/**
	 * Method getEntityManager.
	 * 
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	/**
	 * Method create.
	 * 
	 * @param reportez
	 *            Reportez
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Reportez reportez) throws PreexistingEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Sesion idSesion = reportez.getIdSesion();
			if (idSesion != null) {
				idSesion = em.getReference(idSesion.getClass(),
						idSesion.getId());
				reportez.setIdSesion(idSesion);
			}
			em.persist(reportez);
			if (idSesion != null) {
				idSesion.getReportezList().add(reportez);
				idSesion = em.merge(idSesion);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findReportez(reportez.getId()) != null) {
				throw new PreexistingEntityException("Reportez " + reportez
						+ " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method edit.
	 * 
	 * @param reportez
	 *            Reportez
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Reportez reportez) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Reportez persistentReportez = em.find(Reportez.class,
					reportez.getId());
			Sesion idSesionOld = persistentReportez.getIdSesion();
			Sesion idSesionNew = reportez.getIdSesion();
			if (idSesionNew != null) {
				idSesionNew = em.getReference(idSesionNew.getClass(),
						idSesionNew.getId());
				reportez.setIdSesion(idSesionNew);
			}
			reportez = em.merge(reportez);
			if (idSesionOld != null && !idSesionOld.equals(idSesionNew)) {
				idSesionOld.getReportezList().remove(reportez);
				idSesionOld = em.merge(idSesionOld);
			}
			if (idSesionNew != null && !idSesionNew.equals(idSesionOld)) {
				idSesionNew.getReportezList().add(reportez);
				idSesionNew = em.merge(idSesionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = reportez.getId();
				if (findReportez(id) == null) {
					throw new NonexistentEntityException(
							"The reportez with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method destroy.
	 * 
	 * @param id
	 *            Long
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Reportez reportez;
			try {
				reportez = em.getReference(Reportez.class, id);
				reportez.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The reportez with id "
						+ id + " no longer exists.", enfe);
			}
			Sesion idSesion = reportez.getIdSesion();
			if (idSesion != null) {
				idSesion.getReportezList().remove(reportez);
				idSesion = em.merge(idSesion);
			}
			em.remove(reportez);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findReportezEntities.
	 * 
	 * @return List<Reportez>
	 */
	public List<Reportez> findReportezEntities() {
		return findReportezEntities(true, -1, -1);
	}

	/**
	 * Method findReportezEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Reportez>
	 */
	public List<Reportez> findReportezEntities(int maxResults, int firstResult) {
		return findReportezEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findReportezEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Reportez>
	 */
	private List<Reportez> findReportezEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Reportez.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	/**
	 * Method findReportez.
	 * 
	 * @param id
	 *            Long
	 * @return Reportez
	 */
	public Reportez findReportez(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Reportez.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getReportezCount.
	 * 
	 * @return int
	 */
	public int getReportezCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Reportez> rt = cq.from(Reportez.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
