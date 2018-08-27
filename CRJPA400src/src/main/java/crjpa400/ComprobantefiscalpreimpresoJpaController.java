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
public class ComprobantefiscalpreimpresoJpaController implements Serializable {

	/**
	 * Constructor for ComprobantefiscalpreimpresoJpaController.
	 * 
	 * @param emf
	 *            EntityManagerFactory
	 */
	public ComprobantefiscalpreimpresoJpaController(EntityManagerFactory emf) {
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
	 * @param comprobantefiscalpreimpreso
	 *            Comprobantefiscalpreimpreso
	 * @throws PreexistingEntityException
	 * @throws Exception
	 */
	public void create(Comprobantefiscalpreimpreso comprobantefiscalpreimpreso)
			throws PreexistingEntityException, Exception {
		if (comprobantefiscalpreimpreso.getTransacciondocumentoList() == null) {
			comprobantefiscalpreimpreso
					.setTransacciondocumentoList(new ArrayList<Transacciondocumento>());
		}
		if (comprobantefiscalpreimpreso.getComprobantefiscalnoutilizadoList() == null) {
			comprobantefiscalpreimpreso
					.setComprobantefiscalnoutilizadoList(new ArrayList<Comprobantefiscalnoutilizado>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipodocumento idTipodocumento = comprobantefiscalpreimpreso
					.getIdTipodocumento();
			if (idTipodocumento != null) {
				idTipodocumento = em.getReference(idTipodocumento.getClass(),
						idTipodocumento.getId());
				comprobantefiscalpreimpreso.setIdTipodocumento(idTipodocumento);
			}
			Caja idCaja = comprobantefiscalpreimpreso.getIdCaja();
			if (idCaja != null) {
				idCaja = em.getReference(idCaja.getClass(), idCaja.getId());
				comprobantefiscalpreimpreso.setIdCaja(idCaja);
			}
			List<Transacciondocumento> attachedTransacciondocumentoList = new ArrayList<Transacciondocumento>();
			for (Transacciondocumento transacciondocumentoListTransacciondocumentoToAttach : comprobantefiscalpreimpreso
					.getTransacciondocumentoList()) {
				transacciondocumentoListTransacciondocumentoToAttach = em
						.getReference(
								transacciondocumentoListTransacciondocumentoToAttach
										.getClass(),
								transacciondocumentoListTransacciondocumentoToAttach
										.getId());
				attachedTransacciondocumentoList
						.add(transacciondocumentoListTransacciondocumentoToAttach);
			}
			comprobantefiscalpreimpreso
					.setTransacciondocumentoList(attachedTransacciondocumentoList);
			List<Comprobantefiscalnoutilizado> attachedComprobantefiscalnoutilizadoList = new ArrayList<Comprobantefiscalnoutilizado>();
			for (Comprobantefiscalnoutilizado comprobantefiscalnoutilizadoListComprobantefiscalnoutilizadoToAttach : comprobantefiscalpreimpreso
					.getComprobantefiscalnoutilizadoList()) {
				comprobantefiscalnoutilizadoListComprobantefiscalnoutilizadoToAttach = em
						.getReference(
								comprobantefiscalnoutilizadoListComprobantefiscalnoutilizadoToAttach
										.getClass(),
								comprobantefiscalnoutilizadoListComprobantefiscalnoutilizadoToAttach
										.getId());
				attachedComprobantefiscalnoutilizadoList
						.add(comprobantefiscalnoutilizadoListComprobantefiscalnoutilizadoToAttach);
			}
			comprobantefiscalpreimpreso
					.setComprobantefiscalnoutilizadoList(attachedComprobantefiscalnoutilizadoList);
			em.persist(comprobantefiscalpreimpreso);
			if (idTipodocumento != null) {
				idTipodocumento.getComprobantefiscalpreimpresoList().add(
						comprobantefiscalpreimpreso);
				idTipodocumento = em.merge(idTipodocumento);
			}
			if (idCaja != null) {
				idCaja.getComprobantefiscalpreimpresoList().add(
						comprobantefiscalpreimpreso);
				idCaja = em.merge(idCaja);
			}
			for (Transacciondocumento transacciondocumentoListTransacciondocumento : comprobantefiscalpreimpreso
					.getTransacciondocumentoList()) {
				Comprobantefiscalpreimpreso oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListTransacciondocumento = transacciondocumentoListTransacciondocumento
						.getIdComprobantefiscalpreimpreso();
				transacciondocumentoListTransacciondocumento
						.setIdComprobantefiscalpreimpreso(comprobantefiscalpreimpreso);
				transacciondocumentoListTransacciondocumento = em
						.merge(transacciondocumentoListTransacciondocumento);
				if (oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListTransacciondocumento != null) {
					oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListTransacciondocumento
							.getTransacciondocumentoList()
							.remove(transacciondocumentoListTransacciondocumento);
					oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListTransacciondocumento = em
							.merge(oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListTransacciondocumento);
				}
			}
			for (Comprobantefiscalnoutilizado comprobantefiscalnoutilizadoListComprobantefiscalnoutilizado : comprobantefiscalpreimpreso
					.getComprobantefiscalnoutilizadoList()) {
				Comprobantefiscalpreimpreso oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListComprobantefiscalnoutilizado = comprobantefiscalnoutilizadoListComprobantefiscalnoutilizado
						.getIdComprobantefiscalpreimpreso();
				comprobantefiscalnoutilizadoListComprobantefiscalnoutilizado
						.setIdComprobantefiscalpreimpreso(comprobantefiscalpreimpreso);
				comprobantefiscalnoutilizadoListComprobantefiscalnoutilizado = em
						.merge(comprobantefiscalnoutilizadoListComprobantefiscalnoutilizado);
				if (oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListComprobantefiscalnoutilizado != null) {
					oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListComprobantefiscalnoutilizado
							.getComprobantefiscalnoutilizadoList()
							.remove(comprobantefiscalnoutilizadoListComprobantefiscalnoutilizado);
					oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListComprobantefiscalnoutilizado = em
							.merge(oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListComprobantefiscalnoutilizado);
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findComprobantefiscalpreimpreso(comprobantefiscalpreimpreso
					.getId()) != null) {
				throw new PreexistingEntityException(
						"Comprobantefiscalpreimpreso "
								+ comprobantefiscalpreimpreso
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
	 * @param comprobantefiscalpreimpreso
	 *            Comprobantefiscalpreimpreso
	 * @throws IllegalOrphanException
	 * @throws NonexistentEntityException
	 * @throws Exception
	 */
	public void edit(Comprobantefiscalpreimpreso comprobantefiscalpreimpreso)
			throws IllegalOrphanException, NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Comprobantefiscalpreimpreso persistentComprobantefiscalpreimpreso = em
					.find(Comprobantefiscalpreimpreso.class,
							comprobantefiscalpreimpreso.getId());
			Tipodocumento idTipodocumentoOld = persistentComprobantefiscalpreimpreso
					.getIdTipodocumento();
			Tipodocumento idTipodocumentoNew = comprobantefiscalpreimpreso
					.getIdTipodocumento();
			Caja idCajaOld = persistentComprobantefiscalpreimpreso.getIdCaja();
			Caja idCajaNew = comprobantefiscalpreimpreso.getIdCaja();
			List<Transacciondocumento> transacciondocumentoListOld = persistentComprobantefiscalpreimpreso
					.getTransacciondocumentoList();
			List<Transacciondocumento> transacciondocumentoListNew = comprobantefiscalpreimpreso
					.getTransacciondocumentoList();
			List<Comprobantefiscalnoutilizado> comprobantefiscalnoutilizadoListOld = persistentComprobantefiscalpreimpreso
					.getComprobantefiscalnoutilizadoList();
			List<Comprobantefiscalnoutilizado> comprobantefiscalnoutilizadoListNew = comprobantefiscalpreimpreso
					.getComprobantefiscalnoutilizadoList();
			List<String> illegalOrphanMessages = null;
			for (Comprobantefiscalnoutilizado comprobantefiscalnoutilizadoListOldComprobantefiscalnoutilizado : comprobantefiscalnoutilizadoListOld) {
				if (!comprobantefiscalnoutilizadoListNew
						.contains(comprobantefiscalnoutilizadoListOldComprobantefiscalnoutilizado)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages
							.add("You must retain Comprobantefiscalnoutilizado "
									+ comprobantefiscalnoutilizadoListOldComprobantefiscalnoutilizado
									+ " since its idComprobantefiscalpreimpreso field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			if (idTipodocumentoNew != null) {
				idTipodocumentoNew = em.getReference(
						idTipodocumentoNew.getClass(),
						idTipodocumentoNew.getId());
				comprobantefiscalpreimpreso
						.setIdTipodocumento(idTipodocumentoNew);
			}
			if (idCajaNew != null) {
				idCajaNew = em.getReference(idCajaNew.getClass(),
						idCajaNew.getId());
				comprobantefiscalpreimpreso.setIdCaja(idCajaNew);
			}
			List<Transacciondocumento> attachedTransacciondocumentoListNew = new ArrayList<Transacciondocumento>();
			for (Transacciondocumento transacciondocumentoListNewTransacciondocumentoToAttach : transacciondocumentoListNew) {
				transacciondocumentoListNewTransacciondocumentoToAttach = em
						.getReference(
								transacciondocumentoListNewTransacciondocumentoToAttach
										.getClass(),
								transacciondocumentoListNewTransacciondocumentoToAttach
										.getId());
				attachedTransacciondocumentoListNew
						.add(transacciondocumentoListNewTransacciondocumentoToAttach);
			}
			transacciondocumentoListNew = attachedTransacciondocumentoListNew;
			comprobantefiscalpreimpreso
					.setTransacciondocumentoList(transacciondocumentoListNew);
			List<Comprobantefiscalnoutilizado> attachedComprobantefiscalnoutilizadoListNew = new ArrayList<Comprobantefiscalnoutilizado>();
			for (Comprobantefiscalnoutilizado comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizadoToAttach : comprobantefiscalnoutilizadoListNew) {
				comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizadoToAttach = em
						.getReference(
								comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizadoToAttach
										.getClass(),
								comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizadoToAttach
										.getId());
				attachedComprobantefiscalnoutilizadoListNew
						.add(comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizadoToAttach);
			}
			comprobantefiscalnoutilizadoListNew = attachedComprobantefiscalnoutilizadoListNew;
			comprobantefiscalpreimpreso
					.setComprobantefiscalnoutilizadoList(comprobantefiscalnoutilizadoListNew);
			comprobantefiscalpreimpreso = em.merge(comprobantefiscalpreimpreso);
			if (idTipodocumentoOld != null
					&& !idTipodocumentoOld.equals(idTipodocumentoNew)) {
				idTipodocumentoOld.getComprobantefiscalpreimpresoList().remove(
						comprobantefiscalpreimpreso);
				idTipodocumentoOld = em.merge(idTipodocumentoOld);
			}
			if (idTipodocumentoNew != null
					&& !idTipodocumentoNew.equals(idTipodocumentoOld)) {
				idTipodocumentoNew.getComprobantefiscalpreimpresoList().add(
						comprobantefiscalpreimpreso);
				idTipodocumentoNew = em.merge(idTipodocumentoNew);
			}
			if (idCajaOld != null && !idCajaOld.equals(idCajaNew)) {
				idCajaOld.getComprobantefiscalpreimpresoList().remove(
						comprobantefiscalpreimpreso);
				idCajaOld = em.merge(idCajaOld);
			}
			if (idCajaNew != null && !idCajaNew.equals(idCajaOld)) {
				idCajaNew.getComprobantefiscalpreimpresoList().add(
						comprobantefiscalpreimpreso);
				idCajaNew = em.merge(idCajaNew);
			}
			for (Transacciondocumento transacciondocumentoListOldTransacciondocumento : transacciondocumentoListOld) {
				if (!transacciondocumentoListNew
						.contains(transacciondocumentoListOldTransacciondocumento)) {
					transacciondocumentoListOldTransacciondocumento
							.setIdComprobantefiscalpreimpreso(null);
					transacciondocumentoListOldTransacciondocumento = em
							.merge(transacciondocumentoListOldTransacciondocumento);
				}
			}
			for (Transacciondocumento transacciondocumentoListNewTransacciondocumento : transacciondocumentoListNew) {
				if (!transacciondocumentoListOld
						.contains(transacciondocumentoListNewTransacciondocumento)) {
					Comprobantefiscalpreimpreso oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListNewTransacciondocumento = transacciondocumentoListNewTransacciondocumento
							.getIdComprobantefiscalpreimpreso();
					transacciondocumentoListNewTransacciondocumento
							.setIdComprobantefiscalpreimpreso(comprobantefiscalpreimpreso);
					transacciondocumentoListNewTransacciondocumento = em
							.merge(transacciondocumentoListNewTransacciondocumento);
					if (oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListNewTransacciondocumento != null
							&& !oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListNewTransacciondocumento
									.equals(comprobantefiscalpreimpreso)) {
						oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListNewTransacciondocumento
								.getTransacciondocumentoList()
								.remove(transacciondocumentoListNewTransacciondocumento);
						oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListNewTransacciondocumento = em
								.merge(oldIdComprobantefiscalpreimpresoOfTransacciondocumentoListNewTransacciondocumento);
					}
				}
			}
			for (Comprobantefiscalnoutilizado comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado : comprobantefiscalnoutilizadoListNew) {
				if (!comprobantefiscalnoutilizadoListOld
						.contains(comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado)) {
					Comprobantefiscalpreimpreso oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado = comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado
							.getIdComprobantefiscalpreimpreso();
					comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado
							.setIdComprobantefiscalpreimpreso(comprobantefiscalpreimpreso);
					comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado = em
							.merge(comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado);
					if (oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado != null
							&& !oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado
									.equals(comprobantefiscalpreimpreso)) {
						oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado
								.getComprobantefiscalnoutilizadoList()
								.remove(comprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado);
						oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado = em
								.merge(oldIdComprobantefiscalpreimpresoOfComprobantefiscalnoutilizadoListNewComprobantefiscalnoutilizado);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = comprobantefiscalpreimpreso.getId();
				if (findComprobantefiscalpreimpreso(id) == null) {
					throw new NonexistentEntityException(
							"The comprobantefiscalpreimpreso with id " + id
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
			Comprobantefiscalpreimpreso comprobantefiscalpreimpreso;
			try {
				comprobantefiscalpreimpreso = em.getReference(
						Comprobantefiscalpreimpreso.class, id);
				comprobantefiscalpreimpreso.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException(
						"The comprobantefiscalpreimpreso with id " + id
								+ " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Comprobantefiscalnoutilizado> comprobantefiscalnoutilizadoListOrphanCheck = comprobantefiscalpreimpreso
					.getComprobantefiscalnoutilizadoList();
			for (Comprobantefiscalnoutilizado comprobantefiscalnoutilizadoListOrphanCheckComprobantefiscalnoutilizado : comprobantefiscalnoutilizadoListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages
						.add("This Comprobantefiscalpreimpreso ("
								+ comprobantefiscalpreimpreso
								+ ") cannot be destroyed since the Comprobantefiscalnoutilizado "
								+ comprobantefiscalnoutilizadoListOrphanCheckComprobantefiscalnoutilizado
								+ " in its comprobantefiscalnoutilizadoList field has a non-nullable idComprobantefiscalpreimpreso field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Tipodocumento idTipodocumento = comprobantefiscalpreimpreso
					.getIdTipodocumento();
			if (idTipodocumento != null) {
				idTipodocumento.getComprobantefiscalpreimpresoList().remove(
						comprobantefiscalpreimpreso);
				idTipodocumento = em.merge(idTipodocumento);
			}
			Caja idCaja = comprobantefiscalpreimpreso.getIdCaja();
			if (idCaja != null) {
				idCaja.getComprobantefiscalpreimpresoList().remove(
						comprobantefiscalpreimpreso);
				idCaja = em.merge(idCaja);
			}
			List<Transacciondocumento> transacciondocumentoList = comprobantefiscalpreimpreso
					.getTransacciondocumentoList();
			for (Transacciondocumento transacciondocumentoListTransacciondocumento : transacciondocumentoList) {
				transacciondocumentoListTransacciondocumento
						.setIdComprobantefiscalpreimpreso(null);
				transacciondocumentoListTransacciondocumento = em
						.merge(transacciondocumentoListTransacciondocumento);
			}
			em.remove(comprobantefiscalpreimpreso);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/**
	 * Method findComprobantefiscalpreimpresoEntities.
	 * 
	 * @return List<Comprobantefiscalpreimpreso>
	 */
	public List<Comprobantefiscalpreimpreso> findComprobantefiscalpreimpresoEntities() {
		return findComprobantefiscalpreimpresoEntities(true, -1, -1);
	}

	/**
	 * Method findComprobantefiscalpreimpresoEntities.
	 * 
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Comprobantefiscalpreimpreso>
	 */
	public List<Comprobantefiscalpreimpreso> findComprobantefiscalpreimpresoEntities(
			int maxResults, int firstResult) {
		return findComprobantefiscalpreimpresoEntities(false, maxResults,
				firstResult);
	}

	/**
	 * Method findComprobantefiscalpreimpresoEntities.
	 * 
	 * @param all
	 *            boolean
	 * @param maxResults
	 *            int
	 * @param firstResult
	 *            int
	 * @return List<Comprobantefiscalpreimpreso>
	 */
	private List<Comprobantefiscalpreimpreso> findComprobantefiscalpreimpresoEntities(
			boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Comprobantefiscalpreimpreso.class));
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
	 * Method findComprobantefiscalpreimpreso.
	 * 
	 * @param id
	 *            Long
	 * @return Comprobantefiscalpreimpreso
	 */
	public Comprobantefiscalpreimpreso findComprobantefiscalpreimpreso(Long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Comprobantefiscalpreimpreso.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Method getComprobantefiscalpreimpresoCount.
	 * 
	 * @return int
	 */
	public int getComprobantefiscalpreimpresoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Comprobantefiscalpreimpreso> rt = cq
					.from(Comprobantefiscalpreimpreso.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
