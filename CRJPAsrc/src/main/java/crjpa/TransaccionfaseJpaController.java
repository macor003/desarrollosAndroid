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
public class TransaccionfaseJpaController implements Serializable {

	/**
	 * Constructor for TransaccionfaseJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionfaseJpaController(EntityManagerFactory emf) {
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
	 * @param transaccionfase
	 *            Transaccionfase
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Transaccionfase transaccionfase) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccion idTransaccion = transaccionfase.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion = em.getReference(idTransaccion.getClass(), idTransaccion.getId());
				transaccionfase.setIdTransaccion(idTransaccion);
			}
			em.persist(transaccionfase);
			if (idTransaccion != null) {
				idTransaccion.getTransaccionfaseList().add(transaccionfase);
				idTransaccion = em.merge(idTransaccion);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTransaccionfase(transaccionfase.getId()) != null) {
				throw new PreexistingEntityException("Transaccionfase " + transaccionfase + " already exists.", ex);
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
	 * @param transaccionfase
	 *            Transaccionfase
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Transaccionfase transaccionfase) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionfase persistentTransaccionfase = em.find(Transaccionfase.class, transaccionfase.getId());
			Transaccion idTransaccionOld = persistentTransaccionfase.getIdTransaccion();
			Transaccion idTransaccionNew = transaccionfase.getIdTransaccion();
			if (idTransaccionNew != null) {
				idTransaccionNew = em.getReference(idTransaccionNew.getClass(), idTransaccionNew.getId());
				transaccionfase.setIdTransaccion(idTransaccionNew);
			}
			transaccionfase = em.merge(transaccionfase);
			if (idTransaccionOld != null && !idTransaccionOld.equals(idTransaccionNew)) {
				idTransaccionOld.getTransaccionfaseList().remove(transaccionfase);
				idTransaccionOld = em.merge(idTransaccionOld);
			}
			if (idTransaccionNew != null && !idTransaccionNew.equals(idTransaccionOld)) {
				idTransaccionNew.getTransaccionfaseList().add(transaccionfase);
				idTransaccionNew = em.merge(idTransaccionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = transaccionfase.getId();
				if (findTransaccionfase(id) == null) {
					throw new NonexistentEntityException("The transaccionfase with id " + id + " no longer exists.");
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
			Transaccionfase transaccionfase;
			try {
				transaccionfase = em.getReference(Transaccionfase.class, id);
				transaccionfase.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The transaccionfase with id " + id + " no longer exists.", enfe);
			}
			Transaccion idTransaccion = transaccionfase.getIdTransaccion();
			if (idTransaccion != null) {
				idTransaccion.getTransaccionfaseList().remove(transaccionfase);
				idTransaccion = em.merge(idTransaccion);
			}
			em.remove(transaccionfase);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTransaccionfaseEntities.
	 * 
	 * @return List<Transaccionfase>
	 */
	public List<Transaccionfase> findTransaccionfaseEntities() {
		return findTransaccionfaseEntities(true, -1, -1);
	}

	/**
	 * Method findTransaccionfaseEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionfase>
	 */
	public List<Transaccionfase> findTransaccionfaseEntities(int maxResults, int firstResult) {
		return findTransaccionfaseEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTransaccionfaseEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionfase>
	 */
	private List<Transaccionfase> findTransaccionfaseEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Transaccionfase.class));
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
	 * Method findTransaccionfase.
	 * 
	 * @param id
	 *            Long
	 * @return Transaccionfase
	 */
	public Transaccionfase findTransaccionfase(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Transaccionfase.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTransaccionfaseCount.
	 * 
	 * @return int
	 */
	public int getTransaccionfaseCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Transaccionfase> rt = cq.from(Transaccionfase.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
