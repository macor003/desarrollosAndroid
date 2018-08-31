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
public class TasaimpuestovalorJpaController implements Serializable {

	/**
	 * Constructor for TasaimpuestovalorJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TasaimpuestovalorJpaController(EntityManagerFactory emf) {
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
	 * @param tasaimpuestovalor
	 *            Tasaimpuestovalor
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tasaimpuestovalor tasaimpuestovalor)
			throws PreexistingEntityException, Exception {
		if (tasaimpuestovalor.getOrdendeventaarticuloList() == null) {
			tasaimpuestovalor
					.setOrdendeventaarticuloList(new ArrayList<Ordendeventaarticulo>());
		}
		if (tasaimpuestovalor.getTransaccionarticuloList() == null) {
			tasaimpuestovalor
					.setTransaccionarticuloList(new ArrayList<Transaccionarticulo>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tasaimpuestotipo idTasaimpuestotipo = tasaimpuestovalor
					.getIdTasaimpuestotipo();
			if (idTasaimpuestotipo != null) {
				idTasaimpuestotipo = em.getReference(
						idTasaimpuestotipo.getClass(),
						idTasaimpuestotipo.getId());
				tasaimpuestovalor.setIdTasaimpuestotipo(idTasaimpuestotipo);
			}
			List<Ordendeventaarticulo> attachedOrdendeventaarticuloList = new ArrayList<Ordendeventaarticulo>();
			for (Ordendeventaarticulo ordendeventaarticuloListOrdendeventaarticuloToAttach : tasaimpuestovalor
					.getOrdendeventaarticuloList()) {
				ordendeventaarticuloListOrdendeventaarticuloToAttach = em
						.getReference(
								ordendeventaarticuloListOrdendeventaarticuloToAttach
										.getClass(),
								ordendeventaarticuloListOrdendeventaarticuloToAttach
										.getId());
				attachedOrdendeventaarticuloList
						.add(ordendeventaarticuloListOrdendeventaarticuloToAttach);
			}
			tasaimpuestovalor
					.setOrdendeventaarticuloList(attachedOrdendeventaarticuloList);
			List<Transaccionarticulo> attachedTransaccionarticuloList = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListTransaccionarticuloToAttach : tasaimpuestovalor
					.getTransaccionarticuloList()) {
				transaccionarticuloListTransaccionarticuloToAttach = em
						.getReference(
								transaccionarticuloListTransaccionarticuloToAttach
										.getClass(),
								transaccionarticuloListTransaccionarticuloToAttach
										.getId());
				attachedTransaccionarticuloList
						.add(transaccionarticuloListTransaccionarticuloToAttach);
			}
			tasaimpuestovalor
					.setTransaccionarticuloList(attachedTransaccionarticuloList);
			em.persist(tasaimpuestovalor);
			if (idTasaimpuestotipo != null) {
				idTasaimpuestotipo.getTasaimpuestovalorList().add(
						tasaimpuestovalor);
				idTasaimpuestotipo = em.merge(idTasaimpuestotipo);
			}
			for (Ordendeventaarticulo ordendeventaarticuloListOrdendeventaarticulo : tasaimpuestovalor
					.getOrdendeventaarticuloList()) {
				Tasaimpuestovalor oldIdTasaimpuestovalorOfOrdendeventaarticuloListOrdendeventaarticulo = ordendeventaarticuloListOrdendeventaarticulo
						.getIdTasaimpuestovalor();
				ordendeventaarticuloListOrdendeventaarticulo
						.setIdTasaimpuestovalor(tasaimpuestovalor);
				ordendeventaarticuloListOrdendeventaarticulo = em
						.merge(ordendeventaarticuloListOrdendeventaarticulo);
				if (oldIdTasaimpuestovalorOfOrdendeventaarticuloListOrdendeventaarticulo != null) {
					oldIdTasaimpuestovalorOfOrdendeventaarticuloListOrdendeventaarticulo
							.getOrdendeventaarticuloList()
							.remove(ordendeventaarticuloListOrdendeventaarticulo);
					oldIdTasaimpuestovalorOfOrdendeventaarticuloListOrdendeventaarticulo = em
							.merge(oldIdTasaimpuestovalorOfOrdendeventaarticuloListOrdendeventaarticulo);
				}
			}
			for (Transaccionarticulo transaccionarticuloListTransaccionarticulo : tasaimpuestovalor
					.getTransaccionarticuloList()) {
				Tasaimpuestovalor oldIdTasaimpuestovalorOfTransaccionarticuloListTransaccionarticulo = transaccionarticuloListTransaccionarticulo
						.getIdTasaimpuestovalor();
				transaccionarticuloListTransaccionarticulo
						.setIdTasaimpuestovalor(tasaimpuestovalor);
				transaccionarticuloListTransaccionarticulo = em
						.merge(transaccionarticuloListTransaccionarticulo);
				if (oldIdTasaimpuestovalorOfTransaccionarticuloListTransaccionarticulo != null) {
					oldIdTasaimpuestovalorOfTransaccionarticuloListTransaccionarticulo
							.getTransaccionarticuloList().remove(
									transaccionarticuloListTransaccionarticulo);
					oldIdTasaimpuestovalorOfTransaccionarticuloListTransaccionarticulo = em
							.merge(oldIdTasaimpuestovalorOfTransaccionarticuloListTransaccionarticulo);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTasaimpuestovalor(tasaimpuestovalor.getId()) != null) {
				throw new PreexistingEntityException("Tasaimpuestovalor "
						+ tasaimpuestovalor + " already exists.", ex);
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
	 * @param tasaimpuestovalor
	 *            Tasaimpuestovalor
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tasaimpuestovalor tasaimpuestovalor)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tasaimpuestovalor persistentTasaimpuestovalor = em.find(
					Tasaimpuestovalor.class, tasaimpuestovalor.getId());
			Tasaimpuestotipo idTasaimpuestotipoOld = persistentTasaimpuestovalor
					.getIdTasaimpuestotipo();
			Tasaimpuestotipo idTasaimpuestotipoNew = tasaimpuestovalor
					.getIdTasaimpuestotipo();
			List<Ordendeventaarticulo> ordendeventaarticuloListOld = persistentTasaimpuestovalor
					.getOrdendeventaarticuloList();
			List<Ordendeventaarticulo> ordendeventaarticuloListNew = tasaimpuestovalor
					.getOrdendeventaarticuloList();
			List<Transaccionarticulo> transaccionarticuloListOld = persistentTasaimpuestovalor
					.getTransaccionarticuloList();
			List<Transaccionarticulo> transaccionarticuloListNew = tasaimpuestovalor
					.getTransaccionarticuloList();
			List<String> illegalOrphanMessages = null;
			for (Ordendeventaarticulo ordendeventaarticuloListOldOrdendeventaarticulo : ordendeventaarticuloListOld) {
				if (!ordendeventaarticuloListNew
						.contains(ordendeventaarticuloListOldOrdendeventaarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Ordendeventaarticulo "
									+ ordendeventaarticuloListOldOrdendeventaarticulo
									+ " since its idTasaimpuestovalor field is not nullable.");
				}
			}
			for (Transaccionarticulo transaccionarticuloListOldTransaccionarticulo : transaccionarticuloListOld) {
				if (!transaccionarticuloListNew
						.contains(transaccionarticuloListOldTransaccionarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Transaccionarticulo "
									+ transaccionarticuloListOldTransaccionarticulo
									+ " since its idTasaimpuestovalor field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idTasaimpuestotipoNew != null) {
				idTasaimpuestotipoNew = em.getReference(
						idTasaimpuestotipoNew.getClass(),
						idTasaimpuestotipoNew.getId());
				tasaimpuestovalor.setIdTasaimpuestotipo(idTasaimpuestotipoNew);
			}
			List<Ordendeventaarticulo> attachedOrdendeventaarticuloListNew = new ArrayList<Ordendeventaarticulo>();
			for (Ordendeventaarticulo ordendeventaarticuloListNewOrdendeventaarticuloToAttach : ordendeventaarticuloListNew) {
				ordendeventaarticuloListNewOrdendeventaarticuloToAttach = em
						.getReference(
								ordendeventaarticuloListNewOrdendeventaarticuloToAttach
										.getClass(),
								ordendeventaarticuloListNewOrdendeventaarticuloToAttach
										.getId());
				attachedOrdendeventaarticuloListNew
						.add(ordendeventaarticuloListNewOrdendeventaarticuloToAttach);
			}
			ordendeventaarticuloListNew = attachedOrdendeventaarticuloListNew;
			tasaimpuestovalor
					.setOrdendeventaarticuloList(ordendeventaarticuloListNew);
			List<Transaccionarticulo> attachedTransaccionarticuloListNew = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticuloToAttach : transaccionarticuloListNew) {
				transaccionarticuloListNewTransaccionarticuloToAttach = em
						.getReference(
								transaccionarticuloListNewTransaccionarticuloToAttach
										.getClass(),
								transaccionarticuloListNewTransaccionarticuloToAttach
										.getId());
				attachedTransaccionarticuloListNew
						.add(transaccionarticuloListNewTransaccionarticuloToAttach);
			}
			transaccionarticuloListNew = attachedTransaccionarticuloListNew;
			tasaimpuestovalor
					.setTransaccionarticuloList(transaccionarticuloListNew);
			tasaimpuestovalor = em.merge(tasaimpuestovalor);
			if (idTasaimpuestotipoOld != null
					&& !idTasaimpuestotipoOld.equals(idTasaimpuestotipoNew)) {
				idTasaimpuestotipoOld.getTasaimpuestovalorList().remove(
						tasaimpuestovalor);
				idTasaimpuestotipoOld = em.merge(idTasaimpuestotipoOld);
			}
			if (idTasaimpuestotipoNew != null
					&& !idTasaimpuestotipoNew.equals(idTasaimpuestotipoOld)) {
				idTasaimpuestotipoNew.getTasaimpuestovalorList().add(
						tasaimpuestovalor);
				idTasaimpuestotipoNew = em.merge(idTasaimpuestotipoNew);
			}
			for (Ordendeventaarticulo ordendeventaarticuloListNewOrdendeventaarticulo : ordendeventaarticuloListNew) {
				if (!ordendeventaarticuloListOld
						.contains(ordendeventaarticuloListNewOrdendeventaarticulo)) {
					Tasaimpuestovalor oldIdTasaimpuestovalorOfOrdendeventaarticuloListNewOrdendeventaarticulo = ordendeventaarticuloListNewOrdendeventaarticulo
							.getIdTasaimpuestovalor();
					ordendeventaarticuloListNewOrdendeventaarticulo
							.setIdTasaimpuestovalor(tasaimpuestovalor);
					ordendeventaarticuloListNewOrdendeventaarticulo = em
							.merge(ordendeventaarticuloListNewOrdendeventaarticulo);
					if (oldIdTasaimpuestovalorOfOrdendeventaarticuloListNewOrdendeventaarticulo != null
							&& !oldIdTasaimpuestovalorOfOrdendeventaarticuloListNewOrdendeventaarticulo
									.equals(tasaimpuestovalor)) {
						oldIdTasaimpuestovalorOfOrdendeventaarticuloListNewOrdendeventaarticulo
								.getOrdendeventaarticuloList()
								.remove(ordendeventaarticuloListNewOrdendeventaarticulo);
						oldIdTasaimpuestovalorOfOrdendeventaarticuloListNewOrdendeventaarticulo = em
								.merge(oldIdTasaimpuestovalorOfOrdendeventaarticuloListNewOrdendeventaarticulo);
					}
				}
			}
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticulo : transaccionarticuloListNew) {
				if (!transaccionarticuloListOld
						.contains(transaccionarticuloListNewTransaccionarticulo)) {
					Tasaimpuestovalor oldIdTasaimpuestovalorOfTransaccionarticuloListNewTransaccionarticulo = transaccionarticuloListNewTransaccionarticulo
							.getIdTasaimpuestovalor();
					transaccionarticuloListNewTransaccionarticulo
							.setIdTasaimpuestovalor(tasaimpuestovalor);
					transaccionarticuloListNewTransaccionarticulo = em
							.merge(transaccionarticuloListNewTransaccionarticulo);
					if (oldIdTasaimpuestovalorOfTransaccionarticuloListNewTransaccionarticulo != null
							&& !oldIdTasaimpuestovalorOfTransaccionarticuloListNewTransaccionarticulo
									.equals(tasaimpuestovalor)) {
						oldIdTasaimpuestovalorOfTransaccionarticuloListNewTransaccionarticulo
								.getTransaccionarticuloList()
								.remove(transaccionarticuloListNewTransaccionarticulo);
						oldIdTasaimpuestovalorOfTransaccionarticuloListNewTransaccionarticulo = em
								.merge(oldIdTasaimpuestovalorOfTransaccionarticuloListNewTransaccionarticulo);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tasaimpuestovalor.getId();
				if (findTasaimpuestovalor(id) == null) {
					throw new NonexistentEntityException(
							"The tasaimpuestovalor with id " + id
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
			Tasaimpuestovalor tasaimpuestovalor;
			try {
				tasaimpuestovalor = em
						.getReference(Tasaimpuestovalor.class, id);
				tasaimpuestovalor.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The tasaimpuestovalor with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Ordendeventaarticulo> ordendeventaarticuloListOrphanCheck = tasaimpuestovalor
					.getOrdendeventaarticuloList();
			for (Ordendeventaarticulo ordendeventaarticuloListOrphanCheckOrdendeventaarticulo : ordendeventaarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tasaimpuestovalor ("
								+ tasaimpuestovalor
								+ ") cannot be destroyed since the Ordendeventaarticulo "
								+ ordendeventaarticuloListOrphanCheckOrdendeventaarticulo
								+ " in its ordendeventaarticuloList field has a non-nullable idTasaimpuestovalor field.");
			}
			List<Transaccionarticulo> transaccionarticuloListOrphanCheck = tasaimpuestovalor
					.getTransaccionarticuloList();
			for (Transaccionarticulo transaccionarticuloListOrphanCheckTransaccionarticulo : transaccionarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Tasaimpuestovalor ("
								+ tasaimpuestovalor
								+ ") cannot be destroyed since the Transaccionarticulo "
								+ transaccionarticuloListOrphanCheckTransaccionarticulo
								+ " in its transaccionarticuloList field has a non-nullable idTasaimpuestovalor field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Tasaimpuestotipo idTasaimpuestotipo = tasaimpuestovalor
					.getIdTasaimpuestotipo();
			if (idTasaimpuestotipo != null) {
				idTasaimpuestotipo.getTasaimpuestovalorList().remove(
						tasaimpuestovalor);
				idTasaimpuestotipo = em.merge(idTasaimpuestotipo);
			}
			em.remove(tasaimpuestovalor);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTasaimpuestovalorEntities.
	 * 
	 * @return List<Tasaimpuestovalor>
	 */
	public List<Tasaimpuestovalor> findTasaimpuestovalorEntities() {
		return findTasaimpuestovalorEntities(true, -1, -1);
	}

	/**
	 * Method findTasaimpuestovalorEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tasaimpuestovalor>
	 */
	public List<Tasaimpuestovalor> findTasaimpuestovalorEntities(
			int maxResults, int firstResult) {
		return findTasaimpuestovalorEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTasaimpuestovalorEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tasaimpuestovalor>
	 */
	private List<Tasaimpuestovalor> findTasaimpuestovalorEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tasaimpuestovalor.class));
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
	 * Method findTasaimpuestovalor.
	 * 
	 * @param id
	 *            Long
	 * @return Tasaimpuestovalor
	 */
	public Tasaimpuestovalor findTasaimpuestovalor(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tasaimpuestovalor.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTasaimpuestovalorCount.
	 * 
	 * @return int
	 */
	public int getTasaimpuestovalorCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tasaimpuestovalor> rt = cq.from(Tasaimpuestovalor.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
