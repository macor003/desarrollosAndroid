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
public class AuditoriaJpaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5921550268338176914L;

	/**
	 * Constructor for AuditoriaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public AuditoriaJpaController(EntityManagerFactory emf) {
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
	 * @param auditoria
	 *            Auditoria
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Auditoria auditoria)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(auditoria);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method edit.
	 * 
	 * @param auditoria
	 *            Auditoria
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Auditoria auditoria)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			auditoria = em.merge(auditoria);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = auditoria.getId();
				if (findAuditoria(id) == null) {
					throw new NonexistentEntityException(
							"The auditoria with id " + id
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
			Auditoria auditoria;
			try {
				auditoria = em.getReference(Auditoria.class, id);
				auditoria.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The auditoria with id " + id + " no longer exists.",
						enfe);
			}
			em.remove(auditoria);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findAuditoriaEntities.
	 * 
	 * @return List<Auditoria>
	 */
	public List<Auditoria> findAuditoriaEntities() {
		return findAuditoriaEntities(true, -1, -1);
	}

	/**
	 * Method findAuditoriaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Auditoria>
	 */
	public List<Auditoria> findAuditoriaEntities(int maxResults,
			int firstResult) {
		return findAuditoriaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findAuditoriaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Auditoria>
	 */
	private List<Auditoria> findAuditoriaEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Auditoria.class));
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
	 * Method findAuditoria.
	 * 
	 * @param id
	 *            Long
	 * @return Auditoria
	 */
	public Auditoria findAuditoria(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Auditoria.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getAuditoriaCount.
	 * 
	 * @return int
	 */
	public int getAuditoriaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Auditoria> rt = cq.from(Auditoria.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
