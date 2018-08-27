/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa400;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa400.exceptions.IllegalOrphanException;
import crjpa400.exceptions.NonexistentEntityException;
import crjpa400.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class ExceptionsJpaController implements Serializable {

	/**
	 * Constructor for ExceptionsJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ExceptionsJpaController(EntityManagerFactory emf) {
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
	 * @param exceptions
	 *            Exceptions
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Exceptions exceptions)
			throws PreexistingEntityException, Exception {
		if (exceptions.getTransaccionexceptionsList() == null) {
			exceptions
					.setTransaccionexceptionsList(new ArrayList<Transaccionexceptions>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Transaccionexceptions> attachedTransaccionexceptionsList = new ArrayList<Transaccionexceptions>();
			for (Transaccionexceptions transaccionexceptionsListTransaccionexceptionsToAttach : exceptions
					.getTransaccionexceptionsList()) {
				transaccionexceptionsListTransaccionexceptionsToAttach = em
						.getReference(
								transaccionexceptionsListTransaccionexceptionsToAttach
										.getClass(),
								transaccionexceptionsListTransaccionexceptionsToAttach
										.getTransaccionexceptionsPK());
				attachedTransaccionexceptionsList
						.add(transaccionexceptionsListTransaccionexceptionsToAttach);
			}
			exceptions
					.setTransaccionexceptionsList(attachedTransaccionexceptionsList);
			em.persist(exceptions);
			for (Transaccionexceptions transaccionexceptionsListTransaccionexceptions : exceptions
					.getTransaccionexceptionsList()) {
				Exceptions oldIdExceptionOfTransaccionexceptionsListTransaccionexceptions = transaccionexceptionsListTransaccionexceptions
						.getIdException();
				transaccionexceptionsListTransaccionexceptions
						.setIdException(exceptions);
				transaccionexceptionsListTransaccionexceptions = em
						.merge(transaccionexceptionsListTransaccionexceptions);
				if (oldIdExceptionOfTransaccionexceptionsListTransaccionexceptions != null) {
					oldIdExceptionOfTransaccionexceptionsListTransaccionexceptions
							.getTransaccionexceptionsList()
							.remove(transaccionexceptionsListTransaccionexceptions);
					oldIdExceptionOfTransaccionexceptionsListTransaccionexceptions = em
							.merge(oldIdExceptionOfTransaccionexceptionsListTransaccionexceptions);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findExceptions(exceptions.getId()) != null) {
				throw new PreexistingEntityException("Exceptions " + exceptions
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
	 * @param exceptions
	 *            Exceptions
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Exceptions exceptions) throws IllegalOrphanException,
			NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Exceptions persistentExceptions = em.find(Exceptions.class,
					exceptions.getId());
			List<Transaccionexceptions> transaccionexceptionsListOld = persistentExceptions
					.getTransaccionexceptionsList();
			List<Transaccionexceptions> transaccionexceptionsListNew = exceptions
					.getTransaccionexceptionsList();
			List<String> illegalOrphanMessages = null;
			for (Transaccionexceptions transaccionexceptionsListOldTransaccionexceptions : transaccionexceptionsListOld) {
				if (!transaccionexceptionsListNew
						.contains(transaccionexceptionsListOldTransaccionexceptions)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transaccionexceptions "
									+ transaccionexceptionsListOldTransaccionexceptions
									+ " since its idException field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Transaccionexceptions> attachedTransaccionexceptionsListNew = new ArrayList<Transaccionexceptions>();
			for (Transaccionexceptions transaccionexceptionsListNewTransaccionexceptionsToAttach : transaccionexceptionsListNew) {
				transaccionexceptionsListNewTransaccionexceptionsToAttach = em
						.getReference(
								transaccionexceptionsListNewTransaccionexceptionsToAttach
										.getClass(),
								transaccionexceptionsListNewTransaccionexceptionsToAttach
										.getTransaccionexceptionsPK());
				attachedTransaccionexceptionsListNew
						.add(transaccionexceptionsListNewTransaccionexceptionsToAttach);
			}
			transaccionexceptionsListNew = attachedTransaccionexceptionsListNew;
			exceptions
					.setTransaccionexceptionsList(transaccionexceptionsListNew);
			exceptions = em.merge(exceptions);
			for (Transaccionexceptions transaccionexceptionsListNewTransaccionexceptions : transaccionexceptionsListNew) {
				if (!transaccionexceptionsListOld
						.contains(transaccionexceptionsListNewTransaccionexceptions)) {
					Exceptions oldIdExceptionOfTransaccionexceptionsListNewTransaccionexceptions = transaccionexceptionsListNewTransaccionexceptions
							.getIdException();
					transaccionexceptionsListNewTransaccionexceptions
							.setIdException(exceptions);
					transaccionexceptionsListNewTransaccionexceptions = em
							.merge(transaccionexceptionsListNewTransaccionexceptions);
					if (oldIdExceptionOfTransaccionexceptionsListNewTransaccionexceptions != null
							&& !oldIdExceptionOfTransaccionexceptionsListNewTransaccionexceptions
									.equals(exceptions)) {
						oldIdExceptionOfTransaccionexceptionsListNewTransaccionexceptions
								.getTransaccionexceptionsList()
								.remove(transaccionexceptionsListNewTransaccionexceptions);
						oldIdExceptionOfTransaccionexceptionsListNewTransaccionexceptions = em
								.merge(oldIdExceptionOfTransaccionexceptionsListNewTransaccionexceptions);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = exceptions.getId();
				if (findExceptions(id) == null) {
					throw new NonexistentEntityException(
							"The exceptions with id " + id
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
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 */
	public void destroy(Long id) throws IllegalOrphanException,
			NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Exceptions exceptions;
			try {
				exceptions = em.getReference(Exceptions.class, id);
				exceptions.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The exceptions with id "
						+ id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Transaccionexceptions> transaccionexceptionsListOrphanCheck = exceptions
					.getTransaccionexceptionsList();
			for (Transaccionexceptions transaccionexceptionsListOrphanCheckTransaccionexceptions : transaccionexceptionsListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Exceptions ("
								+ exceptions
								+ ") cannot be destroyed since the Transaccionexceptions "
								+ transaccionexceptionsListOrphanCheckTransaccionexceptions
								+ " in its transaccionexceptionsList field has a non-nullable idException field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(exceptions);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findExceptionsEntities.
	 * 
	 * @return List<Exceptions>
	 */
	public List<Exceptions> findExceptionsEntities() {
		return findExceptionsEntities(true, -1, -1);
	}

	/**
	 * Method findExceptionsEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Exceptions>
	 */
	public List<Exceptions> findExceptionsEntities(int maxResults,
			int firstResult) {
		return findExceptionsEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findExceptionsEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Exceptions>
	 */
	private List<Exceptions> findExceptionsEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Exceptions.class));
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
	 * Method findExceptions.
	 * 
	 * @param id
	 *            Long
	 * @return Exceptions
	 */
	public Exceptions findExceptions(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Exceptions.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getExceptionsCount.
	 * 
	 * @return int
	 */
	public int getExceptionsCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Exceptions> rt = cq.from(Exceptions.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
