/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class SincronizaciondetalleJpaController implements Serializable {

	/**
	 * Constructor for SincronizaciondetalleJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public SincronizaciondetalleJpaController(EntityManagerFactory emf) {
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
	 * @param sincronizaciondetalle
	 *            Sincronizaciondetalle
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Sincronizaciondetalle sincronizaciondetalle) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(sincronizaciondetalle);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findSincronizaciondetalle(sincronizaciondetalle.getId()) != null) {
				throw new PreexistingEntityException("Sincronizaciondetalle " + sincronizaciondetalle
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
	 * @param sincronizaciondetalle
	 *            Sincronizaciondetalle
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Sincronizaciondetalle sincronizaciondetalle) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			sincronizaciondetalle = em.merge(sincronizaciondetalle);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = sincronizaciondetalle.getId();
				if (findSincronizaciondetalle(id) == null) {
					throw new NonexistentEntityException("The sincronizaciondetalle with id " + id
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
			Sincronizaciondetalle sincronizaciondetalle;
			try {
				sincronizaciondetalle = em.getReference(Sincronizaciondetalle.class, id);
				sincronizaciondetalle.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The sincronizaciondetalle with id " + id + " no longer exists.",
						enfe);
			}
			em.remove(sincronizaciondetalle);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findSincronizaciondetalleEntities.
	 * 
	 * @return List<Sincronizaciondetalle>
	 */
	public List<Sincronizaciondetalle> findSincronizaciondetalleEntities() {
		return findSincronizaciondetalleEntities(true, -1, -1);
	}

	/**
	 * Method findSincronizaciondetalleEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Sincronizaciondetalle>
	 */
	public List<Sincronizaciondetalle> findSincronizaciondetalleEntities(int maxResults, int firstResult) {
		return findSincronizaciondetalleEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findSincronizaciondetalleEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Sincronizaciondetalle>
	 */
	private List<Sincronizaciondetalle> findSincronizaciondetalleEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Sincronizaciondetalle.class));
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
	 * Method findSincronizaciondetalle.
	 * 
	 * @param id
	 *            Long
	 * @return Sincronizaciondetalle
	 */
	public Sincronizaciondetalle findSincronizaciondetalle(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Sincronizaciondetalle.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getSincronizaciondetalleCount.
	 * 
	 * @return int
	 */
	public int getSincronizaciondetalleCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Sincronizaciondetalle> rt = cq.from(Sincronizaciondetalle.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
