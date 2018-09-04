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
public class TipoordendeventaJpaController implements Serializable {

	/**
	 * Constructor for TipoordendeventaJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipoordendeventaJpaController(EntityManagerFactory emf) {
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
	 * @param tipoordendeventa
	 *            Tipoordendeventa
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipoordendeventa tipoordendeventa)
			throws PreexistingEntityException, Exception {
		if (tipoordendeventa.getOrdendeventaList() == null) {
			tipoordendeventa.setOrdendeventaList(new ArrayList<Ordendeventa>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Ordendeventa> attachedOrdendeventaList = new ArrayList<Ordendeventa>();
			for (Ordendeventa ordendeventaListOrdendeventaToAttach : tipoordendeventa
					.getOrdendeventaList()) {
				ordendeventaListOrdendeventaToAttach = em.getReference(
						ordendeventaListOrdendeventaToAttach.getClass(),
						ordendeventaListOrdendeventaToAttach.getId());
				attachedOrdendeventaList
						.add(ordendeventaListOrdendeventaToAttach);
			}
			tipoordendeventa.setOrdendeventaList(attachedOrdendeventaList);
			em.persist(tipoordendeventa);
			for (Ordendeventa ordendeventaListOrdendeventa : tipoordendeventa
					.getOrdendeventaList()) {
				Tipoordendeventa oldIdTipoordendeventaOfOrdendeventaListOrdendeventa = ordendeventaListOrdendeventa
						.getIdTipoordendeventa();
				ordendeventaListOrdendeventa
						.setIdTipoordendeventa(tipoordendeventa);
				ordendeventaListOrdendeventa = em
						.merge(ordendeventaListOrdendeventa);
				if (oldIdTipoordendeventaOfOrdendeventaListOrdendeventa != null) {
					oldIdTipoordendeventaOfOrdendeventaListOrdendeventa
							.getOrdendeventaList().remove(
									ordendeventaListOrdendeventa);
					oldIdTipoordendeventaOfOrdendeventaListOrdendeventa = em
							.merge(oldIdTipoordendeventaOfOrdendeventaListOrdendeventa);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipoordendeventa(tipoordendeventa.getId()) != null) {
				throw new PreexistingEntityException("Tipoordendeventa "
						+ tipoordendeventa + " already exists.", ex);
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
	 * @param tipoordendeventa
	 *            Tipoordendeventa
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tipoordendeventa tipoordendeventa)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipoordendeventa persistentTipoordendeventa = em.find(
					Tipoordendeventa.class, tipoordendeventa.getId());
			List<Ordendeventa> ordendeventaListOld = persistentTipoordendeventa
					.getOrdendeventaList();
			List<Ordendeventa> ordendeventaListNew = tipoordendeventa
					.getOrdendeventaList();
			List<String> illegalOrphanMessages = null;
			for (Ordendeventa ordendeventaListOldOrdendeventa : ordendeventaListOld) {
				if (!ordendeventaListNew
						.contains(ordendeventaListOldOrdendeventa)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Ordendeventa "
									+ ordendeventaListOldOrdendeventa
									+ " since its idTipoordendeventa field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Ordendeventa> attachedOrdendeventaListNew = new ArrayList<Ordendeventa>();
			for (Ordendeventa ordendeventaListNewOrdendeventaToAttach : ordendeventaListNew) {
				ordendeventaListNewOrdendeventaToAttach = em.getReference(
						ordendeventaListNewOrdendeventaToAttach.getClass(),
						ordendeventaListNewOrdendeventaToAttach.getId());
				attachedOrdendeventaListNew
						.add(ordendeventaListNewOrdendeventaToAttach);
			}
			ordendeventaListNew = attachedOrdendeventaListNew;
			tipoordendeventa.setOrdendeventaList(ordendeventaListNew);
			tipoordendeventa = em.merge(tipoordendeventa);
			for (Ordendeventa ordendeventaListNewOrdendeventa : ordendeventaListNew) {
				if (!ordendeventaListOld
						.contains(ordendeventaListNewOrdendeventa)) {
					Tipoordendeventa oldIdTipoordendeventaOfOrdendeventaListNewOrdendeventa = ordendeventaListNewOrdendeventa
							.getIdTipoordendeventa();
					ordendeventaListNewOrdendeventa
							.setIdTipoordendeventa(tipoordendeventa);
					ordendeventaListNewOrdendeventa = em
							.merge(ordendeventaListNewOrdendeventa);
					if (oldIdTipoordendeventaOfOrdendeventaListNewOrdendeventa != null
							&& !oldIdTipoordendeventaOfOrdendeventaListNewOrdendeventa
									.equals(tipoordendeventa)) {
						oldIdTipoordendeventaOfOrdendeventaListNewOrdendeventa
								.getOrdendeventaList().remove(
										ordendeventaListNewOrdendeventa);
						oldIdTipoordendeventaOfOrdendeventaListNewOrdendeventa = em
								.merge(oldIdTipoordendeventaOfOrdendeventaListNewOrdendeventa);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipoordendeventa.getId();
				if (findTipoordendeventa(id) == null) {
					throw new NonexistentEntityException(
							"The tipoordendeventa with id " + id
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
			Tipoordendeventa tipoordendeventa;
			try {
				tipoordendeventa = em.getReference(Tipoordendeventa.class, id);
				tipoordendeventa.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The tipoordendeventa with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Ordendeventa> ordendeventaListOrphanCheck = tipoordendeventa
					.getOrdendeventaList();
			for (Ordendeventa ordendeventaListOrphanCheckOrdendeventa : ordendeventaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tipoordendeventa ("
								+ tipoordendeventa
								+ ") cannot be destroyed since the Ordendeventa "
								+ ordendeventaListOrphanCheckOrdendeventa
								+ " in its ordendeventaList field has a non-nullable idTipoordendeventa field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(tipoordendeventa);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTipoordendeventaEntities.
	 * 
	 * @return List<Tipoordendeventa>
	 */
	public List<Tipoordendeventa> findTipoordendeventaEntities() {
		return findTipoordendeventaEntities(true, -1, -1);
	}

	/**
	 * Method findTipoordendeventaEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoordendeventa>
	 */
	public List<Tipoordendeventa> findTipoordendeventaEntities(int maxResults,
			int firstResult) {
		return findTipoordendeventaEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTipoordendeventaEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipoordendeventa>
	 */
	private List<Tipoordendeventa> findTipoordendeventaEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipoordendeventa.class));
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
	 * Method findTipoordendeventa.
	 * 
	 * @param id
	 *            Long
	 * @return Tipoordendeventa
	 */
	public Tipoordendeventa findTipoordendeventa(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipoordendeventa.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipoordendeventaCount.
	 * 
	 * @return int
	 */
	public int getTipoordendeventaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipoordendeventa> rt = cq.from(Tipoordendeventa.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
