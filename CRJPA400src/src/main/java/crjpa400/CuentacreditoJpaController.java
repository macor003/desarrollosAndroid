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
public class CuentacreditoJpaController implements Serializable {

	/**
	 * Constructor for CuentacreditoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public CuentacreditoJpaController(EntityManagerFactory emf) {
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
	 * @param cuentacredito
	 *            Cuentacredito
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Cuentacredito cuentacredito)
			throws PreexistingEntityException, Exception {
		if (cuentacredito.getPagocreditoList() == null) {
			cuentacredito.setPagocreditoList(new ArrayList<Pagocredito>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cliente numeroIdentificacionCliente = cuentacredito
					.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente = em.getReference(
						numeroIdentificacionCliente.getClass(),
						numeroIdentificacionCliente
								.getNumeroIdentificacionCliente());
				cuentacredito
						.setNumeroIdentificacionCliente(numeroIdentificacionCliente);
			}
			List<Pagocredito> attachedPagocreditoList = new ArrayList<Pagocredito>();
			for (Pagocredito pagocreditoListPagocreditoToAttach : cuentacredito
					.getPagocreditoList()) {
				pagocreditoListPagocreditoToAttach = em.getReference(
						pagocreditoListPagocreditoToAttach.getClass(),
						pagocreditoListPagocreditoToAttach.getId());
				attachedPagocreditoList.add(pagocreditoListPagocreditoToAttach);
			}
			cuentacredito.setPagocreditoList(attachedPagocreditoList);
			em.persist(cuentacredito);
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getCuentacreditoList().add(
						cuentacredito);
				numeroIdentificacionCliente = em
						.merge(numeroIdentificacionCliente);
			}
			for (Pagocredito pagocreditoListPagocredito : cuentacredito
					.getPagocreditoList()) {
				Cuentacredito oldIdCuentacreditoOfPagocreditoListPagocredito = pagocreditoListPagocredito
						.getIdCuentacredito();
				pagocreditoListPagocredito.setIdCuentacredito(cuentacredito);
				pagocreditoListPagocredito = em
						.merge(pagocreditoListPagocredito);
				if (oldIdCuentacreditoOfPagocreditoListPagocredito != null) {
					oldIdCuentacreditoOfPagocreditoListPagocredito
							.getPagocreditoList().remove(
									pagocreditoListPagocredito);
					oldIdCuentacreditoOfPagocreditoListPagocredito = em
							.merge(oldIdCuentacreditoOfPagocreditoListPagocredito);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findCuentacredito(cuentacredito.getId()) != null) {
				throw new PreexistingEntityException("Cuentacredito "
						+ cuentacredito + " already exists.", ex);
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
	 * @param cuentacredito
	 *            Cuentacredito
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Cuentacredito cuentacredito)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Cuentacredito persistentCuentacredito = em.find(
					Cuentacredito.class, cuentacredito.getId());
			Cliente numeroIdentificacionClienteOld = persistentCuentacredito
					.getNumeroIdentificacionCliente();
			Cliente numeroIdentificacionClienteNew = cuentacredito
					.getNumeroIdentificacionCliente();
			List<Pagocredito> pagocreditoListOld = persistentCuentacredito
					.getPagocreditoList();
			List<Pagocredito> pagocreditoListNew = cuentacredito
					.getPagocreditoList();
			List<String> illegalOrphanMessages = null;
			for (Pagocredito pagocreditoListOldPagocredito : pagocreditoListOld) {
				if (!pagocreditoListNew.contains(pagocreditoListOldPagocredito)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Pagocredito "
									+ pagocreditoListOldPagocredito
									+ " since its idCuentacredito field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (numeroIdentificacionClienteNew != null) {
				numeroIdentificacionClienteNew = em.getReference(
						numeroIdentificacionClienteNew.getClass(),
						numeroIdentificacionClienteNew
								.getNumeroIdentificacionCliente());
				cuentacredito
						.setNumeroIdentificacionCliente(numeroIdentificacionClienteNew);
			}
			List<Pagocredito> attachedPagocreditoListNew = new ArrayList<Pagocredito>();
			for (Pagocredito pagocreditoListNewPagocreditoToAttach : pagocreditoListNew) {
				pagocreditoListNewPagocreditoToAttach = em.getReference(
						pagocreditoListNewPagocreditoToAttach.getClass(),
						pagocreditoListNewPagocreditoToAttach.getId());
				attachedPagocreditoListNew
						.add(pagocreditoListNewPagocreditoToAttach);
			}
			pagocreditoListNew = attachedPagocreditoListNew;
			cuentacredito.setPagocreditoList(pagocreditoListNew);
			cuentacredito = em.merge(cuentacredito);
			if (numeroIdentificacionClienteOld != null
					&& !numeroIdentificacionClienteOld
							.equals(numeroIdentificacionClienteNew)) {
				numeroIdentificacionClienteOld.getCuentacreditoList().remove(
						cuentacredito);
				numeroIdentificacionClienteOld = em
						.merge(numeroIdentificacionClienteOld);
			}
			if (numeroIdentificacionClienteNew != null
					&& !numeroIdentificacionClienteNew
							.equals(numeroIdentificacionClienteOld)) {
				numeroIdentificacionClienteNew.getCuentacreditoList().add(
						cuentacredito);
				numeroIdentificacionClienteNew = em
						.merge(numeroIdentificacionClienteNew);
			}
			for (Pagocredito pagocreditoListNewPagocredito : pagocreditoListNew) {
				if (!pagocreditoListOld.contains(pagocreditoListNewPagocredito)) {
					Cuentacredito oldIdCuentacreditoOfPagocreditoListNewPagocredito = pagocreditoListNewPagocredito
							.getIdCuentacredito();
					pagocreditoListNewPagocredito
							.setIdCuentacredito(cuentacredito);
					pagocreditoListNewPagocredito = em
							.merge(pagocreditoListNewPagocredito);
					if (oldIdCuentacreditoOfPagocreditoListNewPagocredito != null
							&& !oldIdCuentacreditoOfPagocreditoListNewPagocredito
									.equals(cuentacredito)) {
						oldIdCuentacreditoOfPagocreditoListNewPagocredito
								.getPagocreditoList().remove(
										pagocreditoListNewPagocredito);
						oldIdCuentacreditoOfPagocreditoListNewPagocredito = em
								.merge(oldIdCuentacreditoOfPagocreditoListNewPagocredito);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = cuentacredito.getId();
				if (findCuentacredito(id) == null) {
					throw new NonexistentEntityException(
							"The cuentacredito with id " + id
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
			Cuentacredito cuentacredito;
			try {
				cuentacredito = em.getReference(Cuentacredito.class, id);
				cuentacredito.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The cuentacredito with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Pagocredito> pagocreditoListOrphanCheck = cuentacredito
					.getPagocreditoList();
			for (Pagocredito pagocreditoListOrphanCheckPagocredito : pagocreditoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Cuentacredito ("
								+ cuentacredito
								+ ") cannot be destroyed since the Pagocredito "
								+ pagocreditoListOrphanCheckPagocredito
								+ " in its pagocreditoList field has a non-nullable idCuentacredito field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Cliente numeroIdentificacionCliente = cuentacredito
					.getNumeroIdentificacionCliente();
			if (numeroIdentificacionCliente != null) {
				numeroIdentificacionCliente.getCuentacreditoList().remove(
						cuentacredito);
				numeroIdentificacionCliente = em
						.merge(numeroIdentificacionCliente);
			}
			em.remove(cuentacredito);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findCuentacreditoEntities.
	 * 
	 * @return List<Cuentacredito>
	 */
	public List<Cuentacredito> findCuentacreditoEntities() {
		return findCuentacreditoEntities(true, -1, -1);
	}

	/**
	 * Method findCuentacreditoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Cuentacredito>
	 */
	public List<Cuentacredito> findCuentacreditoEntities(int maxResults,
			int firstResult) {
		return findCuentacreditoEntities(false, maxResults, firstResult);
	}

	/**
	 * Method findCuentacreditoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Cuentacredito>
	 */
	private List<Cuentacredito> findCuentacreditoEntities(boolean all,
			int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Cuentacredito.class));
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
	 * Method findCuentacredito.
	 * 
	 * @param id
	 *            Long
	 * @return Cuentacredito
	 */
	public Cuentacredito findCuentacredito(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Cuentacredito.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getCuentacreditoCount.
	 * 
	 * @return int
	 */
	public int getCuentacreditoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Cuentacredito> rt = cq.from(Cuentacredito.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
