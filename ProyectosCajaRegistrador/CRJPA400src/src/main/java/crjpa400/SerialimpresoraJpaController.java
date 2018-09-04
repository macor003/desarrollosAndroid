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
public class SerialimpresoraJpaController implements Serializable {

	/**
	 * Constructor for SerialimpresoraJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public SerialimpresoraJpaController(EntityManagerFactory emf) {
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
	 * @param serialimpresora
	 *            Serialimpresora
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Serialimpresora serialimpresora)
			throws PreexistingEntityException, Exception {
		if (serialimpresora.getRolloauditoriaList() == null) {
			serialimpresora
					.setRolloauditoriaList(new ArrayList<Rolloauditoria>());
		}
		if (serialimpresora.getTransaccionList() == null) {
			serialimpresora.setTransaccionList(new ArrayList<Transaccion>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Rolloauditoria> attachedRolloauditoriaList = new ArrayList<Rolloauditoria>();
			for (Rolloauditoria rolloauditoriaListRolloauditoriaToAttach : serialimpresora
					.getRolloauditoriaList()) {
				rolloauditoriaListRolloauditoriaToAttach = em.getReference(
						rolloauditoriaListRolloauditoriaToAttach.getClass(),
						rolloauditoriaListRolloauditoriaToAttach.getId());
				attachedRolloauditoriaList
						.add(rolloauditoriaListRolloauditoriaToAttach);
			}
			serialimpresora.setRolloauditoriaList(attachedRolloauditoriaList);
			List<Transaccion> attachedTransaccionList = new ArrayList<Transaccion>();
			for (Transaccion transaccionListTransaccionToAttach : serialimpresora
					.getTransaccionList()) {
				transaccionListTransaccionToAttach = em.getReference(
						transaccionListTransaccionToAttach.getClass(),
						transaccionListTransaccionToAttach.getId());
				attachedTransaccionList.add(transaccionListTransaccionToAttach);
			}
			serialimpresora.setTransaccionList(attachedTransaccionList);
			em.persist(serialimpresora);
			for (Rolloauditoria rolloauditoriaListRolloauditoria : serialimpresora
					.getRolloauditoriaList()) {
				Serialimpresora oldIdSerialimpresoraOfRolloauditoriaListRolloauditoria = rolloauditoriaListRolloauditoria
						.getIdSerialimpresora();
				rolloauditoriaListRolloauditoria
						.setIdSerialimpresora(serialimpresora);
				rolloauditoriaListRolloauditoria = em
						.merge(rolloauditoriaListRolloauditoria);
				if (oldIdSerialimpresoraOfRolloauditoriaListRolloauditoria != null) {
					oldIdSerialimpresoraOfRolloauditoriaListRolloauditoria
							.getRolloauditoriaList().remove(
									rolloauditoriaListRolloauditoria);
					oldIdSerialimpresoraOfRolloauditoriaListRolloauditoria = em
							.merge(oldIdSerialimpresoraOfRolloauditoriaListRolloauditoria);
				}
			}
			for (Transaccion transaccionListTransaccion : serialimpresora
					.getTransaccionList()) {
				Serialimpresora oldIdSerialimpresoraOfTransaccionListTransaccion = transaccionListTransaccion
						.getIdSerialimpresora();
				transaccionListTransaccion
						.setIdSerialimpresora(serialimpresora);
				transaccionListTransaccion = em
						.merge(transaccionListTransaccion);
				if (oldIdSerialimpresoraOfTransaccionListTransaccion != null) {
					oldIdSerialimpresoraOfTransaccionListTransaccion
							.getTransaccionList().remove(
									transaccionListTransaccion);
					oldIdSerialimpresoraOfTransaccionListTransaccion = em
							.merge(oldIdSerialimpresoraOfTransaccionListTransaccion);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findSerialimpresora(serialimpresora.getId()) != null) {
				throw new PreexistingEntityException("Serialimpresora "
						+ serialimpresora + " already exists.", ex);
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
	 * @param serialimpresora
	 *            Serialimpresora
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Serialimpresora serialimpresora)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Serialimpresora persistentSerialimpresora = em.find(
					Serialimpresora.class, serialimpresora.getId());
			List<Rolloauditoria> rolloauditoriaListOld = persistentSerialimpresora
					.getRolloauditoriaList();
			List<Rolloauditoria> rolloauditoriaListNew = serialimpresora
					.getRolloauditoriaList();
			List<Transaccion> transaccionListOld = persistentSerialimpresora
					.getTransaccionList();
			List<Transaccion> transaccionListNew = serialimpresora
					.getTransaccionList();
			List<String> illegalOrphanMessages = null;
			for (Rolloauditoria rolloauditoriaListOldRolloauditoria : rolloauditoriaListOld) {
				if (!rolloauditoriaListNew
						.contains(rolloauditoriaListOldRolloauditoria)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Rolloauditoria "
									+ rolloauditoriaListOldRolloauditoria
									+ " since its idSerialimpresora field is not nullable.");
				}
			}
			for (Transaccion transaccionListOldTransaccion : transaccionListOld) {
				if (!transaccionListNew.contains(transaccionListOldTransaccion)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transaccion "
									+ transaccionListOldTransaccion
									+ " since its idSerialimpresora field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Rolloauditoria> attachedRolloauditoriaListNew = new ArrayList<Rolloauditoria>();
			for (Rolloauditoria rolloauditoriaListNewRolloauditoriaToAttach : rolloauditoriaListNew) {
				rolloauditoriaListNewRolloauditoriaToAttach = em.getReference(
						rolloauditoriaListNewRolloauditoriaToAttach.getClass(),
						rolloauditoriaListNewRolloauditoriaToAttach.getId());
				attachedRolloauditoriaListNew
						.add(rolloauditoriaListNewRolloauditoriaToAttach);
			}
			rolloauditoriaListNew = attachedRolloauditoriaListNew;
			serialimpresora.setRolloauditoriaList(rolloauditoriaListNew);
			List<Transaccion> attachedTransaccionListNew = new ArrayList<Transaccion>();
			for (Transaccion transaccionListNewTransaccionToAttach : transaccionListNew) {
				transaccionListNewTransaccionToAttach = em.getReference(
						transaccionListNewTransaccionToAttach.getClass(),
						transaccionListNewTransaccionToAttach.getId());
				attachedTransaccionListNew
						.add(transaccionListNewTransaccionToAttach);
			}
			transaccionListNew = attachedTransaccionListNew;
			serialimpresora.setTransaccionList(transaccionListNew);
			serialimpresora = em.merge(serialimpresora);
			for (Rolloauditoria rolloauditoriaListNewRolloauditoria : rolloauditoriaListNew) {
				if (!rolloauditoriaListOld
						.contains(rolloauditoriaListNewRolloauditoria)) {
					Serialimpresora oldIdSerialimpresoraOfRolloauditoriaListNewRolloauditoria = rolloauditoriaListNewRolloauditoria
							.getIdSerialimpresora();
					rolloauditoriaListNewRolloauditoria
							.setIdSerialimpresora(serialimpresora);
					rolloauditoriaListNewRolloauditoria = em
							.merge(rolloauditoriaListNewRolloauditoria);
					if (oldIdSerialimpresoraOfRolloauditoriaListNewRolloauditoria != null
							&& !oldIdSerialimpresoraOfRolloauditoriaListNewRolloauditoria
									.equals(serialimpresora)) {
						oldIdSerialimpresoraOfRolloauditoriaListNewRolloauditoria
								.getRolloauditoriaList().remove(
										rolloauditoriaListNewRolloauditoria);
						oldIdSerialimpresoraOfRolloauditoriaListNewRolloauditoria = em
								.merge(oldIdSerialimpresoraOfRolloauditoriaListNewRolloauditoria);
					}
				}
			}
			for (Transaccion transaccionListNewTransaccion : transaccionListNew) {
				if (!transaccionListOld.contains(transaccionListNewTransaccion)) {
					Serialimpresora oldIdSerialimpresoraOfTransaccionListNewTransaccion = transaccionListNewTransaccion
							.getIdSerialimpresora();
					transaccionListNewTransaccion
							.setIdSerialimpresora(serialimpresora);
					transaccionListNewTransaccion = em
							.merge(transaccionListNewTransaccion);
					if (oldIdSerialimpresoraOfTransaccionListNewTransaccion != null
							&& !oldIdSerialimpresoraOfTransaccionListNewTransaccion
									.equals(serialimpresora)) {
						oldIdSerialimpresoraOfTransaccionListNewTransaccion
								.getTransaccionList().remove(
										transaccionListNewTransaccion);
						oldIdSerialimpresoraOfTransaccionListNewTransaccion = em
								.merge(oldIdSerialimpresoraOfTransaccionListNewTransaccion);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = serialimpresora.getId();
				if (findSerialimpresora(id) == null) {
					throw new NonexistentEntityException(
							"The serialimpresora with id " + id
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
			Serialimpresora serialimpresora;
			try {
				serialimpresora = em.getReference(Serialimpresora.class, id);
				serialimpresora.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The serialimpresora with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Rolloauditoria> rolloauditoriaListOrphanCheck = serialimpresora
					.getRolloauditoriaList();
			for (Rolloauditoria rolloauditoriaListOrphanCheckRolloauditoria : rolloauditoriaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Serialimpresora ("
								+ serialimpresora
								+ ") cannot be destroyed since the Rolloauditoria "
								+ rolloauditoriaListOrphanCheckRolloauditoria
								+ " in its rolloauditoriaList field has a non-nullable idSerialimpresora field.");
			}
			List<Transaccion> transaccionListOrphanCheck = serialimpresora
					.getTransaccionList();
			for (Transaccion transaccionListOrphanCheckTransaccion : transaccionListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Serialimpresora ("
								+ serialimpresora
								+ ") cannot be destroyed since the Transaccion "
								+ transaccionListOrphanCheckTransaccion
								+ " in its transaccionList field has a non-nullable idSerialimpresora field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(serialimpresora);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findSerialimpresoraEntities.
	 * 
	 * @return List<Serialimpresora>
	 */
	public List<Serialimpresora> findSerialimpresoraEntities() {
		return findSerialimpresoraEntities(true, -1, -1);
	}

	/**
	 * Method findSerialimpresoraEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Serialimpresora>
	 */
	public List<Serialimpresora> findSerialimpresoraEntities(int maxResults,
			int firstResult) {
		return findSerialimpresoraEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findSerialimpresoraEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Serialimpresora>
	 */
	private List<Serialimpresora> findSerialimpresoraEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Serialimpresora.class));
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
	 * Method findSerialimpresora.
	 * 
	 * @param id
	 *            Long
	 * @return Serialimpresora
	 */
	public Serialimpresora findSerialimpresora(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Serialimpresora.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getSerialimpresoraCount.
	 * 
	 * @return int
	 */
	public int getSerialimpresoraCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Serialimpresora> rt = cq.from(Serialimpresora.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
