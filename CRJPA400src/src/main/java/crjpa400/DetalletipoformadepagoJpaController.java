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
public class DetalletipoformadepagoJpaController implements Serializable {

	/**
	 * Constructor for DetalletipoformadepagoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public DetalletipoformadepagoJpaController(EntityManagerFactory emf) {
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
	 * @param detalletipoformadepago
	 *            Detalletipoformadepago
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Detalletipoformadepago detalletipoformadepago)
			throws PreexistingEntityException, Exception {
		if (detalletipoformadepago.getDetalletipoformadepagolineaList() == null) {
			detalletipoformadepago
					.setDetalletipoformadepagolineaList(new ArrayList<Detalletipoformadepagolinea>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Formadepago idFormadepago = detalletipoformadepago
					.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago = em.getReference(idFormadepago.getClass(),
						idFormadepago.getId());
				detalletipoformadepago.setIdFormadepago(idFormadepago);
			}
			List<Detalletipoformadepagolinea> attachedDetalletipoformadepagolineaList = new ArrayList<Detalletipoformadepagolinea>();
			for (Detalletipoformadepagolinea detalletipoformadepagolineaListDetalletipoformadepagolineaToAttach : detalletipoformadepago
					.getDetalletipoformadepagolineaList()) {
				detalletipoformadepagolineaListDetalletipoformadepagolineaToAttach = em
						.getReference(
								detalletipoformadepagolineaListDetalletipoformadepagolineaToAttach
										.getClass(),
								detalletipoformadepagolineaListDetalletipoformadepagolineaToAttach
										.getId());
				attachedDetalletipoformadepagolineaList
						.add(detalletipoformadepagolineaListDetalletipoformadepagolineaToAttach);
			}
			detalletipoformadepago
					.setDetalletipoformadepagolineaList(attachedDetalletipoformadepagolineaList);
			em.persist(detalletipoformadepago);
			if (idFormadepago != null) {
				idFormadepago.getDetalletipoformadepagoList().add(
						detalletipoformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			for (Detalletipoformadepagolinea detalletipoformadepagolineaListDetalletipoformadepagolinea : detalletipoformadepago
					.getDetalletipoformadepagolineaList()) {
				Detalletipoformadepago oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListDetalletipoformadepagolinea = detalletipoformadepagolineaListDetalletipoformadepagolinea
						.getIdDetalletipoformadepago();
				detalletipoformadepagolineaListDetalletipoformadepagolinea
						.setIdDetalletipoformadepago(detalletipoformadepago);
				detalletipoformadepagolineaListDetalletipoformadepagolinea = em
						.merge(detalletipoformadepagolineaListDetalletipoformadepagolinea);
				if (oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListDetalletipoformadepagolinea != null) {
					oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListDetalletipoformadepagolinea
							.getDetalletipoformadepagolineaList()
							.remove(detalletipoformadepagolineaListDetalletipoformadepagolinea);
					oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListDetalletipoformadepagolinea = em
							.merge(oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListDetalletipoformadepagolinea);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findDetalletipoformadepago(detalletipoformadepago.getId()) != null) {
				throw new PreexistingEntityException("Detalletipoformadepago "
						+ detalletipoformadepago + " already exists.", ex);
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
	 * @param detalletipoformadepago
	 *            Detalletipoformadepago
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Detalletipoformadepago detalletipoformadepago)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Detalletipoformadepago persistentDetalletipoformadepago = em.find(
					Detalletipoformadepago.class,
					detalletipoformadepago.getId());
			Formadepago idFormadepagoOld = persistentDetalletipoformadepago
					.getIdFormadepago();
			Formadepago idFormadepagoNew = detalletipoformadepago
					.getIdFormadepago();
			List<Detalletipoformadepagolinea> detalletipoformadepagolineaListOld = persistentDetalletipoformadepago
					.getDetalletipoformadepagolineaList();
			List<Detalletipoformadepagolinea> detalletipoformadepagolineaListNew = detalletipoformadepago
					.getDetalletipoformadepagolineaList();
			List<String> illegalOrphanMessages = null;
			for (Detalletipoformadepagolinea detalletipoformadepagolineaListOldDetalletipoformadepagolinea : detalletipoformadepagolineaListOld) {
				if (!detalletipoformadepagolineaListNew
						.contains(detalletipoformadepagolineaListOldDetalletipoformadepagolinea)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Detalletipoformadepagolinea "
									+ detalletipoformadepagolineaListOldDetalletipoformadepagolinea
									+ " since its idDetalletipoformadepago field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idFormadepagoNew != null) {
				idFormadepagoNew = em.getReference(idFormadepagoNew.getClass(),
						idFormadepagoNew.getId());
				detalletipoformadepago.setIdFormadepago(idFormadepagoNew);
			}
			List<Detalletipoformadepagolinea> attachedDetalletipoformadepagolineaListNew = new ArrayList<Detalletipoformadepagolinea>();
			for (Detalletipoformadepagolinea detalletipoformadepagolineaListNewDetalletipoformadepagolineaToAttach : detalletipoformadepagolineaListNew) {
				detalletipoformadepagolineaListNewDetalletipoformadepagolineaToAttach = em
						.getReference(
								detalletipoformadepagolineaListNewDetalletipoformadepagolineaToAttach
										.getClass(),
								detalletipoformadepagolineaListNewDetalletipoformadepagolineaToAttach
										.getId());
				attachedDetalletipoformadepagolineaListNew
						.add(detalletipoformadepagolineaListNewDetalletipoformadepagolineaToAttach);
			}
			detalletipoformadepagolineaListNew = attachedDetalletipoformadepagolineaListNew;
			detalletipoformadepago
					.setDetalletipoformadepagolineaList(detalletipoformadepagolineaListNew);
			detalletipoformadepago = em.merge(detalletipoformadepago);
			if (idFormadepagoOld != null
					&& !idFormadepagoOld.equals(idFormadepagoNew)) {
				idFormadepagoOld.getDetalletipoformadepagoList().remove(
						detalletipoformadepago);
				idFormadepagoOld = em.merge(idFormadepagoOld);
			}
			if (idFormadepagoNew != null
					&& !idFormadepagoNew.equals(idFormadepagoOld)) {
				idFormadepagoNew.getDetalletipoformadepagoList().add(
						detalletipoformadepago);
				idFormadepagoNew = em.merge(idFormadepagoNew);
			}
			for (Detalletipoformadepagolinea detalletipoformadepagolineaListNewDetalletipoformadepagolinea : detalletipoformadepagolineaListNew) {
				if (!detalletipoformadepagolineaListOld
						.contains(detalletipoformadepagolineaListNewDetalletipoformadepagolinea)) {
					Detalletipoformadepago oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListNewDetalletipoformadepagolinea = detalletipoformadepagolineaListNewDetalletipoformadepagolinea
							.getIdDetalletipoformadepago();
					detalletipoformadepagolineaListNewDetalletipoformadepagolinea
							.setIdDetalletipoformadepago(detalletipoformadepago);
					detalletipoformadepagolineaListNewDetalletipoformadepagolinea = em
							.merge(detalletipoformadepagolineaListNewDetalletipoformadepagolinea);
					if (oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListNewDetalletipoformadepagolinea != null
							&& !oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListNewDetalletipoformadepagolinea
									.equals(detalletipoformadepago)) {
						oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListNewDetalletipoformadepagolinea
								.getDetalletipoformadepagolineaList()
								.remove(detalletipoformadepagolineaListNewDetalletipoformadepagolinea);
						oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListNewDetalletipoformadepagolinea = em
								.merge(oldIdDetalletipoformadepagoOfDetalletipoformadepagolineaListNewDetalletipoformadepagolinea);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = detalletipoformadepago.getId();
				if (findDetalletipoformadepago(id) == null) {
					throw new NonexistentEntityException(
							"The detalletipoformadepago with id " + id
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
			Detalletipoformadepago detalletipoformadepago;
			try {
				detalletipoformadepago = em.getReference(
						Detalletipoformadepago.class, id);
				detalletipoformadepago.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The detalletipoformadepago with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Detalletipoformadepagolinea> detalletipoformadepagolineaListOrphanCheck = detalletipoformadepago
					.getDetalletipoformadepagolineaList();
			for (Detalletipoformadepagolinea detalletipoformadepagolineaListOrphanCheckDetalletipoformadepagolinea : detalletipoformadepagolineaListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Detalletipoformadepago ("
								+ detalletipoformadepago
								+ ") cannot be destroyed since the Detalletipoformadepagolinea "
								+ detalletipoformadepagolineaListOrphanCheckDetalletipoformadepagolinea
								+ " in its detalletipoformadepagolineaList field has a non-nullable idDetalletipoformadepago field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Formadepago idFormadepago = detalletipoformadepago
					.getIdFormadepago();
			if (idFormadepago != null) {
				idFormadepago.getDetalletipoformadepagoList().remove(
						detalletipoformadepago);
				idFormadepago = em.merge(idFormadepago);
			}
			em.remove(detalletipoformadepago);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findDetalletipoformadepagoEntities.
	 * 
	 * @return List<Detalletipoformadepago>
	 */
	public List<Detalletipoformadepago> findDetalletipoformadepagoEntities() {
		return findDetalletipoformadepagoEntities(true, -1, -1);
	}

	/**
	 * Method findDetalletipoformadepagoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Detalletipoformadepago>
	 */
	public List<Detalletipoformadepago> findDetalletipoformadepagoEntities(
			int maxResults, int firstResult) {
		return findDetalletipoformadepagoEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findDetalletipoformadepagoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Detalletipoformadepago>
	 */
	private List<Detalletipoformadepago> findDetalletipoformadepagoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Detalletipoformadepago.class));
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
	 * Method findDetalletipoformadepago.
	 * 
	 * @param id
	 *            Long
	 * @return Detalletipoformadepago
	 */
	public Detalletipoformadepago findDetalletipoformadepago(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Detalletipoformadepago.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getDetalletipoformadepagoCount.
	 * 
	 * @return int
	 */
	public int getDetalletipoformadepagoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Detalletipoformadepago> rt = cq
					.from(Detalletipoformadepago.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
