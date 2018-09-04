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
public class OpcionJpaController implements Serializable {

	/**
	 * Constructor for OpcionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public OpcionJpaController(EntityManagerFactory emf) {
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
	 * @param opcion
	 *            Opcion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Opcion opcion)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(opcion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findOpcion(opcion.getId()) != null) {
				throw new PreexistingEntityException(
						"Opcion " + opcion + " already exists.", ex);
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
	 * @param opcion
	 *            Opcion
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Opcion opcion)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			opcion = em.merge(opcion);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = opcion.getId();
				if (findOpcion(id) == null) {
					throw new NonexistentEntityException(
							"The opcion with id " + id + " no longer exists.");
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
			Opcion opcion;
			try {
				opcion = em.getReference(Opcion.class, id);
				opcion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The opcion with id " + id + " no longer exists.",
						enfe);
			}
			em.remove(opcion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findOpcionEntities.
	 * 
	 * @return List<Opcion>
	 */
	public List<Opcion> findOpcionEntities() {
		return findOpcionEntities(true, -1, -1);
	}

	/**
	 * Method findOpcionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Opcion>
	 */
	public List<Opcion> findOpcionEntities(int maxResults, int firstResult) {
		return findOpcionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findOpcionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Opcion>
	 */
	private List<Opcion> findOpcionEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Opcion.class));
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
	 * Method findOpcion.
	 * 
	 * @param id
	 *            Long
	 * @return Opcion
	 */
	public Opcion findOpcion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Opcion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getOpcionCount.
	 * 
	 * @return int
	 */
	public int getOpcionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Opcion> rt = cq.from(Opcion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

	public Integer countOptions() {

		Integer number = null;

		EntityManager em = null;

		try {

			em = emf.createEntityManager();

			Query query = em.createNamedQuery("Opcion.findMinimalOptions");

			number = new Integer(query.getSingleResult().toString());

		} catch (Exception e) {
			number = -1;
			return number;
		} finally {
			if (number != -1) {
				em.close();
			}
		}

		return number;
	}
}
