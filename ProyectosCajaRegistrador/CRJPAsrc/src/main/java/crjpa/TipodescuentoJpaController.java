/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crjpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import crjpa.exceptions.IllegalOrphanException;
import crjpa.exceptions.NonexistentEntityException;
import crjpa.exceptions.PreexistingEntityException;

/**
 * 
 * @author eve0014321
 * @version $Revision: 1.0 $
 */
public class TipodescuentoJpaController implements Serializable {

	/**
	 * Constructor for TipodescuentoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public TipodescuentoJpaController(EntityManagerFactory emf) {
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
	 * @param tipodescuento
	 *            Tipodescuento
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Tipodescuento tipodescuento) throws PreexistingEntityException, Exception {
		if (tipodescuento.getPromociontipodescuentoList() == null) {
			tipodescuento.setPromociontipodescuentoList(new ArrayList<Promociontipodescuento>());
		}
		if (tipodescuento.getTransaccionarticuloList() == null) {
			tipodescuento.setTransaccionarticuloList(new ArrayList<Transaccionarticulo>());
		}
		if (tipodescuento.getMotivorebajaList() == null) {
			tipodescuento.setMotivorebajaList(new ArrayList<Motivorebaja>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Promociontipodescuento> attachedPromociontipodescuentoList = new ArrayList<Promociontipodescuento>();
			for (Promociontipodescuento promociontipodescuentoListPromociontipodescuentoToAttach : tipodescuento
					.getPromociontipodescuentoList()) {
				promociontipodescuentoListPromociontipodescuentoToAttach = em.getReference(
						promociontipodescuentoListPromociontipodescuentoToAttach.getClass(),
						promociontipodescuentoListPromociontipodescuentoToAttach.getId());
				attachedPromociontipodescuentoList.add(promociontipodescuentoListPromociontipodescuentoToAttach);
			}
			tipodescuento.setPromociontipodescuentoList(attachedPromociontipodescuentoList);
			List<Transaccionarticulo> attachedTransaccionarticuloList = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListTransaccionarticuloToAttach : tipodescuento
					.getTransaccionarticuloList()) {
				transaccionarticuloListTransaccionarticuloToAttach = em.getReference(
						transaccionarticuloListTransaccionarticuloToAttach.getClass(),
						transaccionarticuloListTransaccionarticuloToAttach.getId());
				attachedTransaccionarticuloList.add(transaccionarticuloListTransaccionarticuloToAttach);
			}
			tipodescuento.setTransaccionarticuloList(attachedTransaccionarticuloList);
			List<Motivorebaja> attachedMotivorebajaList = new ArrayList<Motivorebaja>();
			for (Motivorebaja motivorebajaListMotivorebajaToAttach : tipodescuento.getMotivorebajaList()) {
				motivorebajaListMotivorebajaToAttach = em.getReference(motivorebajaListMotivorebajaToAttach.getClass(),
						motivorebajaListMotivorebajaToAttach.getId());
				attachedMotivorebajaList.add(motivorebajaListMotivorebajaToAttach);
			}
			tipodescuento.setMotivorebajaList(attachedMotivorebajaList);
			em.persist(tipodescuento);
			for (Promociontipodescuento promociontipodescuentoListPromociontipodescuento : tipodescuento
					.getPromociontipodescuentoList()) {
				Tipodescuento oldIdTipodescuentoOfPromociontipodescuentoListPromociontipodescuento = promociontipodescuentoListPromociontipodescuento
						.getIdTipodescuento();
				promociontipodescuentoListPromociontipodescuento.setIdTipodescuento(tipodescuento);
				promociontipodescuentoListPromociontipodescuento = em
						.merge(promociontipodescuentoListPromociontipodescuento);
				if (oldIdTipodescuentoOfPromociontipodescuentoListPromociontipodescuento != null) {
					oldIdTipodescuentoOfPromociontipodescuentoListPromociontipodescuento
							.getPromociontipodescuentoList().remove(promociontipodescuentoListPromociontipodescuento);
					oldIdTipodescuentoOfPromociontipodescuentoListPromociontipodescuento = em
							.merge(oldIdTipodescuentoOfPromociontipodescuentoListPromociontipodescuento);
				}
			}
			for (Transaccionarticulo transaccionarticuloListTransaccionarticulo : tipodescuento
					.getTransaccionarticuloList()) {
				Tipodescuento oldIdTipodescuentoOfTransaccionarticuloListTransaccionarticulo = transaccionarticuloListTransaccionarticulo
						.getIdTipodescuento();
				transaccionarticuloListTransaccionarticulo.setIdTipodescuento(tipodescuento);
				transaccionarticuloListTransaccionarticulo = em.merge(transaccionarticuloListTransaccionarticulo);
				if (oldIdTipodescuentoOfTransaccionarticuloListTransaccionarticulo != null) {
					oldIdTipodescuentoOfTransaccionarticuloListTransaccionarticulo.getTransaccionarticuloList().remove(
							transaccionarticuloListTransaccionarticulo);
					oldIdTipodescuentoOfTransaccionarticuloListTransaccionarticulo = em
							.merge(oldIdTipodescuentoOfTransaccionarticuloListTransaccionarticulo);
				}
			}
			for (Motivorebaja motivorebajaListMotivorebaja : tipodescuento.getMotivorebajaList()) {
				Tipodescuento oldIdTipodescuentoOfMotivorebajaListMotivorebaja = motivorebajaListMotivorebaja
						.getIdTipodescuento();
				motivorebajaListMotivorebaja.setIdTipodescuento(tipodescuento);
				motivorebajaListMotivorebaja = em.merge(motivorebajaListMotivorebaja);
				if (oldIdTipodescuentoOfMotivorebajaListMotivorebaja != null) {
					oldIdTipodescuentoOfMotivorebajaListMotivorebaja.getMotivorebajaList().remove(
							motivorebajaListMotivorebaja);
					oldIdTipodescuentoOfMotivorebajaListMotivorebaja = em
							.merge(oldIdTipodescuentoOfMotivorebajaListMotivorebaja);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findTipodescuento(tipodescuento.getId()) != null) {
				throw new PreexistingEntityException("Tipodescuento " + tipodescuento + " already exists.", ex);
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
	 * @param tipodescuento
	 *            Tipodescuento
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Tipodescuento tipodescuento) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodescuento persistentTipodescuento = em.find(Tipodescuento.class, tipodescuento.getId());
			List<Promociontipodescuento> promociontipodescuentoListOld = persistentTipodescuento
					.getPromociontipodescuentoList();
			List<Promociontipodescuento> promociontipodescuentoListNew = tipodescuento.getPromociontipodescuentoList();
			List<Transaccionarticulo> transaccionarticuloListOld = persistentTipodescuento.getTransaccionarticuloList();
			List<Transaccionarticulo> transaccionarticuloListNew = tipodescuento.getTransaccionarticuloList();
			List<Motivorebaja> motivorebajaListOld = persistentTipodescuento.getMotivorebajaList();
			List<Motivorebaja> motivorebajaListNew = tipodescuento.getMotivorebajaList();
			List<String> illegalOrphanMessages = null;
			for (Promociontipodescuento promociontipodescuentoListOldPromociontipodescuento : promociontipodescuentoListOld) {
				if (!promociontipodescuentoListNew.contains(promociontipodescuentoListOldPromociontipodescuento)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Promociontipodescuento "
							+ promociontipodescuentoListOldPromociontipodescuento
							+ " since its idTipodescuento field is not nullable.");
				}
			}
			for (Transaccionarticulo transaccionarticuloListOldTransaccionarticulo : transaccionarticuloListOld) {
				if (!transaccionarticuloListNew.contains(transaccionarticuloListOldTransaccionarticulo)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Transaccionarticulo "
							+ transaccionarticuloListOldTransaccionarticulo
							+ " since its idTipodescuento field is not nullable.");
				}
			}
			for (Motivorebaja motivorebajaListOldMotivorebaja : motivorebajaListOld) {
				if (!motivorebajaListNew.contains(motivorebajaListOldMotivorebaja)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Motivorebaja " + motivorebajaListOldMotivorebaja
							+ " since its idTipodescuento field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Promociontipodescuento> attachedPromociontipodescuentoListNew = new ArrayList<Promociontipodescuento>();
			for (Promociontipodescuento promociontipodescuentoListNewPromociontipodescuentoToAttach : promociontipodescuentoListNew) {
				promociontipodescuentoListNewPromociontipodescuentoToAttach = em.getReference(
						promociontipodescuentoListNewPromociontipodescuentoToAttach.getClass(),
						promociontipodescuentoListNewPromociontipodescuentoToAttach.getId());
				attachedPromociontipodescuentoListNew.add(promociontipodescuentoListNewPromociontipodescuentoToAttach);
			}
			promociontipodescuentoListNew = attachedPromociontipodescuentoListNew;
			tipodescuento.setPromociontipodescuentoList(promociontipodescuentoListNew);
			List<Transaccionarticulo> attachedTransaccionarticuloListNew = new ArrayList<Transaccionarticulo>();
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticuloToAttach : transaccionarticuloListNew) {
				transaccionarticuloListNewTransaccionarticuloToAttach = em.getReference(
						transaccionarticuloListNewTransaccionarticuloToAttach.getClass(),
						transaccionarticuloListNewTransaccionarticuloToAttach.getId());
				attachedTransaccionarticuloListNew.add(transaccionarticuloListNewTransaccionarticuloToAttach);
			}
			transaccionarticuloListNew = attachedTransaccionarticuloListNew;
			tipodescuento.setTransaccionarticuloList(transaccionarticuloListNew);
			List<Motivorebaja> attachedMotivorebajaListNew = new ArrayList<Motivorebaja>();
			for (Motivorebaja motivorebajaListNewMotivorebajaToAttach : motivorebajaListNew) {
				motivorebajaListNewMotivorebajaToAttach = em.getReference(
						motivorebajaListNewMotivorebajaToAttach.getClass(),
						motivorebajaListNewMotivorebajaToAttach.getId());
				attachedMotivorebajaListNew.add(motivorebajaListNewMotivorebajaToAttach);
			}
			motivorebajaListNew = attachedMotivorebajaListNew;
			tipodescuento.setMotivorebajaList(motivorebajaListNew);
			tipodescuento = em.merge(tipodescuento);
			for (Promociontipodescuento promociontipodescuentoListNewPromociontipodescuento : promociontipodescuentoListNew) {
				if (!promociontipodescuentoListOld.contains(promociontipodescuentoListNewPromociontipodescuento)) {
					Tipodescuento oldIdTipodescuentoOfPromociontipodescuentoListNewPromociontipodescuento = promociontipodescuentoListNewPromociontipodescuento
							.getIdTipodescuento();
					promociontipodescuentoListNewPromociontipodescuento.setIdTipodescuento(tipodescuento);
					promociontipodescuentoListNewPromociontipodescuento = em
							.merge(promociontipodescuentoListNewPromociontipodescuento);
					if (oldIdTipodescuentoOfPromociontipodescuentoListNewPromociontipodescuento != null
							&& !oldIdTipodescuentoOfPromociontipodescuentoListNewPromociontipodescuento
									.equals(tipodescuento)) {
						oldIdTipodescuentoOfPromociontipodescuentoListNewPromociontipodescuento
								.getPromociontipodescuentoList().remove(
										promociontipodescuentoListNewPromociontipodescuento);
						oldIdTipodescuentoOfPromociontipodescuentoListNewPromociontipodescuento = em
								.merge(oldIdTipodescuentoOfPromociontipodescuentoListNewPromociontipodescuento);
					}
				}
			}
			for (Transaccionarticulo transaccionarticuloListNewTransaccionarticulo : transaccionarticuloListNew) {
				if (!transaccionarticuloListOld.contains(transaccionarticuloListNewTransaccionarticulo)) {
					Tipodescuento oldIdTipodescuentoOfTransaccionarticuloListNewTransaccionarticulo = transaccionarticuloListNewTransaccionarticulo
							.getIdTipodescuento();
					transaccionarticuloListNewTransaccionarticulo.setIdTipodescuento(tipodescuento);
					transaccionarticuloListNewTransaccionarticulo = em
							.merge(transaccionarticuloListNewTransaccionarticulo);
					if (oldIdTipodescuentoOfTransaccionarticuloListNewTransaccionarticulo != null
							&& !oldIdTipodescuentoOfTransaccionarticuloListNewTransaccionarticulo.equals(tipodescuento)) {
						oldIdTipodescuentoOfTransaccionarticuloListNewTransaccionarticulo.getTransaccionarticuloList()
								.remove(transaccionarticuloListNewTransaccionarticulo);
						oldIdTipodescuentoOfTransaccionarticuloListNewTransaccionarticulo = em
								.merge(oldIdTipodescuentoOfTransaccionarticuloListNewTransaccionarticulo);
					}
				}
			}
			for (Motivorebaja motivorebajaListNewMotivorebaja : motivorebajaListNew) {
				if (!motivorebajaListOld.contains(motivorebajaListNewMotivorebaja)) {
					Tipodescuento oldIdTipodescuentoOfMotivorebajaListNewMotivorebaja = motivorebajaListNewMotivorebaja
							.getIdTipodescuento();
					motivorebajaListNewMotivorebaja.setIdTipodescuento(tipodescuento);
					motivorebajaListNewMotivorebaja = em.merge(motivorebajaListNewMotivorebaja);
					if (oldIdTipodescuentoOfMotivorebajaListNewMotivorebaja != null
							&& !oldIdTipodescuentoOfMotivorebajaListNewMotivorebaja.equals(tipodescuento)) {
						oldIdTipodescuentoOfMotivorebajaListNewMotivorebaja.getMotivorebajaList().remove(
								motivorebajaListNewMotivorebaja);
						oldIdTipodescuentoOfMotivorebajaListNewMotivorebaja = em
								.merge(oldIdTipodescuentoOfMotivorebajaListNewMotivorebaja);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = tipodescuento.getId();
				if (findTipodescuento(id) == null) {
					throw new NonexistentEntityException("The tipodescuento with id " + id + " no longer exists.");
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
	public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodescuento tipodescuento;
			try {
				tipodescuento = em.getReference(Tipodescuento.class, id);
				tipodescuento.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tipodescuento with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Promociontipodescuento> promociontipodescuentoListOrphanCheck = tipodescuento
					.getPromociontipodescuentoList();
			for (Promociontipodescuento promociontipodescuentoListOrphanCheckPromociontipodescuento : promociontipodescuentoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipodescuento (" + tipodescuento
						+ ") cannot be destroyed since the Promociontipodescuento "
						+ promociontipodescuentoListOrphanCheckPromociontipodescuento
						+ " in its promociontipodescuentoList field has a non-nullable idTipodescuento field.");
			}
			List<Transaccionarticulo> transaccionarticuloListOrphanCheck = tipodescuento.getTransaccionarticuloList();
			for (Transaccionarticulo transaccionarticuloListOrphanCheckTransaccionarticulo : transaccionarticuloListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipodescuento (" + tipodescuento
						+ ") cannot be destroyed since the Transaccionarticulo "
						+ transaccionarticuloListOrphanCheckTransaccionarticulo
						+ " in its transaccionarticuloList field has a non-nullable idTipodescuento field.");
			}
			List<Motivorebaja> motivorebajaListOrphanCheck = tipodescuento.getMotivorebajaList();
			for (Motivorebaja motivorebajaListOrphanCheckMotivorebaja : motivorebajaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipodescuento (" + tipodescuento
						+ ") cannot be destroyed since the Motivorebaja " + motivorebajaListOrphanCheckMotivorebaja
						+ " in its motivorebajaList field has a non-nullable idTipodescuento field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(tipodescuento);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findTipodescuentoEntities.
	 * 
	 * @return List<Tipodescuento>
	 */
	public List<Tipodescuento> findTipodescuentoEntities() {
		return findTipodescuentoEntities(true, -1, -1);
	}

	/**
	 * Method findTipodescuentoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipodescuento>
	 */
	public List<Tipodescuento> findTipodescuentoEntities(int maxResults, int firstResult) {
		return findTipodescuentoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findTipodescuentoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Tipodescuento>
	 */
	private List<Tipodescuento> findTipodescuentoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipodescuento.class));
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
	 * Method findTipodescuento.
	 * 
	 * @param id
	 *            Long
	 * @return Tipodescuento
	 */
	public Tipodescuento findTipodescuento(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipodescuento.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getTipodescuentoCount.
	 * 
	 * @return int
	 */
	public int getTipodescuentoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipodescuento> rt = cq.from(Tipodescuento.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
