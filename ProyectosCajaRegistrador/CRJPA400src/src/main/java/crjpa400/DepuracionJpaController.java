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
public class DepuracionJpaController implements Serializable {

	/**
	 * Constructor for DepuracionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public DepuracionJpaController(EntityManagerFactory emf) {
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
	 * @param depuracion
	 *            Depuracion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Depuracion depuracion)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(depuracion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findDepuracion(depuracion.getId()) != null) {
				throw new PreexistingEntityException("Depuracion " + depuracion
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
	 * @param depuracion
	 *            Depuracion
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Depuracion depuracion) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			depuracion = em.merge(depuracion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = depuracion.getId();
				if (findDepuracion(id) == null) {
					throw new NonexistentEntityException(
							"The depuracion with id " + id
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
			Depuracion depuracion;
			try {
				depuracion = em.getReference(Depuracion.class, id);
				depuracion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The depuracion with id "
						+ id + " no longer exists.", enfe);
			}
			em.remove(depuracion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findDepuracionEntities.
	 * 
	 * @return List<Depuracion>
	 */
	public List<Depuracion> findDepuracionEntities() {
		return findDepuracionEntities(true, -1, -1);
	}

	/**
	 * Method findDepuracionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Depuracion>
	 */
	public List<Depuracion> findDepuracionEntities(int maxResults,
			int firstResult) {
		return findDepuracionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findDepuracionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Depuracion>
	 */
	private List<Depuracion> findDepuracionEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Depuracion.class));
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
	 * Method findDepuracion.
	 * 
	 * @param id
	 *            Long
	 * @return Depuracion
	 */
	public Depuracion findDepuracion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Depuracion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getDepuracionCount.
	 * 
	 * @return int
	 */
	public int getDepuracionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Depuracion> rt = cq.from(Depuracion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
