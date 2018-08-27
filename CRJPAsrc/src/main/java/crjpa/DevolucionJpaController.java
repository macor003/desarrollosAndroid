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
public class DevolucionJpaController implements Serializable {

	/**
	 * Constructor for DevolucionJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public DevolucionJpaController(EntityManagerFactory emf) {
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
	 * @param devolucion
	 *            Devolucion
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Devolucion devolucion) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccion idTransaccion = devolucion.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion = em.getReference(idTransaccion.getClass(), idTransaccion.getId());
				devolucion.setIdTransaccion(idTransaccion);
			}
			em.persist(devolucion);
			if (idTransaccion != null) {
				// idTransaccion.getDevolucionList().add(devolucion);
				idTransaccion = em.merge(idTransaccion);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findDevolucion(devolucion.getId()) != null) {
				throw new PreexistingEntityException("Devolucion " + devolucion + " already exists.", ex);
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
	 * @param devolucion
	 *            Devolucion
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Devolucion devolucion) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Devolucion persistentDevolucion = em.find(Devolucion.class, devolucion.getId());
			Transaccion idTransaccionOld = persistentDevolucion.getIdTransaccion();
			Transaccion idTransaccionNew = devolucion.getIdTransaccion();
			if (idTransaccionNew != null) {
				idTransaccionNew = em.getReference(idTransaccionNew.getClass(), idTransaccionNew.getId());
				devolucion.setIdTransaccion(idTransaccionNew);
			}
			devolucion = em.merge(devolucion);
			if (idTransaccionOld != null && !idTransaccionOld.equals(idTransaccionNew)) {
				// idTransaccionOld.getDevolucionList().remove(devolucion);
				idTransaccionOld = em.merge(idTransaccionOld);
			}
			if (idTransaccionNew != null && !idTransaccionNew.equals(idTransaccionOld)) {
				// idTransaccionNew.getDevolucionList().add(devolucion);
				idTransaccionNew = em.merge(idTransaccionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = devolucion.getId();
				if (findDevolucion(id) == null) {
					throw new NonexistentEntityException("The devolucion with id " + id + " no longer exists.");
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
			Devolucion devolucion;
			try {
				devolucion = em.getReference(Devolucion.class, id);
				devolucion.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The devolucion with id " + id + " no longer exists.", enfe);
			}
			Transaccion idTransaccion = devolucion.getIdTransaccion();
			if (idTransaccion != null) {
				// idTransaccion.getDevolucionList().remove(devolucion);
				idTransaccion = em.merge(idTransaccion);
			}
			em.remove(devolucion);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findDevolucionEntities.
	 * 
	 * @return List<Devolucion>
	 */
	public List<Devolucion> findDevolucionEntities() {
		return findDevolucionEntities(true, -1, -1);
	}

	/**
	 * Method findDevolucionEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Devolucion>
	 */
	public List<Devolucion> findDevolucionEntities(int maxResults, int firstResult) {
		return findDevolucionEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findDevolucionEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Devolucion>
	 */
	private List<Devolucion> findDevolucionEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Devolucion.class));
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
	 * Method findDevolucion.
	 * 
	 * @param id
	 *            Long
	 * @return Devolucion
	 */
	public Devolucion findDevolucion(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Devolucion.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getDevolucionCount.
	 * 
	 * @return int
	 */
	public int getDevolucionCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Devolucion> rt = cq.from(Devolucion.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
