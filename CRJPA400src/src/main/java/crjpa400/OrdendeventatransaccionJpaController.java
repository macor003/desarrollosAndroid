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
public class OrdendeventatransaccionJpaController implements Serializable {

	/**
	 * Constructor for OrdendeventatransaccionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public OrdendeventatransaccionJpaController(EntityManagerFactory emf) {
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
	 * @param ordendeventatransaccion
	 *            Ordendeventatransaccion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Ordendeventatransaccion ordendeventatransaccion)
			throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Ordendeventa idOrdendeventa = ordendeventatransaccion
					.getIdOrdendeventa();
			if (idOrdendeventa != null) {
				idOrdendeventa = em.getReference(idOrdendeventa.getClass(),
						idOrdendeventa.getId());
				ordendeventatransaccion.setIdOrdendeventa(idOrdendeventa);
			}
			em.persist(ordendeventatransaccion);
			if (idOrdendeventa != null) {
				idOrdendeventa.getOrdendeventatransaccionList().add(
						ordendeventatransaccion);
				idOrdendeventa = em.merge(idOrdendeventa);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findOrdendeventatransaccion(ordendeventatransaccion.getId()) != null) {
				throw new PreexistingEntityException("Ordendeventatransaccion "
						+ ordendeventatransaccion + " already exists.", ex);
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
	 * @param ordendeventatransaccion
	 *            Ordendeventatransaccion
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Ordendeventatransaccion ordendeventatransaccion)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Ordendeventatransaccion persistentOrdendeventatransaccion = em
					.find(Ordendeventatransaccion.class,
							ordendeventatransaccion.getId());
			Ordendeventa idOrdendeventaOld = persistentOrdendeventatransaccion
					.getIdOrdendeventa();
			Ordendeventa idOrdendeventaNew = ordendeventatransaccion
					.getIdOrdendeventa();
			if (idOrdendeventaNew != null) {
				idOrdendeventaNew = em
						.getReference(idOrdendeventaNew.getClass(),
								idOrdendeventaNew.getId());
				ordendeventatransaccion.setIdOrdendeventa(idOrdendeventaNew);
			}
			ordendeventatransaccion = em.merge(ordendeventatransaccion);
			if (idOrdendeventaOld != null
					&& !idOrdendeventaOld.equals(idOrdendeventaNew)) {
				idOrdendeventaOld.getOrdendeventatransaccionList().remove(
						ordendeventatransaccion);
				idOrdendeventaOld = em.merge(idOrdendeventaOld);
			}
			if (idOrdendeventaNew != null
					&& !idOrdendeventaNew.equals(idOrdendeventaOld)) {
				idOrdendeventaNew.getOrdendeventatransaccionList().add(
						ordendeventatransaccion);
				idOrdendeventaNew = em.merge(idOrdendeventaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = ordendeventatransaccion.getId();
				if (findOrdendeventatransaccion(id) == null) {
					throw new NonexistentEntityException(
							"The ordendeventatransaccion with id " + id
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
			Ordendeventatransaccion ordendeventatransaccion;
			try {
				ordendeventatransaccion = em.getReference(
						Ordendeventatransaccion.class, id);
				ordendeventatransaccion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The ordendeventatransaccion with id " + id
								+ " no longer exists.", enfe);
			}
			Ordendeventa idOrdendeventa = ordendeventatransaccion
					.getIdOrdendeventa();
			if (idOrdendeventa != null) {
				idOrdendeventa.getOrdendeventatransaccionList().remove(
						ordendeventatransaccion);
				idOrdendeventa = em.merge(idOrdendeventa);
			}
			em.remove(ordendeventatransaccion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findOrdendeventatransaccionEntities.
	 * 
	 * @return List<Ordendeventatransaccion>
	 */
	public List<Ordendeventatransaccion> findOrdendeventatransaccionEntities() {
		return findOrdendeventatransaccionEntities(true, -1, -1);
	}

	/**
	 * Method findOrdendeventatransaccionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Ordendeventatransaccion>
	 */
	public List<Ordendeventatransaccion> findOrdendeventatransaccionEntities(
			int maxResults, int firstResult) {
		return findOrdendeventatransaccionEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findOrdendeventatransaccionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Ordendeventatransaccion>
	 */
	private List<Ordendeventatransaccion> findOrdendeventatransaccionEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Ordendeventatransaccion.class));
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
	 * Method findOrdendeventatransaccion.
	 * 
	 * @param id
	 *            Long
	 * @return Ordendeventatransaccion
	 */
	public Ordendeventatransaccion findOrdendeventatransaccion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Ordendeventatransaccion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getOrdendeventatransaccionCount.
	 * 
	 * @return int
	 */
	public int getOrdendeventatransaccionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Ordendeventatransaccion> rt = cq
					.from(Ordendeventatransaccion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
