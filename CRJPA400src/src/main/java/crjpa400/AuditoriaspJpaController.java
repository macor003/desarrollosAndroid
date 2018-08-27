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
public class AuditoriaspJpaController implements Serializable {

	/**
	 * Constructor for AuditoriaspJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AuditoriaspJpaController(EntityManagerFactory emf) {
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
	 * @param auditoriasp
	 *            Auditoriasp
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Auditoriasp auditoriasp)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(auditoriasp);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findAuditoriasp(auditoriasp.getId()) != null) {
				throw new PreexistingEntityException("Auditoriasp "
						+ auditoriasp + " already exists.", ex);
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
	 * @param auditoriasp
	 *            Auditoriasp
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Auditoriasp auditoriasp)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			auditoriasp = em.merge(auditoriasp);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = auditoriasp.getId();
				if (findAuditoriasp(id) == null) {
					throw new NonexistentEntityException(
							"The auditoriasp with id " + id
									+ " no longer exists.");
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
			Auditoriasp auditoriasp;
			try {
				auditoriasp = em.getReference(Auditoriasp.class, id);
				auditoriasp.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The auditoriasp with id "
						+ id + " no longer exists.", enfe);
			}
			em.remove(auditoriasp);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findAuditoriaspEntities.
	 * 
	 * @return List<Auditoriasp>
	 */
	public List<Auditoriasp> findAuditoriaspEntities() {
		return findAuditoriaspEntities(true, -1, -1);
	}

	/**
	 * Method findAuditoriaspEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Auditoriasp>
	 */
	public List<Auditoriasp> findAuditoriaspEntities(int maxResults,
			int firstResult) {
		return findAuditoriaspEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findAuditoriaspEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Auditoriasp>
	 */
	private List<Auditoriasp> findAuditoriaspEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Auditoriasp.class));
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
	 * Method findAuditoriasp.
	 * 
	 * @param id
	 *            Long
	 * @return Auditoriasp
	 */
	public Auditoriasp findAuditoriasp(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Auditoriasp.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getAuditoriaspCount.
	 * 
	 * @return int
	 */
	public int getAuditoriaspCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Auditoriasp> rt = cq.from(Auditoriasp.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
