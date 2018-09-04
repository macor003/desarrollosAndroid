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
public class TransaccionexceptionsJpaController implements Serializable {

	/**
	 * Constructor for TransaccionexceptionsJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TransaccionexceptionsJpaController(EntityManagerFactory emf) {
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
	 * @param transaccionexceptions
	 *            Transaccionexceptions
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Transaccionexceptions transaccionexceptions)
			throws PreexistingEntityException, Exception {
		if (transaccionexceptions.getTransaccionexceptionsPK() == null) {
			transaccionexceptions
					.setTransaccionexceptionsPK(new TransaccionexceptionsPK());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Exceptions idException = transaccionexceptions.getIdException();
			if (idException != null) {
				idException = em.getReference(idException.getClass(),
						idException.getId());
				transaccionexceptions.setIdException(idException);
			}
			em.persist(transaccionexceptions);
			if (idException != null) {
				idException.getTransaccionexceptionsList().add(
						transaccionexceptions);
				idException = em.merge(idException);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTransaccionexceptions(transaccionexceptions
					.getTransaccionexceptionsPK()) != null) {
				throw new PreexistingEntityException("Transaccionexceptions "
						+ transaccionexceptions + " already exists.", ex);
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
	 * @param transaccionexceptions
	 *            Transaccionexceptions
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Transaccionexceptions transaccionexceptions)
			throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionexceptions persistentTransaccionexceptions = em.find(
					Transaccionexceptions.class,
					transaccionexceptions.getTransaccionexceptionsPK());
			Exceptions idExceptionOld = persistentTransaccionexceptions
					.getIdException();
			Exceptions idExceptionNew = transaccionexceptions.getIdException();
			if (idExceptionNew != null) {
				idExceptionNew = em.getReference(idExceptionNew.getClass(),
						idExceptionNew.getId());
				transaccionexceptions.setIdException(idExceptionNew);
			}
			transaccionexceptions = em.merge(transaccionexceptions);
			if (idExceptionOld != null
					&& !idExceptionOld.equals(idExceptionNew)) {
				idExceptionOld.getTransaccionexceptionsList().remove(
						transaccionexceptions);
				idExceptionOld = em.merge(idExceptionOld);
			}
			if (idExceptionNew != null
					&& !idExceptionNew.equals(idExceptionOld)) {
				idExceptionNew.getTransaccionexceptionsList().add(
						transaccionexceptions);
				idExceptionNew = em.merge(idExceptionNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				TransaccionexceptionsPK id = transaccionexceptions
						.getTransaccionexceptionsPK();
				if (findTransaccionexceptions(id) == null) {
					throw new NonexistentEntityException(
							"The transaccionexceptions with id " + id
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
	 *            TransaccionexceptionsPK
	 * @throws NonexistentEntityException
	 */
	public void destroy(TransaccionexceptionsPK id)
			throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Transaccionexceptions transaccionexceptions;
			try {
				transaccionexceptions = em.getReference(
						Transaccionexceptions.class, id);
				transaccionexceptions.getTransaccionexceptionsPK();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The transaccionexceptions with id " + id
								+ " no longer exists.", enfe);
			}
			Exceptions idException = transaccionexceptions.getIdException();
			if (idException != null) {
				idException.getTransaccionexceptionsList().remove(
						transaccionexceptions);
				idException = em.merge(idException);
			}
			em.remove(transaccionexceptions);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTransaccionexceptionsEntities.
	 * 
	 * @return List<Transaccionexceptions>
	 */
	public List<Transaccionexceptions> findTransaccionexceptionsEntities() {
		return findTransaccionexceptionsEntities(true, -1, -1);
	}

	/**
	 * Method findTransaccionexceptionsEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionexceptions>
	 */
	public List<Transaccionexceptions> findTransaccionexceptionsEntities(
			int maxResults, int firstResult) {
		return findTransaccionexceptionsEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTransaccionexceptionsEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Transaccionexceptions>
	 */
	private List<Transaccionexceptions> findTransaccionexceptionsEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Transaccionexceptions.class));
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
	 * Method findTransaccionexceptions.
	 * 
	 * @param id
	 *            TransaccionexceptionsPK
	 * @return Transaccionexceptions
	 */
	public Transaccionexceptions findTransaccionexceptions(
			TransaccionexceptionsPK id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Transaccionexceptions.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTransaccionexceptionsCount.
	 * 
	 * @return int
	 */
	public int getTransaccionexceptionsCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Transaccionexceptions> rt = cq
					.from(Transaccionexceptions.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
